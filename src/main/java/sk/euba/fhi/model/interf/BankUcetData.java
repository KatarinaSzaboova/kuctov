package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.BankUcet;

import java.util.List;

public interface BankUcetData {
    List<BankUcet> vsetky();
    List<BankUcet> preFirmu(long id_firma);
    BankUcet getBankUcet(long id);
    List<String> nazvyUctov(long id_firma);
    long idUctu(long id_firma, String ucet_nazov);
    void vloz(BankUcet p);
    void zmen(BankUcet p);
    void zmaz(long id);
}
