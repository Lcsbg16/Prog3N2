package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaInicialController implements Initializable {
    
 
    @FXML
    private void BtnBibliotecario_Click(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TelaBibliotecario.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Tela Inicial");
        stage.setScene(scene);
        stage.show();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @FXML
    private void BtnAtendente_Click(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
    }    
}
