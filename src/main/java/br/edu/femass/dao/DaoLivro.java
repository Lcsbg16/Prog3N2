package br.edu.femass.dao;

import java.util.List;

import br.edu.femass.model.Livro;

public class DaoLivro extends Dao<Livro> {
    public List<Livro> buscarTodos(){
        return em.createQuery("select l from Livro l order by l.id").getResultList();
    
    }
}