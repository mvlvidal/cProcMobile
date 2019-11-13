package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import br.com.mvlvidal.cprocmobile.model.PorteMedico;

public class PorteMedicoDaoImpl extends SQLiteOpenHelper implements PorteMedicoDao {

    Context context;

    public PorteMedicoDaoImpl(Context context) {
        super(context, "procDados.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists porteMedico(" +
                "_id Integer primary key autoincrement"+
                ", nome varchar(40)"+
                ", valor Float"+
                ", _idTabela Integer"+
                ", foreign key(id_Tabela) references tabelaPortes(_id)"+
                ")";
        db.execSQL(sql);

        db.execSQL("insert into porteMedico (nome, valor, _idTabela) values ('3A',100.0,1)");
        db.execSQL("insert into porteMedico (nome, valor, _idTabela) values ('8A',500.50,2)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public PorteMedico buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<PorteMedico> buscarTodos() {
        return null;
    }

}
