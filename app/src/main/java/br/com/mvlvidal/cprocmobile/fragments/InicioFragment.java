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
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

import br.com.mvlvidal.cprocmobile.R;
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
    private List<Procedimento> procedimentos = new ArrayList<>();
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
    public void onAttach(Activity activity) {
        this.fragmentActivity = (FragmentActivity)activity;
        super.onAttach(fragmentActivity);
        this.context = fragmentActivity;
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
        ArrayAdapter<Convenio> arrayConvenio = new ArrayAdapter<Convenio>(this.fragmentActivity, android.R.layout.simple_list_item_1, convenios);
        spnConv.setAdapter(arrayConvenio);

        //Lista Procedimentos
        listaProcs = v.findViewById(R.id.listaProcedimentos);
        listaProcs.setOnItemClickListener(clickProc);

        return v;
    }

    //----------------------------------------------------------------------//

    View.OnClickListener clickSelecionar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Convenio conv = (Convenio) spnConv.getSelectedItem();
            String sadt = conv.getTabSadt();
            String hm = conv.getTabHm();
            f = new ConnectionFactory(fragmentActivity);
            procDao = new ProcedimentoDaoImpl(f);
            procedimentos = procDao.buscarTodos(conv);
            ArrayAdapter<Procedimento> adapterProcedimento = new ArrayAdapter<Procedimento>(fragmentActivity,android.R.layout.simple_list_item_1,procedimentos);
            listaProcs.setAdapter(adapterProcedimento);

        }
    };

    //----------------------------------------------------------------------//

    AdapterView.OnItemClickListener clickProc = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Procedimento proc = (Procedimento) parent.getItemAtPosition(position);
            Convenio conv = (Convenio) spnConv.getSelectedItem();

            Long idConv = conv.getId();
            Long idProc = proc.getId();

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, CalculoFragment.newInstance(idConv,idProc));
            ft.commit();
        }
    };

    //-------------------------------------------------------------------------//

}
