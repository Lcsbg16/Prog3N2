package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.model.Aluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
       
        editar(false);
        
    }

    @FXML
    private void incluir_click(ActionEvent event) {
        editar(true);
        
        incluindo = true;

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

        incluindo = false;
    }

    @FXML
    private void excluir_click(ActionEvent event) {
        dao.apagar(aluno);
        preencherLista();
       
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

    private void preencherLista(){
        List<Aluno> alunos = dao.buscarTodos();

        ObservableList<Aluno> data = FXCollections.observableArrayList(alunos);
        lstAlunos.setItems(data);   
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherLista();
        
    }    
}
