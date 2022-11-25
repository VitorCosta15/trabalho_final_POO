package com.modulo_professor;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import com.application.App;
import com.application.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ModuloProfessorController implements Initializable{

    @FXML
    private Button contabilizacaoButton;

    @FXML
    private Button listagemButton;

    @FXML
    private Label nomeProfessorText;

    @FXML
    private Button returnButton;

    @FXML
    private Label saudacaoText;

    @FXML
    private Label siapeText;

    @FXML
    void goToContabilizacaoPage(ActionEvent event) throws IOException{
        App.setRoot("/layouts/contabilizacao_horas.fxml");
    }

    @FXML
    void goToListagemPage(ActionEvent event) throws IOException{
        App.setRoot("/layouts/listagem_alunos.fxml");
    }

    @FXML
    void goToHomePage(ActionEvent event) throws IOException {
        App.setRoot("/layouts/home_screen.fxml");
    }

   

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        nomeProfessorText.setText(Globals.professor.getNome());
        siapeText.setText(Globals.professor.getSiape());
        saudacaoText.setText(saudacao());
        contabilizacaoButton.setText("Contabilização de\natividades");
        
    }
    


    private String saudacao(){
        LocalTime now = LocalTime.now();
        if(now.isBefore(LocalTime.NOON) && now.isAfter(LocalTime.of(6, 0))){
            return "Bom dia professor(a) " + Globals.professor.getNome();
        }
        if(now.isAfter(LocalTime.NOON) && now.isBefore(LocalTime.of(19, 0))){
            return "Boa tarde professor(a) " + Globals.professor.getNome();
        }
        return "Boa noite professor(a) " + Globals.professor.getNome();

    }

}
