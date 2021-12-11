package com.example.appgimnasio.repositories.remote.request;

import com.example.appgimnasio.entity.ResponseReserva;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReservaService {

    @POST("reserva/registrar")
    Call<ResponseReserva> registrar(
            @Query("cliente") String cliente,
            @Query("servicio") String servicio,
            @Query("fecha") String fecha,
            @Query("turno") String turno,
            @Query("hora") String hora
    );

    @POST("reserva/anular")
    Call<ResponseReserva> anular(
            @Query("id") int id
    );

    @GET("reserva/listar")
    Call<ResponseReserva> listar(
            @Query("cliente") String cliente
    );

}
