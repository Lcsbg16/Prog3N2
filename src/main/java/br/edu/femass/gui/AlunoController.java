package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Leitor;
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

public class AlunoController implements Initializable {
    
    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtMatricula;

    @FXML
    private ListView<Aluno> lstAlunos;

    @FXML
    private Button BtnIncluir;
  
    @FXML
    private Button BtnAlterar;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar; 

    @FXML
    private TableView<Aluno> tabelaAluno = new TableView<Aluno>();

    @FXML
    private TableColumn<Aluno,Long> colID = new TableColumn<>();

    @FXML
    private TableColumn<Aluno,String> colNome = new TableColumn<>();

    @FXML
    private TableColumn<Aluno,String> colEndereco = new TableColumn<>();

    @FXML
    private TableColumn<Aluno,String> colTelefone = new TableColumn<>();

    @FXML
    private TableColumn<Leitor,Integer> colPrazo = new TableColumn<>();

    @FXML
    private TableColumn<Aluno,String> colMatricula = new TableColumn<>();




    private DaoAluno dao = new DaoAluno();
    private Aluno aluno;
    private Boolean incluindo;

    @FXML
    private void Gravar_Click(ActionEvent event) {
        aluno.setNome(txtNome.getText());
        aluno.setEndereco(txtEndereco.getText());
        aluno.setTelefone(txtTelefone.getText());
        aluno.setMatricula(txtMatricula.getText());
        if (incluindo) {
            dao.inserir(aluno);
        } else {
            dao.alterar(aluno);
        }

        preencherLista();
        preencherTabela();
        editar(false);
        
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        
        incluindo = true;
        preencherTabela();
        aluno = new Aluno();
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtMatricula.setText("");
        txtNome.requestFocus();
    }

    @FXML
    private void alterar_click(ActionEvent event) {
        editar(true);
        preencherLista();
        preencherTabela();
        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(aluno);
        preencherLista();
        preencherTabela();
       
    }
    
    private void editar(boolean habilitar) {
        lstAlunos.setDisable(habilitar);
        txtNome.setDisable(!habilitar);
        txtEndereco.setDisable(!habilitar);
        txtTelefone.setDisable(!habilitar);
        txtMatricula.setDisable(!habilitar);
        BtnGravar.setDisable(!habilitar);
        BtnAlterar.setDisable(habilitar);
        BtnIncluir.setDisable(habilitar);
        BtnExcluir.setDisable(habilitar);
    }


    @FXML
    private void tabelaAluno_KeyPressed(KeyEvent event) {
        exibirDadosTabela();
    }

    @FXML
    private void tabelaAluno_MouseClicked(MouseEvent event) {
        exibirDadosTabela();
    }

    @FXML
    private void lstAlunos_KeyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML
    private void lstAlunos_MouseClicked(MouseEvent event) {
        exibirDados();
    }

    private void exibirDados(){
        this.aluno = lstAlunos.getSelectionModel().getSelectedItem();
        if(aluno==null) return;

        txtNome.setText(aluno.getNome());
        txtEndereco.setText(aluno.getEndereco());
        txtTelefone.setText(aluno.getTelefone());
        txtMatricula.setText(aluno.getMatricula());
    }

    private void exibirDadosTabela(){
        this.aluno = tabelaAluno.getSelectionModel().getSelectedItem();
        if(aluno==null) return;

        txtNome.setText(aluno.getNome());
        txtEndereco.setText(aluno.getEndereco());
        txtTelefone.setText(aluno.getTelefone());
        txtMatricula.setText(aluno.getMatricula());
    }

    private void preencherLista(){
        List<Aluno> alunos = dao.buscarTodos();
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        lstAlunos.setItems(data);   
    }

    private void preencherTabela(){
        List<Aluno> alunos = dao.buscarTodos();
        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        tabelaAluno.setItems(data);   
        tabelaAluno.refresh(); 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colID.setCellValueFactory(
            new PropertyValueFactory<Aluno,Long>("id")
        );

        colNome.setCellValueFactory(
            new PropertyValueFactory<Aluno,String>("nome")
        );
        
        colEndereco.setCellValueFactory(
            new PropertyValueFactory<Aluno,String>("endereco")
        );

        colTelefone.setCellValueFactory(
            new PropertyValueFactory<Aluno,String>("telefone")
        );

        colPrazo.setCellValueFactory(
            new PropertyValueFactory<Leitor,Integer>("prazoMaximoDevolucao")
        );

        colMatricula.setCellValueFactory(
            new PropertyValueFactory<Aluno,String>("matricula")
        );
        preencherLista();
        preencherTabela();
    }    
}
