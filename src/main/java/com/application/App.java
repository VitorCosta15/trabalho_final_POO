package com.application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import com.models.Aluno;
import com.models.Atividade;
import com.models.Professor;
import com.models.RegistroAtividade;


/**
 * JavaFX App
 */

public class App extends Application {
    static Scene scene;
    
    //Usado somente para invocar o link do certificado.
    private static App urlLauncher = new App();
    
    public static App getUrlLauncher () {
        return urlLauncher;
    }

    @Override
    public void start(Stage stage) throws IOException {
        loadData();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layouts/home_screen.fxml"));
        stage.setHeight(700);
        stage.setWidth(1300);
        stage.setResizable(false);
        scene = new Scene(loader.load());
        stage.setTitle("Cobalto paraguayo");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

    public static void setRoot(String fxml) throws IOException{
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml));
        scene.setRoot(loader.load());
    }


    public static void loadData() throws IOException{
        InputStream fileAtividadesCiencia = new FileInputStream("src/main/resources/data/atividades_ciencia.json");
        InputStream fileAtividadesEngenharia = new FileInputStream("src/main/resources/data/atividades_ciencia.json");
        InputStream fileAlunos = new FileInputStream("src/main/resources/data/alunos.json");
        InputStream fileRegistros = new FileInputStream("src/main/resources/data/registros.json");
        InputStream fileProfessor = new FileInputStream("src/main/resources/data/professor.json");
        JsonReader readerAtividadesCiencia = Json.createReader(fileAtividadesCiencia);
        JsonReader readerAtividadesEngenharia = Json.createReader(fileAtividadesEngenharia);
        JsonReader readerAlunos = Json.createReader(fileAlunos);
        JsonReader readerProfessor = Json.createReader(fileProfessor);
        JsonReader readerRegistros = Json.createReader(fileRegistros);
        JsonObject jsonObjectAtividadesCiencia = readerAtividadesCiencia.readObject();
        JsonObject jsonObjectAtividadesEngenharia = readerAtividadesEngenharia.readObject();
        JsonObject jsonObjectProfessor = readerProfessor.readObject();
        JsonObject jsonObjectAlunos = readerAlunos.readObject();
        JsonObject jsonObjectRegistros = readerRegistros.readObject();
        JsonArray listRegistros = jsonObjectRegistros.getJsonArray("registros");
        JsonArray listAtividadesCiencia = jsonObjectAtividadesCiencia.getJsonArray("atividades");
        JsonArray listAtividadesEngenharia = jsonObjectAtividadesEngenharia.getJsonArray("atividades");
        JsonArray listAlunos = jsonObjectAlunos.getJsonArray("alunos");
        
        for(JsonValue i : listAlunos){
            Globals.alunos.add(Aluno.fromJson(i.asJsonObject()));
        }

        for(JsonValue i : listAtividadesCiencia){
            Globals.atividadesCiencia.add(Atividade.fromJson(i.asJsonObject()));
        }

        for(JsonValue i : listAtividadesEngenharia){
            Globals.atividadesEngenharia.add(Atividade.fromJson(i.asJsonObject()));
        }

        for(JsonValue i : listRegistros){
            Globals.registros.add(RegistroAtividade.fromJson(i.asJsonObject()));
        }
        
        
        
        Globals.professor = Professor.fromJson(jsonObjectProfessor);
    }

}