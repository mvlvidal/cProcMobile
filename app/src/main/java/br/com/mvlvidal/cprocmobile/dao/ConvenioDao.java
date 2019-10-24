package br.com.mvlvidal.cprocmobile.dao;

import java.util.List;

import br.com.mvlvidal.cprocmobile.model.Convenio;

public interface ConvenioDao {

    void salvar(Convenio convenio);

    void editar(Convenio convenio);

    void deletar(Long id);

    Convenio buscarPorId(Long id);

    List<Convenio> buscarTodos();

}
