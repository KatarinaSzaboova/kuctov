package sk.euba.fhi.model.obj;


public class Banka {
    private int id;
    private int id_ucet;
    private int rok;
    private String datum;
    private String ovplyv_zd;
    private String mena;
    private double suma;
    private String partner;
    private String partner_iban;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_ucet() {
        return id_ucet;
    }

    public void setId_ucet(int id_ucet) {
        this.id_ucet = id_ucet;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getOvplyv_zd() {
        return ovplyv_zd;
    }

    public void setOvplyv_zd(String ovplyv_zd) {
        this.ovplyv_zd = ovplyv_zd;
    }

    public String getMena() {
        return mena;
    }

    public void setMena(String mena) {
        this.mena = mena;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPartner_iban() {
        return partner_iban;
    }

    public void setPartner_iban(String partner_iban) {
        this.partner_iban = partner_iban;
    }
}
