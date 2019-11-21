package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import br.com.mvlvidal.cprocmobile.model.TabelaPortes;

public class TabelaPortesDaoImpl extends SQLiteOpenHelper implements TabelaPortesDao {

    private Context context;

    public TabelaPortesDaoImpl(Context context) {
        super(context, "procDados.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists tabelaportes(_id Integer primary key, nome varchar(40))";

        db.execSQL(sql);

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        db.execSQL("insert into tabelaportes(_id, nome) values(1, '5ª 2009')");
        db.execSQL("insert into tabelaportes(_id, nome) values(2, '5ª 2008')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public TabelaPortes buscarPorId(Long id) {

        String nomeTabela = "tabelaportes";
        String[] campos = {"_id", "nome"};
        String where1 = "_id = " + id;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = getReadableDatabase().query(nomeTabela, campos, where1, where2, groupBy, orderBy,having);

        Long colId = c.getLong(c.getColumnIndex("_id"));
        String colNome = c.getString(c.getColumnIndex("nome"));

        return new TabelaPortes(colId, colNome);
    }

    @Override
    public List<TabelaPortes> buscarTodos() {

        List<TabelaPortes> retorno = new ArrayList<>();

        String nomeTabela = "tabelaportes";
        String[] campos = {"_id", "nome"};
        String where1 = null;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = getReadableDatabase().query(nomeTabela, campos, where1, where2, groupBy, orderBy,having);

        while(c.moveToNext()){

            Long colId = c.getLong(c.getColumnIndex("_id"));
            String colNome = c.getString(c.getColumnIndex("nome"));
            TabelaPortes tabela = new TabelaPortes(colId, colNome);

            retorno.add(tabela);
        }

        return retorno;
    }
}
