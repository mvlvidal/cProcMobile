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

public class InicioFragment extends Fragment {

    private Button btNovo;
    private Button btProcLista;
    private FragmentActivity fragmentActivity;

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
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_inicio, container, false);

        btNovo = v.findViewById(R.id.btnNovo);
        btProcLista = v.findViewById(R.id.btnList);

        btNovo.setOnClickListener(clickNovo);
        btProcLista.setOnClickListener(clickProcLista);

        return v;
    }

    //----------------------------------------------------------------------//
    View.OnClickListener clickNovo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft =  fm.beginTransaction();
            ft.replace(R.id.frame, CadastroConvenioFragment.newInstance());
            ft.commit();

        }
    };

    //----------------------------------------------------------------------//

    View.OnClickListener clickProcLista = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, DetalheProcFragment.newInstance());
            ft.commit();
        }
    };

}
