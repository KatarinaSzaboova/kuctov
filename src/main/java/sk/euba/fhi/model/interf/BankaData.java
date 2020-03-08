package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.Banka;

import java.util.List;

public interface BankaData {
    List<Banka> vsetky(long id_firmy, long id_uctu, int rok);
    Banka getBanka(long id);
    void vloz(Banka p);
    void zmen(Banka p);
    void zmaz(long id);
}
