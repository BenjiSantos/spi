package pe.gob.qw.supervision.api.model;

import com.google.gson.annotations.SerializedName;

public class LoginBody {

    @SerializedName("vUsuario")
    private String vUsuario;

    @SerializedName("vPassport")
    private String vPassword;

    public LoginBody() {
    }

    public LoginBody(String vUsuario, String vPassword) {
        this.vUsuario = vUsuario;
        this.vPassword = vPassword;
    }

    public String getvUsuario() {
        return vUsuario;
    }

    public void setvUsuario(String vUsuario) {
        this.vUsuario = vUsuario;
    }

    public String getvPassword() {
        return vPassword;
    }

    public void setvPassword(String vPassword) {
        this.vPassword = vPassword;
    }
}
