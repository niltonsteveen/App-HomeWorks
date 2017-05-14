
package prototipo.udea.edu.co.homeworks.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Asignatura {

    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("colegio")
    @Expose
    private Integer colegio;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getColegio() {
        return colegio;
    }

    public void setColegio(Integer colegio) {
        this.colegio = colegio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
