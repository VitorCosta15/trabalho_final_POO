package com.modulo_professor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import com.application.App;
import com.application.Globals;
import com.models.Aluno;
import com.models.RegistroAtividade;
import com.modulo_aluno.HyperlinkCell;

import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Window;

public class ContabilizacaoHorasController implements Initializable{

    private static Aluno alunoAtual;
    private static ObservableList<RegistroAtividade> atividadesDoAluno = FXCollections.observableArrayList();
    static private ArrayList<Aluno> alunosComAtividades = new ArrayList<Aluno>();
    private int aux; //serve para pegar o old value do text field das horas cumpridas.

    @FXML
    private Button nextAlunoButton1;

    @FXML
    private Label nomeProfessorText;

    @FXML
    private Button prevAlunoButton1;

    @FXML
    private Label horasHomologadasText;


    @FXML
    private Button returnButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label siapeText;

    @FXML
    private TableView<RegistroAtividade> tableAtividades;

    @FXML
    void goToModuloProfessorPage(ActionEvent event) throws IOException{
        App.setRoot("/layouts/modulo_professor.fxml");
        Globals.contAluno = 0;
    }

    @FXML
    void goToNextAluno(ActionEvent event) throws IOException {
        Globals.contAluno++;
        initialize(null, null);
    }

    @FXML
    void goToPrevAluno(ActionEvent event) {
        Globals.contAluno--;
        initialize(null, null);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nomeProfessorText.setText(Globals.professor.getNome());
        siapeText.setText(Globals.professor.getSiape());
        atividadesDoAluno.clear();
        alunosComAtividades.clear();
        tableAtividades.getColumns().clear();
        tableAtividades.getItems().clear();
        loadAlunosComAtividades();
        if(alunosComAtividades.isEmpty()) return;        
        alunoAtual = alunosComAtividades.get(Globals.contAluno);
        aux = alunoAtual.getHorasHomologadas();
        horasHomologadasText.setText("Total de horas homologadas de " + alunoAtual.getNome() + " : " + String.valueOf(alunoAtual.getHorasHomologadas()));
        if(Globals.contAluno == alunosComAtividades.size()-1){
            nextAlunoButton1.setDisable(true);
        } else {
            nextAlunoButton1.setDisable(false);
        }
        if(Globals.contAluno == 0){
            prevAlunoButton1.setDisable(true);
        } else {
            prevAlunoButton1.setDisable(false);
        }
        
        loadAtividadesDoAluno();
        generateTable();
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
    
    private void loadAtividadesDoAluno(){
        for(RegistroAtividade registro : Globals.registros){
            if(registro.getMatricula().equals(alunoAtual.getMatricula())){
                atividadesDoAluno.add(registro);
            }
        }
    }



    private void generateTable(){
        tableAtividades.setEditable(true);
        

        TableColumn<RegistroAtividade, String> nomeAlunoColumn = new TableColumn<RegistroAtividade, String>("Nome Aluno");
        nomeAlunoColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("nomeAluno"));
        nomeAlunoColumn.setPrefWidth(150);

        TableColumn<RegistroAtividade, String> matriculaColumn = new TableColumn<RegistroAtividade, String>("Matr??cula");
        matriculaColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("matricula"));
        matriculaColumn.setPrefWidth(100);

