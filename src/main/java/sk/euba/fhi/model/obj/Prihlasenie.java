package sk.euba.fhi.model.obj;

public class Prihlasenie {
    private Long id_pouzivatel = 0L;
    private Integer rok = 0;
    private Long firma_id = 0L;
    private String firma_nazov = "";
    private Long ucet_id = 0L;
    private String ucet_nazov = "";

    public Long getId_pouzivatel() {
        return id_pouzivatel;
    }

    public void setId_pouzivatel(Long id_pouzivatel) {
        this.id_pouzivatel = id_pouzivatel;
    }

    public Integer getRok() {
        return rok;
    }

    public void setRok(Integer rok) {
        this.rok = rok;
    }

    public Long getFirma_id() {
        return firma_id;
    }

    public void setFirma_id(Long firma_id) {
        this.firma_id = firma_id;
    }

    public String getFirma_nazov() {
        return firma_nazov;
    }

    public void setFirma_nazov(String firma_nazov) {
        this.firma_nazov = firma_nazov;
    }

    public Long getUcet_id() {
        return ucet_id;
    }

    public void setUcet_id(Long ucet_id) {
        this.ucet_id = ucet_id;
    }

    public String getUcet_nazov() {
        return ucet_nazov;
    }

    public void setUcet_nazov(String ucet_nazov) {
        this.ucet_nazov = ucet_nazov;
    }
}
