package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.common.ConnectionManager;
import sk.euba.fhi.model.interf.PokladnaData;
import sk.euba.fhi.model.obj.Pokladna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokladnaDataMySQL implements PokladnaData {
    private static final Logger logger = LoggerFactory.getLogger(PokladnaDataMySQL.class);

    public List<Pokladna> vsetky(int id_firmy, int rok) {
        List<Pokladna> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM pokladna WHERE id_firma = ? AND rok = ?");
                ps.setInt(1, id_firmy);
                ps.setInt(2, rok);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Pokladna obj = new Pokladna();
                    obj.setId(rs.getInt("id"));
                    obj.setId_firma(rs.getInt("id_firma"));
                    obj.setCislo_dokladu(rs.getInt("cislo_dokladu"));
                    obj.setRok(rs.getInt("rok"));
                    obj.setVzor(rs.getString("vzor"));
                    obj.setDatum(rs.getString("datum"));
                    obj.setMena(rs.getString("mena"));
                    obj.setSuma_bez_dph(rs.getDouble("suma_bez_dph"));
                    obj.setSuma_s_dph(rs.getDouble("suma_s_dph"));
                    obj.setOvplyv_zd(rs.getString("ovplyv_zd"));
                    zoznam.add(obj);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return zoznam;
    }

    public Pokladna getPokladna(int id) {
        Pokladna pokladna = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM pokladna WHERE id = ?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    pokladna = new Pokladna();
                    pokladna.setId(rs.getInt("id"));
                    pokladna.setId_firma(rs.getInt("id_firma"));
                    pokladna.setCislo_dokladu(rs.getInt("cislo_dokladu"));
                    pokladna.setRok(rs.getInt("rok"));
                    pokladna.setVzor(rs.getString("vzor"));
                    pokladna.setDatum(rs.getString("datum"));
                    pokladna.setMena(rs.getString("mena"));
                    pokladna.setSuma_bez_dph(rs.getDouble("suma_bez_dph"));
                    pokladna.setSuma_s_dph(rs.getDouble("suma_s_dph"));
                    pokladna.setOvplyv_zd(rs.getString("ovplyv_zd"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return pokladna;
    }

    public void vloz(Pokladna pokladna) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO pokladna (id_firma, cislo_dokladu, rok, vzor, datum, mena, suma_bez_dph, suma_s_dph, ovplyv_zd) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, pokladna.getId_firma());
                ps.setInt(2, pokladna.getCislo_dokladu());
                ps.setInt(3, pokladna.getRok());
                ps.setString(4, pokladna.getVzor());
                ps.setString(5, pokladna.getDatum());
                ps.setString(6, pokladna.getMena());
                ps.setDouble(7, pokladna.getSuma_bez_dph());
                ps.setDouble(8, pokladna.getSuma_s_dph());
                ps.setString(9, pokladna.getOvplyv_zd());
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void zmen(Pokladna pokladna) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE pokladna\n" +
                        "SET \n" +
                        "    id_firma = ?, \n" +
                        "    cislo_dokladu = ?, \n" +
                        "    rok = ?,\n" +
                        "    vzor = ?,\n" +
                        "    datum = ?,\n" +
                        "    mena = ?,\n" +
                        "    suma_bez_dph = ?,\n" +
                        "    suma_s_dph = ?,\n" +
                        "    ovplyv_zd = ?\n" +
                        "WHERE\n" +
                        "    id = ?;");

                ps.setInt(1, pokladna.getId_firma());
                ps.setInt(2, pokladna.getCislo_dokladu());
                ps.setInt(3, pokladna.getRok());
                ps.setString(4, pokladna.getVzor());
                ps.setString(5, pokladna.getDatum());
                ps.setString(6, pokladna.getMena());
                ps.setDouble(7, pokladna.getSuma_bez_dph());
                ps.setDouble(8, pokladna.getSuma_s_dph());
                ps.setString(9, pokladna.getOvplyv_zd());
                ps.setInt(10, pokladna.getId());
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
                PreparedStatement ps = connection.prepareStatement("DELETE FROM pokladna\n" +
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
