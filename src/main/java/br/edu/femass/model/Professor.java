package br.edu.femass.model;
import javax.persistence.Entity;


@Entity
public class Professor extends Leitor{
    private String disciplina;
    public Professor(String nome, String endereco, String telefone, String disciplina) {
        super(nome, endereco, telefone);
        this.disciplina = disciplina;
        
    }

    public Professor(){
        this.prazoMaximoDevolucao = 30;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return  this.getNome();
    }
    
}
