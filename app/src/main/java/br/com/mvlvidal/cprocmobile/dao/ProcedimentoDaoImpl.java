package br.com.mvlvidal.cprocmobile.dao;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import br.com.mvlvidal.cprocmobile.model.Convenio;
import br.com.mvlvidal.cprocmobile.model.Procedimento;

public class ProcedimentoDaoImpl implements ProcedimentoDao{

    private ConnectionFactory factory;
    private Context context;

    public ProcedimentoDaoImpl(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Procedimento buscarPorId(Long id) {

        SQLiteDatabase db = factory.conectar();
        String tabela = "procedimento";
        String[] campos = new String[]{"_id","descricao", "codigo","ch","co","porteMedico","porteAnestesico",
                "qtdFilme","qtdAuxilio","tipo","tabela","percPorte"};
        String where1 = "_id =" + id;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = db.query(tabela, campos, where1, where2, groupBy, orderBy, having);

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
    public List<Procedimento> buscarTodos(String tbHm, String tbSadt) {

        List<Procedimento> retorno = new ArrayList<>();

        try {
            SQLiteDatabase db = factory.conectar();
            String tabela = "procedimento";
            String[] campos = new String[]{"_id", "descricao", "codigo"};
            String where1 = "tabela = '" + tbHm + "' or tabela = '" + tbSadt + "'";
            String[] where2 = null;
            String groupBy = null;
            String orderBy = null;
            String having = null;

            Cursor c = db.query(tabela, campos, where1, where2, groupBy, orderBy, having);

            while (c.moveToNext()) {
                Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
                String colDescricao = c.getString(c.getColumnIndex("descricao"));
                Integer colCodigo = c.getInt(c.getColumnIndex("codigo"));

                Procedimento proc = new Procedimento(colId, colDescricao, colCodigo);

                retorno.add(proc);
            }
        }catch(Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle("Erro!");
            dlg.setMessage(e.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }


        return retorno;
    }

    @Override
    public String popularTabela() {

        return "";

    }
}
