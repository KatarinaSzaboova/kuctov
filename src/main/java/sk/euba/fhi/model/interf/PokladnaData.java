package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Pokladna;

import java.util.List;

public interface PokladnaData {
    List<Pokladna> vsetky(int id_firmy, int rok);
    Pokladna getPokladna(int id);
    void vloz(Pokladna p);
    void zmen(Pokladna p);
    void zmaz(int id);
}
