package com.toni.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    TextView txtProducto;
    ImageView imgProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtProducto = findViewById(R.id.txtProducto);
        imgProducto = findViewById(R.id.imgProducto);

        Intent intent = getIntent();
        String producto = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if(producto != null){
            mostrarProducto(producto);
        }

        imgProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirImagenAnimada();
            }
        });
    }

    public void mostrarProducto(String producto){
        String textoDescripcion = "";
        int idImage = 0;
        int idBackgroundColor = 0;
        int idTextColor = 0;

        if(producto.equals("donut")){
            textoDescripcion = getResources().getString(R.string.descripcion_img_donut);
            idImage = R.drawable.donut_circle;
            idBackgroundColor = R.color.teal_200;
            idTextColor = R.color.teal_700;
        }
        if(producto.equals("froyo")){
            textoDescripcion = getResources().getString(R.string.descripcion_img_froyo);
            idImage = R.drawable.froyo_circle;
            idBackgroundColor = R.color.purple_200;
            idTextColor = R.color.purple_700;

        }
        if(producto.equals("helado")){
            textoDescripcion = getResources().getString(R.string.descripcion_img_helado);
            idImage = R.drawable.icecream_circle;
            idBackgroundColor = R.color.celeste_200;
            idTextColor = R.color.celeste_700;
        }

        txtProducto.setText(textoDescripcion);
        txtProducto.setTextColor(idTextColor);
        txtProducto.setBackgroundResource(idBackgroundColor);
        imgProducto.setImageResource(idImage);

    }

    public void abrirImagenAnimada(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int cx = imgProducto.getWidth() / 2;
            int cy = imgProducto.getHeight() / 2;
            float radius = imgProducto.getWidth();
            Animator anim = ViewAnimationUtils.createCircularReveal(imgProducto, cx, cy, radius, 0);
            anim.setDuration(1000);
            Animator revealAnim = ViewAnimationUtils.createCircularReveal(imgProducto, cx, cy, 0, radius);
            revealAnim.setDuration(1000);
            anim.setDuration(1000);
            AnimatorSet set = new AnimatorSet();
            set.playSequentially(anim, revealAnim);
            set.start();
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });
            //anim.start();
        }
    }
}