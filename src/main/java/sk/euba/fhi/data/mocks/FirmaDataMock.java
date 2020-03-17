package sk.euba.fhi.data.mocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.model.interf.FirmaData;
import sk.euba.fhi.model.obj.Firma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class FirmaDataMock implements FirmaData {
    private static final Logger logger = LoggerFactory.getLogger(FirmaDataMock.class);
    private static List<Firma> firmaList = new ArrayList<>();

    public FirmaDataMock() {
        if (firmaList.size() > 0) {
            return;
        }

        {
            Firma firma = new Firma();
            firma.setId(1);
            firma.setId_pouzivatel(1);
            firma.setNazov("Firma 1");
            firma.setAdresa("Červená 21");
            firma.setPsc("82102");
            firma.setIco("44981082");
            firma.setDic("2022903949");
            firma.setIc_dph("Sk2022903949");
            firmaList.add(firma);
        }
        {
            Firma firma = new Firma();
            firma.setId(2);
            firma.setId_pouzivatel(1);
            firma.setNazov("Firma 2");
            firma.setAdresa("Modrá 13");
            firma.setPsc("83104");
            firma.setIco("44981073");
            firma.setDic("2022903987");
            firma.setIc_dph("Sk2022903654");
            firmaList.add(firma);
        }

        {
            Firma firma = new Firma();
            firma.setId(3);
            firma.setId_pouzivatel(2);
            firma.setNazov("Firma 3");
            firma.setAdresa("Zelená 9");
            firma.setPsc("85102");
            firma.setIco("45281082");
            firma.setDic("2022520949");
            firma.setIc_dph("Sk2088456949");
            firmaList.add(firma);
        }
    }

    public List<Firma> vsetky(int pouzivatel_id) {
        if (pouzivatel_id == 0) {
            return firmaList;
        }
        Predicate<Firma> byFilter = p -> p.getId_pouzivatel() == pouzivatel_id;
        List<Firma> result = firmaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result;
    }

    public List<String> nazvyFiriem(int pouzivatel_id) {
        Predicate<Firma> byFilter = p -> p.getId_pouzivatel() == pouzivatel_id;
        List<Firma> result = firmaList.stream().filter(byFilter)
                .collect(Collectors.toList());

        List<String> nazvy = new ArrayList<String>();
        for (Firma firma : result) {
            nazvy.add(firma.getNazov());
        }
        return nazvy;
    }

    public int idFirmy(int pouzivatel_id, String firma_nazov) {
        Predicate<Firma> byFilter = p -> p.getId_pouzivatel() == pouzivatel_id && p.getNazov().equals(firma_nazov);
        List<Firma> result = firmaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0).getId();
    }

    public Firma getFirma(int id) {
        Predicate<Firma> byFilter = p -> p.getId() == id;
        List<Firma> result = firmaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public void vloz(Firma firma) {
        Firma max = firmaList.stream().reduce(firma, (a, b) -> a.getId() > b.getId() ? a : b);
        firma.setId(max.getId() + 1);
        firmaList.add(firma);
    }

    public void zmen(Firma firma) {
    }

    public void zmaz(int id) {
        Predicate<Firma> byFilter = p -> p.getId() != id;
        firmaList = firmaList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }
}
