package br.com.mvlvidal.cprocmobile.service;

import android.content.Context;

import br.com.mvlvidal.cprocmobile.dao.DbUtilDao;

public class ConvenioService extends DbUtilDao {


    public ConvenioService(Context context) {
        super(context);
    }

    public boolean salvar() {

        return true;
    }
}
