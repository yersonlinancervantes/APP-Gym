package com.example.appgimnasio.entity;

public class UsuarioResponse {
    private String message;
    private int code;
    private String nombre;
    private int idUsuario;

    public UsuarioResponse(String message, int code, String nombre,int idUsuario) {
        this.message = message;
        this.code = code;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
