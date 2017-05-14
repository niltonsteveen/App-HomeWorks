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

import java.util.ArrayList;
import java.util.List;

import prototipo.udea.edu.co.homeworks.R;
import prototipo.udea.edu.co.homeworks.utils.ActivityAdapter;


public class Activities_teacher extends Fragment {
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
    List items=new ArrayList();

    public Activities_teacher() {
        // Required empty public constructor
    }

    public static Activities_teacher newInstance(Bundle arguments) {
        Activities_teacher fragment = new Activities_teacher();
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
        View view= inflater.inflate(R.layout.fragment_activities_teacher, container, false);
items.add("nilton");

        actionButton=(FloatingActionButton) view.findViewById(R.id.fabAddActivity);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crear_Actividad crear_actividad=new Crear_Actividad();

                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, crear_actividad)
                        .commit();
            }
        });

        recycler = (RecyclerView) view.findViewById(R.id.reciclador1);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);
        adapter = new ActivityAdapter(items);
        recycler.setAdapter(adapter);

        return view;

    }

}
