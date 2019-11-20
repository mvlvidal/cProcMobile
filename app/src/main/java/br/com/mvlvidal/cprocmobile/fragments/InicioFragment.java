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
import android.widget.Spinner;
import java.util.List;
import br.com.mvlvidal.cprocmobile.R;
import br.com.mvlvidal.cprocmobile.adapter.ArrayAdapterConvenio;
import br.com.mvlvidal.cprocmobile.dao.ConvenioDaoImpl;
import br.com.mvlvidal.cprocmobile.model.Convenio;

public class InicioFragment extends Fragment {

    private Button btNovo;
    private Button btProcLista;
    private Spinner spnConv;
    private FragmentActivity fragmentActivity;
    List<Convenio> convenios;

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
        btNovo.setOnClickListener(clickNovo);

        //Spinner Convenios
        spnConv = v.findViewById(R.id.spinnerConv);
        ConvenioDaoImpl cdao = new ConvenioDaoImpl(this.fragmentActivity);
        convenios = cdao.buscarTodos();
        ArrayAdapterConvenio arrayConvenio = new ArrayAdapterConvenio(this.fragmentActivity, convenios);
        spnConv.setAdapter(arrayConvenio);

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
}
