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

public class DetalheProcFragment extends Fragment {

    private Button btVoltar;
    private FragmentActivity fragmentActivity;

    public DetalheProcFragment() {
        // Required empty public constructor
    }

    public static DetalheProcFragment newInstance() {
        DetalheProcFragment fragment = new DetalheProcFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detalhe_proc, container, false);

        btVoltar = v.findViewById(R.id.btnVoltarProcs);
        btVoltar.setOnClickListener(clickVoltar);

        return v;
    }


    @Override
    public void onAttach(Activity activity) {

        fragmentActivity = (FragmentActivity) activity;

        super.onAttach(activity);

    }

    //----------------------------------------------------------------//

    View.OnClickListener clickVoltar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, InicioFragment.newInstance());
            ft.commit();
        }
    };

}
