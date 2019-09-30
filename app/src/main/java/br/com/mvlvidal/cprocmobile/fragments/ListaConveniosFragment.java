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

public class ListaConveniosFragment extends Fragment {

    private Button btnConv;
    private FragmentActivity fragmentActivity;

    public ListaConveniosFragment() {
        // Required empty public constructor
    }

    public static ListaConveniosFragment newInstance() {
        ListaConveniosFragment fragment = new ListaConveniosFragment();

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
        View v = inflater.inflate(R.layout.fragment_lista_convenios, container, false);

        btnConv = v.findViewById(R.id.btnConvList);
        btnConv.setOnClickListener(clickConv);

        return v;
    }

    //------------------------------------------------------------------/

    View.OnClickListener clickConv = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frame, DetalheConvFragment.newInstance());
            ft.commit();

        }
    };

}
