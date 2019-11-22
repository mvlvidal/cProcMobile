package br.com.mvlvidal.cprocmobile.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.com.mvlvidal.cprocmobile.R;
import br.com.mvlvidal.cprocmobile.model.Procedimento;

public class ArrayAdapterProcedimento extends ArrayAdapter<Procedimento> {

    Context context;
    List<Procedimento> procedimentos;

    public ArrayAdapterProcedimento(Context context, List<Procedimento> procedimentos) {
        super(context, android.R.layout.simple_list_item_1, procedimentos);

        this.context = context;
        this.procedimentos = procedimentos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.adapter_procedimento, parent, false);

        Procedimento procedimento = procedimentos.get(position);

        TextView txtCodigo = v.findViewById(R.id.txtCodigo);
        TextView txtDescricao = v.findViewById(R.id.txtDescricao);

        txtCodigo.setText(String.valueOf(procedimento.getCodigo()));
        txtDescricao.setText(procedimento.getDescricao());

        return v;
    }
}
