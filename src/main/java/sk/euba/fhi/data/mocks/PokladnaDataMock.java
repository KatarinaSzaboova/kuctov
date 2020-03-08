package sk.euba.fhi.data.mocks;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitDouble;
import net.andreinc.mockneat.abstraction.MockUnitLong;
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

        int count = 200;

        MockNeat m = MockNeat.threadLocal();
        MockUnitLong id = m.longSeq().start(1);
        MockUnitLong id_firmy = m.probabilites(Long.class)
                .add(1.0, m.longs().range(1, 10))
                .mapToLong(Long::longValue);
        MockUnitLong cislo_dokladu = m.longSeq().start(10000);

        MockUnitDouble cena = m.probabilites(Double.class)
                .add(0.5, m.doubles().range(10.0, 100.0))
                .add(0.3, m.doubles().range(100.0, 200.0))
                .add(0.2, m.doubles().range(200.0, 500.0))
                .mapToDouble(Double::doubleValue);

        List<String> dates = CommonMock.generateDates(count, LocalDate.of(2019, 1, 1), LocalDate.of(2019, 12, 31));
        List<String> prijem_vydaj = m.fmt("#{value}").param("value", m.from(CommonMock.prijem_vydaj)).list(count).val();
        List<String> ovplyv_zd = m.fmt("#{value}").param("value", m.from(CommonMock.ano_nie)).list(count).val();
        List<String> vzory = m.fmt("#{value}").param("value", m.from(CommonMock.vzory)).list(count).val();

        for (int i = 0; i < vzory.size(); i++) {
            Pokladna pokladna = new Pokladna();

            pokladna.setId(id.val().longValue());
            pokladna.setId_firma(id_firmy.val().longValue());
            pokladna.setCislo_dokladu(cislo_dokladu.val().longValue());
            pokladna.setRok(2019); // TODO ****
            pokladna.setVzor(vzory.get(i));
            String nDatum = dates.get(i);
            pokladna.setDatum(nDatum);
            pokladna.setMena("EUR");
            double cebaBez = cena.val().doubleValue();
            pokladna.setSuma_bez_dph(Math.round(cebaBez * 100.0) / 100.0);
            pokladna.setSuma_s_dph(Math.round(cebaBez * 1.2 * 100.0) / 100.0);
            pokladna.setPrijem_vydaj(prijem_vydaj.get(i));
            pokladna.setOvplyv_zd(ovplyv_zd.get(i));

            pokladnaList.add(pokladna);
        }
    }

    public List<Pokladna> vsetky(long id_firmy, int rok) {
        Predicate<Pokladna> byFilter = p -> p.getId_firma() == id_firmy && p.getRok() == rok;
        List<Pokladna> result = pokladnaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result;
    }

    public Pokladna getPokladna(long id) {
        Predicate<Pokladna> byFilter = p -> p.getId() == id;
        List<Pokladna> result = pokladnaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public void vloz(Pokladna pokladna) {
        Pokladna max = pokladnaList.stream().reduce(pokladna, (a, b) -> a.getId() > b.getId() ? a : b);
        pokladna.setId(max.getId() + 1L);
        pokladnaList.add(pokladna);
    }

    public void zmen(Pokladna pokladna) {
    }

    public void zmaz(long id) {
        Predicate<Pokladna> byFilter = p -> p.getId() != id;
        pokladnaList = pokladnaList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }
}
