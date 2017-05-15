package prototipo.udea.edu.co.homeworks.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by AW 13 on 15/05/2017.
 */

public class ConfigParent   {

    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("grupo")
    @Expose
    private Integer grupo;
    @SerializedName("habilitada")
    @Expose
    private Boolean habilitada;
    @SerializedName("id")
    @Expose
    private Integer id;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(Boolean habilitada) {
        this.habilitada = habilitada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
