package br.com.mvlvidal.cprocmobile.util;

public class InsertsProcedimentos {

    public String inserir(){

        String SQL = "insert into procedimento (descricao,codigo,ch,co,porteMedico" +
                ",porteAnestesico,qtdFilme,qtdAuxilio,tipo,tabela,percPorte) values ('Consulta',10101012,'',1.5,'1A',0,0,0,'hm','cbhpm5',1.0);";

        return SQL;
    }

}
