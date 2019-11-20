package br.com.mvlvidal.cprocmobile.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.mvlvidal.cprocmobile.model.TabelaProcedimento;

public class TabelaProcedimentoDaoImpl implements TabelaProcedimentoDao {

    @Override
    public List<TabelaProcedimento> listar() {

        List<TabelaProcedimento> retorno = new ArrayList<>();

        retorno.add(new TabelaProcedimento("amb90","AMB 90"));
        retorno.add(new TabelaProcedimento("amb92","AMB 92"));
        retorno.add(new TabelaProcedimento("cbhpm4","CBHPM 4ª"));
        retorno.add(new TabelaProcedimento("cbhpm5","CBHPM 5ª"));

        return retorno;
    }

}
