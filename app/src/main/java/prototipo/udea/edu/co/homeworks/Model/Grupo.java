
package prototipo.udea.edu.co.homeworks.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Grupo {

    @SerializedName("numeroestudiantes")
    @Expose
    private String numeroestudiantes;
    @SerializedName("grado")
    @Expose
    private String grado;
    @SerializedName("fecha_expiracion")
    @Expose
    private String fechaExpiracion;
    @SerializedName("colegio")
    @Expose
    private String colegio;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getNumeroestudiantes() {
        return numeroestudiantes;
    }

    public void setNumeroestudiantes(String numeroestudiantes) {
        this.numeroestudiantes = numeroestudiantes;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
