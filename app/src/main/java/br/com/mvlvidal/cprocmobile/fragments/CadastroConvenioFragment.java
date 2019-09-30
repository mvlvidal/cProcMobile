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

import br.com.mvlvidal.cprocmobile.R;

public class CadastroConvenioFragment extends Fragment {

    private Button btSalvar;
    private Button btCancelar;
    private FragmentActivity fragmentActivity;

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

        btSalvar.setOnClickListener(clickSalvar);
        btCancelar.setOnClickListener(clickCancelar);

        return v;
    }

    //-------------------------------------------------------------------------//

    View.OnClickListener clickSalvar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

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
