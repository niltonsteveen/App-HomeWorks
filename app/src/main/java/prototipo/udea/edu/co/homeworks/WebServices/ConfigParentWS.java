package prototipo.udea.edu.co.homeworks.WebServices;

import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.ConfProfesor;
import prototipo.udea.edu.co.homeworks.Model.ConfigParent;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by AW 13 on 15/05/2017.
 */

public interface ConfigParentWS {

    @GET("/confacudientes")
    void getConfsByUser(Callback<List<ConfigParent>> cb);

}
