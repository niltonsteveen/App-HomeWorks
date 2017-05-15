package prototipo.udea.edu.co.homeworks.WebServices.builders;

import java.net.URL;

import prototipo.udea.edu.co.homeworks.BuildConfig;
import prototipo.udea.edu.co.homeworks.Model.ConfigParent;
import prototipo.udea.edu.co.homeworks.WebServices.ActividadWS;
import prototipo.udea.edu.co.homeworks.WebServices.ConfigParentWS;
import retrofit.RestAdapter;

/**
 * Created by AW 13 on 15/05/2017.
 */

public class RestClientBuilder {


    public static final String URL_APP = "https://rest-homeworks.herokuapp.com/api";
    public static ConfigParentWS parentWS;
    public static ActividadWS actividadWSParent;


    public static ConfigParentWS restConfigParent(){

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(URL_APP).build();
        return  restAdapter.create(ConfigParentWS.class);


    }

    public static ActividadWS restActivitiesParent(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(URL_APP).build();
        return  restAdapter.create(ActividadWS.class);


    }

}
