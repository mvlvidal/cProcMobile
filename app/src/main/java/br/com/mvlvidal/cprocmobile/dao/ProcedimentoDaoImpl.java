package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.mvlvidal.cprocmobile.model.Convenio;
import br.com.mvlvidal.cprocmobile.model.Procedimento;

public class ProcedimentoDaoImpl extends SQLiteOpenHelper implements ProcedimentoDao{

    Context context;

    public ProcedimentoDaoImpl(Context context) {
        super(context, "procDados.db", null, 1);
        this.context = context;
    }

    @Override
    public Procedimento buscarPorId(Long id) {


        String tabela = "procedimento";
        String[] campos = new String[]{"_id","descricao", "codigo","ch","co","porteMedico","porteAnestesico",
                "qtdFilme","qtdAuxilio","tipo","tabela","percPorte"};
        String where1 = "_id =" + id;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = getReadableDatabase().query(tabela, campos, where1, where2, groupBy, orderBy, having);

        Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
        String colDescricao = c.getString(c.getColumnIndex("descricao"));
        Integer colCodigo = c.getInt(c.getColumnIndex("codigo"));
        Float colCh = c.getFloat(c.getColumnIndex("ch"));
        Float colCo = c.getFloat(c.getColumnIndex("co"));
        String colPorteMed = c.getString(c.getColumnIndex("porteMedico"));
        Integer colPorteAnes = c.getInt(c.getColumnIndex("porteAnestesico"));
        Float colQtdFilme = c.getFloat(c.getColumnIndex("qtdFilme"));
        Integer colQtdAuxilio = c.getInt(c.getColumnIndex("qtdAuxilio"));
        String colTipo = c.getString(c.getColumnIndex("tipo"));
        String colTabela = c.getString(c.getColumnIndex("tabela"));
        Float colPercPorte = c.getFloat(c.getColumnIndex("percPorte"));

        return new Procedimento(colId,colDescricao,colCodigo,colCh,colCo,colPorteMed,colPorteAnes,colQtdFilme,colQtdAuxilio,colTipo,colTabela, colPercPorte);
    }

    @Override
    public List<Procedimento> buscarTodos(Convenio conv) {

        List<Procedimento> retorno = new ArrayList<>();

        try {
            String tabela = "procedimento";
            String[] campos = new String[]{"_id", "descricao", "codigo"};
            String where1 = "tabela = '" + conv.getTabHm() + "' and tabela = '" + conv.getTabSadt() + "'";
            String[] where2 = null;
            String groupBy = null;
            String orderBy = null;
            String having = null;

            Cursor c = getReadableDatabase().query(tabela, campos, where1, where2, groupBy, orderBy, having);

            while (c.moveToNext()) {
                Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
                String colDescricao = c.getString(c.getColumnIndex("descricao"));
                Integer colCodigo = c.getInt(c.getColumnIndex("codigo"));

                Procedimento proc = new Procedimento(colId, colDescricao, colCodigo);

                retorno.add(proc);
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        return retorno;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlProc = "create table if not exists procedimento(" +
                "_id Integer primary key autoincrement," +
                "descricao varchar(40),"+
                "codigo Integer," +
                "ch Float," +
                "co Float," +
                "porteMedico varchar(3)," +
                "porteAnestesico Integer," +
                "qtdFilme Float," +
                "qtdAuxilio Integer," +
                "tipo varchar(4)," +
                "tabela varchar(20)," +
                "percPorte Float" +
                ")";
        db.execSQL(sqlProc);

        db.execSQL("insert into procedimento (descricao,codigo,porteMedico" +
                "tipo,tabela,percPorte) values ('Consulta',10101012,'1A','hm','cbhpm5',1.0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    @Override
    public String popularTabela() {

        return "";

    }
}
