package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.model.Professor;
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

public class ProfessorController implements Initializable {
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtDisciplina;

    @FXML
    private TableView<Professor> tabelaProfessor = new TableView<Professor>();

    @FXML
    private TableColumn<Professor,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Professor,String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Professor,String> colEndereco = new TableColumn<>();

    @FXML
    private TableColumn<Professor,String> colTelefone = new TableColumn<>();

    @FXML
    private TableColumn<Professor,Integer> colPrazo = new TableColumn<>();

    @FXML
    private TableColumn<Professor,String> colDisciplina = new TableColumn<>();


    @FXML
    private Button BtnIncluir;
  
    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar; 



    private DaoProfessor dao = new DaoProfessor();
    private Professor professor;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        professor.setNome(txtNome.getText());
        professor.setEndereco(txtEndereco.getText());
        professor.setTelefone(txtTelefone.getText());
        professor.setDisciplina(txtDisciplina.getText());
        if (incluindo) {
            dao.inserir(professor);
        } else {
            dao.alterar(professor);
        }

        preencherTabela();
       
        editar(false);
        
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        
        incluindo = true;

        professor = new Professor();
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtDisciplina.setText("");
        txtNome.requestFocus();


    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(professor);
        preencherTabela();
       
    }
    
    private void editar(boolean habilitar) {
        txtNome.setDisable(!habilitar);
        txtEndereco.setDisable(!habilitar);
        txtTelefone.setDisable(!habilitar);
        txtDisciplina.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }

    @FXML
    private void tabelaProfessor_KeyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML
    private void tabelaProfessor_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados(){
        this.professor = tabelaProfessor.getSelectionModel().getSelectedItem();
        
        if(professor==null) return;

        txtNome.setText(professor.getNome());
        txtEndereco.setText(professor.getEndereco());
        txtTelefone.setText(professor.getTelefone());
        txtDisciplina.setText(professor.getDisciplina());
    }

    private void preencherTabela(){
        List<Professor> professores = dao.buscarTodos();
        ObservableList<Professor> data = FXCollections.observableArrayList(professor);
        tabelaProfessor.setItems(data);   
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
            new PropertyValueFactory<Professor,Long>("id")
        );

        colNome.setCellValueFactory(
            new PropertyValueFactory<Professor,String>("nome")
        );
        
        colEndereco.setCellValueFactory(
            new PropertyValueFactory<Professor,String>("endereco")
        );

        colTelefone.setCellValueFactory(
            new PropertyValueFactory<Professor,String>("telefone")
        );  
        
        colPrazo.setCellValueFactory(
            new PropertyValueFactory<Professor,Integer>("prazo")
        );          

        colPrazo.setCellValueFactory(
            new PropertyValueFactory<Professor,Integer>("prazo")
        );

        colDisciplina.setCellValueFactory(
            new PropertyValueFactory<Professor,String>("disciplina")
        );       

        preencherTabela();
        
    }    
}
