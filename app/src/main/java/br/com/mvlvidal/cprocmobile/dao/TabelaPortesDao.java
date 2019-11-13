package br.com.mvlvidal.cprocmobile.dao;

import java.util.List;

import br.com.mvlvidal.cprocmobile.model.TabelaPortes;

public interface TabelaPortesDao {

    TabelaPortes buscarPorId(Long id);

    List<TabelaPortes> buscarTodos();
}
