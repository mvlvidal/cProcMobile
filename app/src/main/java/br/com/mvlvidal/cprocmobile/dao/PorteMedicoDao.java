package br.com.mvlvidal.cprocmobile.dao;

import java.util.List;

import br.com.mvlvidal.cprocmobile.model.PorteMedico;

public interface PorteMedicoDao {

    PorteMedico buscarPorId(Long id);

    List<PorteMedico> buscarTodos();

}
