package br.com.mvlvidal.cprocmobile.model;

public class Convenio extends AbstractModel {

    private String nome;
    private Float ucoSadt;
    private Float ucoHm;
    private Float valorChHm;
    private Float valorChSadt;
    private String tabHm;
    private String tabSadt;
    private Float percPorteHm;
    private Float percPorteSadt;

    public Convenio(Long id, String nome, Float ucoSadt, Float ucoHm, Float valorChHm, Float valorChSadt, String tabHm, String tabSadt, Float percPorteHm, Float percPorteSadt) {
        this.id = id;
        this.nome = nome;
        this.ucoSadt = ucoSadt;
        this.ucoHm = ucoHm;
        this.valorChHm = valorChHm;
        this.valorChSadt = valorChSadt;
        this.tabHm = tabHm;
        this.tabSadt = tabSadt;
        this.percPorteHm = percPorteHm;
        this.percPorteSadt = percPorteSadt;
    }

    public Convenio(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getUcoSadt() {
        return ucoSadt;
    }

    public void setUcoSadt(Float ucoSadt) {
        this.ucoSadt = ucoSadt;
    }

    public Float getUcoHm() {
        return ucoHm;
    }

    public void setUcoHm(Float ucoHm) {
        this.ucoHm = ucoHm;
    }

    public Float getValorChHm() {
        return valorChHm;
    }

    public void setValorChHm(Float valorChHm) {
        this.valorChHm = valorChHm;
    }

    public Float getValorChSadt() {
        return valorChSadt;
    }

    public void setValorChSadt(Float valorChSadt) {
        this.valorChSadt = valorChSadt;
    }

    public String getTabHm() {
        return tabHm;
    }

    public void setTabHm(String tabHm) {
        this.tabHm = tabHm;
    }

    public String getTabSadt() {
        return tabSadt;
    }

    public void setTabSadt(String tabSadt) {
        this.tabSadt = tabSadt;
    }

    public Float getPercPorteHm() {
        return percPorteHm;
    }

    public void setPercPorteHm(Float percPorteHm) {
        this.percPorteHm = percPorteHm;
    }

    public Float getPercPorteSadt() {
        return percPorteSadt;
    }

    public void setPercPorteSadt(Float percPorteSadt) {
        this.percPorteSadt = percPorteSadt;
    }
}
