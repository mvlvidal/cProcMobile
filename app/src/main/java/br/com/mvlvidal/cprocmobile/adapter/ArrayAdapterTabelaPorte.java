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
import br.com.mvlvidal.cprocmobile.model.TabelaPortes;

public class ArrayAdapterTabelaPorte extends ArrayAdapter<TabelaPortes> {

    Context context;
    List<TabelaPortes> tabelasPortes;

    public ArrayAdapterTabelaPorte(Context context, List<TabelaPortes> tabelasPortes) {
        super(context, android.R.layout.simple_list_item_1, tabelasPortes);

        this.context = context;
        this.tabelasPortes = tabelasPortes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.adapter_tabelaportes, parent, false);

        TabelaPortes tabelaPortes = tabelasPortes.get(position);

        TextView txtNomeTabPortes = v.findViewById(R.id.txtNomeTabPortes);

        txtNomeTabPortes.setText(tabelaPortes.getNome());

        return v;
    }
}
