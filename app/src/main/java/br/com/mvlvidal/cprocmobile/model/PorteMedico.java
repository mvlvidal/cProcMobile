package br.com.mvlvidal.cprocmobile.model;

public class PorteMedico extends AbstractModel {

    private String tabPorte; //Tipo: CBHPM 5ª 2009
    private String nome; //Tipo: 3A
    private Float valor;

    public PorteMedico(Long id, String tabPorte, String nome, Float valor) {
        this.id = id;
        this.tabPorte = tabPorte;
        this.nome = nome;
        this.valor = valor;
    }

    public String getTabPorte() {
        return tabPorte;
    }

    public void setTabPorte(String tabPorte) {
        this.tabPorte = tabPorte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
