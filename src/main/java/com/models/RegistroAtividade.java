package com.models;



import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class RegistroAtividade {
    private String nomeAluno;
    private String nomeAtividade;
    private String matricula;
    private String email;
    private String ingresso;
    private String formatura;
    private String curso;
    private String unidade;
    private int horasAtividade;
    private int maximoHoras;
    private String linkCertificado;
    private int horasCumpridas;
    private boolean homologado;
    private String data;
    private String hora;
    private String justificativa;

    
    
    public RegistroAtividade(String nomeAluno, String nomeAtividade, String matricula, String email, String ingresso,
            String formatura, String curso, String unidade, int horasAtividade, int maximoHoras, String linkCertificado,
            int horasCumpridas, boolean homologado, String data, String hora, String justificativa) {
        this.nomeAluno = nomeAluno;
        this.nomeAtividade = nomeAtividade;
        this.matricula = matricula;
        this.email = email;
        this.ingresso = ingresso;
        this.formatura = formatura;
        this.curso = curso;
        this.unidade = unidade;
        this.horasAtividade = horasAtividade;
        this.maximoHoras = maximoHoras;
        this.linkCertificado = linkCertificado;
        this.horasCumpridas = horasCumpridas;
        this.homologado = homologado;
        this.data = data;
        this.hora = hora;
        this.justificativa = justificativa;
    }



    public String getNomeAluno() {
        return nomeAluno;
    }



    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }



    public String getNomeAtividade() {
        return nomeAtividade;
    }



    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }



    public String getMatricula() {
        return matricula;
    }



    public void setMatricula(String matricula) {
        this.matricula = matricula;
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



    public String getCurso() {
        return curso;
    }



    public void setCurso(String curso) {
        this.curso = curso;
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



    public int getMaximoHoras() {
        return maximoHoras;
    }



    public void setMaximoHoras(int maximoHoras) {
        this.maximoHoras = maximoHoras;
    }



    public String getLinkCertificado() {
        return linkCertificado;
    }



    public void setLinkCertificado(String linkCertificado) {
        this.linkCertificado = linkCertificado;
    }



    public int getHorasCumpridas() {
        return horasCumpridas;
    }



    public void setHorasCumpridas(int horasCumpridas) {
        this.horasCumpridas = horasCumpridas;
    }



    public boolean isHomologado() {
        return homologado;
    }



    public void setHomologado(boolean homologado) {
        this.homologado = homologado;
    }



    public String getData() {
        return data;
    }



    public void setData(String data) {
        this.data = data;
    }



    public String getHora() {
        return hora;
    }



    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getJustificativa(){
        return justificativa;
    }

    public void setJustificativa(String justificativa){
        this.justificativa = justificativa;
    }




    public static RegistroAtividade fromJson(JsonObject json){
        return new RegistroAtividade(
            json.getString("nome_aluno"),
            json.getString("nome_atividade"),
            json.getString("matricula"),
            json.getString("email"),
            json.getString("ingresso"),
            json.getString("formatura"),
            json.getString("curso"),
            json.getString("unidade"),
            json.getInt("horas_atividade"),
            json.getInt("maximo_horas"),
            json.getString("link_certificado"),
            json.getInt("horas_cumpridas"),
            json.getBoolean("homologado"),
            json.getString("data"),
            json.getString("hora"),
            json.getString("justificativa")

        );
    }


    public static JsonObject toJson(RegistroAtividade registroAtividade){
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("nome_aluno", registroAtividade.getNomeAluno());
        json.add("nome_atividade", registroAtividade.getNomeAtividade());
        json.add("matricula", registroAtividade.getMatricula());
        json.add("email", registroAtividade.getEmail());
        json.add("ingresso", registroAtividade.getIngresso());
        json.add("formatura", registroAtividade.getFormatura());
        json.add("curso", registroAtividade.getCurso());
        json.add("unidade", registroAtividade.getUnidade());
        json.add("horas_atividade", registroAtividade.getHorasAtividade());
        json.add("maximo_horas", registroAtividade.getMaximoHoras());
        json.add("link_certificado", registroAtividade.getLinkCertificado());
        json.add("horas_cumpridas", registroAtividade.getHorasCumpridas());
        json.add("homologado", registroAtividade.isHomologado());
        json.add("data", registroAtividade.getData());
        json.add("hora", registroAtividade.getHora());
        json.add("justificativa", registroAtividade.getJustificativa());
        JsonObject jsonObject = json.build();
        return jsonObject;
    }








    

    
    
    
}
