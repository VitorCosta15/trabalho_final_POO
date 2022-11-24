package com.modulo_aluno;

import com.application.App;
import com.models.RegistroAtividade;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class HyperlinkCell implements Callback<TableColumn<RegistroAtividade,String>, TableCell<RegistroAtividade, String>>{

    @Override
    public TableCell<RegistroAtividade, String> call(TableColumn<RegistroAtividade, String> param) {
        TableCell<RegistroAtividade, String> cell = new TableCell<RegistroAtividade, String>() {
            private final Hyperlink hyperlink = new Hyperlink();
            {
                hyperlink.setOnAction(e -> {
                    App.getUrlLauncher().getHostServices().showDocument(getItem());
                }); //Funcionalidadde para abrir site ao clicar.
            }
            
            @Override
            protected void updateItem(String url, boolean empty){
                super.updateItem(url, empty);
                if(empty){
                    setGraphic(null);
                } else {
                    hyperlink.setText(url);
                    setGraphic(hyperlink);
                }

            }
        };
        return cell;
    }
    
}
