package com.toni.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txtProducto;
    ImageView imgProducto;
    Spinner mSpinner;
    EditText editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtProducto = findViewById(R.id.txtProducto);
        imgProducto = findViewById(R.id.imgProducto);


        //crea la referencia para el EditText del teléfono
        editTextPhone = findViewById(R.id.editTextPhone);
        //crea un listener para el edittextphone
        if(editTextPhone != null){
            editTextPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if(actionId == EditorInfo.IME_ACTION_SEND){
                        dialNumber();
                        handled = true;
                    }
                    return handled;
                }
            });
        }

        //Crea la referencia para el spinner
        mSpinner = findViewById(R.id.spinner);
        //Enlaza el spinner con el listener
        if(mSpinner != null){
            mSpinner.setOnItemSelectedListener(this);
        }

        //crea un array para pasarle los datos al spinner y establece el layout que va a utilizar
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.texto_sppiner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (mSpinner != null){
            mSpinner.setAdapter(adapter);
        }

        //Lee los datos del intent
        Intent intent = getIntent();
        String producto = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        if(producto != null){
            mostrarProducto(producto);
        }

        //crea un listener para la imagen
        imgProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirImagenAnimada();
            }
        });
    }

    //comprueba que producto ha sido seleccionado para mostrar su imagen y descripcion
    //a cada producto le aplica un color de texto y fondo diferentes
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

    //listener para el radiobutton
    public void onRadioButtonClicked(View view){
        //comprueba si el boton que ha generado el evento está seleccionado
        boolean checked = ((RadioButton) view).isChecked();
        //comprueba que boton ha generado el evento
        switch (view.getId()) {
            case R.id.rbMismoDia:
                if(checked){
                    mostrarMensaje(getString(R.string.mismo_dia));
                }
                break;
            case R.id.rbDiaSiguiente:
                if(checked){
                    mostrarMensaje(getString(R.string.dia_siguiente));
                }
                break;
            case R.id.rbSeleccionarFecha:
                if(checked){
                    mostrarMensaje(getString(R.string.seleccionar_fecha));
                }
                break;
            default:
                break;
        }
    }

    //animación para la imagen
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

    public void mostrarMensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    //listener para el spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();
        mostrarMensaje(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //utiliza una intencion implicita para buscar una aplicación que marque un número de teléfono
    public void dialNumber(){
        String numero = null;
        if(editTextPhone != null){
            numero = "tel:" + editTextPhone.getText().toString();
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(numero));

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            mostrarMensaje("No puedo realizar la llamada");
        }
    }
}