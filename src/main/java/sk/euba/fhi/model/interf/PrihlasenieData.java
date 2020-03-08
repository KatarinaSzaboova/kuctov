package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Prihlasenie;

public interface PrihlasenieData {
    Prihlasenie getSession(long id_pouzivatel);
}
