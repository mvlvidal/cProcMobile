package br.com.mvlvidal.cprocmobile.dao;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

        if(c.moveToFirst()) {
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
            String where1 = "tabela = ? AND tipo = ? OR tabela = ? AND tipo = ?";
            String[] where2 = {tbHm,"hm",tbSadt, "sadt"};
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
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle("Erro!");
            dlg.setMessage(e.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }


        return retorno;
    }

    @Override
    public List<Procedimento> filtrarTodos(String filtro) {

        List<Procedimento> retorno = new ArrayList<>();

        if(filtro.equals("")) {
            SQLiteDatabase db = factory.conectarLeitura();
            String tabela = "procedimento";
            String[] campos = new String[]{"_id", "descricao", "codigo", "tabela"};
            String where1 = "descricao LIKE '%" + filtro + "%' OR codigo LIKE '%" + filtro + "%'";
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

                Procedimento proc = new Procedimento(colId, colDescricao, colCodigo, colTabela);

                retorno.add(proc);
            }
        }

        return retorno;
    }

    public List<String> calcularProcedimento(Long idConv, Long idProc){

        List<String> resultados = new ArrayList<>();

        SQLiteDatabase db = factory.conectarLeitura();

        ConvenioDaoImpl cdao = new ConvenioDaoImpl(factory);
        ProcedimentoDaoImpl procdao = new ProcedimentoDaoImpl(factory);
        PorteMedicoDaoImpl pmeddao = new PorteMedicoDaoImpl(factory);

        Convenio conv = cdao.buscarPorId(idConv);


        //Procedimento -----------------------------------------
        Procedimento proc = procdao.buscarPorId(idProc);
        String porteMed = proc.getPorteMedico();
        Long tabPorte = proc.getTipo().equals("sadt") ? conv.getTabelaPortesSadt().getId() : conv.getTabelaPortesHm().getId();

        PorteMedico pMed = pmeddao.buscarPorNomeETabela(porteMed, tabPorte);

        Float percPorte = proc.getTipo().equals("sadt") ? conv.getPercPorteSadt() : conv.getPercPorteHm(); //Percentual sobre PorteMedico de acordo com tipo de procedimento
        Float uco = proc.getTipo().equals("sadt") ? conv.getUcoSadt() : conv.getUcoHm(); //UCO de acordo com tipo de procedimento
        Float valorCh = proc.getTipo().equals("sadt") ? conv.getValorChSadt() : conv.getValorChHm(); //Valor CH de acordo com tipo de procedimento

        Float valorPorteMed = (porteMed == null) ? 0.0f : (pMed.getValor() * proc.getPercPorte()) * percPorte; //Se o convenio não usa CH retorna zero
        Float valorUcoCo = uco * proc.getCo();
        Float valorFilme = proc.getQtdFilme() * conv.getValorFilme();
        Float valorTotalCh = proc.getCh() * valorCh;

        NumberFormat floatFormat = new DecimalFormat("0.00");

        resultados.add("R$ "+floatFormat.format(valorPorteMed));
        resultados.add("R$ "+floatFormat.format(valorUcoCo));
        resultados.add("R$ "+floatFormat.format(valorFilme));
        resultados.add("R$ "+floatFormat.format(valorTotalCh));
        resultados.add("R$ "+floatFormat.format(valorPorteMed+valorUcoCo+valorFilme+valorTotalCh));
        return resultados;
    }

}
