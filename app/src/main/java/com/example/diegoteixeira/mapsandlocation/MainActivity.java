package com.example.diegoteixeira.mapsandlocation;

import android.content.Intent;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String menu [] = new String [] {"Minha casa na cidade natal","Minha casa em Viçosa", "Meu departamento", "Fechar aplicação"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long d) {
        Intent it = new Intent(getBaseContext(), MainActivity.class);
        String aux = l.getItemAtPosition(position).toString();

        switch(position) {
            case 0:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                // parametro do mapa
                //startActivity(it);
                break;
            case 1:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                // parametro do mapa
                //startActivity(it);
                break;
            case 2:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                // parametro do mapa
                //startActivity(it);
                break;
            case 3:
                finish();
                break;
        }
    }
}