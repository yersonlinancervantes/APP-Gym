package com.example.appgimnasio.repositories.remote.request;

import com.example.appgimnasio.entity.FooRequest;
import com.example.appgimnasio.entity.Porcino;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PorcinoService {
    @GET("listar")
    Call<List<Porcino>> getPorcino();

    @POST("registrar")
    Call<ResponseBody> updateUserImage(
            @Body FooRequest body);
}
