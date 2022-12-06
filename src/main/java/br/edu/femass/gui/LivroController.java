package br.edu.femass.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LivroController implements Initializable {
    
    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAno;

   @FXML
    private ListView<Livro> lstLivro;

    @FXML
    private ListView<Autor> lstAutor;

    @FXML
    private TableView<Livro> tabelaLivro = new TableView<Livro>();

    @FXML
    private TableColumn<Livro,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Livro,String> colTitulo = new TableColumn<>();

    @FXML
    private TableColumn<Livro,String> colAno = new TableColumn<>();

    @FXML
    private TableColumn<Autor,String> colAutor = new TableColumn<>();

    @FXML
    private Button BtnIncluir;
  
    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar; 

    @FXML
    private ComboBox<Autor> cboxAutor;

    private DaoLivro dao = new DaoLivro();
    private DaoAutor daoAutor = new DaoAutor();
    private Livro livro;
    private Autor autor;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        livro.setTitulo(txtTitulo.getText());
        livro.setAutor(cboxAutor.getSelectionModel().getSelectedItem());
        livro.setAno(txtAno.getText());
        if (incluindo) {
            dao.inserir(livro);
        } else {
            dao.alterar(livro);
        }
        preencherLista();
        preencherTabela();
        editar(false);     
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        preencherCombo();

        incluindo = true;

        livro = new Livro();
       // autor = new Autor();
        txtTitulo.setText("");
        txtAno.setText("");
        txtTitulo.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        preencherCombo();
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(livro);
        preencherLista();
        preencherTabela();
    }
    
    private void editar(boolean habilitar) {
        lstLivro.setDisable(habilitar);
        txtTitulo.setDisable(!habilitar);
        txtAno.setDisable(!habilitar);
        cboxAutor.setDisable(!habilitar);
        tabelaLivro.setDisable(habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

  
    @FXML
    private void lstLivro_KeyPressed(KeyEvent event) {
        exibirDadosLista();
    }

    @FXML
    private void lstLivro_MouseClicked(MouseEvent event) {
        exibirDadosLista();
    } 


    @FXML
    private void tabelaLivro_KeyPressed(KeyEvent event) {
        exibirDadosTabela();
    }

    @FXML
    private void tabelaLivro_MouseClicked(MouseEvent event) {
        exibirDadosTabela();
    }

    private void exibirDadosTabela(){
        this.livro = tabelaLivro.getSelectionModel().getSelectedItem();
        
        if(livro==null) return;

        txtTitulo.setText(livro.getTitulo());
        txtAno.setText(livro.getAno());
        //cboxAutor.setText(livro.getAutor());
    }

     private void exibirDadosLista(){
        this.livro = lstLivro.getSelectionModel().getSelectedItem();
        
        if(livro==null) return;

        txtTitulo.setText(livro.getTitulo());
        txtAno.setText(livro.getAno());
        //cboxAutor.setText(livro.getAutor());
    }  

    private void preencherCombo(){
        List<Autor> autores = daoAutor.buscarTodos();
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        cboxAutor.setItems(data);   
    }

    private void preencherTabela(){
        List<Livro> livros = dao.buscarTodos();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        tabelaLivro.setItems(data); 
        tabelaLivro.refresh();  
    }

    private void preencherLista(){
        List<Livro> livros = dao.buscarTodos();
        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        lstLivro.setItems(data); 
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
            new PropertyValueFactory<Livro,Long>("id")
        );
        
        colTitulo.setCellValueFactory(
            new PropertyValueFactory<Livro,String>("titulo")
        );

        colAno.setCellValueFactory(
            new PropertyValueFactory<Livro,String>("ano")
        );

        colAutor.setCellValueFactory(
            new PropertyValueFactory<Autor,String>("autor")
        );

        preencherTabela();
        preencherLista();
    }    
}
