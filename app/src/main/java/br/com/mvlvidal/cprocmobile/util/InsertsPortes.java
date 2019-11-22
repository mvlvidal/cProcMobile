package br.com.mvlvidal.cprocmobile.util;

public class InsertsPortes {

    public String inserir(){

        String SQL = "insert into portemedico (nome, valor, _idTabela) values ('3A',100.0,1);" +
                "insert into portemedico (nome, valor, _idTabela) values ('8A',500.50,2);";

        return SQL;
    }

}
