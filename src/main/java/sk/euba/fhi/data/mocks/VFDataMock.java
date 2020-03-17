package sk.euba.fhi.data.mocks;

import net.andreinc.mockneat.abstraction.MockUnitDouble;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.model.interf.VFData;
import sk.euba.fhi.model.obj.VF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;


import net.andreinc.mockneat.MockNeat;


public class VFDataMock implements VFData {
    private static final Logger logger = LoggerFactory.getLogger(VFDataMock.class);
    private static List<VF> vfList = new ArrayList<>();

    public VFDataMock() {
        if (vfList.size() > 0) {
            return;
        }

        MockNeat m = MockNeat.threadLocal();
        MockUnitInt pocet = m.probabilites(Integer.class)
                .add(1.0, m.ints().range(20, 30))
                .mapToInt(Integer::intValue);
        MockUnitInt id = m.intSeq().start(1);

        for (int rok = 2018; rok <= 2020; rok++) {

            int count = pocet.val().intValue();

            MockUnitInt id_firmy = m.probabilites(Integer.class)
                    .add(1.0, m.ints().range(1, 4))
                    .mapToInt(Integer::intValue);
            MockUnitInt cislo_faktury = m.intSeq().start(10000);
            List<String> dates = CommonMock.generateDates(count, LocalDate.of(rok, 1, 1), LocalDate.of(rok, 12, 31));

            MockUnitDouble cena = m.probabilites(Double.class)
                    .add(0.9, m.doubles().range(1000.0, 2000.0))
                    .add(0.1, m.doubles().range(2000.0, 5000.0))
                    .mapToDouble(Double::doubleValue);

            List<String> odberateila = m.fmt("#{value}").param("value", m.from(CommonMock.odberateila)).list(count).val();

            for (int i = 0; i < odberateila.size(); i++) {
                VF vf = new VF();

                vf.setId(id.val().intValue());
                vf.setId_firma(id_firmy.val().intValue());
                vf.setCislo_faktury(cislo_faktury.val().intValue());
                vf.setRok(rok);
                vf.setOdberatel(odberateila.get(i));
                String nDatum = dates.get(i);
                vf.setDatum(nDatum);
                vf.setDatum_dodania(nDatum);
                vf.setDatum_splatnosti(nDatum);
                vf.setMena("EUR");
                double cebaBez = cena.val().doubleValue();
                vf.setSuma_bez_dph(Math.round(cebaBez * 100.0) / 100.0);
                vf.setSuma_s_dph(Math.round(cebaBez * 1.2 * 100.0) / 100.0);

                vfList.add(vf);
            }
        }
    }

    public List<VF> vsetky(int id_firmy, int rok) {
        if (id_firmy == 0) {
            return vfList;
        }
        Predicate<VF> byFilter = p -> p.getId_firma() == id_firmy && p.getRok() == rok;
        List<VF> result = vfList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result;
    }

    public VF getVF(int id) {
        Predicate<VF> byFilter = p -> p.getId() == id;
        List<VF> result = vfList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public void vloz(VF vf) {
        VF max = vfList.stream().reduce(vf, (a, b) -> a.getId() > b.getId() ? a : b);
        vf.setId(max.getId() + 1);
        vfList.add(vf);
    }

    public void zmen(VF vf) {
    }

    public void zmaz(int id) {
        Predicate<VF> byFilter = p -> p.getId() != id;
        vfList = vfList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }
}
