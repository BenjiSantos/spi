package pe.gob.qw.supervision.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import pe.gob.qw.supervision.api.model.LoginBody;
import pe.gob.qw.supervision.data.Postor;
import pe.gob.qw.supervision.data.Proveedor;
import pe.gob.qw.supervision.data.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QWService {
    @POST("Logueo")
    Call<Usuario> login(@Body LoginBody loginBody);

    @GET("GetListaPostores/{iCodUT}")
    Call<Postor> getPostor(@Path("iCodUT") int iCodUT);

    @GET("GetListaProveedores/{iCodUT}")
    Call<Proveedor> getProveedor(@Path("iCodUT") int iCodUT);

}

