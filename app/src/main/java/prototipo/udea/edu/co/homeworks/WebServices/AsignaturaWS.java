package prototipo.udea.edu.co.homeworks.WebServices;

import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.Actividad;
import prototipo.udea.edu.co.homeworks.Model.Asignatura;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by nilto on 14/05/2017.
 */

public interface AsignaturaWS {

    @GET("/asignaturas/{id}")
    void getAsignaturaById(@Path("id") int id, Callback<Asignatura> cb) ;
}
