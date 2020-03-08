package sk.euba.fhi.data.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.model.interf.BankUcetData;
import sk.euba.fhi.model.obj.BankUcet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class BankUcetDataMock implements BankUcetData {
    private static final Logger logger = LoggerFactory.getLogger(BankUcetDataMock.class);
    private static List<BankUcet> bankUcetList = new ArrayList<>();

    public BankUcetDataMock() {
        if (bankUcetList.size() > 0) {
            return;
        }

        {
            BankUcet bank_ucet = new BankUcet();
            bank_ucet.setId(1);
            bank_ucet.setId_firma(1);
            bank_ucet.setNazov("Účet VÚB");
            bank_ucet.setBic("SUBASKBX");
            bank_ucet.setIban("SK3302000000000000012351");
            bank_ucet.setMena("EUR");
            bankUcetList.add(bank_ucet);
        }

        {
            BankUcet bank_ucet = new BankUcet();
            bank_ucet.setId(2);
            bank_ucet.setId_firma(1);
            bank_ucet.setNazov("Účet TATRA BANKA");
            bank_ucet.setBic("TATRSKBX");
            bank_ucet.setIban("SK330200000000000002508");
            bank_ucet.setMena("EUR");
            bankUcetList.add(bank_ucet);
        }

        {
            BankUcet bank_ucet = new BankUcet();
            bank_ucet.setId(3);
            bank_ucet.setId_firma(2);
            bank_ucet.setNazov("Účet SLSP");
            bank_ucet.setBic("GIBASKBX");
            bank_ucet.setIban("SK330200000000000009999");
            bank_ucet.setMena("EUR");
            bankUcetList.add(bank_ucet);
        }

    }

    public List<BankUcet> vsetky() {
        return bankUcetList;
    }

    public List<BankUcet> preFirmu(long id_firma) {
        Predicate<BankUcet> byFilter = p -> p.getId_firma() == id_firma;
        List<BankUcet> result = bankUcetList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result;
    }

    public BankUcet getBankUcet(long id) {
        Predicate<BankUcet> byFilter = p -> p.getId() == id;
        List<BankUcet> result = bankUcetList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public List<String> nazvyUctov(long id_firma) {
        Predicate<BankUcet> byFilter = p -> p.getId_firma() == id_firma;
        List<BankUcet> result = bankUcetList.stream().filter(byFilter)
                .collect(Collectors.toList());

        List<String> nazvy = new ArrayList<String>();
        for (BankUcet ucet : result) {
            nazvy.add(ucet.getNazov());
        }
        return nazvy;
    }

    public long idUctu(long id_firma, String ucet_nazov) {
        Predicate<BankUcet> byFilter = p -> p.getId_firma() == id_firma && p.getNazov().equals(ucet_nazov);
        List<BankUcet> result = bankUcetList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0).getId();
    }

    public void vloz(BankUcet ucet) {
        BankUcet max = bankUcetList.stream().reduce(ucet, (a, b) -> a.getId() > b.getId() ? a : b);
        ucet.setId(max.getId() + 1L);
        bankUcetList.add(ucet);
    }

    public void zmen(BankUcet ucet) {
    }

    public void zmaz(long id) {
        Predicate<BankUcet> byFilter = p -> p.getId() != id;
        bankUcetList = bankUcetList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }
}
