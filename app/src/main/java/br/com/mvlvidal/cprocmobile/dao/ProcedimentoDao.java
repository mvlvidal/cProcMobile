package br.com.mvlvidal.cprocmobile.dao;

import java.util.List;

import br.com.mvlvidal.cprocmobile.model.Procedimento;

public interface ProcedimentoDao {

    void salvar(Procedimento procedimento);

    void editar(Procedimento procedimento);

    void deletar(Procedimento procedimento);

    Procedimento buscarPorId(Long id);

    List<Procedimento> buscarTodos();

}
