package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Banka;

import java.util.List;

public interface BankaData {
    List<Banka> vsetky(int id_firmy, int id_uctu, int rok);
    Banka getBanka(int id);
    void vloz(Banka p);
    void zmen(Banka p);
    void zmaz(int id);
}
