// package br.edu.femass.gui;

// import java.net.URL;
// import java.time.LocalDate;
// import java.util.List;
// import java.util.ResourceBundle;

// import br.edu.femass.dao.DaoEmprestimo;
// import br.edu.femass.model.Emprestimo;
// import br.edu.femass.model.Exemplar;
// import br.edu.femass.model.Leitor;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.Button;
// import javafx.scene.control.ListView;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.TextField;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.input.KeyEvent;
// import javafx.scene.input.MouseEvent;

// public class EmprestimoController implements Initializable {

//     @FXML
//     private ListView<Leitor> lstLeitor;
    
//     @FXML
//     private ListView<Exemplar> lstExemplar;

//     @FXML
//     private Button BtnIncluir;
  
//     @FXML
//     private Button BtnAlterar;

//     @FXML
//     private Button BtnExcluir;

//     @FXML
//     private Button BtnGravar; 

//     @FXML
//     private TableView<Emprestimo> tabelaEmprestimo = new TableView<Emprestimo>();

//     @FXML
//     private TableColumn<Emprestimo,Long> colID = new TableColumn<>();

//     @FXML
//     private TableColumn<Leitor,String> colLeitor = new TableColumn<>();

//     @FXML
//     private TableColumn<Exemplar,String> colExemplar = new TableColumn<>();

//     @FXML
//     private TableColumn<LocalDate,String> colDataEmprestimo = new TableColumn<>();

//     @FXML
//     private TableColumn<LocalDate,String> colPrevisao = new TableColumn<>();
    
//     @FXML
//     private TableColumn<LocalDate,String> colDevolucao = new TableColumn<>();

//     private DaoEmprestimo dao = new DaoEmprestimo();
//     private Dao dao = new DaoEmprestimo();
//     private DaoEmprestimo dao = new DaoEmprestimo();

//     private Emprestimo emprestimo;
//     private Boolean incluindo;

//     @FXML
//     private void Gravar_Click(ActionEvent event) {
//         emprestimo.setLeitor(lstLeitor.getSelectionModel().getSelectedItem());
//         emprestimo.setExemplar(lstExemplar.getSelectionModel().getSelectedItem());
//         if (incluindo) {
//             dao.inserir(emprestimo);
//         } else {
//             dao.alterar(emprestimo);
//         }

//         preencherLista();
//         preencherTabela();
//         editar(false);
        
//     }

//     @FXML
//     private void incluir_click(ActionEvent event) {
//         editar(true);
//         preencherLista();
//         incluindo = true;
//         emprestimo = new Emprestimo();

//     }


//     @FXML
//     private void excluir_click(ActionEvent event) {
//         dao.apagar(emprestimo);
//         preencherLista();
//         preencherTabela();
//     }
    
//     private void editar(boolean habilitar) {
//         lstLeitor.setDisable(habilitar);
//         lstExemplar.setDisable(!habilitar);
//         BtnGravar.setDisable(!habilitar);
//         BtnAlterar.setDisable(habilitar);
//         BtnIncluir.setDisable(habilitar);
//         BtnExcluir.setDisable(habilitar);
//     }





//     private void preencherLista(){
//         List<Leitor> leitores = dao.buscarTodos();

//         ObservableList<Autor> data = FXCollections.observableArrayList(autores);
//         lstAutores.setItems(data);   
//     }

//     private void preencherTabela(){
//         List<Autor> autores = dao.buscarTodos();

//         ObservableList<Autor> data = FXCollections.observableArrayList(autores);
//         tabelaAutor.setItems(data);   
//     }

//     @Override
//     public void initialize(URL url, ResourceBundle rb) {
//         colID.setCellValueFactory(
//             new PropertyValueFactory<Autor,Long>("id")
//         );

//         colNome.setCellValueFactory(
//             new PropertyValueFactory<Autor,String>("nome")
//         );
        
//         colSobrenome.setCellValueFactory(
//             new PropertyValueFactory<Autor,String>("sobrenome")
//         );

//         colNacionalidade.setCellValueFactory(
//             new PropertyValueFactory<Autor,String>("nacionalidade")
//         );

//         preencherLista();
//         preencherTabela();
//     }    
// }
