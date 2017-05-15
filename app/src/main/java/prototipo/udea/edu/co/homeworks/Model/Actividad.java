
package prototipo.udea.edu.co.homeworks.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Actividad implements Parcelable {

    @SerializedName("profesor")
    @Expose
    private String profesor;
    @SerializedName("grupo")
    @Expose
    private Integer grupo;
    @SerializedName("asignatura")
    @Expose
    private Integer asignatura;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("fecha_creacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("fecha_limite")
    @Expose
    private String fechaLimite;
    @SerializedName("activa")
    @Expose
    private Boolean activa;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Integer asignatura) {
        this.asignatura = asignatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Actividad() {
    }

    protected Actividad(Parcel in) {
        profesor = in.readString();
        grupo = in.readByte() == 0x00 ? null : in.readInt();
        asignatura = in.readByte() == 0x00 ? null : in.readInt();
        descripcion = in.readString();
        fechaCreacion = in.readString();
        fechaLimite = in.readString();
        byte activaVal = in.readByte();
        activa = activaVal == 0x02 ? null : activaVal != 0x00;
        id = in.readByte() == 0x00 ? null : in.readInt();
    }

    @Override
    public String toString() {
        String res=this.getDescripcion()+"-------"+this.getFechaCreacion()+"-------"+
                this.getFechaLimite()+"-------"+this.getProfesor()+"-------"+this.getActiva()+
                "-------"+this.getAsignatura()+"-------"+this.getGrupo()+"-------"+this.getId();
        return res;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(profesor);
        if (grupo == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(grupo);
        }
        if (asignatura == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(asignatura);
        }
        dest.writeString(descripcion);
        dest.writeString(fechaCreacion);
        dest.writeString(fechaLimite);
        if (activa == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (activa ? 0x01 : 0x00));
        }
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Actividad> CREATOR = new Parcelable.Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel in) {
            return new Actividad(in);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };
}