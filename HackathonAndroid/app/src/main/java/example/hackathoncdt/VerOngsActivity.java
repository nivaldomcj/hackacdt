package example.hackathoncdt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import example.hackathoncdt.models.Organizacao;
import example.hackathoncdt.other.Constants;
import example.hackathoncdt.retrofit.ColaboreEndpointInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerOngsActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String TAG = VerOngsActivity.class.getName();

    private static final int PERMISSAO_OBTER_LOCALIZACAO = 99;

    private LocationManager locationManager;
    private SupportMapFragment mMapFragment;
    private GoogleMap mGoogleMap;

    private List<Organizacao> listOrganizacao;

    private boolean atualizouCamera;
    private boolean adicionouMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_ongs);

        // Define o callback no mapa
        mMapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mMapFragment.getMapAsync(this);

        // Ligar o GPS do dispositivo
        atualizouCamera = false;
        inicializarGps();

        // ActionBar disponível?
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getMarkersFromApi();
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Referencia do mapa para edição
        mGoogleMap = googleMap;

        // Com o mapa pronto na tela, vamos inicializá-lo =)
        inicializarMapa();
    }

    /**
     * Inicializa o GPS usando o LocationManager, no qual lida com a localização do dispositivo.
     * Caso o dispositivo não tenha permissão de usar o GPS (permissões dangerous do android 5+),
     * o usuário deve ativar/permitir o GPS em tempo de execução para a aplicação usar.
     */
    private void inicializarGps() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // GPS habilitado?
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d(TAG, "GPS habilitado no dispositivo!");

            // Aplicativo não pode usar o GPS?
            if (ActivityCompat.checkSelfPermission(
                    getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Deve perguntar ao usuário para usar o GPS?
                perguntarUsuarioAtivarGps();
            }

            // Tenta obter a última localização conhecida
            Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (l != null) {
                atualizarPosicaoMapa(new LatLng(l.getLatitude(), l.getLongitude()));
                Log.d(TAG, "Posição da últ. local. conhecida: " + l.getLatitude() + " / " + l.getLongitude());
            }


            // Registra o listener para obter o update da localização, caso atualize
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // Obtenha a latitude e longitude da localização
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    Log.d(TAG, "Latitude: " + latitude + " / Longitude: " + longitude);

                    // Atualiza o mapa com as novas coordenadas só uma vez
                    if (!atualizouCamera)
                        atualizarPosicaoMapa(new LatLng(latitude, longitude));
                    atualizouCamera = true;
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {
                    Log.d(TAG, "Status modificado: " + s);
                }

                @Override
                public void onProviderEnabled(String s) {
                    Log.d(TAG, "Provedor habilitado: " + s);
                }

                @Override
                public void onProviderDisabled(String s) {
                    Log.d(TAG, "Provedor desabilitado: " + s);
                }
            });
        }
    }

    private void perguntarUsuarioAtivarGps() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.ACCESS_FINE_LOCATION },
                    PERMISSAO_OBTER_LOCALIZACAO);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.ACCESS_FINE_LOCATION },
                    PERMISSAO_OBTER_LOCALIZACAO);
        }
    }

    /**
     * Inicializa o mapa (define controles e gestos) e uma localização inicial.
     * Essa localização é suposta até o dispositivo definir a localização atual,
     * então o mesmo deve atualizar a posição no mapa.
     */
    private void inicializarMapa() {
        // Gestos de zoom e scroll do mapa
        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.getUiSettings().setScrollGesturesEnabled(true);

        // Controles de zoom e localização
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

        // Mostra a localização do dispositivo
        // Devemos antes verificar se há permissão para isso
        if (ActivityCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Deve perguntar ao usuário para usar o GPS?
            perguntarUsuarioAtivarGps();
        }

        mGoogleMap.setMyLocationEnabled(true);
        Log.d(TAG, "Status da flag da minha localização: " + mGoogleMap.isMyLocationEnabled());

        // Define uma localização inicial até ser atualizado com o local do dispositivo
        atualizarPosicaoMapa(new LatLng(-7.120878, -34.880461));

    }

    /**
     * Atualiza a posição do mapa/câmera.
     * O valor padrão de zoom da câmera é de 12 (em relação a tiles).
     * Esse valor pode ir de 1 até 18, dependendo do provedor de tiles.
     *
     * @param posicao coordenadas de latitude/longitude
     */
    private void atualizarPosicaoMapa(LatLng posicao) {
        if (mGoogleMap != null)
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicao, 15.0f));
    }

    private void getMarkersFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ColaboreEndpointInterface apiService = retrofit.create(ColaboreEndpointInterface.class);

        Call<List<Organizacao>> call = apiService.getOrganizacaos();
        call.enqueue(new Callback<List<Organizacao>>() {
            @Override
            public void onResponse(Call<List<Organizacao>> call, Response<List<Organizacao>> response) {
                listOrganizacao = response.body();
                Log.d(TAG, "Tamanho da resposta = " + response.body().size());

                addMarkersMap(listOrganizacao);
            }

            @Override
            public void onFailure(Call<List<Organizacao>> call, Throwable t) {
                Log.e(TAG, "Falha ao obter o JSON...");
            }
        });
    }

    private Marker[] addMarkersMap(List<Organizacao> ongList) {
        if (mGoogleMap != null) {
            if (!adicionouMarkers) {
                // Quantidade de locais
                int numLocations = ongList.size();

                // Marcadores para o mapa
                MarkerOptions markerOptions[] = new MarkerOptions[numLocations];
                Marker markers[] = new Marker[numLocations];

                // Bitmap de ponteiro de localização
                BitmapDescriptor iconDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_marker);

                // Preenche o array de marcadores
                for (int i = 0; i < numLocations; i++) {
                    // Objeto wraper da localização
                    Organizacao ong = ongList.get(i);
                    LatLng posicao = new LatLng(ong.getLat(), ong.getLog());
                    String titulo = ong.getNome();
                    String descricao = ong.getEndereco();

                    markerOptions[i] = new MarkerOptions()
                            .position(posicao)
                            .title(titulo)
                            .snippet(descricao)
                            .icon(iconDescriptor);
                }

                // Seta cada marcador no mapa
                for (int i = 0; i < numLocations; i++) {
                    markers[i] = mGoogleMap.addMarker(markerOptions[i]);
                }

                return markers;
            }
        }

        return null;
    }
}
