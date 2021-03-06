/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:14
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 4:46
 *
 */

package com.jcsp.historiasinteractivas.REST;

import com.jcsp.historiasinteractivas.Objetos_gestion.Historia;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.Objetos_gestion.Pregunta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetPostService {

    /**
     * Crear usuario
     *
     * @param email      Email del usuario
     * @param nombre     Nombre de usuario
     * @param password   Contraseña del usuario
     * @param imagen_usu Imagen de perfil del usuario
     * @return Respuesta del servidor
     */
    @POST("/rest/usuario")
    @FormUrlEncoded
    Call<Respuesta> crearUsuario(@Field("email") String email,
                                 @Field("nombre") String nombre,
                                 @Field("password") String password,
                                 @Field("imagen_usu") String imagen_usu);

    /**
     * Funcion para iniciar sesión
     *
     * @param email    Email del usuario
     * @param password Contraseña del usuario
     * @return Respuesta del servidor
     */
    @POST("/rest/login")
    @FormUrlEncoded
    Call<Respuesta> loginUsuario(@Field("email") String email,
                                 @Field("password") String password);

    /**
     * Función para solicitar al servidor cambiar la contraseña
     *
     * @param email Email del usuario
     * @return Respuesta del servidor
     */
    @POST("/rest/cambio")
    @FormUrlEncoded
    Call<Respuesta> solicitud_reseteo_password(@Field("email") String email);

    /**
     * Obtiene una lista básica de las historias (id, nombre_historia)
     *
     * @return Lista de las historias
     */
    @GET("/historia/list")
    Call<List<Historia>> getListHistorias();

    @POST("/historia")
    @FormUrlEncoded
    Call<Historia> solicitud_datos_historia(@Field("id") String id,
                                            @Field("email") String email);

    @POST("/rest/completar_mision")
    @FormUrlEncoded
    Call<Respuesta> post_completar_mision(@Field("email") String email,
                                          @Field("id_historia") int id_historia,
                                          @Field("id_mision") int id_mision);

    @POST("/pregunta_json")
    @FormUrlEncoded
    Call<Pregunta> solicitud_pregunta(@Field("codigo_prueba_mision") String codigo_prueba_mision);

    @POST("/mision_json")
    @FormUrlEncoded
    Call<Mision> solicitud_mision(@Field("id") int id);

    @POST("/rest/cambio_password")
    @FormUrlEncoded
    Call<Respuesta> cambiar_password(@Field("email") String email,
                                     @Field("pass") String pass);

    @POST("/rest/eliminar_usuario")
    @FormUrlEncoded
    Call<Respuesta> eliminar_usuario(@Field("email") String email);

    @POST("/prueba")
    @FormUrlEncoded
    Call<Respuesta> enviar_texto(@Field("texto") String texto);

    @POST("/rest/delete/progreso_historia")
    @FormUrlEncoded
    Call<Respuesta> reiniciar_progreso_historia(@Field("email") String email,
                                                @Field("id_historia") int id_historia);
}