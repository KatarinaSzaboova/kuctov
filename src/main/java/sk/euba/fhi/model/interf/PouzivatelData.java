package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Pouzivatel;

import java.util.List;

public interface PouzivatelData {
    List<Pouzivatel> vsetky();
    Pouzivatel getPouzivatel(int id);
    Pouzivatel autentifikuj(String meno, String heslo);
    void vloz(Pouzivatel p);
    void zmen(Pouzivatel p);
    void zmaz(int id);
}
