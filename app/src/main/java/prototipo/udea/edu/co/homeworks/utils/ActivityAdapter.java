package prototipo.udea.edu.co.homeworks.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import prototipo.udea.edu.co.homeworks.R;


/**
 * Creado por Hermosa Programación
 */
public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {
    //private List<Solicitud> items;
    private List items;

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView descripcion;
        public TextView grupo;
        public TextView asignatura;

        public ActivityViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagen);
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
    public void onBindViewHolder(ActivityViewHolder viewHolder, int i) {
            viewHolder.imagen.setImageResource(R.drawable.reclamo);
        viewHolder.descripcion.setText("Hola");
    }
}
