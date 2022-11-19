package com.models;
import javax.json.JsonObject;

public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private String email;
    private String ingresso;
    private String formatura;

    Aluno(String nome, String matricula, String curso, String email, String ingresso, String formatura){
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.email = email;
        this.ingresso = ingresso;
        this.formatura = formatura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIngresso() {
        return ingresso;
    }

    public void setIngresso(String ingresso) {
        this.ingresso = ingresso;
    }

    public String getFormatura() {
        return formatura;
    }

    public void setFormatura(String formatura) {
        this.formatura = formatura;
    }

    public static Aluno fromJson(JsonObject json){
        return new Aluno(
            json.getString("nome"),
            json.getString("matricula"),
            json.getString("curso"),
            json.getString("email"),
            json.getString("ingresso"),
            json.getString("formatura")
        );
    }

    
}
