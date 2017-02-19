package example.hackathoncdt;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback, android.location.LocationListener {

    public static final String TAG = MainActivity.class.getName();

    private LocationManager mLocationManager;
    private SupportMapFragment mMapFragment;
    private GoogleMap mGoogleMap;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mMapFragment.getMapAsync(this);

        inicializarLocalizacao();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        inicializarMapa();
    }

    /**
     * Inicializa o mapa (define controles e gestos) e uma localização inicial.
     * Essa localização é suposta até o dispositivo definir a localização atual,
     * então o mesmo deve atualizar a posição no mapa.
     */
    private void inicializarMapa() {
        // Tipo do mapa para exibição
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // Gestos de zoom e scroll do mapa
        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.getUiSettings().setScrollGesturesEnabled(true);

        // Controles de zoom e localização
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);

        // Mostra a localização do dispositivo
        // Permissões runtime do Android 5+
        boolean permitidoGps = ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (permitidoGps) {
            mGoogleMap.setMyLocationEnabled(true);
        } else {
            Log.e(TAG, "Não foi possível definir a localização do dispositivo. Sem permissões?");
        }

        // Define uma localização inicial até ser atualizado com o local do dispositivo
        atualizarPosicaoMapa(new LatLng(-7.120878, -34.880461));
    }

    private void atualizarPosicaoMapa(LatLng posicao) {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posicao, 17.0f));
    }
}
