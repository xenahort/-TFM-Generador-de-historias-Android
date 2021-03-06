/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:15
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 8:09
 *
 */

package com.jcsp.historiasinteractivas.Actividades;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Objetos_gestion.Historia;
import com.jcsp.historiasinteractivas.Util.Permisos;

public class HistoriaActivity extends AppCompatActivity {

    private Historia historia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);
        getSupportActionBar().hide();

        historia = (Historia) getIntent().getSerializableExtra("Historia");

        //Titulo
        ImageView imgTit = findViewById(R.id.imageViewTituloHistoria);
        byte[] imageAsBytes = Base64.decode(historia.getImagen_historia().getBytes(), Base64.DEFAULT);
        imgTit.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imgTit.setAdjustViewBounds(true);

        //Descripción
        TextView textDes = findViewById(R.id.textViewDescripcionHistoria);
        textDes.setText(historia.getDescripcion_historia().replaceAll("#", System.getProperty("line.separator") + System.getProperty("line.separator")));
        textDes.setMovementMethod(new ScrollingMovementMethod());

        //Boton
        Button btnRegistro = findViewById(R.id.buttonEmpezarHistoria);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoriaActivity.this, NavigationDrawerActivity.class);
                intent.putExtra("Historia", historia);
                startActivityForResult(intent, 0);
            }
        });

        (new Permisos()).soliciarPermisoLocalizacion(this);
    }

}
