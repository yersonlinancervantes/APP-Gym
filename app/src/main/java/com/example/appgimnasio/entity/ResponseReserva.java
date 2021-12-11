package com.example.appgimnasio.entity;

import java.util.List;

public class ResponseReserva {

    private String message;
    private int code;
    private List<Reserva> reservas;

    public ResponseReserva(String message, int code, List<Reserva> reservas) {
        this.message = message;
        this.code = code;
        this.reservas = reservas;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
