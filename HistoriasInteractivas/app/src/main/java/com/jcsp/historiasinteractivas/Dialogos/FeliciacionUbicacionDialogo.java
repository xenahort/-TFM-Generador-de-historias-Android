/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 31/03/19 18:43
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 31/03/19 18:43
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Fragments.MapFragment;
import com.jcsp.historiasinteractivas.Objetos_gestion.Pregunta;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.Util.Permisos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FeliciacionUbicacionDialogo {

    private Mision mision;
    private MapFragment map;

    public FeliciacionUbicacionDialogo(final Context contexto, Mision mis, MapFragment mmap) {
        final Dialog dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        dialogo.setContentView(R.layout.dialogo_feliciacion_ubicacion);

        this.mision = mis;
        this.map = mmap;

        //Boton aceptar
        Button btn_escalear = (Button) dialogo.findViewById(R.id.empezar_prueba);
        btn_escalear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mision.getTipo_prueba().equals("qr")) {
                    map.iniciarDialogo(5);
                } else {
                    //Miro si pregunta es NULL, y de ser así la pido al servidor
                    if(mision.getPregunta()==null){
                        GetPostService mAPIService = ApiUtils.getAPIService();
                        mAPIService.solicitud_pregunta(mision.getCodigo_prueba()).enqueue(new Callback<Pregunta>() {

                            @Override
                            public void onResponse(Call<Pregunta> call, Response<Pregunta> response) {
                                mision.setPregunta(response.body());
                                map.iniciarDialogo(6);
                            }

                            @Override
                            public void onFailure(Call<Pregunta> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
                            }
                        });


                    }else{
                        map.iniciarDialogo(6);
                    }
                }
                dialogo.dismiss();
            }
        });

        if (mision.getTipo_localizacion().equals("qr")) {
            (new Permisos()).soliciarPermisoCamara(contexto);
        }

        dialogo.show();
    }
}
