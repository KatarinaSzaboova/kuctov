package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.DF;

import java.util.List;

public interface DFData {
    List<DF> vsetky(long id_firmy, int rok);
    DF getDF(long id);
    void vloz(DF p);
    void zmen(DF p);
    void zmaz(long id);
}
