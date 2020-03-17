package sk.euba.fhi.model.obj;

public class Prihlasenie {
    private Integer id_pouzivatel = 0;
    private Integer rok = 0;
    private Integer id_firma = 0;
    private String firma_nazov = "";
    private Integer id_ucet = 0;
    private String ucet_nazov = "";

    public Integer getId_pouzivatel() {
        return id_pouzivatel;
    }

    public void setId_pouzivatel(Integer id_pouzivatel) {
        this.id_pouzivatel = id_pouzivatel;
    }

    public Integer getRok() {
        return rok;
    }

    public void setRok(Integer rok) {
        this.rok = rok;
    }

    public Integer getId_firma() {
        return id_firma;
    }

    public void setId_firma(Integer id_firma) {
        this.id_firma = id_firma;
    }

    public String getFirma_nazov() {
        return firma_nazov;
    }

    public void setFirma_nazov(String firma_nazov) {
        this.firma_nazov = firma_nazov;
    }

    public Integer getId_ucet() {
        return id_ucet;
    }

    public void setId_ucet(Integer id_ucet) {
        this.id_ucet = id_ucet;
    }

    public String getUcet_nazov() {
        return ucet_nazov;
    }

    public void setUcet_nazov(String ucet_nazov) {
        this.ucet_nazov = ucet_nazov;
    }
}
