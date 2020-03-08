package sk.euba.fhi.data.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.model.interf.PrihlasenieData;
import sk.euba.fhi.model.obj.Prihlasenie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class PrihlasenieDataMock implements PrihlasenieData {
    private static final Logger logger = LoggerFactory.getLogger(PrihlasenieDataMock.class);
    private static List<Prihlasenie> prihlasenieList = new ArrayList<>();

    public PrihlasenieDataMock() {
        if (prihlasenieList.size() > 0) {
            return;
        }

        {
            Prihlasenie prihlasenie = new Prihlasenie();
            prihlasenie.setId_pouzivatel(1L);
            prihlasenie.setRok(2019);
            prihlasenie.setFirma_id(1L);
            prihlasenie.setFirma_nazov("Firma 1");
            prihlasenie.setUcet_id(1L);
            prihlasenie.setUcet_nazov("Účet VÚB");
            prihlasenieList.add(prihlasenie);
        }

        {
            Prihlasenie prihlasenie = new Prihlasenie();
            prihlasenie.setId_pouzivatel(2L);
            prihlasenie.setRok(2019);
            prihlasenie.setFirma_id(3L);
            prihlasenie.setFirma_nazov("Firma 3");
            prihlasenie.setUcet_id(3L);
            prihlasenie.setUcet_nazov("Účet SLSP");
            prihlasenieList.add(prihlasenie);
        }
    }

    public Prihlasenie getSession(long pouzivatel_id) {
        Predicate<Prihlasenie> byFilter = p -> p.getId_pouzivatel() == pouzivatel_id;
        List<Prihlasenie> result = prihlasenieList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result.get(0);
    }
}
