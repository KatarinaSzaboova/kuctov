package sk.euba.fhi.model.obj;

public class BankUcet {
    private long id;
    private long id_firma;
    private String nazov;
    private String bic;
    private String iban;
    private String mena;


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

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getMena() {
        return mena;
    }

    public void setMena(String mena) {
        this.mena = mena;
    }
}
