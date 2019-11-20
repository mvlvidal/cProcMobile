package br.com.mvlvidal.cprocmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import br.com.mvlvidal.cprocmobile.R;
import br.com.mvlvidal.cprocmobile.model.TabelaProcedimento;

public class ArrayAdapterTabelaProc extends ArrayAdapter<TabelaProcedimento> {

    List<TabelaProcedimento> tabelas;
    Context context;

    public ArrayAdapterTabelaProc(Context context,  List<TabelaProcedimento> tabelas) {
        super(context, android.R.layout.simple_list_item_1, tabelas);

        this.tabelas = tabelas;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.adapter_tabelaproc, parent, false);

        TabelaProcedimento tabProc = tabelas.get(position);

        TextView txtNomeTab = v.findViewById(R.id.txtNomeTabProc);

        txtNomeTab.setText(tabProc.getNome());

        return v;
    }
}
