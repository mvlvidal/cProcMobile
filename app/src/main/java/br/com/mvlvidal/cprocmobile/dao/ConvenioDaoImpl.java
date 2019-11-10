package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import br.com.mvlvidal.cprocmobile.model.Convenio;

public class ConvenioDaoImpl extends SQLiteOpenHelper implements ConvenioDao {

    private Context context;

    public ConvenioDaoImpl(Context context) {
        super(context, "procDados.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists convenio(" +
                "_id Integer primary key autoincrement"+
                ", nome varchar(40)"+
                ", ucoSadt Float"+
                ", ucoHm Float"+
                ", valorChHm Float"+
                ", valorChSadt Float"+
                ", tabHm varchar(20)"+
                ", tabSadt varchar(20)"+
                ", percPorteHm Float"+
                ", percPorteSadt Float"+
                ")";

        db.execSQL(sql);

        db.execSQL("insert into convenio (nome,ucoSadt,ucoHm,valorChHm,valorChSadt,tabHm,tabSadt" +
                ",percPorteHm,percPorteSadt) values ('CNU', 10.0,10.0,0,0,'cbhpm5','amb92',1.0,1.0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void salvar(Convenio convenio) {

    }

    @Override
    public void editar(Convenio convenio) {

    }

    @Override
    public void deletar(Long id) {

    }

    @Override
    public Convenio buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Convenio> buscarTodos() {
        return null;
    }

}
