package com.modulo_aluno;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class PageAlunoController implements Initializable{

    private static Aluno alunoAtual;
    private static ArrayList<RegistroAtividade> atividadesDoAluno = new ArrayList<RegistroAtividade>();


    @FXML
    private Label TextMatricula;

    @FXML
    private Label textEmail;

    @FXML
    private Label textFormatura;

    @FXML
    private TableView<RegistroAtividade> tableAtividades;

    @FXML
    private ScrollPane scroller;
    
    
    @FXML
    private Button cadastrarAtividadeButton;

    @FXML
    private Button nextAlunoButton;

    @FXML
    private Button prevAlunoButton;

    @FXML
    private Label textCurso;

    @FXML
    private Label textIngresso;

    @FXML
    private Label textNome;

    @FXML
    private Button returnButton;

    @FXML
    private ListView<Object> listaAtividadesComplementares;


    @FXML
    void goToHomePage(ActionEvent event) throws IOException {
        App.setRoot("/layouts/home_screen.fxml");
        Globals.contAluno = 0;

        
    }

    @FXML
    void goToCadastrarAtividade(ActionEvent event) throws IOException {
        if(!Globals.alunos.isEmpty()){
            App.setRoot("/layouts/cadastrar_atividade.fxml");
        }
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
        tableAtividades.getColumns().clear();
        tableAtividades.getItems().clear();
        atividadesDoAluno.clear();
        if(Globals.alunos.isEmpty()){
            textNome.setText("Não há alunos");
            TextMatricula.setText("Não há alunos");
            textEmail.setText("Não há alunos");
            textFormatura.setText("Não há alunos");
            textIngresso.setText("Não há alunos");
            textCurso.setText("Não há alunos");
            return;
        }
        alunoAtual = Globals.alunos.get(Globals.contAluno);
        if(Globals.contAluno == Globals.alunos.size()-1){
            nextAlunoButton.setDisable(true);
        } else {
            nextAlunoButton.setDisable(false);
        }
        if(Globals.contAluno == 0){
            prevAlunoButton.setDisable(true);
        } else {
            prevAlunoButton.setDisable(false);
        }
        textNome.setText(alunoAtual.getNome());
        TextMatricula.setText(alunoAtual.getMatricula());
        textEmail.setText(alunoAtual.getEmail());
        textFormatura.setText(alunoAtual.getFormatura());
        textIngresso.setText(alunoAtual.getIngresso());
        textCurso.setText(alunoAtual.getCurso());
        for(RegistroAtividade registro : Globals.registros){
            if(registro.getMatricula().equals(alunoAtual.getMatricula())){
                atividadesDoAluno.add(registro);
            }
        }
        generateTable();

    }



    
    
    
    private void generateTable(){
        //Gera a tabela com as atividades.
        tableAtividades.setEditable(false);
        // tableAtividades.setDisable(false);
        TableColumn<RegistroAtividade, String> nomeAtividadeColumn = new TableColumn<RegistroAtividade, String>("Nome atividade");
        nomeAtividadeColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("nomeAtividade"));
        nomeAtividadeColumn.setPrefWidth(300);
        
        
        TableColumn<RegistroAtividade, String> unidadeAtividadeColumn = new TableColumn<RegistroAtividade, String>("Unidade");
        unidadeAtividadeColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("unidade"));
        unidadeAtividadeColumn.setPrefWidth(100);

        TableColumn<RegistroAtividade, Integer> horasAtividadeColumn = new TableColumn<RegistroAtividade, Integer> ("Horas/atividade");
        horasAtividadeColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, Integer>("horasAtividade"));
        horasAtividadeColumn.setPrefWidth(100);

        TableColumn<RegistroAtividade, Integer> horasMaximoColumn = new TableColumn<RegistroAtividade, Integer> ("Horas máximas");
        horasMaximoColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, Integer>("maximoHoras"));
        horasMaximoColumn.setPrefWidth(100);

        TableColumn<RegistroAtividade, String> horasCumpridasColumn = new TableColumn<RegistroAtividade, String> ("Horas cumpridas");
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
        homologadoColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, Boolean>("homologado"));
        homologadoColumn.setCellFactory(CheckBoxTableCell.forTableColumn(homologadoColumn));
        
        homologadoColumn.setPrefWidth(80);




        TableColumn<RegistroAtividade, String> justificativaColumn = new TableColumn<RegistroAtividade, String>("Justificativa");
        justificativaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        justificativaColumn.setCellValueFactory(new PropertyValueFactory<RegistroAtividade, String>("justificativa"));
        justificativaColumn.setPrefWidth(400);




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

}
