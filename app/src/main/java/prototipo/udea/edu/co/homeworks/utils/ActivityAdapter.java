package prototipo.udea.edu.co.homeworks.utils;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import prototipo.udea.edu.co.homeworks.Model.Actividad;
import prototipo.udea.edu.co.homeworks.Model.Asignatura;
import prototipo.udea.edu.co.homeworks.Model.Grupo;
import prototipo.udea.edu.co.homeworks.Model.Usuario;
import prototipo.udea.edu.co.homeworks.R;
import prototipo.udea.edu.co.homeworks.WebServices.ActividadWS;
import prototipo.udea.edu.co.homeworks.WebServices.AsignaturaWS;
import prototipo.udea.edu.co.homeworks.WebServices.GrupoWs;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
/**
 * Creado por Hermosa Programación
 */
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {
    //private List<Solicitud> items;
    private List <Actividad> items;
    private List<String> colores;
    public RestAdapter restAdapter;
    private String url= "https://rest-homeworks.herokuapp.com/api";
    final AsignaturaWS asignaturaWS;
    final GrupoWs grupoWs;
    int numero;


    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView descripcion;
        public TextView grupo;
        public TextView asignatura;
        public CardView cardImg;
        public TextView tvCard;



        public ActivityViewHolder(View v) {
            super(v);
            cardImg=(CardView) v.findViewById(R.id.cardImg);
            tvCard=(TextView) v.findViewById(R.id.tvLetterCard);
            descripcion = (TextView) v.findViewById(R.id.tvDescripcionList);
            grupo = (TextView) v.findViewById(R.id.tvGroupList);
            asignatura = (TextView) v.findViewById(R.id.tvAsignaturaList);
        }
    }

    /*public ActivityAdapter(List<Solicitud> items) {
        this.items = items;
    }*/
    public ActivityAdapter(List items) {
        this.items = items;
        this.colores=new ArrayList<>();
        asignarArray();
        this.restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
        asignaturaWS=restAdapter.create(AsignaturaWS.class);
        grupoWs=restAdapter.create(GrupoWs.class);
    }

    private void asignarArray() {
        colores.add("#B34444");
        colores.add("#B3447B");
        colores.add("#AC44B3");
        colores.add("#7444B3");
        colores.add("#4453B3");
        colores.add("#447FB3");
        colores.add("#44B383");
        colores.add("#44B34B");
        colores.add("#70B344");
        colores.add("#AFB344");
        colores.add("#B38744");
        colores.add("#B36944");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_card, viewGroup, false);
        return new ActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ActivityViewHolder viewHolder, final int i) {

        asignaturaWS.getAsignaturaById(items.get(i).getAsignatura(), new Callback<Asignatura>() {
                    @Override
                    public void success(Asignatura asignatura, Response response) {
                        Random r=new Random();
                        numero=r.nextInt(12);
                        String letra=asignatura.getNombre().charAt(0)+"";
                        viewHolder.tvCard.setText(letra);
                        viewHolder.cardImg.setCardBackgroundColor(Color.parseColor(colores.get(numero)));
                        viewHolder.asignatura.setText(asignatura.getNombre());
                        viewHolder.descripcion.setText(items.get(i).getDescripcion());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });
        grupoWs.getGrupoById(items.get(i).getGrupo(), new Callback<Grupo>() {
            @Override
            public void success(Grupo grupo, Response response) {
                viewHolder.grupo.setText(grupo.getNombre());
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

    }

}
