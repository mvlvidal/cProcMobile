package br.com.mvlvidal.cprocmobile.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.mvlvidal.cprocmobile.R;
import br.com.mvlvidal.cprocmobile.dao.ConnectionFactory;
import br.com.mvlvidal.cprocmobile.dao.ConvenioDaoImpl;
import br.com.mvlvidal.cprocmobile.model.Convenio;

public class ListaConveniosFragment extends Fragment {

    private Button btnNovoConv;
    private ListView listaConvenios;
    private FragmentActivity fragmentActivity;
    private Context context;
    private List<Convenio> convenios;

    public ListaConveniosFragment() {
        // Required empty public constructor
    }

    public static ListaConveniosFragment newInstance() {
        ListaConveniosFragment fragment = new ListaConveniosFragment();

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        this.fragmentActivity = (FragmentActivity)activity;
        super.onAttach(fragmentActivity);
        this.context = fragmentActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_convenios, container, false);

        ConnectionFactory f = new ConnectionFactory(context);

        btnNovoConv = v.findViewById(R.id.btnNovoConv);
        btnNovoConv.setOnClickListener(clickNovo);

        //Lista Convenios
        ConvenioDaoImpl cdao = new ConvenioDaoImpl(f);
        convenios = cdao.buscarTodos();
        listaConvenios = v.findViewById(R.id.listaConvenios);
        ArrayAdapter<Convenio> adapterConvenios = new ArrayAdapter<Convenio>(this.fragmentActivity,android.R.layout.simple_list_item_1,convenios);
        listaConvenios.setAdapter(adapterConvenios);
        listaConvenios.setOnItemClickListener(clickConv);
        return v;
    }

    //------------------------------------------------------------------/

    AdapterView.OnItemClickListener clickConv = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, DetalheConvFragment.newInstance());
            ft.commit();
        }
    };

    //-----------------------------------------------------------------------//

    View.OnClickListener clickNovo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, CadastroConvenioFragment.newInstance());
            ft.commit();
        }
    };
}
