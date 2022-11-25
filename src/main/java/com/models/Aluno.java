package com.models;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private String email;
    private String ingresso;
    private String formatura;
    private int horasHomologadas;

    Aluno(String nome, String matricula, String curso, String email, String ingresso, String formatura, int horasHomologadas){
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.email = email;
        this.ingresso = ingresso;
        this.formatura = formatura;
        this.horasHomologadas = horasHomologadas;
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

    public int getHorasHomologadas(){
        return horasHomologadas;
    }

    public void setHorasHomologadas(int horasHomologadas){
        this.horasHomologadas = horasHomologadas;
    }

    public static Aluno fromJson(JsonObject json){
        return new Aluno(
            json.getString("nome"),
            json.getString("matricula"),
            json.getString("curso"),
            json.getString("email"),
            json.getString("ingresso"),
            json.getString("formatura"),
            json.getInt("horas_homologadas")
        );
    }

    public static JsonObject toJson (Aluno aluno){
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("nome", aluno.getNome());
        json.add("matricula", aluno.getMatricula());
        json.add("curso", aluno.getCurso());
        json.add("email", aluno.getEmail());
        json.add("ingresso", aluno.getIngresso());
        json.add("formatura", aluno.getFormatura());
        json.add("horas_homologadas", aluno.getHorasHomologadas());
        return json.build();
    }

    
}
