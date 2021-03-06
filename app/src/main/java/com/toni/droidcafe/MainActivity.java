package com.toni.droidcafe;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "com.toni.droidcafe.extra.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getResources().getString(R.string.no_seleccionado), Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    /**
     * Muestra un mensaje cuando se hace click en la imagen del donut
     *
     * @param view
     */
    public void mostrarDonut(View view) {
        mostrarMensaje(getString(R.string.pedido_donut));
        ordenarPedido("donut");
    }

    /**
     * Muestra un mensaje cuando se hace click en la imagen del froyo
     *
     * @param view
     */
    public void mostrarFroyo(View view) {

        mostrarMensaje(getString(R.string.pedido_froyo));
        ordenarPedido("froyo");
    }

    /**
     * Muestra un mensaje cuando se hace click en la imagen del helado
     *
     * @param view
     */
    public void mostrarHelado(View view) {

        mostrarMensaje(getString(R.string.pedido_helado));
        ordenarPedido("helado");
    }

    /**
     * Muestra un toast con el mensaje
     *
     * @param mensaje
     */

    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    public void ordenarPedido(String pedido){
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, pedido);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_pedido:
                mostrarMensaje(getString(R.string.action_pedido_mensaje));
                ordenarPedido("froyo");
                break;
            case R.id.action_estado:
                mostrarMensaje(getString(R.string.action_estado_mensaje));
                break;
            case R.id.action_favorites:
                mostrarMensaje(getString(R.string.action_favoritos_mensaje));
                Intent intent = new Intent(MainActivity.this, RecetaActivity.class);
                startActivity(intent);
                break;
            case R.id.action_contact:
                mostrarMensaje(getString(R.string.action_contacto_mensaje));
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}