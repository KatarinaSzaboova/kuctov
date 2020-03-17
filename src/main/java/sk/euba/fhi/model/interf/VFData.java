package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.VF;

import java.util.List;

public interface VFData {
    List<VF> vsetky(int id_firmy, int rok);
    VF getVF(int id);
    void vloz(VF p);
    void zmen(VF p);
    void zmaz(int id);
}
