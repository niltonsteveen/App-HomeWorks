package prototipo.udea.edu.co.homeworks.WebServices;

import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.Actividad;
import prototipo.udea.edu.co.homeworks.Model.Usuario;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by nilto on 13/05/2017.
 */

public interface ActividadWS {
    /*@GET("/actividades")
    void getActividades(@Query("profesor") String profesor, Callback<List<Actividad>> cb) ;*/

    @GET("/actividades")
    void getTodasActividades(Callback<List<Actividad>> cb) ;

    @POST("/actividades")
    void createActivity(@Body Actividad actividad, Callback<Actividad> cb);
}