        TableColumn<RegistroAtividade, String> nomeAtividadeColumn = new TableColumn<RegistroAtividade, String>("Nome atividade");
        nomeAtividadeColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("nomeAtividade"));
        nomeAtividadeColumn.setPrefWidth(300);
        
        
        TableColumn<RegistroAtividade, String> unidadeAtividadeColumn = new TableColumn<RegistroAtividade, String>("Unidade");
        unidadeAtividadeColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("unidade"));
        unidadeAtividadeColumn.setPrefWidth(100);

        TableColumn<RegistroAtividade, Integer> horasAtividadeColumn = new TableColumn<RegistroAtividade, Integer> ("Horas/atividade");
        horasAtividadeColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, Integer>("horasAtividade"));
        horasAtividadeColumn.setPrefWidth(100);

        TableColumn<RegistroAtividade, Integer> horasMaximoColumn = new TableColumn<RegistroAtividade, Integer> ("Horas m??ximas");
        horasMaximoColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, Integer>("maximoHoras"));
        horasMaximoColumn.setPrefWidth(100);

        TableColumn<RegistroAtividade, String> horasCumpridasColumn = new TableColumn<RegistroAtividade, String> ("Horas cumpridas");
        horasCumpridasColumn.setOnEditStart(e -> 
            aux = Integer.parseInt(e.getOldValue())
        );
        horasCumpridasColumn.setOnEditCommit(e -> {
            try{           
                if(Integer.parseInt(e.getNewValue()) > e.getRowValue().getHorasAtividade()){
                    Dialog<String>dialog = new Dialog<String>();
                    dialog.setHeaderText("Horas cumpridas excedem o m??ximo por atividade");
                    dialog.show();
                    Window    window = dialog.getDialogPane().getScene().getWindow();
                    window.setOnCloseRequest(eventwindow -> window.hide());
                    return;
                    }
                } catch(Exception exc){
                    Dialog<String>dialog = new Dialog<String>();
                    dialog.setHeaderText("Horas cumpridas inv??lidas");
                    dialog.show();
                    Window    window = dialog.getDialogPane().getScene().getWindow();
                    window.setOnCloseRequest(eventwindow -> window.hide());
                    return;
                }
                e.getRowValue().setHorasCumpridas(Integer.parseInt(e.getNewValue()));
                if(e.getRowValue().isHomologado()){ //NEcess??rio para fazer um controle em tempo real do total de horas do aluno.
                    alunoAtual.setHorasHomologadas(alunoAtual.getHorasHomologadas() - aux + Integer.parseInt(e.getNewValue()));
                    horasHomologadasText.setText("Total de horas homologadas de " + alunoAtual.getNome() + " : " + String.valueOf(alunoAtual.getHorasHomologadas()));
                }
            });
        horasCumpridasColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("horasCumpridas"));
        horasCumpridasColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        horasCumpridasColumn.setPrefWidth(100);

        TableColumn<RegistroAtividade, String> dataColumn = new TableColumn<RegistroAtividade, String>("Data");
        dataColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("data"));
        dataColumn.setPrefWidth(70);

        TableColumn<RegistroAtividade, String> horaColumn = new TableColumn<RegistroAtividade, String>("Hora");
        horaColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("hora"));
        horaColumn.setPrefWidth(70);

        TableColumn<RegistroAtividade, String> linkColumn = new TableColumn<RegistroAtividade, String>("Link Certificado");
        linkColumn.setCellFactory(new HyperlinkCell());
        linkColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("linkCertificado"));
        linkColumn.setPrefWidth(250);

        TableColumn<RegistroAtividade, Boolean> homologadoColumn = new TableColumn<RegistroAtividade, Boolean>("Homologado");
        homologadoColumn.setCellValueFactory(value -> {
            RegistroAtividade cellData = value.getValue();
            BooleanProperty property = cellData.homologadoProperty();
            property.addListener((observable, oldValue, newVAlue) -> {
                cellData.setHomologado(newVAlue);
                if(newVAlue){
                    alunoAtual.setHorasHomologadas(alunoAtual.getHorasHomologadas() + cellData.getHorasCumpridas());
                    horasHomologadasText.setText("Total de horas homologadas de " + alunoAtual.getNome() + " : " + String.valueOf(alunoAtual.getHorasHomologadas()));
                } else {
                    alunoAtual.setHorasHomologadas(alunoAtual.getHorasHomologadas() - cellData.getHorasCumpridas());
                    horasHomologadasText.setText("Total de horas homologadas de " + alunoAtual.getNome() + " : " + String.valueOf(alunoAtual.getHorasHomologadas()));
                }
            });
            return property;
        });
        // homologadoColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, Boolean>("homologado"));
        homologadoColumn.setOnEditStart(e -> System.out.println(e.getNewValue()));
        homologadoColumn.setOnEditCommit(e -> System.out.println(e.getNewValue()));
        homologadoColumn.setOnEditCancel(e -> System.out.println(e.getOldValue()));
        homologadoColumn.setCellFactory(CheckBoxTableCell.forTableColumn(homologadoColumn));
        homologadoColumn.setPrefWidth(80);


        TableColumn<RegistroAtividade, String> justificativaColumn = new TableColumn<RegistroAtividade, String>("Justificativa");
        justificativaColumn.setOnEditCommit(e -> {
            e.getRowValue().setJustificativa(e.getNewValue());
        });
        justificativaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        justificativaColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("justificativa"));
        justificativaColumn.setPrefWidth(400);




        tableAtividades.getColumns().add(nomeAlunoColumn);
        tableAtividades.getColumns().add(matriculaColumn);
        tableAtividades.getColumns().add(nomeAtividadeColumn);
        tableAtividades.getColumns().add(unidadeAtividadeColumn);
        tableAtividades.getColumns().add(horasAtividadeColumn);
        tableAtividades.getColumns().add(horasMaximoColumn);
        tableAtividades.getColumns().add(horasCumpridasColumn);
        tableAtividades.getColumns().add(dataColumn);
        tableAtividades.getColumns().add(horaColumn);
        tableAtividades.getColumns().add(linkColumn);
        tableAtividades.getColumns().add(homologadoColumn);
        tableAtividades.getColumns().add(justificativaColumn);

        

        
        atividadesDoAluno.forEach(e -> {
            tableAtividades.getItems().add(e);
        });



    }


    @FXML
    void save(ActionEvent event) throws FileNotFoundException {
        //Crio uma nova lista de alunos contando as horas homologadas atualizadas dos alunos
        JsonArrayBuilder novaListaDeAlunos = Json.createArrayBuilder();
        Globals.alunos.forEach(element -> novaListaDeAlunos.add(Aluno.toJson(element)));
        JsonObject novoObjetodeAlunos = Json.createObjectBuilder().add("alunos", novaListaDeAlunos).build();
        OutputStream outputStream = new FileOutputStream("src/main/resources/data/alunos.json");
        JsonWriter writerr = Json.createWriter(outputStream);
        writerr.writeObject(novoObjetodeAlunos);
        
        
        
        
        //devido aos StringProperties, ao atualizar uma vari??vel na tabela seu valor j?? ?? setado em todas as instancias.
        //logo so preciso passar a lista atualizada de registros para o json.

        JsonArrayBuilder novaListaDeRegistros =  Json.createArrayBuilder();
        Globals.registros.forEach(element -> novaListaDeRegistros.add(RegistroAtividade.toJson(element)));
        JsonObject novoObjetoDeRegistros = Json.createObjectBuilder().add("registros", novaListaDeRegistros).build();
        OutputStream output = new FileOutputStream("src/main/resources/data/registros.json");
        JsonWriter writer = Json.createWriter(output);
        writer.writeObject(novoObjetoDeRegistros);
    }

}
