package br.com.mvlvidal.cprocmobile.dao;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import br.com.mvlvidal.cprocmobile.model.Convenio;
import br.com.mvlvidal.cprocmobile.model.PorteMedico;
import br.com.mvlvidal.cprocmobile.model.Procedimento;

public class ProcedimentoDaoImpl implements ProcedimentoDao{

    private ConnectionFactory factory;
    private Context context;

    public ProcedimentoDaoImpl(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Procedimento buscarPorId(Long id) {

        SQLiteDatabase db = factory.conectarLeitura();
        String tabela = "procedimento";
        String[] campos = new String[]{"_id","descricao", "codigo","ch","co","porteMedico","porteAnestesico",
                "qtdFilme","qtdAuxilio","tipo","tabela","percPorte"};
        String where1 = "_id =" + id;
        String[] where2 = null;
        String groupBy = null;
        String orderBy = null;
        String having = null;

        Cursor c = db.query(tabela, campos, where1, where2, groupBy, orderBy, having);

        Procedimento proc = new Procedimento();

        while(c.moveToFirst()) {
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
            proc = new Procedimento(colId,colDescricao,colCodigo,colCh,colCo,colPorteMed,colPorteAnes,colQtdFilme,colQtdAuxilio,colTipo,colTabela, colPercPorte);
        }
        return proc;
    }



    @Override
    public List<Procedimento> buscarTodos(Convenio conv) {

        List<Procedimento> retorno = new ArrayList<>();

        String tbHm = conv.getTabHm();
        String tbSadt = conv.getTabSadt();

        try {
            SQLiteDatabase db = factory.conectarLeitura();
            String tabela = "procedimento";
            String[] campos = new String[]{"_id", "descricao", "codigo","tabela"};
            String where1 = "tabela = '"+tbHm+"' OR tabela = '"+tbSadt+"'";
            String[] where2 = null;
            String groupBy = null;
            String orderBy = null;
            String having = null;

            Cursor c = db.query(tabela, campos, where1, where2, groupBy, orderBy, having);

            while (c.moveToNext()) {
                Long colId = Long.valueOf(c.getInt(c.getColumnIndex("_id")));
                String colDescricao = c.getString(c.getColumnIndex("descricao"));
                Integer colCodigo = c.getInt(c.getColumnIndex("codigo"));
                String colTabela = c.getString(c.getColumnIndex("tabela"));

                Procedimento proc = new Procedimento(colId, colDescricao, colCodigo,colTabela);

                retorno.add(proc);
            }
        }catch(Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this.context);
            dlg.setTitle("Erro!");
            dlg.setMessage(e.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }


        return retorno;
    }

    public List<Float> calcularProcedimento(Long idConv, Long idProc){

        List<Float> resultados = new ArrayList<>();

        SQLiteDatabase db = factory.conectarLeitura();

        ConvenioDaoImpl cdao = new ConvenioDaoImpl(factory);
        ProcedimentoDaoImpl procdao = new ProcedimentoDaoImpl(factory);
        PorteMedicoDaoImpl pmeddao = new PorteMedicoDaoImpl(factory);

        Convenio conv = cdao.buscarPorId(idConv);
        Procedimento proc = procdao.buscarPorId(idProc);

        PorteMedico pMed;

        Float totalUcoCo = 0F;
        Float totalPorteMed = 0F;
        Float totalCh = 0F;

        if(proc.getTipo() == "sadt"){
            totalUcoCo = proc.getCo() * conv.getUcoSadt();
            pMed = pmeddao.buscarPorNomeETabela(proc.getPorteMedico(), conv.getTabelaPortesSadt().getId());
            totalPorteMed = pMed.getValor();
            totalCh = proc.getCh() * conv.getValorChSadt();
        }else if(proc.getTipo() == "hm"){
            totalUcoCo = proc.getCo() * conv.getUcoHm();
            totalCh = proc.getCh() * conv.getValorChHm();
        }

        Float totalFilme = proc.getQtdFilme() * conv.getValorFilme();

        resultados.add(0,totalUcoCo);
        resultados.add(1,totalPorteMed);
        resultados.add(2,totalCh);
        resultados.add(3,totalFilme);

        return resultados;
    }
}
