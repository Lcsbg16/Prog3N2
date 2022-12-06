package br.edu.femass.model;

import java.time.LocalDate;

import javax.annotation.processing.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;


    @ManyToOne(cascade = CascadeType.ALL)
    private Leitor leitor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Aluno aluno;

    @ManyToOne(cascade = CascadeType.ALL)
    private Professor professor;

    @ManyToOne(cascade = CascadeType.ALL)
    private Exemplar exemplar;

    // public Emprestimo(Aluno aluno, Exemplar exemplar) {
    //     this.aluno = aluno;
    //     this.exemplar = exemplar;

    //     this.dataEmprestimo = LocalDate.now();
    //     this.dataPrevistaDevolucao = LocalDate.now().plusDays(aluno.getPrazoMaximoDevolucao());
    // }   

    // public Emprestimo(Professor professor, Exemplar exemplar) {
    //     this.professor = professor;
    //     this.exemplar = exemplar;

    //     this.dataEmprestimo = LocalDate.now();
    //     this.dataPrevistaDevolucao = LocalDate.now().plusDays(professor.getPrazoMaximoDevolucao());

    // }   
    
    public Emprestimo(Leitor leitor, Exemplar exemplar){
        this.leitor = leitor;
        this.exemplar = exemplar;
        this.dataEmprestimo = LocalDate.now();  
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(leitor.getPrazoMaximoDevolucao());
    }

    public Emprestimo(){
        this.dataEmprestimo = LocalDate.now();           
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    

}
