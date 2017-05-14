package prototipo.udea.edu.co.homeworks.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.Asignatura;
import prototipo.udea.edu.co.homeworks.Model.ConfProfesor;
import prototipo.udea.edu.co.homeworks.Model.Grupo;
import prototipo.udea.edu.co.homeworks.Model.Usuario;
import prototipo.udea.edu.co.homeworks.R;
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
    List<RadioButton> radioButtonsGrupos=new ArrayList<RadioButton>();
    List<RadioButton> radioButtonsAsignaturas=new ArrayList<RadioButton>();
    Context context;
    private String url= "https://rest-homeworks.herokuapp.com/api";

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

        Bundle arguments=getArguments();
        usuario=arguments.getParcelable("Usuario");
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
        final ConfProfesorWS confProfesorWS=restAdapter.create(ConfProfesorWS.class);
        context=this.getContext();
        radioGroupAsignaturas=(RadioGroup)view.findViewById(R.id.radioGroupAsignaturas);
        radioGroupGrupos=(RadioGroup)view.findViewById(R.id.radioGroupGrupos);

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

        tvFechaLimiteC=(TextView) view.findViewById(R.id.tvFechaLim);
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
                        RadioButton radioButtonAsignatura=new RadioButton(context);
                        radioButtonAsignatura.setText(asignatura.getNombre());
                        radioGroupAsignaturas.addView(radioButtonAsignatura);
                        ids.add(asignatura.getId());
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
                        RadioButton radioButtonGrupo=new RadioButton(context);
                        radioButtonGrupo.setText(grupo.getNombre());
                        radioGroupGrupos.addView(radioButtonGrupo);
                        ids.add(grupo.getId());
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

            tvFechaLimiteC.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(year));
        }
    };

}
