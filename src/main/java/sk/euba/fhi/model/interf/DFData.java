package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.DF;

import java.util.List;

public interface DFData {
    List<DF> vsetky(int id_firmy, int rok);
    DF getDF(int id);
    void vloz(DF p);
    void zmen(DF p);
    void zmaz(int id);
}
