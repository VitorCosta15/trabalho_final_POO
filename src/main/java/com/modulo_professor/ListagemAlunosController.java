package com.modulo_professor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.application.App;
import com.application.Globals;
import com.models.Aluno;
import com.models.RegistroAtividade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListagemAlunosController implements Initializable{

    static private ArrayList<Aluno> alunosComAtividades = new ArrayList<Aluno>();

    @FXML
    private Label nomeProfessorText;

    @FXML
    private Label horasHomologadasText;

    @FXML
    private Button returnButton;

    @FXML
    private Label siapeText;

    @FXML
    private TableView<Aluno> tableAlunos;

    @FXML
    void goToModuloProfessorPage(ActionEvent event) throws IOException{
        App.setRoot("/layouts/modulo_professor.fxml");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alunosComAtividades.clear();
        nomeProfessorText.setText(Globals.professor.getNome());
        siapeText.setText(Globals.professor.getSiape());
        loadAlunosComAtividades();
        generateTable();
        
    }




    private void generateTable(){
        tableAlunos.setEditable(false);

        TableColumn<Aluno, String> nomeColumn = new TableColumn<Aluno, String>("Nome aluno");
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        nomeColumn.setPrefWidth(400);

        TableColumn<Aluno, String> matriculaColumn = new TableColumn<Aluno, String>("Matricula");
        matriculaColumn.setCellValueFactory(new PropertyValueFactory<Aluno, String>("matricula"));
        matriculaColumn.setPrefWidth(100);

        TableColumn<Aluno, String> cursoColumn = new TableColumn<Aluno, String>("Curso");
        cursoColumn.setCellValueFactory(new PropertyValueFactory<Aluno, String>("curso"));
        cursoColumn.setPrefWidth(400);

        TableColumn<Aluno, Integer> horasHomologadasColumn = new TableColumn<Aluno, Integer>("Horas homologadas");
        horasHomologadasColumn.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("horasHomologadas"));
        horasHomologadasColumn.setPrefWidth(200);

        TableColumn<Aluno, String> emailColumn = new TableColumn<Aluno, String>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email"));
        emailColumn.setPrefWidth(240);

        TableColumn<Aluno, String> ingressoColumn = new TableColumn<Aluno, String>("Ingresso");
        ingressoColumn.setCellValueFactory(new PropertyValueFactory<Aluno, String>("ingresso"));

        TableColumn<Aluno, String> formaturaColumn = new TableColumn<Aluno, String>("Formatura");
        formaturaColumn.setCellValueFactory(new PropertyValueFactory<Aluno, String>("formatura"));

        tableAlunos.getColumns().add(nomeColumn);
        tableAlunos.getColumns().add(matriculaColumn);
        tableAlunos.getColumns().add(cursoColumn);
        tableAlunos.getColumns().add(emailColumn);
        tableAlunos.getColumns().add(horasHomologadasColumn);
        tableAlunos.getColumns().add(ingressoColumn);
        tableAlunos.getColumns().add(formaturaColumn);

        alunosComAtividades.forEach(aluno -> {
            tableAlunos.getItems().add(aluno);
        }
        );

    }











    private void loadAlunosComAtividades(){
        Globals.alunos.forEach(aluno -> {
            for(RegistroAtividade registro : Globals.registros){
                if(registro.getMatricula().equals(aluno.getMatricula())){
                    alunosComAtividades.add(aluno);
                    break;
                }
            }
        });
    }

}
