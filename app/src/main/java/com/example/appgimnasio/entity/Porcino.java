package com.example.appgimnasio.entity;

public class Porcino {
    private int id;
    private String nombre;
    private String fechaCompra;
    private String url;
    private String genero;
    private String raza;

    public Porcino(){

    }

    public Porcino(int id, String nombre, String fechaCompra, String url, String genero, String raza) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCompra = fechaCompra;
        this.url = url;
        this.genero = genero;
        this.raza = raza;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public String getUrl() {
        return url;
    }

    public String getGenero() {
        return genero;
    }

    public String getRaza() {
        return raza;
    }
}

