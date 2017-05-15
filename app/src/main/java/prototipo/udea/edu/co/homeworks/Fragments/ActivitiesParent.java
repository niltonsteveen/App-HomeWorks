package prototipo.udea.edu.co.homeworks.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.Actividad;
import prototipo.udea.edu.co.homeworks.Model.ConfigParent;
import prototipo.udea.edu.co.homeworks.Model.Grupo;
import prototipo.udea.edu.co.homeworks.Model.Usuario;
import prototipo.udea.edu.co.homeworks.R;
import prototipo.udea.edu.co.homeworks.WebServices.ActividadWS;
import prototipo.udea.edu.co.homeworks.WebServices.ConfigParentWS;
import prototipo.udea.edu.co.homeworks.WebServices.builders.RestClientBuilder;
import prototipo.udea.edu.co.homeworks.utils.ActivityAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AW 13 on 15/05/2017.
 */

public class ActivitiesParent extends Fragment {

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
   // private String url= "https://rest-homeworks.herokuapp.com/api";
    List items=new ArrayList();
    List<Actividad> activities= new LinkedList<>();
    List<ConfigParent> configParents = new LinkedList<>();


    public ActivitiesParent(){

    }


    public static ActivitiesParent newInstance(Bundle arguments) {
        ActivitiesParent fragment = new ActivitiesParent();
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
        final View view= inflater.inflate(R.layout.fragment_activities_parent, container, false);

        Bundle arguments=getArguments();
        usuario=arguments.getParcelable("Usuario");

        final ConfigParentWS configParentWs= RestClientBuilder.restConfigParent();

        configParentWs.getConfsByUser(new Callback<List<ConfigParent>>() {
            @Override
            public void success(List<ConfigParent> configs, Response response) {
                Log.e("entro", ""+configs.size());
                for(ConfigParent conf: configs){
                    if(conf.getUsuario().equalsIgnoreCase(usuario.getEmail())){
                        configParents.add(conf);
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("fallo2", error.getMessage());
            }
        });

        final ActividadWS activitiesWs= RestClientBuilder.restActivitiesParent();
            activitiesWs.getActividadesAll( new Callback<List<Actividad>>() {
                @Override
                public void success(List<Actividad> listActivities, Response response) {
                    Log.e("entro", ""+listActivities.size());
                    for(Actividad actividad: listActivities) {
                        for(ConfigParent config: configParents) {
                            Log.e("entro before add", "activity"+config.getGrupo());
                            if(config.getGrupo()== actividad.getGrupo()) {
                                Log.e("entro add", "activity"+config.getGrupo());
                                activities.add(actividad);
                            }
                        }
                    }
                    addActivities(view);


                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("fallo1", error.getMessage());
                }
            });



        return view;

    }



    public  void addActivities(View view){

        recycler = (RecyclerView) view.findViewById(R.id.reciclador1);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);
        adapter = new ActivityAdapter(this.activities, getContext());
        recycler.setAdapter(adapter);

    }



}
