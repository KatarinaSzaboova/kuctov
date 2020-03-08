package sk.euba.fhi.model.obj;


public class Pokladna {
    private long id;
    private long id_firma;
    private long cislo_dokladu;
    private int rok;
    private String vzor;
    private String datum;
    private String mena;
    private double suma_bez_dph;
    private double suma_s_dph;
    private String prijem_vydaj;
    private String ovplyv_zd;

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

    public long getCislo_dokladu() {
        return cislo_dokladu;
    }

    public void setCislo_dokladu(long cislo_dokladu) {
        this.cislo_dokladu = cislo_dokladu;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public String getVzor() {
        return vzor;
    }

    public void setVzor(String vzor) {
        this.vzor = vzor;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
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

    public String getPrijem_vydaj() {
        return prijem_vydaj;
    }

    public void setPrijem_vydaj(String prijem_vydaj) {
        this.prijem_vydaj = prijem_vydaj;
    }

    public String getOvplyv_zd() {
        return ovplyv_zd;
    }

    public void setOvplyv_zd(String ovplyv_zd) {
        this.ovplyv_zd = ovplyv_zd;
    }
}
