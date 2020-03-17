package sk.euba.fhi.model.interf;

import sk.euba.fhi.model.obj.BankUcet;

import java.util.List;

public interface BankUcetData {
    List<BankUcet> vsetky();
    List<BankUcet> preFirmu(int id_firma);
    BankUcet getBankUcet(int id);
    List<String> nazvyUctov(int id_firma);
    int idUctu(int id_firma, String ucet_nazov);
    void vloz(BankUcet p);
    void zmen(BankUcet p);
    void zmaz(int id);
}
