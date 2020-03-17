package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.common.ConnectionManager;
import sk.euba.fhi.model.interf.VFData;
import sk.euba.fhi.model.obj.VF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VFDataMySQL implements VFData {
    private static final Logger logger = LoggerFactory.getLogger(VFDataMySQL.class);

    public List<VF> vsetky(int id_firmy, int rok) {
        List<VF> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM vf WHERE id_firma = ? AND rok = ?");
                ps.setInt(1, id_firmy);
                ps.setInt(2, rok);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    VF obj = new VF();
                    obj.setId(rs.getInt("id"));
                    obj.setId_firma(rs.getInt("id_firma"));
                    obj.setCislo_faktury(rs.getInt("cislo_faktury"));
                    obj.setRok(rs.getInt("rok"));
                    obj.setOdberatel(rs.getString("odberatel"));
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

    public VF getVF(int id) {
        VF vf = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM vf WHERE id = ?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    vf = new VF();
                    vf.setId(rs.getInt("id"));
                    vf.setId_firma(rs.getInt("id_firma"));
                    vf.setCislo_faktury(rs.getInt("cislo_faktury"));
                    vf.setRok(rs.getInt("rok"));
                    vf.setOdberatel(rs.getString("odberatel"));
                    vf.setDatum(rs.getString("datum"));
                    vf.setDatum_dodania(rs.getString("datum_dodania"));
                    vf.setDatum_splatnosti(rs.getString("datum_splatnosti"));
                    vf.setMena(rs.getString("mena"));
                    vf.setSuma_bez_dph(rs.getDouble("suma_bez_dph"));
                    vf.setSuma_s_dph(rs.getDouble("suma_s_dph"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return vf;
    }

    public void vloz(VF vf) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO vf (id_firma, cislo_faktury, rok, odberatel, datum, datum_dodania, datum_splatnosti, mena, suma_bez_dph, suma_s_dph) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
                ps.setInt(1, vf.getId_firma());
                ps.setInt(2, vf.getCislo_faktury());
                ps.setInt(3, vf.getRok());
                ps.setString(4, vf.getOdberatel());
                ps.setString(5, vf.getDatum());
                ps.setString(6, vf.getDatum_dodania());
                ps.setString(7, vf.getDatum_splatnosti());
                ps.setString(8, vf.getMena());
                ps.setDouble(9, vf.getSuma_bez_dph());
                ps.setDouble(10, vf.getSuma_s_dph());
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
    public void zmen(VF vf) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE vf\n" +
                        "SET \n" +
                        "    id_firma = ?, \n" +
                        "    cislo_faktury = ?, \n" +
                        "    rok = ?,\n" +
                        "    odberatel = ?,\n" +
                        "    datum = ?,\n" +
                        "    datum_dodania = ?,\n" +
                        "    datum_splatnosti = ?,\n" +
                        "    mena = ?,\n" +
                        "    suma_bez_dph = ?,\n" +
                        "    suma_s_dph = ?\n" +
                        "WHERE\n" +
                        "    id = ?;");
                ps.setInt(1, vf.getId_firma());
                ps.setInt(2, vf.getCislo_faktury());
                ps.setInt(3, vf.getRok());
                ps.setString(4, vf.getOdberatel());
                ps.setString(5, vf.getDatum());
                ps.setString(6, vf.getDatum_dodania());
                ps.setString(7, vf.getDatum_splatnosti());
                ps.setString(8, vf.getMena());
                ps.setDouble(9, vf.getSuma_bez_dph());
                ps.setDouble(10, vf.getSuma_s_dph());
                ps.setInt(11, vf.getId());
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
                PreparedStatement ps = connection.prepareStatement("DELETE FROM vf\n" +
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
