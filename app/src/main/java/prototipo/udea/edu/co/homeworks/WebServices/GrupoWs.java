package prototipo.udea.edu.co.homeworks.WebServices;

import prototipo.udea.edu.co.homeworks.Model.Grupo;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by nilto on 14/05/2017.
 */

public interface GrupoWs {

    @GET("/grupos/{id}")
    void getGrupoById(@Path("id") int id, Callback<Grupo> cb) ;
}
