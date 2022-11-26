package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AutorController implements Initializable {
    
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtSobrenome;

    @FXML
    private TextField txtNacionalidade;

    @FXML
    private ListView<Autor> lstAutores;

    @FXML
    private Button BtnIncluir;
  
    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar; 

    private DaoAutor dao = new DaoAutor();
    private Autor autor;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        Autor autor = new Autor(txtNome.getText(),
        txtSobrenome.getText(),
        txtNacionalidade.getText());
    
        dao.inserir(autor);
        
    }
    
    @FXML
    private void lstAutores_KeyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML
    private void lstAutores_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados(){
        this.autor = lstAutores.getSelectionModel().getSelectedItem();
        
        if(autor==null) return;

        txtNome.setText(autor.getNome());
        txtSobrenome.setText(autor.getSobrenome());
        txtNacionalidade.setText(autor.getNacionalidade());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Autor> autores = dao.buscarTodos();
        
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        lstAutores.setItems(data);
    }    
}
