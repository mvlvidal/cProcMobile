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
import br.com.mvlvidal.cprocmobile.model.Convenio;

public class ArrayAdapterConvenio extends ArrayAdapter<Convenio> {

    Context context;
    List<Convenio> convenios;

    public ArrayAdapterConvenio(Context context, List<Convenio> convenios) {
        super(context,android.R.layout.simple_list_item_1, convenios);

        this.context = context;
        this.convenios = convenios;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.adapter_convenio, parent, false);

        Convenio convenio = convenios.get(position);

        TextView txtNomeConv = v.findViewById(R.id.txtNomeConv);

        txtNomeConv.setText(convenio.getNome());

        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

}
