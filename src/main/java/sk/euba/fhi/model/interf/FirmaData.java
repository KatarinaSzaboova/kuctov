package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Firma;

import java.util.List;

public interface FirmaData {
    List<Firma> vsetky(long pouzivatel_id);
    List<String> nazvyFiriem(long pouzivatel_id);
    long idFirmy(long pouzivatel_id, String firma_nazov);
    Firma getFirma(long id);
    void vloz(Firma p);
    void zmen(Firma p);
    void zmaz(long id);
}
