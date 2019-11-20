package br.com.mvlvidal.cprocmobile.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.mvlvidal.cprocmobile.R;

public class CalculoFragment extends Fragment {

    public CalculoFragment(){

    }

    public static CalculoFragment newInstance(){

        CalculoFragment fragment = new CalculoFragment();

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculo, container, false);
    }

}
