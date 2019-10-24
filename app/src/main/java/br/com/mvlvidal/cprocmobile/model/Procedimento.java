package br.com.mvlvidal.cprocmobile.model;

public class Procedimento extends AbstractModel {

    private String descricao;
    private Float ch;
    private Float co;
    private String porteMedico;
    private Integer porteAnestesico;
    private Float qtdFilme;
    private Integer qtdAuxilio;
    private String tipo;
    private String tabela;
    private Float percPorte;

    public Procedimento(Long id,Float ch, String descricao, Float co, String porteMedico, Integer porteAnestesico, Float qtdFilme, Integer qtdAuxilio, String tipo, String tabela, Float percPorte) {
        this.id = id;
        this.ch = ch;
        this.descricao = descricao;
        this.co = co;
        this.porteMedico = porteMedico;
        this.porteAnestesico = porteAnestesico;
        this.qtdFilme = qtdFilme;
        this.qtdAuxilio = qtdAuxilio;
        this.tipo = tipo;
        this.tabela = tabela;
        this.percPorte = percPorte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getCo() {
        return co;
    }

    public Float getCh() {
        return ch;
    }

    public void setCh(Float ch) {
        this.ch = ch;
    }

    public void setCo(Float co) {
        this.co = co;
    }

    public String getPorteMedico() {
        return porteMedico;
    }

    public void setPorteMedico(String porteMedico) {
        this.porteMedico = porteMedico;
    }

    public Integer getPorteAnestesico() {
        return porteAnestesico;
    }

    public void setPorteAnestesico(Integer porteAnestesico) {
        this.porteAnestesico = porteAnestesico;
    }

    public Float getQtdFilme() {
        return qtdFilme;
    }

    public void setQtdFilme(Float qtdFilme) {
        this.qtdFilme = qtdFilme;
    }

    public Integer getQtdAuxilio() {
        return qtdAuxilio;
    }

    public void setQtdAuxilio(Integer qtdAuxilio) {
        this.qtdAuxilio = qtdAuxilio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public Float getPercPorte() {
        return percPorte;
    }

    public void setPercPorte(Float percPorte) {
        this.percPorte = percPorte;
    }
}
