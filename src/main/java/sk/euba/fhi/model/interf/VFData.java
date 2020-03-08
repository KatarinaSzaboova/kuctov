package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.VF;

import java.util.List;

public interface VFData {
    List<VF> vsetky(long id_firmy, int rok);
    VF getVF(long id);
    void vloz(VF p);
    void zmen(VF p);
    void zmaz(long id);
}
