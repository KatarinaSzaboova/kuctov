package sk.euba.fhi.data.mocks;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitDouble;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.model.interf.BankaData;
import sk.euba.fhi.model.obj.Banka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class BankaDataMock implements BankaData {
    private static final Logger logger = LoggerFactory.getLogger(BankaDataMock.class);
    private static List<Banka> bankaList = new ArrayList<>();

    public BankaDataMock() {
        if (bankaList.size() > 0) {
            return;
        }

        MockNeat m = MockNeat.threadLocal();
        MockUnitInt pocet = m.probabilites(Integer.class)
                .add(1.0, m.ints().range(100, 300))
                .mapToInt(Integer::intValue);
        MockUnitInt id = m.intSeq().start(1);

        MockUnitInt id_uctu = m.probabilites(Integer.class)
                .add(1.0, m.ints().range(1, 5))
                .mapToInt(Integer::intValue);

        MockUnitInt id_ucet1 = m.probabilites(Integer.class)
                .add(1.0, m.ints().range(1, 3))
                .mapToInt(Integer::intValue);

        MockUnitDouble suma = m.probabilites(Double.class)
                .add(0.9, m.doubles().range(1000.0, 2000.0))
                .add(0.1, m.doubles().range(2000.0, 5000.0))
                .mapToDouble(Double::doubleValue);

        for (int rok = 2018; rok <= 2020; rok++) {

            int count = pocet.val().intValue();

            List<String> dates = CommonMock.generateDates(count, LocalDate.of(rok, 1, 1), LocalDate.of(rok, 12, 31));

            List<String> odberateila = m.fmt("#{value}").param("value", m.from(CommonMock.odberateila)).list(count).val();
            List<String> partneri = m.fmt("#{value}").param("value", m.from(CommonMock.partneri)).list(count).val();
            List<String> partner_iban = m.fmt("#{value}").param("value", m.from(CommonMock.partneriban)).list(count).val();
            List<String> ovplyv_zd = m.fmt("#{value}").param("value", m.from(CommonMock.ano_nie)).list(count).val();


            for (int i = 0; i < odberateila.size(); i++) {
                Banka banka = new Banka();

                int idUctu = id_uctu.val().intValue();

                banka.setId(id.val().intValue());
                banka.setId_ucet(idUctu);
                banka.setRok(rok);
                String nDatum = dates.get(i);
                banka.setDatum(nDatum);
                banka.setOvplyv_zd(ovplyv_zd.get(i));
                banka.setMena("EUR");
                double dSuma = suma.val().doubleValue();
                banka.setSuma(Math.round(dSuma * 100.0) / 100.0);
                banka.setPartner(partneri.get(i));
                banka.setPartner_iban(partner_iban.get(i));
                bankaList.add(banka);
            }
        }
    }

    public List<Banka> vsetky(int id_uctu, int rok) {
        if (id_uctu == 0) {
            return bankaList;
        }
        Predicate<Banka> byFilter = p -> p.getId_ucet() == id_uctu && p.getRok() == rok;
        List<Banka> result = bankaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result;
    }

    public Banka getBanka(int id) {
        Predicate<Banka> byFilter = p -> p.getId() == id;
        List<Banka> result = bankaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public void vloz(Banka banka) {
        Banka max = bankaList.stream().reduce(banka, (a, b) -> a.getId() > b.getId() ? a : b);
        banka.setId(max.getId() + 1);
        bankaList.add(banka);
    }

    public void zmen(Banka banka) {
    }

    public void zmaz(int id) {
        Predicate<Banka> byFilter = p -> p.getId() != id;
        bankaList = bankaList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }
}
