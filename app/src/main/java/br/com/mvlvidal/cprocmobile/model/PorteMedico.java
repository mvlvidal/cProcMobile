package br.com.mvlvidal.cprocmobile.model;

public class PorteMedico extends AbstractModel {

    private String nome; //Tipo: CBHPM 5ª 2009
    private String porte; //Tipo: 3A
    private Float valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
