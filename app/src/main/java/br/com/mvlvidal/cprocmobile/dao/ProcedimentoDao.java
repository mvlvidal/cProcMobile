package br.com.mvlvidal.cprocmobile.dao;

import java.util.List;
import br.com.mvlvidal.cprocmobile.model.Convenio;
import br.com.mvlvidal.cprocmobile.model.Procedimento;

public interface ProcedimentoDao {

    String popularTabela();

    Procedimento buscarPorId(Long id);

    List<Procedimento> buscarTodos(Convenio c);

}
