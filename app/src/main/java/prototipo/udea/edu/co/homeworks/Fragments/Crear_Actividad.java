package prototipo.udea.edu.co.homeworks.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.Actividad;
import prototipo.udea.edu.co.homeworks.Model.Asignatura;
import prototipo.udea.edu.co.homeworks.Model.ConfProfesor;
import prototipo.udea.edu.co.homeworks.Model.Grupo;
import prototipo.udea.edu.co.homeworks.Model.Usuario;
import prototipo.udea.edu.co.homeworks.R;
import prototipo.udea.edu.co.homeworks.WebServices.ActividadWS;
import prototipo.udea.edu.co.homeworks.WebServices.AsignaturaWS;
import prototipo.udea.edu.co.homeworks.WebServices.ConfProfesorWS;
import prototipo.udea.edu.co.homeworks.WebServices.GrupoWs;
import prototipo.udea.edu.co.homeworks.utils.DatePickerFragment;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Crear_Actividad extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int dia, mes, año;
    private Usuario usuario;
    private TextView tvFechaLimiteC;
    private Button btnCambiarFecha;
    private RadioGroup radioGroupGrupos;
    private RadioGroup radioGroupAsignaturas;
    Context context;
    private HashMap hashMapGrupos;
    private HashMap hashMapAsignaturas;
    private Button btnCrear;
    private TextInputEditText descripcionCrearAct;
    private String fechaLimite="";
    private String url= "https://rest-homeworks.herokuapp.com/api";
    List<RadioButton> radioButtonsGrupos;
    List<RadioButton> radioButtonsAsignaturas;

    public Crear_Actividad() {
        // Required empty public constructor
    }

    public static Crear_Actividad newInstance(Bundle arguments) {
        Crear_Actividad fragment = new Crear_Actividad();
        if(arguments != null){
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_crear__actividad, container, false);

        radioButtonsAsignaturas=new ArrayList<>();
        radioButtonsGrupos=new ArrayList<>();
        hashMapGrupos=new HashMap();
        hashMapAsignaturas=new HashMap();
        Bundle arguments=getArguments();
        usuario=arguments.getParcelable("Usuario");
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
        final ConfProfesorWS confProfesorWS=restAdapter.create(ConfProfesorWS.class);
        final ActividadWS actividadWS=restAdapter.create(ActividadWS.class);
        context=this.getContext();
        radioGroupAsignaturas=(RadioGroup)view.findViewById(R.id.radioGroupAsignaturas);
        radioGroupGrupos=(RadioGroup)view.findViewById(R.id.radioGroupGrupos);
        tvFechaLimiteC=(TextView) view.findViewById(R.id.tvFechaLim);
        descripcionCrearAct=(TextInputEditText)view.findViewById(R.id.etDescripcionCrearAct);
        btnCrear=(Button) view.findViewById(R.id.btnCrearAct);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actividad actividad=new Actividad();
                actividad.setProfesor(usuario.getEmail());
                actividad.setActiva(true);
                actividad.setDescripcion(descripcionCrearAct.getText().toString());
                Date fechaCreacion=new Date();
                actividad.setFechaCreacion(fechaCreacion.toString());

                System.out.println("-------------------------"+fechaLimite);
                actividad.setFechaLimite(fechaLimite);

                String asignatura="";
                int valor=0;
                for(int i=0;i<radioButtonsAsignaturas.size();i++){
                    if(radioButtonsAsignaturas.get(i).isChecked()){
                        asignatura=radioButtonsAsignaturas.get(i).getText().toString();
                        valor=(int)hashMapAsignaturas.get(asignatura);
                    }
                }
                actividad.setAsignatura(valor);

                String grupo="";
                int valor1=0;
                for(int i=0;i<radioButtonsGrupos.size();i++){
                    if(radioButtonsGrupos.get(i).isChecked()){
                        grupo=radioButtonsGrupos.get(i).getText().toString();
                        valor1=(int)hashMapGrupos.get(grupo);
                    }
                }
                actividad.setGrupo(valor1);

                System.out.println(actividad.toString());

                actividadWS.createActivity(actividad, new Callback<Actividad>() {
                    @Override
                    public void success(Actividad actividad, Response response) {
                        Toast.makeText(getContext(),"Registrada correctamente",Toast.LENGTH_LONG).show();
                        FragmentManager fragmentManager=getFragmentManager();
                        Bundle arguments=new Bundle();
                        arguments.putParcelable("Usuario",usuario);
                        FragmentoActividadesMain actividadesMain=FragmentoActividadesMain.newInstance(arguments);
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.main_content, actividadesMain)
                                .commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });
            }
        });


        confProfesorWS.getConfsByUser(usuario.getEmail(), new Callback<List<ConfProfesor>>() {
            @Override
            public void success(List<ConfProfesor> confProfesores, Response response) {
                getGrupos(confProfesores);
                getAsignaturas(confProfesores);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });


        btnCambiarFecha=(Button)view.findViewById(R.id.btnCambiarFecha);
        btnCambiarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        final Calendar c = Calendar.getInstance();
        año = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);



        mostrarFecha();
        return view;
    }

    private void getAsignaturas(List<ConfProfesor> confProfesores) {
        final List<Integer> ids=new ArrayList<>();
        ids.add(0);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
        final AsignaturaWS asignaturaWS=restAdapter.create(AsignaturaWS .class);
        for(int i=0;i<confProfesores.size();i++){
            asignaturaWS.getAsignaturaById(confProfesores.get(i).getGrupo(), new Callback<Asignatura>() {
                @Override
                public void success(Asignatura asignatura, Response response) {

                    if(ids.indexOf(asignatura.getId())<0){
                        hashMapAsignaturas.put(asignatura.getNombre(),asignatura.getId());
                        RadioButton radioButtonAsignatura=new RadioButton(context);
                        radioButtonAsignatura.setText(asignatura.getNombre());
                        radioGroupAsignaturas.addView(radioButtonAsignatura);
                        ids.add(asignatura.getId());
                        radioButtonsAsignaturas.add(radioButtonAsignatura);
                    }
                }
                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });
        }
    }

    private void getGrupos(List<ConfProfesor> confProfesores) {
        final List<Integer> ids=new ArrayList<>();
        ids.add(0);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
        final GrupoWs grupoWs=restAdapter.create(GrupoWs.class);
        for(int i=0;i<confProfesores.size();i++){
            grupoWs.getGrupoById(confProfesores.get(i).getGrupo(), new Callback<Grupo>() {
                @Override
                public void success(Grupo grupo, Response response) {
                    if(ids.indexOf(grupo.getId())<0){
                        hashMapGrupos.put(grupo.getNombre(),grupo.getId());
                        RadioButton radioButtonGrupo=new RadioButton(context);
                        radioButtonGrupo.setText(grupo.getNombre());
                        radioGroupGrupos.addView(radioButtonGrupo);
                        ids.add(grupo.getId());
                        radioButtonsGrupos.add(radioButtonGrupo);
                    }
                }
                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });

        }
    }

    public void mostrarFecha(){
        tvFechaLimiteC.setText(dia +"/"+(mes +1)+"/"+ año);
    }


    public void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt("year", año);
        args.putInt("month", mes);
        args.putInt("day", mes);
        newFragment.setArguments(args);
        newFragment.setCallBack(ondate);
        newFragment.show(getActivity().getFragmentManager(), "datePicker");
    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            fechaLimite=String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+String.valueOf(dayOfMonth);
            tvFechaLimiteC.setText(fechaLimite);
        }
    };

}
