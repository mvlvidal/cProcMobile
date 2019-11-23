package br.com.mvlvidal.cprocmobile.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private EditText etTotalUcoCo,etTotalPorteMedico,etTotalCh,etTotalFilme;

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

        etTotalCh = v.findViewById(R.id.etTotalCh);
        etTotalFilme = v.findViewById(R.id.etValorFilme);
        etTotalPorteMedico = v.findViewById(R.id.etTotalPorteMedico);
        etTotalUcoCo = v.findViewById(R.id.etTotalUcoCo);


        if(this.getArguments() != null){
            idConv = this.getArguments().getLong("idConv");
            idProc = this.getArguments().getLong("idProc");
        }

        ConnectionFactory f = new ConnectionFactory(context);
        ProcedimentoDaoImpl procDao = new ProcedimentoDaoImpl(f);
        Procedimento proc = procDao.buscarPorId(idProc);

        List<Float> resultado = procDao.calcularProcedimento(idConv, idProc);

        etTotalUcoCo.setText(String.valueOf(resultado.get(0)));
        etTotalPorteMedico.setText(String.valueOf(resultado.get(1)));
        etTotalCh.setText(String.valueOf(resultado.get(2)));
        etTotalFilme.setText(String.valueOf(resultado.get(3)));

        System.out.println("@@@@@@@@@@@@@@@@@@");
        System.out.println(idConv);
        System.out.println(idProc);

        return v;
    }

}
