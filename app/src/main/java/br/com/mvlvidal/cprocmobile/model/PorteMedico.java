package br.com.mvlvidal.cprocmobile.model;

public class PorteMedico extends AbstractModel {

    private String nome; //Tipo: 3A
    private Float valor;
    private TabelaPortes tabelaPortes;

    public PorteMedico(Long id, String nome, Float valor, TabelaPortes tabela){
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tabelaPortes = tabela;
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

    public TabelaPortes getTabelaPortes() {
        return tabelaPortes;
    }

    public void setTabelaPortes(TabelaPortes tabelaPortes) {
        this.tabelaPortes = tabelaPortes;
    }
}
