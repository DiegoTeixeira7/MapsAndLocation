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
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent it = new Intent(getBaseContext(), Maps.class);
        String aux = l.getItemAtPosition(position).toString();

        switch(position) {
            case 0:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                it.putExtra("local", "PC");
                startActivityForResult(it,1);
                break;
            case 1:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                it.putExtra("local", "Vicosa");
                startActivityForResult(it,2);
                break;
            case 2:
                Toast.makeText(getBaseContext(), aux, Toast.LENGTH_SHORT).show();
                it.putExtra("local", "UFV");
                startActivityForResult(it,3);
                break;
            case 3:
                finish();
                break;
        }
    }
}