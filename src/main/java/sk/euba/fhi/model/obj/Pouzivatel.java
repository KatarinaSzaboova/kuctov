package sk.euba.fhi.model.obj;

public class Pouzivatel {
    private long id;
    private String login_meno;
    private String login_heslo;
    private String meno;
    private String priezvisko;
    private String email;
    private String adresa;
    private String psc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin_meno() {
        return login_meno;
    }

    public void setLogin_meno(String login_meno) {
        this.login_meno = login_meno;
    }

    public String getLogin_heslo() {
        return login_heslo;
    }

    public void setLogin_heslo(String login_heslo) {
        this.login_heslo = login_heslo;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
