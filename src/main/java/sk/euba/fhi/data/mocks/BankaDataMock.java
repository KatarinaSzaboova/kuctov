package sk.euba.fhi.data.mocks;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitDouble;
import net.andreinc.mockneat.abstraction.MockUnitLong;
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

        int count = 200;

        MockNeat m = MockNeat.threadLocal();
        MockUnitLong id = m.longSeq().start(1);
        MockUnitLong id_firmy = m.probabilites(Long.class)
                .add(1.0, m.longs().range(1, 10))
                .mapToLong(Long::longValue);

        MockUnitLong id_ucet = m.probabilites(Long.class)
                .add(1.0, m.longs().range(1, 4))
                .mapToLong(Long::longValue);

        //MockUnitLong cislo_faktury = m.longSeq().start(10000);
        List<String> dates = CommonMock.generateDates(count, LocalDate.of(2019, 1, 1), LocalDate.of(2019, 12, 31));

        MockUnitDouble suma = m.probabilites(Double.class)
                .add(0.9, m.doubles().range(1000.0, 2000.0))
                .add(0.1, m.doubles().range(2000.0, 5000.0))
                .mapToDouble(Double::doubleValue);

        List<String> odberateila = m.fmt("#{value}").param("value",m.from(CommonMock.odberateila)).list(count).val();
        List<String> partneri = m.fmt("#{value}").param("value",m.from(CommonMock.partneri)).list(count).val();
        List<String> partner_iban = m.fmt("#{value}").param("value",m.from(CommonMock.partneriban)).list(count).val();
        List<String> ovplyv_zd = m.fmt("#{value}").param("value", m.from(CommonMock.ano_nie)).list(count).val();


        for (int i = 0; i < odberateila.size(); i++) {
            Banka banka = new Banka();

            banka.setId(id.val().longValue());
            banka.setId_firma(id_firmy.val().longValue());
            banka.setId_ucet(id_ucet.val().longValue());
            banka.setRok(2019); // TODO ****
            String nDatum = dates.get(i);
            banka.setDatum(nDatum);
            banka.setOvplyv_zd(ovplyv_zd.get(i));
            banka.setMena("EUR");
            banka.setSuma(suma.val().longValue());
            banka.setPartner(partneri.get(i));
            banka.setPartner_iban(partner_iban.get(i));
            bankaList.add(banka);
        }
    }

    public List<Banka> vsetky(long id_firmy, long id_uctu, int rok) {
        Predicate<Banka> byFilter = p -> p.getId_firma() == id_firmy
                && p.getId_ucet() == id_uctu && p.getRok() == rok;
        List<Banka> result = bankaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        return result;
    }

    public Banka getBanka(long id) {
        Predicate<Banka> byFilter = p -> p.getId() == id;
        List<Banka> result = bankaList.stream().filter(byFilter)
                .collect(Collectors.toList());
        assert (result.size() != 1);
        return result.get(0);
    }

    public void vloz(Banka banka) {
        Banka max = bankaList.stream().reduce(banka, (a, b) -> a.getId() > b.getId() ? a : b);
        banka.setId(max.getId() + 1L);
        bankaList.add(banka);
    }

    public void zmen(Banka banka) {
    }

    public void zmaz(long id) {
        Predicate<Banka> byFilter = p -> p.getId() != id;
        bankaList = bankaList.stream().filter(byFilter)
                .collect(Collectors.toList());
    }
}
