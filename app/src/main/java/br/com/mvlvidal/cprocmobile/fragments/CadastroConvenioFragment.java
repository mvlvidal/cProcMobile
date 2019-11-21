package br.com.mvlvidal.cprocmobile.fragments;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.com.mvlvidal.cprocmobile.R;
import br.com.mvlvidal.cprocmobile.adapter.ArrayAdapterProcedimento;
import br.com.mvlvidal.cprocmobile.adapter.ArrayAdapterTabelaPorte;
import br.com.mvlvidal.cprocmobile.adapter.ArrayAdapterTabelaProc;
import br.com.mvlvidal.cprocmobile.dao.TabelaPortesDaoImpl;
import br.com.mvlvidal.cprocmobile.dao.TabelaProcedimentoDaoImpl;
import br.com.mvlvidal.cprocmobile.model.Convenio;
import br.com.mvlvidal.cprocmobile.model.TabelaPortes;
import br.com.mvlvidal.cprocmobile.model.TabelaProcedimento;

public class CadastroConvenioFragment extends Fragment {

    private Button btSalvar;
    private Button btCancelar;
    private FragmentActivity fragmentActivity;
    private EditText etNome, etPercPorteHm, etUcoHm, etChHm, etPercPorteSadt, etUcoSadt, etChSadt;
    private Spinner spTbHm, spPorteHm, spTbSadt, spPorteSadt;
    private List<TabelaPortes> tabelasPorte;
    private List<TabelaProcedimento> tabelasProcedimento;

    public CadastroConvenioFragment() {
        // Required empty public constructor
    }

    public static CadastroConvenioFragment newInstance() {
        CadastroConvenioFragment fragment = new CadastroConvenioFragment();

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_cadastro_convenio, container, false);

        btSalvar = v.findViewById(R.id.btnSalvar);
        btCancelar = v.findViewById(R.id.btnCancelar);

        etChHm = v.findViewById(R.id.etChHm);
        etNome = v.findViewById(R.id.etNome);
        etPercPorteHm = v.findViewById(R.id.etPercPorteHm);
        etUcoHm = v.findViewById(R.id.etUcoHm);
        etPercPorteSadt = v.findViewById(R.id.etPercPorteSadt);
        etUcoSadt = v.findViewById(R.id.etUcoSadt);
        etChSadt = v.findViewById(R.id.etChSadt);
        spTbHm = v.findViewById(R.id.spTbHm);
        spPorteHm = v.findViewById(R.id.spPorteHm);
        spTbSadt = v.findViewById(R.id.spTbSadt);
        spPorteSadt = v.findViewById(R.id.spPorteSadt);

        btSalvar.setOnClickListener(clickSalvar);
        btCancelar.setOnClickListener(clickCancelar);

        //Spinners
        TabelaPortesDaoImpl tbPortesDao = new TabelaPortesDaoImpl(this.fragmentActivity);
        TabelaProcedimentoDaoImpl tbProcDao = new TabelaProcedimentoDaoImpl();
        tabelasPorte = tbPortesDao.buscarTodos();
        tabelasProcedimento = tbProcDao.listar();
        ArrayAdapterTabelaProc adapterTabProc = new ArrayAdapterTabelaProc(this.fragmentActivity, tabelasProcedimento);
        ArrayAdapterTabelaPorte adapterTabPorte = new ArrayAdapterTabelaPorte(this.fragmentActivity, tabelasPorte);

        spPorteHm.setAdapter(adapterTabPorte);
        spPorteSadt.setAdapter(adapterTabPorte);
        spTbHm.setAdapter(adapterTabProc);
        spTbSadt.setAdapter(adapterTabProc);

        return v;
    }

    //-------------------------------------------------------------------------//

    View.OnClickListener clickSalvar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //TabelaPortes tabPortes = new TabelaPortes();
            //TabelaProcedimento tabProcededimento = new TabelaProcedimento();
            //Convenio conv = new Convenio();
            //TODO: Função Salvar Convênio



        }
    };

    //------------------------------------------------------------------------//

    View.OnClickListener clickCancelar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, InicioFragment.newInstance());
            ft.commit();

        }
    };

}
