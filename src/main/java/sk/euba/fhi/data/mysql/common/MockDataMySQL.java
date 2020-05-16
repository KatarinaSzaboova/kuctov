package sk.euba.fhi.data.mysql.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mocks.*;
import sk.euba.fhi.data.mysql.BankaDataMySQL;
import sk.euba.fhi.model.obj.*;

import java.sql.*;
import java.util.List;

public class MockDataMySQL {
    private static final Logger logger = LoggerFactory.getLogger(BankaDataMySQL.class);

    static public void CreateTables() {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                {
                    String sql = "CREATE TABLE `pouzivatel` (\n" +
                            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `login_meno` char(60) NOT NULL,\n" +
                            "  `login_heslo` char(25) NOT NULL,\n" +
                            "  `meno` char(25) NOT NULL,\n" +
                            "  `priezvisko` char(25) NOT NULL,\n" +
                            "  `email` char(25) NOT NULL,\n" +
                            "  `adresa` char(120) DEFAULT NULL,\n" +
                            "  `psc` char(10) NOT NULL,\n" +
                            "  PRIMARY KEY (`id`)\n" +
                            ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "CREATE TABLE `firma` (\n" +
                            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `id_pouzivatel` int(11) NOT NULL,\n" +
                            "  `nazov` char(60) NOT NULL,\n" +
                            "  `adresa` char(120) DEFAULT NULL,\n" +
                            "  `psc` char(10) NOT NULL,\n" +
                            "  `ico` char(20) NOT NULL,\n" +
                            "  `dic` char(20) NOT NULL,\n" +
                            "  `ic_dph` char(20) NOT NULL,\n" +
                            "  FOREIGN KEY(`id_pouzivatel`) references `pouzivatel`(`id`),\n" +
                            "  PRIMARY KEY (`id`)\n" +
                            ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "CREATE TABLE `bank_ucet`(\n" +
                            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `id_firma` int(11) NOT NULL,\n" +
                            "  `nazov` varchar(60) NOT NULL,\n" +
                            "  `bic` char(20) NOT NULL,\n" +
                            "  `iban` char(40) NOT NULL,\n" +
                            "  `mena` char(6) DEFAULT 'EUR',\n" +
                            "  FOREIGN KEY(`id_firma`) references `firma`(`id`),\n" +
                            "  PRIMARY KEY (`id`)\n" +
                            ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "CREATE TABLE `pokladna`(\n" +
                            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `id_firma` int(11) NOT NULL,\n" +
                            "  `cislo_dokladu` int(11) NOT NULL,\n" +
                            "  `rok` int(4) NOT NULL,\n" +
                            "  `vzor` varchar(20) NOT NULL,\n" +
                            "  `datum` char(12) NOT NULL,\n" +
                            "  `mena` char(6) DEFAULT 'EUR',\n" +
                            "  `suma_bez_dph` decimal(9,2) NOT NULL,\n" +
                            "  `suma_s_dph` decimal(9,2) NOT NULL,\n" +
                            "  `ovplyv_zd` char(2),\n" +
                            "  FOREIGN KEY(`id_firma`) references `firma`(`id`),\n" +
                            "  PRIMARY KEY (`id`)\n" +
                            ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "CREATE TABLE `vf`(\n" +
                            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `id_firma` int(11) NOT NULL,\n" +
                            "  `cislo_faktury` int(11) NOT NULL,\n" +
                            "  `rok` int(4) NOT NULL,\n" +
                            "  `odberatel` varchar(20) NOT NULL,\n" +
                            "  `datum` char(12) NOT NULL,\n" +
                            "  `datum_dodania` char(12) NOT NULL,\n" +
                            "  `datum_splatnosti` char(12) NOT NULL,\n" +
                            "  `mena` char(6) DEFAULT 'EUR',\n" +
                            "  `suma_bez_dph` decimal(9,2) NOT NULL,\n" +
                            "  `suma_s_dph` decimal(9,2) NOT NULL,\n" +
                            "  FOREIGN KEY(`id_firma`) references `firma`(`id`),\n" +
                            "  PRIMARY KEY (`id`)\n" +
                            ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "CREATE TABLE `df`(\n" +
                            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `id_firma` int(11) NOT NULL,\n" +
                            "  `cislo_faktury` int(11) NOT NULL,\n" +
                            "  `rok` int(4) NOT NULL,\n" +
                            "  `dodavatel` varchar(20) NOT NULL,\n" +
                            "  `datum` char(12) NOT NULL,\n" +
                            "  `datum_dodania` char(12) NOT NULL,\n" +
                            "  `datum_splatnosti` char(12) NOT NULL,\n" +
                            "  `mena` char(6) DEFAULT 'EUR',\n" +
                            "  `suma_bez_dph` decimal(9,2) NOT NULL,\n" +
                            "  `suma_s_dph` decimal(9,2) NOT NULL,\n" +
                            "  FOREIGN KEY(`id_firma`) references `firma`(`id`),\n" +
                            "  PRIMARY KEY (`id`)\n" +
                            ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "CREATE TABLE `banka`(\n" +
                            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                            "  `id_ucet` int(11) NOT NULL,\n" +
                            "  `rok` int(4) NOT NULL,\n" +
                            "  `datum` char(12) NOT NULL,\n" +
                            "  `ovplyv_zd` char(2),\n" +
                            "  `mena` char(6) DEFAULT 'EUR',\n" +
                            "  `suma` decimal(9,2) NOT NULL,\n" +
                            "  `partner` char(40),\n" +
                            "  `partner_iban` char(40),\n" +
                            "  FOREIGN KEY(`id_ucet`) references `bank_ucet`(`id`),\n" +
                            "  PRIMARY KEY (`id`)\n" +
                            ")ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;\n";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "CREATE TABLE `prihlasenie`(\n" +
                            "  `id_pouzivatel` int(11) NOT NULL,\n" +
                            "  `rok` int(4) NOT NULL,\n" +
                            "  `id_firma` int(11) NOT NULL,\n" +
                            "  `firma_nazov` char(60) NOT NULL,\n" +
                            "  `id_ucet` int(11) NOT NULL,\n" +
                            "  `ucet_nazov` varchar(60) NOT NULL,\n" +
                            "  PRIMARY KEY (`id_pouzivatel`)\n" +
                            ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    static public void DropTables() {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                {
                    String sql = "DROP TABLE banka;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "DROP TABLE df;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "DROP TABLE vf;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "DROP TABLE pokladna;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "DROP TABLE bank_ucet;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "DROP TABLE firma;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "DROP TABLE pouzivatel;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
                {
                    String sql = "DROP TABLE prihlasenie;";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.close();
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    static public void InsertInto() {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                {
                    PouzivatelDataMock data = new PouzivatelDataMock();
                    List<Pouzivatel> vs = data.vsetky();
                    for (Pouzivatel e : vs) {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO pouzivatel \n" +
                                "(login_meno, login_heslo, meno, priezvisko, email, adresa, psc) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?);");
                        ps.setString(1, e.getLogin_meno());
                        ps.setString(2, e.getLogin_heslo());
                        ps.setString(3, e.getMeno());
                        ps.setString(4, e.getPriezvisko());
                        ps.setString(5, e.getEmail());
                        ps.setString(6, e.getAdresa());
                        ps.setString(7, e.getPsc());
                        ps.executeUpdate();
                    }
                }
                {
                    FirmaDataMock data = new FirmaDataMock();
                    List<Firma> vs = data.vsetky(0);
                    for (Firma e : vs) {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO firma \n" +
                                "(id_pouzivatel, nazov, adresa, psc, ico, dic, ic_dph) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?);");
                        ps.setInt(1, e.getId_pouzivatel());
                        ps.setString(2, e.getNazov());
                        ps.setString(3, e.getAdresa());
                        ps.setString(4, e.getPsc());
                        ps.setString(5, e.getIco());
                        ps.setString(6, e.getDic());
                        ps.setString(7, e.getIc_dph());
                        ps.executeUpdate();
                    }
                }
                {
                    BankUcetDataMock data = new BankUcetDataMock();
                    List<BankUcet> vs = data.vsetky();
                    for (BankUcet e : vs) {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO bank_ucet \n" +
                                "(id_firma, nazov, bic, iban, mena) \n" +
                                "VALUES (?, ?, ?, ?, ?);");
                        ps.setInt(1, e.getId_firma());
                        ps.setString(2, e.getNazov());
                        ps.setString(3, e.getBic());
                        ps.setString(4, e.getIban());
                        ps.setString(5, e.getMena());
                        ps.executeUpdate();
                    }
                }
                {
                    PokladnaDataMock data = new PokladnaDataMock();
                    List<Pokladna> vs = data.vsetky(0, 0);
                    for (Pokladna e : vs) {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO pokladna \n" +
                                "(id_firma, cislo_dokladu, rok, vzor, datum, mena, suma_bez_dph, suma_s_dph,ovplyv_zd) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
                        ps.setInt(1, e.getId_firma());
                        ps.setInt(2, e.getCislo_dokladu());
                        ps.setInt(3, e.getRok());
                        ps.setString(4, e.getVzor());
                        ps.setString(5, e.getDatum());
                        ps.setString(6, e.getMena());
                        ps.setDouble(7, e.getSuma_bez_dph());
                        ps.setDouble(8, e.getSuma_s_dph());
                        ps.setString(9, e.getOvplyv_zd());
                        ps.executeUpdate();
                    }
                }
                {
                    VFDataMock data = new VFDataMock();
                    List<VF> vs = data.vsetky(0, 0);
                    for (VF e : vs) {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO vf\n" +
                                "(id_firma, cislo_faktury, rok, odberatel, datum, datum_dodania, datum_splatnosti, mena, suma_bez_dph, suma_s_dph) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                        ps.setInt(1, e.getId_firma());
                        ps.setInt(2, e.getCislo_faktury());
                        ps.setInt(3, e.getRok());
                        ps.setString(4, e.getOdberatel());
                        ps.setString(5, e.getDatum());
                        ps.setString(6, e.getDatum_dodania());
                        ps.setString(7, e.getDatum_splatnosti());
                        ps.setString(8, e.getMena());
                        ps.setDouble(9, e.getSuma_bez_dph());
                        ps.setDouble(10, e.getSuma_s_dph());
                        ps.executeUpdate();
                    }
                }
                {
                    DFDataMock data = new DFDataMock();
                    List<DF> vs = data.vsetky(0, 0);
                    for (DF e : vs) {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO df\n" +
                                "(id_firma, cislo_faktury, rok, dodavatel, datum, datum_dodania, datum_splatnosti, mena, suma_bez_dph, suma_s_dph) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                        ps.setInt(1, e.getId_firma());
                        ps.setInt(2, e.getCislo_faktury());
                        ps.setInt(3, e.getRok());
                        ps.setString(4, e.getDodavatel());
                        ps.setString(5, e.getDatum());
                        ps.setString(6, e.getDatum_dodania());
                        ps.setString(7, e.getDatum_splatnosti());
                        ps.setString(8, e.getMena());
                        ps.setDouble(9, e.getSuma_bez_dph());
                        ps.setDouble(10, e.getSuma_s_dph());
                        ps.executeUpdate();
                    }
                }
                {
                    BankaDataMock data = new BankaDataMock();
                    List<Banka> vs = data.vsetky(0, 0);
                    for (Banka e : vs) {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO banka\n" +
                                "(id_ucet, rok, datum, ovplyv_zd, mena, suma, partner, partner_iban) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
                        ps.setInt(1, e.getId_ucet());
                        ps.setInt(2, e.getRok());
                        ps.setString(3, e.getDatum());
                        ps.setString(4, e.getOvplyv_zd());
                        ps.setString(5, e.getMena());
                        ps.setDouble(6, e.getSuma());
                        ps.setString(7, e.getPartner());
                        ps.setString(8, e.getPartner_iban());
                        ps.executeUpdate();
                    }
                }
                {
                    PrihlasenieDataMock data = new PrihlasenieDataMock();
                    List<Prihlasenie> vs = data.vsetky();
                    for (Prihlasenie e : vs) {
                        PreparedStatement ps = connection.prepareStatement("INSERT INTO prihlasenie\n" +
                                "(id_pouzivatel, rok, id_firma, firma_nazov, id_ucet, ucet_nazov) \n" +
                                "VALUES (?, ?, ?, ?, ?, ?);");
                        ps.setInt(1, e.getId_pouzivatel());
                        ps.setInt(2, e.getRok());
                        ps.setInt(3, e.getId_firma());
                        ps.setString(4, e.getFirma_nazov());
                        ps.setInt(5, e.getId_ucet());
                        ps.setString(6, e.getUcet_nazov());
                        ps.executeUpdate();
                    }
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
}
