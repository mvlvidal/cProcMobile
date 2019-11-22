package br.com.mvlvidal.cprocmobile.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public final class ConnectionFactory {

    private final Banco banco;
    private final String nomeBanco = "procdados.db";
    private final int versao = 1;

    public ConnectionFactory(Context context){
        this.banco = new Banco(context, nomeBanco,null, versao);
    }

    public SQLiteDatabase conectarLeitura() throws SQLiteException {
        return banco.getReadableDatabase();
    }

    public SQLiteDatabase conectarEscrita() throws SQLiteException{
        return banco.getWritableDatabase();
    }
}
