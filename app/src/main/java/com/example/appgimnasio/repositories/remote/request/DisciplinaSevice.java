package com.example.appgimnasio.repositories.remote.request;

import com.example.appgimnasio.entity.ResponseDisciplina;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DisciplinaSevice {

    @POST("disciplina/registrar")
    Call<ResponseDisciplina> registrar(
            @Query("cliente") String cliente,
            @Query("disciplina") String disciplina,
            @Query("periodo") String periodo,
            @Query("profesor") String profesor,
            @Query("horario") String horario
    );

    @POST("disciplina/anular")
    Call<ResponseDisciplina> anular(
            @Query("id") int id
    );

    @GET("disciplina/listar")
    Call<ResponseDisciplina> listar(
            @Query("cliente") String cliente
    );

}
