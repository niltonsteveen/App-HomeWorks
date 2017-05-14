package prototipo.udea.edu.co.homeworks.WebServices;

import java.util.List;

import prototipo.udea.edu.co.homeworks.Model.Usuario;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by nilto on 12/05/2017.
 */

public interface UsuarioWS {

    @GET("/usuarios")
    void getUsers(Callback<List<Usuario>> cb) ;

    @POST("/usuarios")
    void createUser(@Body Usuario user, Callback<Usuario> cb);

    @GET("/usuarios/{email}")
    void getUserById(@Path("email") String email, Callback<Usuario> cb);

    @PUT("/usuarios")
    void updateUser(@Body Usuario user, Callback<Usuario> cb);
}
