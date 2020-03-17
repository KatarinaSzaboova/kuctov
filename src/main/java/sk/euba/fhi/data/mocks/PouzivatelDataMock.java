package sk.euba.fhi.data.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.model.interf.PouzivatelData;
import sk.euba.fhi.model.obj.Pouzivatel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class PouzivatelDataMock implements PouzivatelData {
    private static final Logger logger = LoggerFactory.getLogger(PouzivatelDataMock.class);
    private static List<Pouzivatel> pouzivatelList = new ArrayList<>();

    public PouzivatelDataMock() {
        if (pouzivatelList.size() > 0) {
            return;
        }

        {
            Pouzivatel pouzivatel = new Pouzivatel();
            pouzivatel.setId(1);
            pouzivatel.setLogin_meno("katka");
            pouzivatel.setLogin_heslo("heslo");
            pouzivatel.setMeno("Katarína");
            pouzivatel.setPriezvisko("Szabóová");
            pouzivatel.setEmail("katka12@gmail.com");
            pouzivatel.setAdresa("Belehradská 8, Bratislava");
            pouzivatel.setPsc("80005");
            pouzivatelList.add(pouzivatel);
        }
        {
            Pouzivatel pouzivatel = new Pouzivatel();
            pouzivatel.setId(2);
            pouzivatel.setLogin_meno("petra");
            pouzivatel.setLogin_heslo("heslo");
            pouzivatel.setMeno("Petra");
            pouzivatel.setPriezvisko("Malá");
            pouzivatel.setEmail("petra@yahoo.com");
            pouzivatel.setAdresa("Prievoszká 54, Bratislava");
            pouzivatel.setPsc("80003");
            pouzivatelList.add(pouzivatel);
        }

    }

    public List<Pouzivatel> vsetky() {
        return pouzivatelList;
    }

    public Pouzivatel getPouzivatel(int id) {
        Predicate<Pouzivatel> byFilter = p -> p.getId() == id;
        List<Pouzivatel> result = pouzivatelList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public void vloz(Pouzivatel pouzivatel) {
        Pouzivatel max = pouzivatelList.stream().reduce(pouzivatel, (a, b) -> a.getId() > b.getId() ? a : b);
        pouzivatel.setId(max.getId() + 1);
        pouzivatelList.add(pouzivatel);
    }

    public void zmen(Pouzivatel pouzivatel) {
    }

    public void zmaz(int id) {
        Predicate<Pouzivatel> byFilter = p -> p.getId() != id;
        pouzivatelList = pouzivatelList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }

    public Pouzivatel autentifikuj(String meno, String heslo) {
        Predicate<Pouzivatel> byFilter = p -> p.getLogin_meno().equals(meno);
        List<Pouzivatel> result = pouzivatelList.stream().filter(byFilter)
                .collect(Collectors.toList());
        if (result.size() == 0) {
            return null;
        }
        assert (result.size() != 1);
        if (result.get(0).getLogin_heslo().equals(heslo)) {
            return result.get(0);
        }
        return null;
    }
}
