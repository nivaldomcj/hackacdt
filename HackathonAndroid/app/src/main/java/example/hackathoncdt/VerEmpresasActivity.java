package example.hackathoncdt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.hackathoncdt.models.Organizacao;
import example.hackathoncdt.models.Parceiro;
import example.hackathoncdt.other.Constants;
import example.hackathoncdt.retrofit.ColaboreEndpointInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerEmpresasActivity extends AppCompatActivity {

    private static final String TAG = VerEmpresasActivity.class.getName();

    @BindView(R.id.lst_empresas)
    ListView lstEmpresas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_empresas);
        ButterKnife.bind(this);

        // ActionBar disponível?
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ArrayAdapter<Parceiro> arrayAdapter = new ArrayAdapter<Parceiro>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<Parceiro>());

        lstEmpresas.setAdapter(arrayAdapter);

        getParceirosFromApi();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Voltar
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getParceirosFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ColaboreEndpointInterface apiService = retrofit.create(ColaboreEndpointInterface.class);

        Call<List<Parceiro>> call = apiService.getParceiros();
        call.enqueue(new Callback<List<Parceiro>>() {
            @Override
            public void onResponse(Call<List<Parceiro>> call, Response<List<Parceiro>> response) {
                ArrayAdapter<Parceiro> adapter = (ArrayAdapter<Parceiro>) lstEmpresas.getAdapter();
                adapter.clear();
                adapter.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Parceiro>> call, Throwable t) {
                Log.e(TAG, "Falha ao receber o JSON...");
            }
        });



    }
}
