package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Firma;

import java.util.List;

public interface FirmaData {
    List<Firma> vsetky(int pouzivatel_id);
    List<String> nazvyFiriem(int pouzivatel_id);
    int idFirmy(int pouzivatel_id, String firma_nazov);
    Firma getFirma(int id);
    void vloz(Firma p);
    void zmen(Firma p);
    void zmaz(int id);
}
