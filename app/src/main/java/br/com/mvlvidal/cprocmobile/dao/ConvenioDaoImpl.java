package br.com.mvlvidal.cprocmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import br.com.mvlvidal.cprocmobile.model.Convenio;
import br.com.mvlvidal.cprocmobile.model.TabelaPortes;

public class ConvenioDaoImpl implements ConvenioDao {

    private final ConnectionFactory factory;

    public ConvenioDaoImpl(ConnectionFactory factory){
        this.factory = factory;
    }

    @Override
    public boolean salvar(Convenio convenio) {

        boolean retorno = false;

        try{
            SQLiteDatabase db = factory.conectar();
            ContentValues cv = new ContentValues();
            cv.put("nome", convenio.getNome());
            cv.put("ucoSadt", convenio.getUcoSadt());
            cv.put("ucoHm", convenio.getUcoHm());
            cv.put("valorChHm", convenio.getValorChHm());
            cv.put("valorChSadt", convenio.getValorChSadt());
            cv.put("tabHm", convenio.getTabHm());
            cv.put("tabSadt", convenio.getTabSadt());
            cv.put("percPorteHm", convenio.getPercPorteHm());
            cv.put("percPorteSadt", convenio.getPercPorteSadt());
            cv.put("idTabPortesHm", convenio.getTabelaPortesHm().getId());
            cv.put("idTabPortesSadt", convenio.getTabelaPortesSadt().getId());

            if(convenio.getId() == null){

                long i = db.insert("convenio", null, cv);
                retorno = (i>0 ? true : false);

            }else{
                String where = "_id = " + convenio.getId();
                int i = db.update("convenio", cv, where, null);
                retorno = (i>0 ? true : false);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    public boolean deletar(Long id) {

        boolean retorno = false;

        try {
            SQLiteDatabase db = factory.conectar();
            String where = "_id = " + id;
            int i = db.delete("convenio", where, null);
            retorno = (i > 0 ? true : false);

        }catch (Exception e){
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    public Convenio buscarPorId(Long id) {
        SQLiteDatabase db = factory.conectar();
        String nomeTabela = "convenio";
        String[] campos = {"_id", "nome", "ucoSadt", "ucoHm", "valorChHm", "valorChSadt", "tabHm",
                "tabSadt", "percPorteHm", "percPorteSadt", "idTabPorteHm", "idTabPorteSadt"};
        String where1 = "_id = "+ id;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = db.query(nomeTabela, campos, where1, where2, groupBy, orderBy, having);

        Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
        String colNome = c.getString(c.getColumnIndex("nome"));
        Float colUcoSadt = c.getFloat(c.getColumnIndex("ucoSadt"));
        Float colUcoHm = c.getFloat(c.getColumnIndex("ucoHm"));
        Float colValorChHm = c.getFloat(c.getColumnIndex("valorChHm"));
        Float colValorSadt = c.getFloat(c.getColumnIndex("valorChSadt"));
        String colTabHm = c.getString(c.getColumnIndex("tabHm"));
        String colTabSadt = c.getString(c.getColumnIndex("tabSadt"));
        Float colPercPorteHm = c.getFloat(c.getColumnIndex("percPorteHm"));
        Float colPercPorteSadt = c.getFloat(c.getColumnIndex("percPorteSadt"));
        Long colIdTabPortesHm = Long.valueOf(c.getInt(c.getColumnIndex("idTabPortesHm")));
        Long colIdTabPortesSadt = Long.valueOf(c.getInt(c.getColumnIndex("idTabPortesSadt")));

        return new Convenio(colId,colNome,colUcoSadt,colUcoHm,colValorChHm,colValorSadt,colTabHm,
                colTabSadt,colPercPorteHm,colPercPorteSadt, new TabelaPortes(colIdTabPortesHm, ""), new TabelaPortes(colIdTabPortesSadt, ""));
    }

    @Override
    public List<Convenio> buscarTodos() {

        List<Convenio> retorno = new ArrayList<>();
        SQLiteDatabase db = factory.conectar();
        String nomeTabela = "convenio";
        String[] campos = {"_id", "nome","tabHm", "tabSadt"};
        String where1 = null;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = db.query(nomeTabela, campos, where1, where2, groupBy, orderBy, having);

        while(c.moveToNext()){

            Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
            String colNome = c.getString(c.getColumnIndex("nome"));
            String colTabHm = c.getString(c.getColumnIndex("tabHm"));
            String colTabSadt = c.getString(c.getColumnIndex("tabSadt"));

            Convenio conv = new Convenio(colId,colNome, colTabHm, colTabSadt);

            retorno.add(conv);
        }

        return retorno;

    }

}
