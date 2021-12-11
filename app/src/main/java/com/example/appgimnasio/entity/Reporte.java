package com.example.appgimnasio.entity;

public class Reporte {
    private int cantCerdosHembras;
    private int cantCerdosMachos;
    private double pcTotalHembras;
    private double pcTotalMacho;
    private double pcTotal;

    public Reporte(int cantCerdosHembras, int cantCerdosMachos, double pcTotalHembras, double pcTotalMacho, double pcTotal) {
        this.cantCerdosHembras = cantCerdosHembras;
        this.cantCerdosMachos = cantCerdosMachos;
        this.pcTotalHembras = pcTotalHembras;
        this.pcTotalMacho = pcTotalMacho;
        this.pcTotal = pcTotal;
    }

    public void setCantCerdosHembras(int cantCerdosHembras) {
        this.cantCerdosHembras = cantCerdosHembras;
    }

    public void setCantCerdosMachos(int cantCerdosMachos) {
        this.cantCerdosMachos = cantCerdosMachos;
    }

    public void setPcTotalHembras(double pcTotalHembras) {
        this.pcTotalHembras = pcTotalHembras;
    }

    public void setPcTotalMacho(double pcTotalMacho) {
        this.pcTotalMacho = pcTotalMacho;
    }

    public void setPcTotal(double pcTotal) {
        this.pcTotal = pcTotal;
    }

    public int getCantCerdosHembras() {
        return cantCerdosHembras;
    }

    public int getCantCerdosMachos() {
        return cantCerdosMachos;
    }

    public double getPcTotalHembras() {
        return pcTotalHembras;
    }

    public double getPcTotalMacho() {
        return pcTotalMacho;
    }

    public double getPcTotal() {
        return pcTotal;
    }
}
