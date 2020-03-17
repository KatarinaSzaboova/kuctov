package sk.euba.fhi.data.mocks;

import net.andreinc.mockneat.MockNeat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommonMock {
    // partnery rozdelene na dodavatelia a odberateila
    static public String[] dodavatelia = new String[]{"Alza", "Agem", "Orange", "O2", "Telekom", "Billa", "Tesco", "Kaufland"};
    static public String[] odberateila = new String[]{"SPP", "OMV", "Slovnaft", "KIA", "Volkswagen"};
    static public String[] vzory = new String[]{"Vklad", "Výber", "Benzín", "Olej", "Elektronika", "Tovar"};
    static public String[] ano_nie = new String[]{"A", "N"};
    static public String[] partneri = new String[] {"BILLA" ,"NOTINO" ,"TESCOMA" ,"TSCHIBO" ,"PANTA RHEI" ,"DM DROGÉRIE" ,"FIT RESTAURANT,BA" ,"8554 TERNO  Drienov" ,"KAUFLAND 8820, BA, I.CEST" ,"ZARA" ,"Slovenský plynárenský priemysel, a.s." ,"TESCO STORES SR 21004" ,"RHAPIS, OC DANUBIA, BA" ,"OMV 2415/" ,"Spojená škola" ,"DELIA POTRAVINY,S.R.O" ,"SLOVNAFT-SK40109" ,"LIDL BA,TRENČIANSKA"};
    static public String[] partneriban = new String[]{ "SK5009000000000464407777", "SK6809000000005118999999", "SK1009000000005119755555", "SK4811000000002629003222", "SK0509000000000173584444", "SK3381800000007000200111"};


    static public String[] menoM = new String[]{"Peter", "Juraj", "Jozef"};
    static public String[] priezviskoM = new String[]{"Novák", "Malík", "Kováč"};
    static public String[] menoZ = new String[]{"Katarína", "Veronika", "Adriana", "Alena"};
    static public String[] priezviskoZ = new String[]{"Pekná", "Malá", "Veselá"};
    static public String[] email = new String[]{"@gmail.com", "@yahoo.com", "@hotmail.com", "@zoznam.sk", "@pobox.sk"};

    static public String[] psca = new String[]{"90045", "07801", "95134", "06741", "97401"};
    static public String[] mesta = new String[]{"Bratislava", "Nitra", "Trnava", "Senec", "Senica"};
    static public String[] ulice = new String[]{"Dlhá", "Krátka", "Široká", "Dolná", "Horná"};


    static public List<String> generateDates(int count, LocalDate ld1, LocalDate ld2) {
        List<String> ldlist = new ArrayList<>();
        MockNeat m = MockNeat.threadLocal();
        for (int n = 0; n < count; n++) {
            LocalDate localDate = m.localDates().between(ld1, ld2).val();
            String ld = localDate.toString();
            ldlist.add(ld);
        }
        Collections.sort(ldlist);
        return ldlist;
    }

}
