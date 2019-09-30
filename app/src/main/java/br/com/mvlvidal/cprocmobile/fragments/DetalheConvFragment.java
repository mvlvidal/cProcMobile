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

public class DetalheConvFragment extends Fragment {

    private Button btEditar;
    private Button btVoltar;
    private FragmentActivity fragmentActivity;

    public DetalheConvFragment() {
        // Required empty public constructor
    }

    public static DetalheConvFragment newInstance() {
        DetalheConvFragment fragment = new DetalheConvFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detalhe_conv, container, false);

        btEditar = v.findViewById(R.id.btnEditar);
        btVoltar = v.findViewById(R.id.btnVoltar);
        btVoltar.setOnClickListener(clickVoltar);
        btEditar.setOnClickListener(clickEditar);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {

        fragmentActivity = (FragmentActivity) activity;

        super.onAttach(activity);

    }

    //-----------------------------------------------------------------------//

    View.OnClickListener clickVoltar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, ListaConveniosFragment.newInstance());
            ft.commit();

        }
    };

    //------------------------------------------------------------------------//

    View.OnClickListener clickEditar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, CadastroConvenioFragment.newInstance());
            ft.commit();
        }
    };
}
