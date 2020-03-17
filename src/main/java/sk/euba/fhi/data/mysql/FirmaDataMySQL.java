package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.common.ConnectionManager;
import sk.euba.fhi.model.interf.FirmaData;
import sk.euba.fhi.model.obj.Firma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FirmaDataMySQL implements FirmaData {
    private static final Logger logger = LoggerFactory.getLogger(FirmaDataMySQL.class);

    public List<Firma> vsetky(int pouzivatel_id) {
        List<Firma> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM firma WHERE id_pouzivatel = ?");
                ps.setInt(1, pouzivatel_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Firma obj = new Firma();
                    obj.setId(rs.getInt("id"));
                    obj.setId_pouzivatel(rs.getInt("id_pouzivatel"));
                    obj.setNazov(rs.getString("nazov"));
                    obj.setAdresa(rs.getString("adresa"));
                    obj.setPsc(rs.getString("psc"));
                    obj.setIco(rs.getString("ico"));
                    obj.setDic(rs.getString("dic"));
                    obj.setIc_dph(rs.getString("ic_dph"));
                    zoznam.add(obj);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return zoznam;
    }

    public List<String> nazvyFiriem(int pouzivatel_id) {
        List<String> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT nazov FROM firma WHERE id_pouzivatel = ?");
                ps.setInt(1, pouzivatel_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String nazov = rs.getString("nazov");
                    zoznam.add(nazov);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return zoznam;
    }

    public int idFirmy(int id_pouzivatel, String firma_nazov) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id FROM firma WHERE id_pouzivatel = ? AND nazov = ? ");
                ps.setInt(1, id_pouzivatel);
                ps.setString(2, firma_nazov);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return id;
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return 0;
    }


    public Firma getFirma(int id) {
        Firma firma = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM firma WHERE id = ?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    firma = new Firma();
                    firma.setId(rs.getInt("id"));
                    firma.setId_pouzivatel(rs.getInt("id_pouzivatel"));
                    firma.setNazov(rs.getString("nazov"));
                    firma.setAdresa(rs.getString("adresa"));
                    firma.setPsc(rs.getString("psc"));
                    firma.setIco(rs.getString("ico"));
                    firma.setDic(rs.getString("dic"));
                    firma.setIc_dph(rs.getString("ic_dph"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return firma;
    }


    public void vloz(Firma firma) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO firma (id_pouzivatel, nazov, adresa, psc, ico, dic, ic_dph) VALUES (?, ?, ?, ?, ?, ?, ? )");
                ps.setInt(1, firma.getId_pouzivatel());
                ps.setString(2, firma.getNazov());
                ps.setString(3, firma.getAdresa());
                ps.setString(4, firma.getPsc());
                ps.setString(5, firma.getIco());
                ps.setString(6, firma.getDic());
                ps.setString(7, firma.getIc_dph());
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void zmen(Firma firma) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE firma\n" +
                        "SET \n" +
                        "    id_pouzivatel = ?, \n" +
                        "    nazov = ?, \n" +
                        "    adresa = ?,\n" +
                        "    psc = ?,\n" +
                        "    ico = ?,\n" +
                        "    dic = ?,\n" +
                        "    ic_dph = ?\n" +
                        "WHERE\n" +
                        "    id = ?;");

                ps.setInt(1, firma.getId_pouzivatel());
                ps.setString(2, firma.getNazov());
                ps.setString(3, firma.getAdresa());
                ps.setString(4, firma.getPsc());
                ps.setString(5, firma.getIco());
                ps.setString(6, firma.getDic());
                ps.setString(7, firma.getIc_dph());
                ps.setInt(8, firma.getId());
                int rows = ps.executeUpdate();
                assert (rows != 1);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void zmaz(int id) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("DELETE FROM firma\n" +
                        "WHERE id = ?;");
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                assert (rows != 1);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
}