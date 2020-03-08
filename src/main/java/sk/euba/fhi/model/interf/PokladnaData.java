package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Pokladna;

import java.util.List;

public interface PokladnaData {
    List<Pokladna> vsetky(long id_firmy, int rok);
    Pokladna getPokladna(long id);
    void vloz(Pokladna p);
    void zmen(Pokladna p);
    void zmaz(long id);
}
