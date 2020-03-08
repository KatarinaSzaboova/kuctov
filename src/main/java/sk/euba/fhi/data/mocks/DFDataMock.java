package sk.euba.fhi.data.mocks;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitDouble;
import net.andreinc.mockneat.abstraction.MockUnitLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.model.interf.DFData;
import sk.euba.fhi.model.obj.DF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class DFDataMock implements DFData {
    private static final Logger logger = LoggerFactory.getLogger(DFDataMock.class);
    private static List<DF> dfList = new ArrayList<>();

    public DFDataMock() {
        if (dfList.size() > 0) {
            return;
        }

        int count = 200;

        MockNeat m = MockNeat.threadLocal();
        MockUnitLong id = m.longSeq().start(1);
        MockUnitLong id_firmy = m.probabilites(Long.class)
                .add(1.0, m.longs().range(1, 10))
                .mapToLong(Long::longValue);
        MockUnitLong cislo_faktury = m.longSeq().start(10000);
        List<String> dates = CommonMock.generateDates(count, LocalDate.of(2019, 1, 1), LocalDate.of(2019, 12, 31));

        MockUnitDouble cena = m.probabilites(Double.class)
                .add(0.5, m.doubles().range(10.0, 100.0))
                .add(0.3, m.doubles().range(100.0, 200.0))
                .add(0.2, m.doubles().range(200.0, 500.0))
                .mapToDouble(Double::doubleValue);

        List<String> dodavatelia = m.fmt("#{value}").param("value",m.from(CommonMock.dodavatelia)).list(count).val();

        for (int i = 0; i < dodavatelia.size(); i++) {
            DF df = new DF();

            df.setId(id.val().longValue());
            df.setId_firma(id_firmy.val().longValue());
            df.setCislo_faktury(cislo_faktury.val().longValue());
            df.setRok(2019); // TODO ****
            df.setDodavatel(dodavatelia.get(i));
            String nDatum = dates.get(i);
            df.setDatum(nDatum);
            df.setDatum_dodania(nDatum);
            df.setDatum_splatnosti(nDatum);
            df.setMena("EUR");
            double cebaBez = cena.val().doubleValue();
            df.setSuma_bez_dph(Math.round(cebaBez * 100.0) / 100.0);
            df.setSuma_s_dph(Math.round(cebaBez * 1.2 * 100.0) / 100.0);

            dfList.add(df);
        }
    }

    public List<DF> vsetky(long id_firmy, int rok) {
        Predicate<DF> byFilter = p -> p.getId_firma() == id_firmy && p.getRok() == rok;
        List<DF> result = dfList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result;
    }

    public DF getDF(long id) {
        Predicate<DF> byFilter = p -> p.getId() == id;
        List<DF> result = dfList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public void vloz(DF df) {
        DF max = dfList.stream().reduce(df, (a, b) -> a.getId() > b.getId() ? a : b);
        df.setId(max.getId() + 1L);
        dfList.add(df);
    }

    public void zmen(DF df) {
    }

    public void zmaz(long id) {
        Predicate<DF> byFilter = p -> p.getId() != id;
        dfList = dfList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }
}
