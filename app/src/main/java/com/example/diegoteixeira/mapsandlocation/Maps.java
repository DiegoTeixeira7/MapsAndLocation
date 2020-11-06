package com.example.diegoteixeira.mapsandlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity {
    private final LatLng VICOSA = new LatLng(-20.752946, -42.879097);
    private final LatLng MURIAE = new LatLng(-21.12881, -42.374247);
    private final LatLng FAMINAS = new LatLng(-21.109725, -42.381738);

    private GoogleMap map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //map  = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        //acrescentar atributo icon com imagem do projeto
        //map.addMarker(new MarkerOptions().position(VICOSA).title("Meu apt Viçosa"));
        //map.addMarker(new MarkerOptions().position(MURIAE).title("Minha casa Muriae"));
        //map.addMarker(new MarkerOptions().position(FAMINAS).title("Faculdade de Minas"))
    }

    public void onClick_Vicosa(View view) {
    }

    public void onClick_UFV(View view) {
    }

    public void onClick_PC(View view) {
    }
}