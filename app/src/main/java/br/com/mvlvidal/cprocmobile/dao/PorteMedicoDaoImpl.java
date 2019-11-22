package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import br.com.mvlvidal.cprocmobile.model.PorteMedico;

public class PorteMedicoDaoImpl implements PorteMedicoDao {

    private ConnectionFactory factory;

    public PorteMedicoDaoImpl(ConnectionFactory factory) {
        this.factory = factory;
    }


    @Override
    public PorteMedico buscarPorId(Long id) {

        SQLiteDatabase db = factory.conectarLeitura();

        return null;
    }

    @Override
    public List<PorteMedico> buscarTodos() {

        SQLiteDatabase db = factory.conectarLeitura();

        return null;
    }

}
