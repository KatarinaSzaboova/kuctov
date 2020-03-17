package sk.euba.fhi.data;

import sk.euba.fhi.data.mocks.*;
import sk.euba.fhi.data.mysql.*;
import sk.euba.fhi.model.interf.*;
import sk.euba.fhi.model.obj.Prihlasenie;

public class DataFactory {
    static boolean isMock = true;

    public static VFData createVFData() {
        if (true == isMock)
            return new VFDataMock();
        else
            return new VFDataMySQL();
    }

    public static DFData createDFData() {
        if (true == isMock)
            return new DFDataMock();
        else
            return new DFDataMySQL();
    }

    public static PokladnaData createPokladnaData() {
        if (true == isMock)
            return new PokladnaDataMock();
        else
            return new PokladnaDataMySQL();
    }

    public static PouzivatelData createPouzivatelData() {
        if (true == isMock)
            return new PouzivatelDataMock();
        else
            return new PouzivatelDataMySQL();
    }

    public static BankaData createBankaData() {
        if (true == isMock)
            return new BankaDataMock();
        else
            return new BankaDataMySQL();
    }

    public static BankUcetData createBankUcetData() {
        if (true == isMock)
            return new BankUcetDataMock();
        else
            return new BankUcetDataMySQL();
    }

    public static FirmaData createFirmaData() {
        if (true == isMock)
            return new FirmaDataMock();
        else
            return new FirmaDataMySQL();
    }

    public static PrihlasenieData createSessionData() {
        if (true == isMock)
            return new PrihlasenieDataMock();
        else
            return new PrihlasenieDataMySQL();
    }
    public static Prihlasenie getPrihlasenie(int pouzivatel_id) {
        PrihlasenieData sessionData = DataFactory.createSessionData();
        return sessionData.getSession(pouzivatel_id);
    }
}
