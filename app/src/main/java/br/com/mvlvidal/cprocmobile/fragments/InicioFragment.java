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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.mvlvidal.cprocmobile.MainActivity;
import br.com.mvlvidal.cprocmobile.R;
import br.com.mvlvidal.cprocmobile.adapter.ArrayAdapterConvenio;
import br.com.mvlvidal.cprocmobile.adapter.ArrayAdapterProcedimento;
import br.com.mvlvidal.cprocmobile.dao.ConnectionFactory;
import br.com.mvlvidal.cprocmobile.dao.ConvenioDaoImpl;
import br.com.mvlvidal.cprocmobile.dao.ProcedimentoDaoImpl;
import br.com.mvlvidal.cprocmobile.model.Convenio;
import br.com.mvlvidal.cprocmobile.model.Procedimento;

public class InicioFragment extends Fragment {

    private Button btSelecionar;
    private Button btProcLista;
    private Spinner spnConv;
    private FragmentActivity fragmentActivity;
    private List<Convenio> convenios;
    private ListView listaProcs;
    private List<Procedimento> procedimentos;
    private ProcedimentoDaoImpl procDao;
    private ConnectionFactory f;
    private Context context;

    public InicioFragment() {
        // Required empty public constructor
    }

    public static InicioFragment newInstance() {

        InicioFragment fragment = new InicioFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_inicio, container, false);

        f = new ConnectionFactory(context);
        btSelecionar = v.findViewById(R.id.btnSelecionar);
        btSelecionar.setOnClickListener(clickSelecionar);

        //Spinner Convenios

        spnConv = v.findViewById(R.id.spinnerConv);;
        ConvenioDaoImpl cdao = new ConvenioDaoImpl(f);
        convenios = cdao.buscarTodos();
        ArrayAdapterConvenio arrayConvenio = new ArrayAdapterConvenio(context, convenios);
        spnConv.setAdapter(arrayConvenio);

        //Lista Procedimentos
        listaProcs = v.findViewById(R.id.listaProcedimentos);


        return v;
    }

    //----------------------------------------------------------------------//

    View.OnClickListener clickSelecionar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            /**
            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft =  fm.beginTransaction();
            ft.replace(R.id.frame, CadastroConvenioFragment.newInstance());
            ft.commit();
            */
            Convenio conv = (Convenio) spnConv.getSelectedItem();
            String sadt = conv.getTabHm();
            String hm = conv.getTabSadt();
            procDao = new ProcedimentoDaoImpl(f);
            procedimentos = new ArrayList<>();
            procedimentos = procDao.buscarTodos(hm,sadt);
            System.out.println("@@@@@@@ " + hm + sadt);
            ArrayAdapterProcedimento adapterProcedimento = new ArrayAdapterProcedimento(context, procedimentos);
            listaProcs.setAdapter(adapterProcedimento);
        }
    };



    //----------------------------------------------------------------------//

    /**
    View.OnClickListener clickProcLista = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, DetalheProcFragment.newInstance());
            ft.commit();
        }
    };
    */
    //-------------------------------------------------------------------------//

}
