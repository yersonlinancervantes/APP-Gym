package com.example.appgimnasio.entity;

public class Disciplina {
    private int id;
    private String Cliente;
    private String Disciplina;
    private String Periodo;
    private String Profesor;
    private String Horario;
    private String Estado;

    public Disciplina(int id, String cliente, String disciplina, String periodo, String profesor, String horario, String estado) {
        this.id = id;
        Cliente = cliente;
        Disciplina = disciplina;
        Periodo = periodo;
        Profesor = profesor;
        Horario = horario;
        Estado = estado;
    }

    public Disciplina(String cliente, String disciplina, String periodo, String profesor, String horario, String estado) {
        Cliente = cliente;
        Disciplina = disciplina;
        Periodo = periodo;
        Profesor = profesor;
        Horario = horario;
        Estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return Cliente;
    }

    public String getDisciplina() {
        return Disciplina;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public String getProfesor() {
        return Profesor;
    }

    public String getHorario() {
        return Horario;
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

    public void setDisciplina(String disciplina) {
        Disciplina = disciplina;
    }

    public void setPeriodo(String periodo) {
        Periodo = periodo;
    }

    public void setProfesor(String profesor) {
        Profesor = profesor;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
