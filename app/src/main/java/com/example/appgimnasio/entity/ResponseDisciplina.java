package com.example.appgimnasio.entity;

import java.util.List;

public class ResponseDisciplina {
    private String message;
    private int code;
    private List<Disciplina> disciplinas;

    public ResponseDisciplina(String message, int code, List<Disciplina> disciplinas) {
        this.message = message;
        this.code = code;
        this.disciplinas = disciplinas;
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
