package com.example.appgimnasio.entity;

public class ControlPorcino {
    private int id;
    private int porcinoId;
    private double peso;
    private String fechaVacunacion;
    private String dosis;
    private String observacion;

    public ControlPorcino(){

    }

    public ControlPorcino(int porcinoId, double peso, String fechaVacunacion, String dosis, String observacion) {
        this.porcinoId = porcinoId;
        this.peso = peso;
        this.fechaVacunacion = fechaVacunacion;
        this.dosis = dosis;
        this.observacion = observacion;
    }

    public ControlPorcino(int id, int porcinoId, double peso, String fechaVacunacion, String dosis, String observacion) {
        this.id = id;
        this.porcinoId = porcinoId;
        this.peso = peso;
        this.fechaVacunacion = fechaVacunacion;
        this.dosis = dosis;
        this.observacion = observacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPorcinoId(int porcinoId) {
        this.porcinoId = porcinoId;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setFechaVacunacion(String fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public int getPorcinoId() {
        return porcinoId;
    }

    public double getPeso() {
        return peso;
    }

    public String getFechaVacunacion() {
        return fechaVacunacion;
    }

    public String getDosis() {
        return dosis;
    }

    public String getObservacion() {
        return observacion;
    }
}
