package sk.euba.fhi.model.obj;


public class VF {
    private long id;
    private long id_firma;
    private long cislo_faktury;
    private int rok;
    private String odberatel;
    private String datum;
    private String datum_dodania;
    private String datum_splatnosti;
    private String mena;
    private double suma_bez_dph;
    private double suma_s_dph;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_firma() {
        return id_firma;
    }

    public void setId_firma(long id_firma) {
        this.id_firma = id_firma;
    }

    public long getCislo_faktury() {
        return cislo_faktury;
    }

    public void setCislo_faktury(long cislo_faktury) {
        this.cislo_faktury = cislo_faktury;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public String getOdberatel() {
        return odberatel;
    }

    public void setOdberatel(String odberatel) {
        this.odberatel = odberatel;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getDatum_dodania() {
        return datum_dodania;
    }

    public void setDatum_dodania(String datum_dodania) {
        this.datum_dodania = datum_dodania;
    }

    public String getDatum_splatnosti() {
        return datum_splatnosti;
    }

    public void setDatum_splatnosti(String datum_splatnosti) {
        this.datum_splatnosti = datum_splatnosti;
    }

    public String getMena() {
        return mena;
    }

    public void setMena(String mena) {
        this.mena = mena;
    }

    public double getSuma_bez_dph() {
        return suma_bez_dph;
    }

    public void setSuma_bez_dph(double suma_bez_dph) {
        this.suma_bez_dph = suma_bez_dph;
    }

    public double getSuma_s_dph() {
        return suma_s_dph;
    }

    public void setSuma_s_dph(double suma_s_dph) {
        this.suma_s_dph = suma_s_dph;
    }
}
