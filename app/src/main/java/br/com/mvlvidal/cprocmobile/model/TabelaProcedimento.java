package br.com.mvlvidal.cprocmobile.model;

public class TabelaProcedimento {

    private String cod;
    private String nome;

    public TabelaProcedimento(String cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
