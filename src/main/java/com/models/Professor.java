package com.models;

import javax.json.JsonObject;

public class Professor {
    

    private String nome;
    private String siape;
    
    public Professor(String nome, String siape) {
        this.nome = nome;
        this.siape = siape;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }


    public static Professor fromJson (JsonObject json){
        return new Professor(
            json.getString("nome_professor"),
            json.getString("siape")
        );
    }

    

    
}
