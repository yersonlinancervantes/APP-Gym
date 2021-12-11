package com.example.appgimnasio.entity;

public class PartoPorcino {
    private int id;
    private int idPorcino;
    private String fechaParto;
    private int lechonesVivosMachos;
    private int lechonesVivosHembras;
    private int lechonesMuertosMachos;
    private int lechonesMuertosHembras;
    private double promedioPeso;

    public PartoPorcino() {

    }

    public PartoPorcino(int idPorcino, String fechaParto, int lechonesVivosMachos, int lechonesVivosHembras, int lechonesMuertosMachos, int lechonesMuertosHembras, double promedioPeso) {
        this.idPorcino = idPorcino;
        this.fechaParto = fechaParto;
        this.lechonesVivosMachos = lechonesVivosMachos;
        this.lechonesVivosHembras = lechonesVivosHembras;
        this.lechonesMuertosMachos = lechonesMuertosMachos;
        this.lechonesMuertosHembras = lechonesMuertosHembras;
        this.promedioPeso = promedioPeso;
    }

    public PartoPorcino(int id,int idPorcino, String fechaParto, int lechonesVivosMachos, int lechonesVivosHembras, int lechonesMuertosMachos, int lechonesMuertosHembras, double promedioPeso) {
        this.id = id;
        this.idPorcino = idPorcino;
        this.fechaParto = fechaParto;
        this.lechonesVivosMachos = lechonesVivosMachos;
        this.lechonesVivosHembras = lechonesVivosHembras;
        this.lechonesMuertosMachos = lechonesMuertosMachos;
        this.lechonesMuertosHembras = lechonesMuertosHembras;
        this.promedioPeso = promedioPeso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPorcino(int idPorcino) {
        this.idPorcino = idPorcino;
    }

    public void setFechaParto(String fechaParto) {
        this.fechaParto = fechaParto;
    }

    public void setLechonesVivosMachos(int lechonesVivosMachos) {
        this.lechonesVivosMachos = lechonesVivosMachos;
    }

    public void setLechonesVivosHembras(int lechonesVivosHembras) {
        this.lechonesVivosHembras = lechonesVivosHembras;
    }

    public void setLechonesMuertosMachos(int lechonesMuertosMachos) {
        this.lechonesMuertosMachos = lechonesMuertosMachos;
    }

    public void setLechonesMuertosHembras(int lechonesMuertosHembras) {
        this.lechonesMuertosHembras = lechonesMuertosHembras;
    }

    public void setPromedioPeso(double promedioPeso) {
        this.promedioPeso = promedioPeso;
    }

    public int getId() {
        return id;
    }

    public int getIdPorcino() {
        return idPorcino;
    }

    public String getFechaParto() {
        return fechaParto;
    }

    public int getLechonesVivosMachos() {
        return lechonesVivosMachos;
    }

    public int getLechonesVivosHembras() {
        return lechonesVivosHembras;
    }

    public int getLechonesMuertosMachos() {
        return lechonesMuertosMachos;
    }

    public int getLechonesMuertosHembras() {
        return lechonesMuertosHembras;
    }

    public double getPromedioPeso() {
        return promedioPeso;
    }
}

