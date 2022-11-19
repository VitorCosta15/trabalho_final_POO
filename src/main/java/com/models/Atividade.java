package com.models;

import javax.json.JsonObject;

public class Atividade {
    private String nome;
    private String unidade;
    private int horasAtividade;
    private int maximoAtividade;


    Atividade(String nome, String unidade, int horasAtividade, int maximoAtividade){
        this.nome = nome;
        this.unidade = unidade;
        this.horasAtividade = horasAtividade;
        this.maximoAtividade = maximoAtividade;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getUnidade() {
        return unidade;
    }


    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }


    public int getHorasAtividade() {
        return horasAtividade;
    }


    public void setHorasAtividade(int horasAtividade) {
        this.horasAtividade = horasAtividade;
    }


    public int getMaximoAtividade() {
        return maximoAtividade;
    }


    public void setMaximoAtividade(int maximoAtividade) {
        this.maximoAtividade = maximoAtividade;
    }

    public static Atividade fromJson(JsonObject json){
        return new Atividade(
            json.getString("nome"),
            json.getString("unidade"),
            json.getInt("horas_atividade"),
            json.getInt("maximo_atividade")
        );
    }

    
}
