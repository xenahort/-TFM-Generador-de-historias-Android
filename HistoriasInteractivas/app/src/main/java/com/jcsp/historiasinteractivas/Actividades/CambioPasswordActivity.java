/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:14
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 5:07
 *
 */

package com.jcsp.historiasinteractivas.Actividades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.REST.Respuesta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambioPasswordActivity extends AppCompatActivity {

    private TextView email_field;
    private Button btnCambiar;
    private CambioPasswordActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_password);
        act = this;
        getSupportActionBar().setTitle(R.string.cambiar_pass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email_field = findViewById(R.id.cambio_pass_editText);
        btnCambiar = findViewById(R.id.btn_cambio_pass);
        btnCambiar.setEnabled(false);

        email_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!email_field.getText().toString().trim().isEmpty()) {
                    btnCambiar.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String mail = email_field.getText().toString().trim();
                if (!mail.isEmpty()) {
                    enviarPeticion(mail);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.mail_invalid, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void enviarPeticion(String mail) {
        GetPostService mAPIService = ApiUtils.getAPIService();
        mAPIService.solicitud_reseteo_password(mail).enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(act);
                dialogo1.setMessage(R.string.mail_enviado);
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton(R.string.confirmar2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        //aceptar();
                        //TODO revisar esto
                    }
                });
                dialogo1.show();
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
