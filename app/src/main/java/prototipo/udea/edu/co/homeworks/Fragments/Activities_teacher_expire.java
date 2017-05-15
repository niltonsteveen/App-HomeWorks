package prototipo.udea.edu.co.homeworks.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.Actividad;
import prototipo.udea.edu.co.homeworks.Model.Usuario;
import prototipo.udea.edu.co.homeworks.R;
import prototipo.udea.edu.co.homeworks.WebServices.ActividadWS;
import prototipo.udea.edu.co.homeworks.utils.ActivityAdapter;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Activities_teacher_expire extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private FloatingActionButton actionButton;
    private Usuario usuario;
    private String url= "https://rest-homeworks.herokuapp.com/api";
    List items=new ArrayList();

    public Activities_teacher_expire() {
        // Required empty public constructor
    }

    public static Activities_teacher_expire newInstance(Bundle arguments) {
        Activities_teacher_expire fragment = new Activities_teacher_expire();
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
        final View view= inflater.inflate(R.layout.fragment_activities_teacher_expire, container, false);

        Bundle arguments=getArguments();
        usuario=arguments.getParcelable("Usuario");

        actionButton=(FloatingActionButton) view.findViewById(R.id.fabAddActivity1);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments=new Bundle();
                arguments.putParcelable("Usuario", usuario);
                Crear_Actividad crear_actividad=Crear_Actividad.newInstance(arguments);

                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, crear_actividad)
                        .commit();
            }
        });

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(url).build();

        final ActividadWS actividadWS=restAdapter.create(ActividadWS.class);
        final List<Actividad> activitiesByEmail=new ArrayList<>();
        actividadWS.getActividadesAll(new Callback<List<Actividad>>() {
                @Override
                public void success(List<Actividad> actividads, Response response) {
                    Date fecha=new Date();
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar c=Calendar.getInstance();
                    c.setTime(fecha);
                    int año=c.get(Calendar.YEAR);
                    int mes=c.get(Calendar.MONTH)+1;
                    int dia=c.get(Calendar.DAY_OF_MONTH);
                    for(int i=0;i<actividads.size();i++){
                        try {
                            Date fechaLim=formatter.parse(actividads.get(i).getFechaLimite());
                            c.setTime(fechaLim);
                            if(actividads.get(i).getProfesor().equals(usuario.getEmail())&&año==c.get(Calendar.YEAR)){
                                if((mes==c.get(Calendar.MONTH)+1&&dia>c.get(Calendar.DAY_OF_MONTH))||mes>c.get(Calendar.MONTH)+1){
                                    activitiesByEmail.add(actividads.get(i));
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                    recycler = (RecyclerView) view.findViewById(R.id.reciclador2);
                    recycler.setHasFixedSize(true);
                    lManager = new LinearLayoutManager(getContext());
                    recycler.setLayoutManager(lManager);
                    adapter = new ActivityAdapter(activitiesByEmail, getContext());
                    recycler.setAdapter(adapter);
                }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });



        return view;

    }

}
