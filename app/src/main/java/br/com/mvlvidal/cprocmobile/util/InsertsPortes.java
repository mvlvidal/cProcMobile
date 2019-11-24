package br.com.mvlvidal.cprocmobile.util;

public class InsertsPortes {

    public String inserir(){

        String SQL = "insert into portemedico (nome, valor, _idTabela) values ('1A',100.0,1);" +
                "insert into portemedico (nome, valor, _idTabela) values ('2A',200.0,1);";

        return SQL;
    }

}
