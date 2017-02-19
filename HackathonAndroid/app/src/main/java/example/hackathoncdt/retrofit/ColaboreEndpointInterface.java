package example.hackathoncdt.retrofit;

import java.util.List;

import example.hackathoncdt.models.Organizacao;
import example.hackathoncdt.models.Parceiro;
import example.hackathoncdt.models.Voluntario;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nivaldo Carvalho on 19/02/2017.
 */

public interface ColaboreEndpointInterface {

    @GET("voluntarios.json")
    Call<List<Voluntario>> getVoluntarios();

    @GET("ongs.json")
    Call<List<Organizacao>> getOrganizacaos();

    @GET("parcs.json")
    Call<List<Parceiro>> getParceiros();
}
