package br.com.mvlvidal.cprocmobile.util;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.mvlvidal.cprocmobile.dao.ConnectionFactory;

public class InsertsProcedimentos {

    public List<String> inserir(){

        List<String> inserts = new ArrayList<>();

         inserts.add("insert into procedimento (descricao,codigo,ch,co,porteMedico,porteAnestesico,qtdFilme,qtdAuxilio,tipo,tabela,percPorte) values ('Consulta CBHPM',10101012,0.0,1.5,'1A',0,0,0,'hm','cbhpm5',1.0);");
         inserts.add("insert into procedimento (descricao,codigo,ch,co,porteMedico,porteAnestesico,qtdFilme,qtdAuxilio,tipo,tabela,percPorte) values ('Exame AMB',10101010,100.0,0,null,0,0,0,'sadt','amb92',1.0);");
         inserts.add("insert into procedimento (descricao,codigo,ch,co,porteMedico,porteAnestesico,qtdFilme,qtdAuxilio,tipo,tabela,percPorte) values ('Cirurgia CBHPM',30409020,0.0,1.5,'1A',0,0,0,'hm','cbhpm5',1.0);");
         inserts.add("insert into procedimento (descricao,codigo,ch,co,porteMedico,porteAnestesico,qtdFilme,qtdAuxilio,tipo,tabela,percPorte) values ('Exame CBHPM',30409020,0.0,1.5,'1A',0,0,0,'sadt','cbhpm5',1.0);");

         return inserts;
    }

}
