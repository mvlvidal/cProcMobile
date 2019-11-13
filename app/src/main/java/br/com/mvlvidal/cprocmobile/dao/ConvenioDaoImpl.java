package br.com.mvlvidal.cprocmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import br.com.mvlvidal.cprocmobile.model.Convenio;

public class ConvenioDaoImpl extends SQLiteOpenHelper implements ConvenioDao {

    private Context context;

    public ConvenioDaoImpl(Context context) {
        super(context, "procDados.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists convenio(" +
                "_id Integer primary key autoincrement"+
                ", nome varchar(40)"+
                ", ucoSadt Float"+
                ", ucoHm Float"+
                ", valorChHm Float"+
                ", valorChSadt Float"+
                ", tabHm varchar(20)"+
                ", tabSadt varchar(20)"+
                ", percPorteHm Float"+
                ", percPorteSadt Float"+
                ")";

        db.execSQL(sql);

        db.execSQL("insert into convenio (nome,ucoSadt,ucoHm,valorChHm,valorChSadt,tabHm,tabSadt" +
                ",percPorteHm,percPorteSadt) values ('CNU', 10.0,10.0,0,0,'cbhpm5','amb92',1.0,1.0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public boolean salvar(Convenio convenio) {

        boolean retorno = false;

        try{

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

            if(convenio.getId() == null){

                long i = getWritableDatabase().insert("convenio", null, cv);
                retorno = (i>0 ? true : false);

            }else{
                String where = "_id = " + convenio.getId();
                int i = getWritableDatabase().update("convenio", cv, where, null);
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

            String where = "_id = " + id;
            int i = getWritableDatabase().delete("convenio", where, null);
            retorno = (i > 0 ? true : false);

        }catch (Exception e){
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    public Convenio buscarPorId(Long id) {

        String nomeTabela = "convenio";
        String[] campos = {"_id", "nome", "ucoSadt", "ucoHm", "valorChHm", "valorChSadt", "tabHm",
                "tabSadt", "percPorteHm", "percPorteSadt"};
        String where1 = "_id = "+ id;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = getReadableDatabase().query(nomeTabela, campos, where1, where2, groupBy, orderBy, having);

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

        return new Convenio(colId,colNome,colUcoSadt,colUcoHm,colValorChHm,colValorSadt,colTabHm,
                colTabSadt,colPercPorteHm,colPercPorteSadt);
    }

    @Override
    public List<Convenio> buscarTodos() {

        List<Convenio> retorno = new ArrayList<>();

        String nomeTabela = "convenio";
        String[] campos = {"_id", "nome"};
        String where1 = null;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = getReadableDatabase().query(nomeTabela, campos, where1, where2, groupBy, orderBy, having);

        while(c.moveToNext()){

            Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
            String colNome = c.getString(c.getColumnIndex("nome"));

            Convenio conv = new Convenio(colId,colNome);

            retorno.add(conv);
        }

        return retorno;

    }

}
