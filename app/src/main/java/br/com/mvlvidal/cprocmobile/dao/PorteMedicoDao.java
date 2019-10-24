package br.com.mvlvidal.cprocmobile.dao;

import java.util.List;

import br.com.mvlvidal.cprocmobile.model.PorteMedico;

public interface PorteMedicoDao {

    void salvar(PorteMedico porteMedico);

    void editar(PorteMedico porteMedico);

    void deletar(Long id);

    PorteMedico buscarPorId(Long id);

    List<PorteMedico> buscarTodos();

}
