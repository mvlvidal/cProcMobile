package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import br.com.mvlvidal.cprocmobile.model.TabelaPortes;

public class TabelaPortesDaoImpl implements TabelaPortesDao {

    private final ConnectionFactory factory;

    public TabelaPortesDaoImpl(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabelaPortes buscarPorId(Long id) {

        SQLiteDatabase db = factory.conectarLeitura();
        String nomeTabela = "tabelaportes";
        String[] campos = {"_id", "nome"};
        String where1 = "_id = " + id;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = db.query(nomeTabela, campos, where1, where2, groupBy, orderBy,having);

        Long colId = c.getLong(c.getColumnIndex("_id"));
        String colNome = c.getString(c.getColumnIndex("nome"));

        return new TabelaPortes(colId, colNome);
    }

    @Override
    public List<TabelaPortes> buscarTodos() {

        List<TabelaPortes> retorno = new ArrayList<>();
        SQLiteDatabase db = factory.conectarLeitura();
        String nomeTabela = "tabelaportes";
        String[] campos = {"_id", "nome"};
        String where1 = null;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = db.query(nomeTabela, campos, where1, where2, groupBy, orderBy,having);

        while(c.moveToNext()){

            Long colId = c.getLong(c.getColumnIndex("_id"));
            String colNome = c.getString(c.getColumnIndex("nome"));
            TabelaPortes tabela = new TabelaPortes(colId, colNome);

            retorno.add(tabela);
        }

        return retorno;
    }
}
