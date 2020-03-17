package sk.euba.fhi.data.mocks;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitDouble;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.model.interf.PokladnaData;
import sk.euba.fhi.model.obj.Pokladna;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class PokladnaDataMock implements PokladnaData {
    private static final Logger logger = LoggerFactory.getLogger(PokladnaDataMock.class);
    private static List<Pokladna> pokladnaList = new ArrayList<>();

    public PokladnaDataMock() {
        if (pokladnaList.size() > 0) {
            return;
        }

        MockNeat m = MockNeat.threadLocal();
        MockUnitInt pocet = m.probabilites(Integer.class)
                .add(1.0, m.ints().range(200, 300))
                .mapToInt(Integer::intValue);
        MockUnitInt id = m.intSeq().start(1);

        MockUnitInt id_firmy = m.probabilites(Integer.class)
                .add(1.0, m.ints().range(1, 4))
                .mapToInt(Integer::intValue);

        MockUnitDouble cena = m.probabilites(Double.class)
                .add(0.5, m.doubles().range(10.0, 100.0))
                .add(0.3, m.doubles().range(100.0, 200.0))
                .add(0.2, m.doubles().range(200.0, 500.0))
                .mapToDouble(Double::doubleValue);

        for (int rok = 2018; rok <= 2020; rok++) {

            int count = pocet.val().intValue();
            MockUnitInt cislo_dokladu1 = m.intSeq().start(10000);
            MockUnitInt cislo_dokladu2 = m.intSeq().start(20000);
            MockUnitInt cislo_dokladu3 = m.intSeq().start(30000);
            MockUnitInt cislo_dokladu4 = m.intSeq().start(40000);


            List<String> dates = CommonMock.generateDates(count, LocalDate.of(rok, 1, 1), LocalDate.of(rok, 12, 31));
            List<String> ovplyv_zd = m.fmt("#{value}").param("value", m.from(CommonMock.ano_nie)).list(count).val();
            List<String> vzory = m.fmt("#{value}").param("value", m.from(CommonMock.vzory)).list(count).val();

            for (int i = 0; i < vzory.size(); i++) {
                Pokladna pokladna = new Pokladna();

                int idFirmy = id_firmy.val().intValue();
                int cisloDokladu = 0;
                if (idFirmy == 1) {
                    cisloDokladu = cislo_dokladu1.val().intValue();
                } else if (idFirmy == 2) {
                    cisloDokladu = cislo_dokladu2.val().intValue();
                } else if (idFirmy == 3) {
                    cisloDokladu = cislo_dokladu3.val().intValue();
                } else {
                    cisloDokladu = cislo_dokladu4.val().intValue();
                }

                pokladna.setId(id.val().intValue());
                pokladna.setId_firma(idFirmy);
                pokladna.setCislo_dokladu(cisloDokladu);
                pokladna.setRok(rok);
                String vzor = vzory.get(i);
                pokladna.setVzor(vzor);
                String nDatum = dates.get(i);
                pokladna.setDatum(nDatum);
                pokladna.setMena("EUR");
                double cebaBez = cena.val().doubleValue();
                if (vzor.equals("Vklad")) {
                    pokladna.setSuma_bez_dph(500.0);
                    pokladna.setSuma_s_dph(500.0);
                    pokladna.setOvplyv_zd("N");
                } else if (vzor.equals("Výber")) {
                    pokladna.setSuma_bez_dph(-100.0);
                    pokladna.setSuma_s_dph(-100.0);
                    pokladna.setOvplyv_zd("N");
                } else {
                    pokladna.setSuma_bez_dph(-1.0 * Math.round(cebaBez * 100.0) / 100.0);
                    pokladna.setSuma_s_dph(-1.0 * Math.round(cebaBez * 1.2 * 100.0) / 100.0);
                    pokladna.setOvplyv_zd("A");
                }

                pokladnaList.add(pokladna);
            }
        }
    }

    public List<Pokladna> vsetky(int id_firmy, int rok) {
        if (id_firmy == 0) {
            return pokladnaList;
        }
        Predicate<Pokladna> byFilter = p -> p.getId_firma() == id_firmy && p.getRok() == rok;
        List<Pokladna> result = pokladnaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result;
    }

    public Pokladna getPokladna(int id) {
        Predicate<Pokladna> byFilter = p -> p.getId() == id;
        List<Pokladna> result = pokladnaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public void vloz(Pokladna pokladna) {
        Pokladna max = pokladnaList.stream().reduce(pokladna, (a, b) -> a.getId() > b.getId() ? a : b);
        pokladna.setId(max.getId() + 1);
        pokladnaList.add(pokladna);
    }

    public void zmen(Pokladna pokladna) {
    }

    public void zmaz(int id) {
        Predicate<Pokladna> byFilter = p -> p.getId() != id;
        pokladnaList = pokladnaList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }
}
