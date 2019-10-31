package br.com.mvlvidal.cprocmobile.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbUtilDao extends SQLiteOpenHelper {

    protected Context context;

    public DbUtilDao(Context context) {
        super(context, "procDados.db",null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String sqlConv = "create table if not exists convenio(" +
                "_id Integer primary key autoincrement," +
                "nome varchar(40)," +
                "ucoSadt Float," +
                "ucoHm Float," +
                "valorChHm Float," +
                "valorChSadt Float," +
                "tabHm varchar(20)," +
                "tabSadt varchar(20)," +
                "percPorteHm Float," +
                "percPorteSadt Float" +
                ")";

        db.execSQL(sqlConv);

        String sqlPorteMed = "create table if not exists porteMedico(" +
                "_id Integer primary key autoincrement," +
                "nome varchar(40)," +
                "porte varchar(3)," +
                "valor Float" +
                ")";

        db.execSQL(sqlPorteMed);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
