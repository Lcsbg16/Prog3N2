package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.bytebuddy.asm.Advice.Local;


public class ExemplarController implements Initializable {
    @FXML
    private TableView<Exemplar> tabelaExemplar = new TableView<Exemplar>(); 

    @FXML
    private TableColumn<Exemplar,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Livro,String> colLivro = new TableColumn<>();

    @FXML
    private TableColumn<Exemplar,LocalDate> colData = new TableColumn<>();

    @FXML
    private Button BtnIncluir;
  
    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar; 

    @FXML
    private ComboBox<Livro> cboxLivro;

    private DaoExemplar dao = new DaoExemplar();
    private DaoLivro daoLivro = new DaoLivro();
    
    private Livro livro;
    private Exemplar exemplar;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        exemplar.setLivro(cboxLivro.getSelectionModel().getSelectedItem());
        if (incluindo) {
            dao.inserir(exemplar);
        } else {
            dao.alterar(exemplar);
        }

        preencherTabela();
        editar(false);
        
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        preencherCombo();
        preencherTabela();
        incluindo = true;

        exemplar = new Exemplar();
        cboxLivro.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        preencherCombo();
        preencherTabela();
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(exemplar);

        preencherTabela();
    }
    
    private void editar(boolean habilitar) {
        cboxLivro.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    private void preencherTabela(){
        List<Exemplar> exemplares = dao.buscarTodos();
        ObservableList<Exemplar> data = FXCollections.observableArrayList(exemplares);
        tabelaExemplar.setItems(data);
        tabelaExemplar.refresh();   
    }

    private void preencherCombo(){
        List<Livro> livros = daoLivro.buscarTodos();

        ObservableList<Livro> data = FXCollections.observableArrayList(livros);
        cboxLivro.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
            new PropertyValueFactory<Exemplar,Long>("id")
        );

        colLivro.setCellValueFactory(
            new PropertyValueFactory<Livro,String>("livro")
        );

        colData.setCellValueFactory(
            new PropertyValueFactory<Exemplar,LocalDate>("dataAquisicao")
        );

        preencherCombo();
        preencherTabela();
    }    
}
