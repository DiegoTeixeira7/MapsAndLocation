package com.example.diegoteixeira.mapsandlocation;


import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {
    private final LatLng VICOSA = new LatLng(-20.755921, -42.8804686);
    private final LatLng PC = new LatLng(-20.871925, -42.98892);
    private final LatLng UFV = new LatLng(-20.7610643, -42.8723519);

    private GoogleMap map;

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
        if(local.equals("PC")){
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(PC, 10);
            map.animateCamera(update);
        }else if(local.equals("Vicosa")) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(VICOSA, 10);
            map.animateCamera(update);
        }else if(local.equals("UFV")) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(UFV, 10);
            map.animateCamera(update);
        }

        // .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
    }

    public void onClick_Localizar(View view) {
    }
}