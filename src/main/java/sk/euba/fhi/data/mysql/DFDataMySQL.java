package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.common.ConnectionManager;
import sk.euba.fhi.model.interf.DFData;
import sk.euba.fhi.model.obj.DF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DFDataMySQL implements DFData {
    private static final Logger logger = LoggerFactory.getLogger(DFDataMySQL.class);

    public List<DF> vsetky(int id_firmy, int rok) {
        List<DF> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM df WHERE id_firma = ? AND rok = ?");
                ps.setInt(1, id_firmy);
                ps.setInt(2, rok);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    DF obj = new DF();
                    obj.setId(rs.getInt("id"));
                    obj.setId_firma(rs.getInt("id_firma"));
                    obj.setCislo_faktury(rs.getInt("cislo_faktury"));
                    obj.setRok(rs.getInt("rok"));
                    obj.setDodavatel(rs.getString("dodavatel"));
                    obj.setDatum(rs.getString("datum"));
                    obj.setDatum_dodania(rs.getString("datum_dodania"));
                    obj.setDatum_splatnosti(rs.getString("datum_splatnosti"));
                    obj.setMena(rs.getString("mena"));
                    obj.setSuma_bez_dph(rs.getDouble("suma_bez_dph"));
                    obj.setSuma_s_dph(rs.getDouble("suma_s_dph"));
                    zoznam.add(obj);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return zoznam;
    }

    public DF getDF(int id) {
        DF df = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM df WHERE id = ?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    df = new DF();
                    df.setId(rs.getInt("id"));
                    df.setId_firma(rs.getInt("id_firma"));
                    df.setCislo_faktury(rs.getInt("cislo_faktury"));
                    df.setRok(rs.getInt("rok"));
                    df.setDodavatel(rs.getString("dodavatel"));
                    df.setDatum(rs.getString("datum"));
                    df.setDatum_dodania(rs.getString("datum_dodania"));
                    df.setDatum_splatnosti(rs.getString("datum_splatnosti"));
                    df.setMena(rs.getString("mena"));
                    df.setSuma_bez_dph(rs.getDouble("suma_bez_dph"));
                    df.setSuma_s_dph(rs.getDouble("suma_s_dph"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return df;


    }

    public void vloz(DF df) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO df (id_firma, cislo_faktury, rok, dodavatel, datum, datum_dodania, datum_splatnosti, mena, suma_bez_dph, suma_s_dph) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
                ps.setInt(1, df.getId_firma());
                ps.setInt(2, df.getCislo_faktury());
                ps.setInt(3, df.getRok());
                ps.setString(4, df.getDodavatel());
                ps.setString(5, df.getDatum());
                ps.setString(6, df.getDatum_dodania());
                ps.setString(7, df.getDatum_splatnosti());
                ps.setString(8, df.getMena());
                ps.setDouble(9, df.getSuma_bez_dph());
                ps.setDouble(10, df.getSuma_s_dph());
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void zmen(DF df) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE df\n" +
                        "SET \n" +
                        "    id_firma = ?, \n" +
                        "    cislo_faktury = ?, \n" +
                        "    rok = ?,\n" +
                        "    dodavatel = ?,\n" +
                        "    datum = ?,\n" +
                        "    datum_dodania = ?,\n" +
                        "    datum_splatnosti = ?,\n" +
                        "    mena = ?,\n" +
                        "    suma_bez_dph = ?,\n" +
                        "    suma_s_dph = ?\n" +
                        "WHERE\n" +
                        "    id = ?;");
                ps.setInt(1, df.getId_firma());
                ps.setInt(2, df.getCislo_faktury());
                ps.setInt(3, df.getRok());
                ps.setString(4, df.getDodavatel());
                ps.setString(5, df.getDatum());
                ps.setString(6, df.getDatum_dodania());
                ps.setString(7, df.getDatum_splatnosti());
                ps.setString(8, df.getMena());
                ps.setDouble(9, df.getSuma_bez_dph());
                ps.setDouble(10, df.getSuma_s_dph());
                ps.setInt(11, df.getId());
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
                PreparedStatement ps = connection.prepareStatement("DELETE FROM df\n" +
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

