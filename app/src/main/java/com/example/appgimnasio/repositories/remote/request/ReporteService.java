package com.example.appgimnasio.repositories.remote.request;

import com.example.appgimnasio.entity.Reporte;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReporteService {
    @GET("reporte")
    Call<List<Reporte>> getReporte();
}
