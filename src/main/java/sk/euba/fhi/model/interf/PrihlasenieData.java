package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Prihlasenie;

import java.util.List;

public interface PrihlasenieData {
    Prihlasenie getSession(int id_pouzivatel);
    List<Prihlasenie> vsetky();
}
