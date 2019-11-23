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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.com.mvlvidal.cprocmobile.R;
import br.com.mvlvidal.cprocmobile.dao.ConnectionFactory;
import br.com.mvlvidal.cprocmobile.dao.ConvenioDaoImpl;
import br.com.mvlvidal.cprocmobile.dao.TabelaPortesDaoImpl;
import br.com.mvlvidal.cprocmobile.dao.TabelaProcedimentoDaoImpl;
import br.com.mvlvidal.cprocmobile.model.Convenio;
import br.com.mvlvidal.cprocmobile.model.TabelaPortes;
import br.com.mvlvidal.cprocmobile.model.TabelaProcedimento;

public class CadastroConvenioFragment extends Fragment {

    private Button btSalvar;
    private Button btCancelar;
    private FragmentActivity fragmentActivity;
    private EditText etId,etNome, etPercPorteHm, etUcoHm, etChHm, etPercPorteSadt, etUcoSadt, etChSadt, etValorFIlme;
    private Spinner spTbHm, spPorteHm, spTbSadt, spPorteSadt;
    private List<TabelaPortes> tabelasPorte;
    private List<TabelaProcedimento> tabelasProcedimento;
    private Context context;

    public CadastroConvenioFragment() {
        // Required empty public constructor
    }

    public static CadastroConvenioFragment newInstance() {
        CadastroConvenioFragment fragment = new CadastroConvenioFragment();

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

        View v = inflater.inflate(R.layout.fragment_cadastro_convenio, container, false);

        ConnectionFactory f = new ConnectionFactory(this.fragmentActivity);
        btSalvar = v.findViewById(R.id.btnSalvar);
        btCancelar = v.findViewById(R.id.btnCancelar);

        etId = v.findViewById(R.id.etId);
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
        etValorFIlme = v.findViewById(R.id.etValorFilme);

        btSalvar.setOnClickListener(clickSalvar);
        btCancelar.setOnClickListener(clickCancelar);

        //Spinners
        TabelaPortesDaoImpl tbPortesDao = new TabelaPortesDaoImpl(f);
        TabelaProcedimentoDaoImpl tbProcDao = new TabelaProcedimentoDaoImpl();
        tabelasPorte = tbPortesDao.buscarTodos();
        tabelasProcedimento = tbProcDao.listar();
        ArrayAdapter<TabelaProcedimento> adapterTabProc = new ArrayAdapter<TabelaProcedimento>(this.fragmentActivity,android.R.layout.simple_spinner_dropdown_item,tabelasProcedimento);
        ArrayAdapter<TabelaPortes> adapterTabPorte = new ArrayAdapter<TabelaPortes>(this.fragmentActivity,android.R.layout.simple_spinner_dropdown_item,tabelasPorte);

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

            try {
                ConnectionFactory f = new ConnectionFactory(context);

                TabelaPortes tabPortesHm = (TabelaPortes) spPorteHm.getSelectedItem();
                TabelaPortes tabPortesSadt = (TabelaPortes) spPorteSadt.getSelectedItem();
                TabelaProcedimento tabProcHm = (TabelaProcedimento) spTbHm.getSelectedItem();
                TabelaProcedimento tabProcSadt = (TabelaProcedimento) spTbSadt.getSelectedItem();

                String nome = etNome.getText().toString();
                Float percPorteHm = Float.parseFloat(etPercPorteHm.getText().toString());
                Float ucoHm = Float.parseFloat(etUcoHm.getText().toString());
                Float valorChHm = Float.parseFloat(etChHm.getText().toString());
                Float percPorteSadt = Float.parseFloat(etPercPorteSadt.getText().toString());
                Float ucoSadt = Float.parseFloat(etUcoSadt.getText().toString());
                Float valorChSadt = Float.parseFloat(etChSadt.getText().toString());
                Float valorFilme = Float.parseFloat(etValorFIlme.getText().toString());
                String tabHm = tabProcHm.getCod();
                String tabSadt = tabProcSadt.getCod();

                Convenio convenio;

                if (etId.getText().toString().equals("")) {
                    convenio = new Convenio(nome, ucoSadt, ucoHm, valorChHm, valorChSadt, tabHm, tabSadt, percPorteHm, percPorteSadt, valorFilme, tabPortesHm, tabPortesSadt);
                } else {
                    Long id = Long.valueOf(etId.getText().toString());
                    convenio = new Convenio(id, nome, ucoSadt, ucoHm, valorChHm, valorChSadt, tabHm, tabSadt, percPorteHm, percPorteSadt, valorFilme, tabPortesHm, tabPortesSadt);
                }

                ConvenioDaoImpl cdao = new ConvenioDaoImpl(f);

                if (cdao.salvar(convenio)) {
                    Toast.makeText(context, "Convênio cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    FragmentManager fm = fragmentActivity.getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frame, InicioFragment.newInstance());
                    ft.commit();
                }
            }catch(Exception e){
                Toast.makeText(context, "Erro: "+ e.getMessage(), Toast.LENGTH_LONG).show();
            }
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
