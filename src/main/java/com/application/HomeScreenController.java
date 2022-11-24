package com.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class HomeScreenController implements Initializable {

    

    @FXML
    private Button moduloAlunoButton;

    @FXML
    private Button moduloProfessorButton;

    @FXML
    void goToModuloAluno(ActionEvent event) throws IOException {
        App.setRoot("/layouts/modulo_aluno.fxml");
    }

    @FXML
    void goToModuloProfessor(ActionEvent event) throws IOException{
        App.setRoot("/layouts/modulo_professor.fxml");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    }

}