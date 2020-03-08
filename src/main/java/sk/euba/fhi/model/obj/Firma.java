package sk.euba.fhi.model.obj;

public class Firma {
    private long id;
    private long id_pouzivatel;
    private String nazov;
    private String adresa;
    private String psc;
    private String ico;
    private String dic;
    private String ic_dph;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_pouzivatel() {
        return id_pouzivatel;
    }

    public void setId_pouzivatel(long id_pouzivatel) {
        this.id_pouzivatel = id_pouzivatel;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    public String getIc_dph() {
        return ic_dph;
    }

    public void setIc_dph(String ic_dph) {
        this.ic_dph = ic_dph;
    }
}
