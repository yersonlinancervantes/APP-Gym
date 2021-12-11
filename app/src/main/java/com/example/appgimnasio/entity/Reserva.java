package com.example.appgimnasio.entity;

public class Reserva {

    private int id;
    private String Cliente;
    private String Servicio;
    private String Fecha;
    private String Turno;
    private String Hora;
    private String Estado;

    public Reserva(int id, String cliente, String servicio, String fecha, String turno, String hora, String estado) {
        this.id = id;
        Cliente = cliente;
        Servicio = servicio;
        Fecha = fecha;
        Turno = turno;
        Hora = hora;
        Estado = estado;
    }

    public Reserva(String cliente, String servicio, String fecha, String turno, String hora, String estado) {
        Cliente = cliente;
        Servicio = servicio;
        Fecha = fecha;
        Turno = turno;
        Hora = hora;
        Estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return Cliente;
    }

    public String getServicio() {
        return Servicio;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getTurno() {
        return Turno;
    }

    public String getHora() {
        return Hora;
    }

    public String getEstado() {
        return Estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public void setServicio(String servicio) {
        Servicio = servicio;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public void setTurno(String turno) {
        Turno = turno;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
