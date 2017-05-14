
package prototipo.udea.edu.co.homeworks.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario implements Parcelable {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("apellidos")
    @Expose
    private String apellidos;
    @SerializedName("expire_token")
    @Expose
    private String expireToken;
    @SerializedName("rol")
    @Expose
    private String rol;
    @SerializedName("habilitado")
    @Expose
    private Boolean habilitado;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getExpireToken() {
        return expireToken;
    }

    public void setExpireToken(String expireToken) {
        this.expireToken = expireToken;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }


    protected Usuario(Parcel in) {
        email = in.readString();
        password = in.readString();
        token = in.readString();
        nombres = in.readString();
        apellidos = in.readString();
        expireToken = in.readString();
        rol = in.readString();
        byte habilitadoVal = in.readByte();
        habilitado = habilitadoVal == 0x02 ? null : habilitadoVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(token);
        dest.writeString(nombres);
        dest.writeString(apellidos);
        dest.writeString(expireToken);
        dest.writeString(rol);
        if (habilitado == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (habilitado ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}