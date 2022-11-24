package com.application;

import java.util.ArrayList;

import com.models.Aluno;
import com.models.Atividade;
import com.models.Professor;
import com.models.RegistroAtividade;

public class Globals {
    static public ArrayList<Aluno> alunos = new ArrayList<Aluno>();

    static public Professor professor;

    static public ArrayList<Atividade> atividadesCiencia = new ArrayList<Atividade>();

    static public ArrayList<Atividade> atividadesEngenharia = new ArrayList<Atividade>();

    static public ArrayList<RegistroAtividade> registros = new ArrayList<RegistroAtividade>();
    

    static public int contAluno = 0;
}
