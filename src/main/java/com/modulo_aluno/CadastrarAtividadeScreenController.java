package com.modulo_aluno;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import com.application.App;
import com.application.Globals;
import com.models.Aluno;
import com.models.Atividade;
import com.models.RegistroAtividade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class CadastrarAtividadeScreenController implements Initializable{
    // public static boolean isSelected = false;
    private static int indexDaAtividadeSelecionada = -1;
    @FXML
    private Label TextMatricula;

    @FXML
    private Label labelUnidade;

    @FXML
    private Button returnButton;
    
    @FXML
    private Label horasHomologadasText;
    
    @FXML
    private Label horasAtividadeText;

    @FXML
    private Label maximoHorasText;
    
    @FXML
    private Button saveButton;

    @FXML
    private Label textCurso;

    @FXML
    private Label textEmail;

    @FXML
    private TextField textFieldComprovante;

    @FXML
    private TextField textFieldUnidade;

    @FXML
    private Label textFormatura;

    @FXML
    private Label textIngresso;

    @FXML
    private Label textNome;

    @FXML
    private ListView<CheckBox> listTipoAtividade;

    @FXML
    void goToAlunoPage(ActionEvent event) throws Exception {
        App.setRoot("/layouts/modulo_aluno.fxml");
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        textNome.setText(Globals.alunos.get(Globals.contAluno).getNome());
        TextMatricula.setText(Globals.alunos.get(Globals.contAluno).getMatricula());
        textEmail.setText(Globals.alunos.get(Globals.contAluno).getEmail());
        textFormatura.setText(Globals.alunos.get(Globals.contAluno).getFormatura());
        textIngresso.setText(Globals.alunos.get(Globals.contAluno).getIngresso());
        textCurso.setText(Globals.alunos.get(Globals.contAluno).getCurso());
        horasHomologadasText.setText("Total de horas homologadas: " + String.valueOf(Globals.alunos.get(Globals.contAluno).getHorasHomologadas()));
        if(Globals.alunos.get(Globals.contAluno).getCurso().equals("Engenharia de computação")){
            for(Atividade i : Globals.atividadesEngenharia){
                CheckBox checkBox = new CheckBox();
                checkBox.setText(i.getNome());
                checkBox.setOnAction(event-> { //Digo oq cada checkBox irá fazer quando eu clicar nele.
                    indexDaAtividadeSelecionada = listTipoAtividade.getItems().indexOf(event.getSource()); //Aqui eu peguei o indice do checkbox que foi ativado, checkbox que ocorreu o event.
                    for(int j = 0; j < listTipoAtividade.getItems().size(); j++){
                        if(indexDaAtividadeSelecionada != j){ //Se os checkboxes forem diferentes do checkbox que foi ativado por último, eu manualmente devo desativa-los.
                            listTipoAtividade.getItems().get(j).setSelected(false);
                        }
                    }
                    horasAtividadeText.setText(Integer.toString(Globals.atividadesEngenharia.get(indexDaAtividadeSelecionada).getHorasAtividade()));
                    maximoHorasText.setText(Integer.toString(Globals.atividadesEngenharia.get(indexDaAtividadeSelecionada).getMaximoAtividade()));
                }); 
                listTipoAtividade.getItems().add(checkBox);
            }
        } else { //se ele n for engenharia vai ser ciencia
            for(Atividade i : Globals.atividadesCiencia){
                CheckBox checkBox = new CheckBox();
                checkBox.setText(i.getNome());
                checkBox.setOnAction(event-> { //Digo oq cada checkBox irá fazer quando eu clicar nele.
                    indexDaAtividadeSelecionada = listTipoAtividade.getItems().indexOf(event.getSource()); //Aqui eu peguei o indice do checkbox que foi ativado, checkbox que ocorreu o event.
                    for(int j = 0; j < listTipoAtividade.getItems().size(); j++){
                        if(indexDaAtividadeSelecionada != j){ //Se os checkboxes forem diferentes do checkbox que foi ativado por último, eu manualmente devo desativa-los.
                            listTipoAtividade.getItems().get(j).setSelected(false);
                        }
                    }
                    horasAtividadeText.setText(Integer.toString(Globals.atividadesCiencia.get(indexDaAtividadeSelecionada).getHorasAtividade()));
                    maximoHorasText.setText(Integer.toString(Globals.atividadesCiencia.get(indexDaAtividadeSelecionada).getMaximoAtividade()));
                });
                listTipoAtividade.getItems().add(checkBox);
                
            }
        }
        
    }

    @FXML
    void saveAtividade(ActionEvent event) throws IOException{
        
        //Caso não haja nenhuma checkbox selecionada.
        if(CadastrarAtividadeScreenController.indexDaAtividadeSelecionada == -1){
            Dialog<String>dialog = new Dialog<String>();
            dialog.setHeaderText("Por favor selecione uma atividade");
            dialog.show();
            Window    window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(eventwindow -> window.hide());
            return;
        } 
        
        
        //Caso usuário ponha um valor não numérico.
        try {

        //Caso usuário ponha um valor maior que o maximo por atividade nas horas cumpridas.
            if(Integer.parseInt(textFieldUnidade.getText()) > Integer.parseInt(horasAtividadeText.getText())){
                Dialog<String>dialog = new Dialog<String>();
                dialog.setHeaderText("Horas cumpridas excedem o máximo por atividade");
                dialog.show();
                Window    window = dialog.getDialogPane().getScene().getWindow();
                window.setOnCloseRequest(eventwindow -> window.hide());
                return;
            }


            if(textFieldComprovante.getText().length() < 10){
                Dialog<String>dialog = new Dialog<String>();
                dialog.setHeaderText("Link inválido");
                dialog.show();
                Window    window = dialog.getDialogPane().getScene().getWindow();
                window.setOnCloseRequest(eventwindow -> window.hide());
                return;
            } 
        } catch (Exception e) {
            Dialog<String>dialog = new Dialog<String>();
            dialog.setHeaderText("Horas cumpridas inválidas");
            dialog.show();
            Window    window = dialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(eventwindow -> window.hide());
            return;
        }



        final Aluno aluno =  Globals.alunos.get(Globals.contAluno);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String formattedDate = formatDate(date);
        String formattedTime = formatTime(time);
        RegistroAtividade atividade;
        if(Globals.alunos.get(Globals.contAluno).getCurso().equals("Engenharia de computação")){
            final Atividade atividadeEngenharia = Globals.atividadesEngenharia.get(CadastrarAtividadeScreenController.indexDaAtividadeSelecionada);
            
            atividade = new RegistroAtividade(
                aluno.getNome(),
                atividadeEngenharia.getNome(),
                aluno.getMatricula(),
                aluno.getEmail(),
                aluno.getIngresso(),
                aluno.getFormatura(),
                aluno.getCurso(),
                atividadeEngenharia.getUnidade(),
                atividadeEngenharia.getHorasAtividade(),
                atividadeEngenharia.getMaximoAtividade(),
                textFieldComprovante.getText(),
                Integer.parseInt(textFieldUnidade.getText()),
                false,
                formattedDate,
                formattedTime,
                ""
            );
        } else {
            final Atividade atividadeCiencia = Globals.atividadesCiencia.get(CadastrarAtividadeScreenController.indexDaAtividadeSelecionada);
            
            atividade = new RegistroAtividade(
                aluno.getNome(),
                atividadeCiencia.getNome(),
                aluno.getMatricula(),
                aluno.getEmail(),
                aluno.getIngresso(),
                aluno.getFormatura(),
                aluno.getCurso(),
                atividadeCiencia.getUnidade(),
                atividadeCiencia.getHorasAtividade(),
                atividadeCiencia.getMaximoAtividade(),
                textFieldComprovante.getText(),
                Integer.parseInt(textFieldUnidade.getText()),
                false,
                formattedDate,
                formattedTime,
                ""
            ); 
        }

        salvarNoJson(atividade);
        Globals.registros.add(atividade);
        
        
        
        App.setRoot("/layouts/modulo_aluno.fxml");
        
    }


    
    
    
    
    
    
    public static void salvarNoJson(RegistroAtividade atividade) throws FileNotFoundException{
        JsonArrayBuilder novaListadeRegistros = Json.createArrayBuilder();
        
        //Pega o que já tem carregad em memória e remonta alista que será persistida no json.
        Globals.registros.forEach(element -> novaListadeRegistros.add(RegistroAtividade.toJson(element)));
        novaListadeRegistros.add(RegistroAtividade.toJson(atividade));
        
        //Remonta o object encabeçando a chave "registros".
        JsonObject novoObjetoDeRegistros = Json.createObjectBuilder().add("registros", novaListadeRegistros).build();
        
        //Faz a sobreescrição.
        OutputStream output = new FileOutputStream("src/main/resources/data/registros.json");
        JsonWriter writer = Json.createWriter(output);
        writer.writeObject(novoObjetoDeRegistros);
    }
    
    
    
    public static String formatDate(LocalDate date){
        String[] split = date.toString().split("-");
        return split[2]+"/"+split[1]+"/"+split[0];
    }
    
    public static String formatTime(LocalTime time){
        String[] split = time.toString().split(":");
        return split[0] + ":"+ split[1];
    }
    

}

