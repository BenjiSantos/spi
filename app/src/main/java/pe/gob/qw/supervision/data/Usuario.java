package pe.gob.qw.supervision.data;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity( nameInDb = "USUARIOS")
public class Usuario {

    @Id(autoincrement = true)
    private Long id;

    @SerializedName("iCodUsuario")
    @Property(nameInDb = "iCodUsuario")
    private int iCodUsuario;

    @SerializedName("vUsuario")
    @Property(nameInDb = "vUsuario")
    private String vUsuario;

    @SerializedName("vPassword")
    @Property(nameInDb = "vPassword")
    private String vPassword;

    @SerializedName("iCodPersona")
    @Property(nameInDb = "iCodPersona")
    private int iCodPersona;

    @SerializedName("vNombres")
    @Property(nameInDb = "vNombres")
    private String vNombres;

    @SerializedName("cCodDocPersona")
    @Property(nameInDb = "cCodDocPersona")
    private String cCodDocPersona;

    @SerializedName("vEmail")
    @Property(nameInDb = "vEmail")
    private String vEmail;

    @SerializedName("iCodCargo")
    @Property(nameInDb = "iCodCargo")
    private int iCodCargo;

    @SerializedName("vDesCargo")
    @Property(nameInDb = "vDesCargo")
    private String vDesCargo;

    @SerializedName("iCodUnidad")
    @Property(nameInDb = "iCodUnidad")
    private int iCodUnidad;

    @SerializedName("vNomUnidad")
    @Property(nameInDb = "vNomUnidad")
    private String vNomUnidad;

    @SerializedName("dtFechaSistema")
    @Property(nameInDb = "dtFechaSistema")
    private String dtFechaSistema;

    @SerializedName("bActivo")
    @Property(nameInDb = "bActivo")
    private boolean bActivo;

    @Generated(hash = 2071940011)
    public Usuario(Long id, int iCodUsuario, String vUsuario, String vPassword,
                   int iCodPersona, String vNombres, String cCodDocPersona, String vEmail,
                   int iCodCargo, String vDesCargo, int iCodUnidad, String vNomUnidad,
                   String dtFechaSistema, boolean bActivo) {
        this.id = id;
        this.iCodUsuario = iCodUsuario;
        this.vUsuario = vUsuario;
        this.vPassword = vPassword;
        this.iCodPersona = iCodPersona;
        this.vNombres = vNombres;
        this.cCodDocPersona = cCodDocPersona;
        this.vEmail = vEmail;
        this.iCodCargo = iCodCargo;
        this.vDesCargo = vDesCargo;
        this.iCodUnidad = iCodUnidad;
        this.vNomUnidad = vNomUnidad;
        this.dtFechaSistema = dtFechaSistema;
        this.bActivo = bActivo;
    }

    @Generated(hash = 562950751)
    public Usuario() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getICodUsuario() {
        return this.iCodUsuario;
    }

    public void setICodUsuario(int iCodUsuario) {
        this.iCodUsuario = iCodUsuario;
    }

    public String getVUsuario() {
        return this.vUsuario;
    }

    public void setVUsuario(String vUsuario) {
        this.vUsuario = vUsuario;
    }

    public String getVPassword() {
        return this.vPassword;
    }

    public void setVPassword(String vPassword) {
        this.vPassword = vPassword;
    }

    public int getICodPersona() {
        return this.iCodPersona;
    }

    public void setICodPersona(int iCodPersona) {
        this.iCodPersona = iCodPersona;
    }

    public String getVNombres() {
        return this.vNombres;
    }

    public void setVNombres(String vNombres) {
        this.vNombres = vNombres;
    }

    public String getCCodDocPersona() {
        return this.cCodDocPersona;
    }

    public void setCCodDocPersona(String cCodDocPersona) {
        this.cCodDocPersona = cCodDocPersona;
    }

    public String getVEmail() {
        return this.vEmail;
    }

    public void setVEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public int getICodCargo() {
        return this.iCodCargo;
    }

    public void setICodCargo(int iCodCargo) {
        this.iCodCargo = iCodCargo;
    }

    public String getVDesCargo() {
        return this.vDesCargo;
    }

    public void setVDesCargo(String vDesCargo) {
        this.vDesCargo = vDesCargo;
    }

    public int getICodUnidad() {
        return this.iCodUnidad;
    }

    public void setICodUnidad(int iCodUnidad) {
        this.iCodUnidad = iCodUnidad;
    }

    public String getVNomUnidad() {
        return this.vNomUnidad;
    }

    public void setVNomUnidad(String vNomUnidad) {
        this.vNomUnidad = vNomUnidad;
    }

    public String getDtFechaSistema() {
        return this.dtFechaSistema;
    }

    public void setDtFechaSistema(String dtFechaSistema) {
        this.dtFechaSistema = dtFechaSistema;
    }

    public boolean getBActivo() {
        return this.bActivo;
    }

    public void setBActivo(boolean bActivo) {
        this.bActivo = bActivo;
    }

}
