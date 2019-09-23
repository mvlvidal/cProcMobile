package com.example.cprocmobile.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cprocmobile.R;

public class ListaConveniosFragment extends Fragment {

    public ListaConveniosFragment(){

    }

    public static ListaConveniosFragment newInstance(){

        ListaConveniosFragment fragment = new ListaConveniosFragment();

        Bundle args = new Bundle();

        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_convenios, container, false);
    }


}
