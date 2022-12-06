package br.edu.femass;

import java.time.LocalDate;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Emprestimo;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;

public class teste {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("Lucas","Macabu","27794487","20");

        Autor autor = new Autor("Mario","Andrade","Brasileiro");

        Livro livro = new Livro("Teste",autor,"2000");
        
        Exemplar exemplar = new Exemplar(livro);

        Emprestimo emprestimo = new Emprestimo(aluno,exemplar);

        System.out.println(emprestimo.getDataEmprestimo());

    }

}
