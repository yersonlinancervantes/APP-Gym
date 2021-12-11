package com.example.appgimnasio.repositories.remote.request;

import com.example.appgimnasio.entity.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UsuarioService {

    @POST("usuario/login")
    Call<UsuarioResponse> login(
            @Query("username") String username,
            @Query("password") String password
    );

}