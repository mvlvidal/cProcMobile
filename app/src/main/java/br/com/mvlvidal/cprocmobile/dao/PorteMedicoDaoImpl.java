package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import br.com.mvlvidal.cprocmobile.model.PorteMedico;
import br.com.mvlvidal.cprocmobile.model.TabelaPortes;

public class PorteMedicoDaoImpl implements PorteMedicoDao {

    private ConnectionFactory factory;

    public PorteMedicoDaoImpl(ConnectionFactory factory) {
        this.factory = factory;
    }


    @Override
    public PorteMedico buscarPorId(Long id) {

        SQLiteDatabase db = factory.conectarLeitura();
        String nomeTabela = "portemedico";
        String[] campos = {"_id", "nome", "valor", "_idTabela"};
        String where1 = "_id = "+ id;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = db.query(nomeTabela, campos, where1, where2, groupBy, orderBy, having);

        Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
        String colNome = c.getString(c.getColumnIndex("nome"));
        Float colValor = c.getFloat(c.getColumnIndex("valor"));
        Long colIdTabela = c.getLong(c.getInt(c.getColumnIndex("_idTabela")));

        return new PorteMedico(colId,colNome,colValor,new TabelaPortes(colIdTabela,""));
    }

    @Override
    public List<PorteMedico> buscarTodos() {

        SQLiteDatabase db = factory.conectarLeitura();

        return null;
    }

    public PorteMedico buscarPorNomeETabela(String nome, Long idTab){

            SQLiteDatabase db = factory.conectarLeitura();

            String nomeTabela = "portemedico";
            String[] campos = {"_id", "nome", "valor", "_idTabela"};
            String where1 = "nome = '"+nome+"' AND _idTabela = '"+idTab+"'";
            String[] where2 = null;
            String groupBy = null;
            String orderBy = null;
            String having = null;

            Cursor c = db.query(nomeTabela, campos, where1, where2, groupBy, orderBy, having);

            PorteMedico pMed = new PorteMedico();

            if(c.moveToNext()) {
                Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
                String colNome = c.getString(c.getColumnIndex("nome"));
                Float colValor = c.getFloat(c.getColumnIndex("valor"));
                Long colIdTabela = c.getLong(c.getInt(c.getColumnIndex("_idTabela")));

                pMed = new PorteMedico(colId, colNome, colValor, new TabelaPortes(colIdTabela, ""));
            }

            return pMed;
    }

}
