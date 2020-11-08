package com.example.diegoteixeira.mapsandlocation;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements LocationListener, OnMapReadyCallback {
    private final LatLng VICOSA = new LatLng(-20.755921, -42.8804686);
    private final LatLng PC = new LatLng(-20.871925, -42.98892);
    private final LatLng UFV = new LatLng(-20.7610643, -42.8723519);

    private GoogleMap map;

    public LocationManager lm;
    public Criteria criteria;
    public String provider;
    public int TEMPO_REQUISICAO_LATLONG = 0;

    public Marker marker;


    private final int LOCATION_PERMISSION = 1;
    private final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync((OnMapReadyCallback) this);
    }

    public void onClick_Vicosa(View view) {
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(VICOSA, 14);
        map.animateCamera(update);
    }

    public void onClick_UFV(View view) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(UFV, 12);
        map.animateCamera(update);
    }

    public void onClick_PC(View view) {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(PC, 16);
        map.animateCamera(update);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.addMarker(new MarkerOptions().position(PC).title("Minha casa em PC"));
        map.addMarker(new MarkerOptions().position(VICOSA).title("Meu apartamento em Viçosa"));
        map.addMarker(new MarkerOptions().position(UFV).title("Universidade Federal de Viçosa"));

        Intent it = getIntent();
        String local = it.getStringExtra("local");

        assert local != null;
        if (local.equals("PC")) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(PC, 10);
            map.animateCamera(update);
        } else if (local.equals("Vicosa")) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(VICOSA, 10);
            map.animateCamera(update);
        } else if (local.equals("UFV")) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(UFV, 10);
            map.animateCamera(update);
        }
    }

    public void permissionLocation() {
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION);
            Log.i("Permission", "Pede a permissão");
        } else {

            //Location Manager
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();

            //Testa se o aparelho tem GPS
            PackageManager packageManager = getPackageManager();
            boolean hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);

            //Estabelece critério de precisão
            if (hasGPS) {
                criteria.setAccuracy(Criteria.ACCURACY_FINE);
                Log.i("LOCATION", "Usando GPS");
            } else {
                Log.i("LOCATION", "Usando WI-FI ou dados");
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            }

            //Obtem melhor provedor habilitado com o critério estabelecido
            provider = lm.getBestProvider(criteria, true);

            if (provider == null) {
                Log.e("PROVEDOR", "Nenhum provedor encontrado!");
            } else {
                Log.i("PROVEDOR", "Está sendo utilizado o provedor: " + provider);

                //Obtem atualizações de posição
                if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (android.location.LocationListener) this);
                    lm.requestLocationUpdates(provider, TEMPO_REQUISICAO_LATLONG, 0, (android.location.LocationListener) this);
                }
            }
        }
    }

    public void onClick_Localizar(View view) {

        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            permissionLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionLocation();
                    Log.i("Permission","Deu a permissão");
                } else {
                    Log.i("Permission","Não permitiu");
                }
                return;
            }

            case MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionLocation();
                    Log.i("Permission","Deu a permissão");
                } else {
                    Log.i("Permission","Não permitiu");
                }
                return;
            }

        }
    }

    @Override
    protected void onDestroy() {
        //interrompe o Location Manager
        lm.removeUpdates((android.location.LocationListener) this);
        Log.w("PROVEDOR","Provedor " + provider + " parado!");
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null) {

            marker.remove();

            LatLng atual = new LatLng(location.getLatitude(), location.getLongitude());

            marker = map.addMarker(new MarkerOptions()
                    .position(atual).title("Minha localização atual")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(atual, 17);
            map.animateCamera(update);
        }
    }
}