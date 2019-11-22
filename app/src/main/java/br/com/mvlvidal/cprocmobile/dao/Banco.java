package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.mvlvidal.cprocmobile.util.InsertsPortes;
import br.com.mvlvidal.cprocmobile.util.InsertsProcedimentos;

public class Banco extends SQLiteOpenHelper {

    public Banco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static final String CREATE_CONVENIO = "create table if not exists convenio(" +
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
            ",idTabPortesHm Integer"+
            ",idTabPortesSadt Integer"+
            ",foreign key(idTabPortesHm) references tabelaPortes(_id)"+
            ",foreign key(idTabPortesSadt) references tabelaPortes(_id)"+
            ")";

    public static final String CREATE_PROCEDIMENTO = "create table if not exists procedimento(" +
            "_id Integer primary key autoincrement," +
            "descricao varchar(40),"+
            "codigo Integer," +
            "ch Float," +
            "co Float," +
            "porteMedico varchar(3)," +
            "porteAnestesico Integer," +
            "qtdFilme Float," +
            "qtdAuxilio Integer," +
            "tipo varchar(4)," +
            "tabela varchar(20)," +
            "percPorte Float" +
            ")";

    private static final String CREATE_TABPORTE = "create table if not exists tabelaportes(" +
            "_id Integer primary key" +
            ", nome varchar(40)" +
            ");";

    private static final String CREATE_PORTEMEDICO = "create table if not exists porteMedico(" +
            "_id Integer primary key autoincrement"+
            ", nome varchar(40)"+
            ", valor Float"+
            ", _idTabela Integer"+
            ", foreign key(_idTabela) references tabelaportes(_id)"+
            ")";

    private InsertsPortes iPortes;
    private InsertsProcedimentos iProcedimentos;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CONVENIO);

        db.execSQL("insert into convenio (nome,ucoSadt,ucoHm,valorChHm,valorChSadt,tabHm,tabSadt" +
                ",percPorteHm,percPorteSadt,idTabPortesHm,idTabPortesSadt) values ('CNU', 10.0,10.0,0,0,'cbhpm5','amb92',1.0,1.0,1,1)");

        db.execSQL(CREATE_PROCEDIMENTO);
        iProcedimentos = new InsertsProcedimentos();
        db.execSQL(iProcedimentos.inserir());

        db.execSQL(CREATE_TABPORTE);
        iPortes = new InsertsPortes();
        db.execSQL(iPortes.inserir());


        db.execSQL(CREATE_PORTEMEDICO);

        db.execSQL("insert into porteMedico (nome, valor, _idTabela) values ('3A',100.0,1)");
        db.execSQL("insert into porteMedico (nome, valor, _idTabela) values ('8A',500.50,2)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}