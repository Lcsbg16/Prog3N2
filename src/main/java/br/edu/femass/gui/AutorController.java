package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    private TableView<Autor> tabelaAutor = new TableView<Autor>();

    @FXML
    private TableColumn<Autor,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colSobrenome = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colNacionalidade = new TableColumn<>();

    private DaoAutor dao = new DaoAutor();
    private Autor autor;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        autor.setNome(txtNome.getText());
        autor.setSobrenome(txtSobrenome.getText());
        autor.setNacionalidade(txtNacionalidade.getText());
        if (incluindo) {
            dao.inserir(autor);
        } else {
            dao.alterar(autor);
        }

        preencherLista();
        preencherTabela();
        editar(false);
        
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        
        incluindo = true;
        autor = new Autor();
        txtNome.setText("");
        txtSobrenome.setText("");
        txtNacionalidade.setText("");
        txtNome.requestFocus();


    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(autor);
        preencherLista();
        preencherTabela();
    }
    
    private void editar(boolean habilitar) {
        lstAutores.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtSobrenome.setDisable(!habilitar);
        txtNacionalidade.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
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

    private void preencherLista(){
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        lstAutores.setItems(data);   
    }

    private void preencherTabela(){
        List<Autor> autores = dao.buscarTodos();

        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        tabelaAutor.setItems(data);   
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
            new PropertyValueFactory<Autor,Long>("id")
        );

        colNome.setCellValueFactory(
            new PropertyValueFactory<Autor,String>("nome")
        );
        
        colSobrenome.setCellValueFactory(
            new PropertyValueFactory<Autor,String>("sobrenome")
        );

        colNacionalidade.setCellValueFactory(
            new PropertyValueFactory<Autor,String>("nacionalidade")
        );

        preencherLista();
        preencherTabela();
    }    
}
