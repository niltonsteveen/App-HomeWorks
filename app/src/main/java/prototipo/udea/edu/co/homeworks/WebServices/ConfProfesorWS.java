package prototipo.udea.edu.co.homeworks.WebServices;

import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.ConfProfesor;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by nilto on 14/05/2017.
 */

public interface ConfProfesorWS {

    @GET("/confprofesores")
    void getConfsByUser(@Query("usuario") String usuario, Callback<List<ConfProfesor>> cb);

}
