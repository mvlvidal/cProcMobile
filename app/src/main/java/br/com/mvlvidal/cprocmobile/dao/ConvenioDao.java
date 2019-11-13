package br.com.mvlvidal.cprocmobile.dao;

import java.util.List;

import br.com.mvlvidal.cprocmobile.model.Convenio;

public interface ConvenioDao {

    boolean salvar(Convenio convenio);

    boolean deletar(Long id);

    Convenio buscarPorId(Long id);

    List<Convenio> buscarTodos();

}
