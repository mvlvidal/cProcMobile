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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.mvlvidal.cprocmobile.MainActivity;
import br.com.mvlvidal.cprocmobile.R;
import br.com.mvlvidal.cprocmobile.dao.ConnectionFactory;
import br.com.mvlvidal.cprocmobile.dao.ProcedimentoDaoImpl;
import br.com.mvlvidal.cprocmobile.model.Procedimento;

public class CalculoFragment extends Fragment {

    private FragmentActivity fragmentActivity;
    private Context context;
    private MainActivity main;
    private Long idConv, idProc;
    private TextView tvNomeProc;
    private EditText etTotalUcoCo,etTotalPorteMedico,etTotalCh,etTotalFilme, etTotal;
    private Button btnVoltar;

    public CalculoFragment(){

    }

    public static CalculoFragment newInstance(Long idConv, Long idProc){

        CalculoFragment fragment = new CalculoFragment();
        Bundle args = new Bundle();
        args.putLong("idConv",idConv);
        args.putLong("idProc", idProc);
        fragment.setArguments(args);
        return fragment;

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
        View v = inflater.inflate(R.layout.fragment_calculo, container, false);

        tvNomeProc = v.findViewById(R.id.tvNomeProc);
        etTotalCh = v.findViewById(R.id.etTotalCh);
        etTotalFilme = v.findViewById(R.id.etTotalFilme);
        etTotalPorteMedico = v.findViewById(R.id.etTotalPorteMedico);
        etTotalUcoCo = v.findViewById(R.id.etTotalUcoCo);
        etTotal = v.findViewById(R.id.etTotal);
        btnVoltar = v.findViewById(R.id.btnVoltarLista);


        if(this.getArguments() != null){
            idConv = this.getArguments().getLong("idConv");
            idProc = this.getArguments().getLong("idProc");
        }

        ConnectionFactory f = new ConnectionFactory(context);
        ProcedimentoDaoImpl procDao = new ProcedimentoDaoImpl(f);
        Procedimento proc = procDao.buscarPorId(idProc);

        List<String> resultado = procDao.calcularProcedimento(idConv, idProc);


        tvNomeProc.setText("Procedimento: "+proc.getCodigo()+" - "+proc.getDescricao());
        etTotalPorteMedico.setText(resultado.get(0));
        etTotalUcoCo.setText(resultado.get(1));
        etTotalFilme.setText(resultado.get(2));
        etTotalCh.setText(resultado.get(3));
        etTotal.setText(resultado.get(4));

        btnVoltar.setOnClickListener(clickVoltar);

        return v;
    }

    //--------------------------------------------------------------------------------//

    View.OnClickListener clickVoltar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, InicioFragment.newInstance());
            ft.commit();
        }
    };

    //--------------------------------------------------------------------------------//
}
