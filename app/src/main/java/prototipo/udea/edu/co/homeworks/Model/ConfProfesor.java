package prototipo.udea.edu.co.homeworks.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nilto on 14/05/2017.
 */
public class ConfProfesor {
    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("grupo")
    @Expose
    private Integer grupo;
    @SerializedName("habilitada")
    @Expose
    private Boolean habilitada;
    @SerializedName("asignatura")
    @Expose
    private Integer asignatura;
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

    public Integer getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Integer asignatura) {
        this.asignatura = asignatura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
