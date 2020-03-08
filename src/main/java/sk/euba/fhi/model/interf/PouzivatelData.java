package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Pouzivatel;

import java.util.List;

public interface PouzivatelData {
    List<Pouzivatel> vsetky();
    Pouzivatel getPouzivatel(long id);
    void vloz(Pouzivatel p);
    void zmen(Pouzivatel p);
    void zmaz(long id);
}
