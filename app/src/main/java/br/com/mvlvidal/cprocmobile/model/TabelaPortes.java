package br.com.mvlvidal.cprocmobile.model;

public class TabelaPortes extends AbstractModel {

    private String nome;

    public TabelaPortes(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
