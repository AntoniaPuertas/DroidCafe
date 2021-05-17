package com.toni.droidcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RecetaActivity extends AppCompatActivity {

    TextView txtReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);
        txtReceta = findViewById(R.id.txtRecetaDescripcion);
        registerForContextMenu(txtReceta);
    }

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.context_editar:
                mostrarMensaje("Has seleccionado editar");
                return true;
            case R.id.context_compartir:
                mostrarMensaje("Has seleccionado compartir");
                return true;
            case R.id.context_borrar:
                mostrarMensaje("Has seleccionado borrar");
            default:
                return super.onContextItemSelected(item);
        }
    }
}