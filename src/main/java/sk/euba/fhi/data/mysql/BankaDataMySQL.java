package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.common.ConnectionManager;
import sk.euba.fhi.model.interf.BankaData;
import sk.euba.fhi.model.obj.Banka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankaDataMySQL implements BankaData {
    private static final Logger logger = LoggerFactory.getLogger(BankaDataMySQL.class);

    public List<Banka> vsetky(int id_ucet, int rok) {
        List<Banka> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM banka WHERE id_ucet = ? AND rok = ?");
                ps.setInt(1,id_ucet);
                ps.setInt(2, rok);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Banka obj = new Banka();
                    obj.setId(rs.getInt("id"));
                    obj.setId_ucet(rs.getInt("id_ucet"));
                    obj.setRok(rs.getInt("rok"));
                    obj.setDatum(rs.getString("datum"));
                    obj.setOvplyv_zd(rs.getString("ovplyv_zd"));
                    obj.setMena(rs.getString("mena"));
                    obj.setSuma(rs.getDouble("suma"));
                    obj.setPartner(rs.getString("partner"));
                    obj.setPartner_iban(rs.getString("partner_iban"));
                    zoznam.add(obj);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return zoznam;
    }

    public Banka getBanka(int id) {
        Banka banka = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM banka WHERE id = ?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    banka = new Banka();
                    banka.setId(rs.getInt("id"));
                    banka.setId_ucet(rs.getInt("id_ucet"));
                    banka.setRok(rs.getInt("rok"));
                    banka.setDatum(rs.getString("datum"));
                    banka.setOvplyv_zd(rs.getString("ovplyv_zd"));
                    banka.setMena(rs.getString("mena"));
                    banka.setSuma(rs.getDouble("suma"));
                    banka.setPartner(rs.getString("partner"));
                    banka.setPartner_iban(rs.getString("partner_iban"));
                }
            } catch (SQLException ex) {
                //logger.error(ex.getMessage(), ex);
            }
        }
        return banka;

    }

    public void vloz(Banka banka) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO banka (id_ucet, rok, datum, ovplyv_zd, mena, suma, partner, partner_iban) VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
                ps.setInt(1, banka.getId_ucet());
                ps.setInt(2, banka.getRok());
                ps.setString(3, banka.getDatum());
                ps.setString(4, banka.getOvplyv_zd());
                ps.setString(5, banka.getMena());
                ps.setDouble(6, banka.getSuma());
                ps.setString(7, banka.getPartner());
                ps.setString(8, banka.getPartner_iban());
                int rows = ps.executeUpdate();
                //logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void zmen(Banka banka) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE banka\n" +
                        "SET \n" +
                        "    id_ucet = ?, \n" +
                        "    rok = ?,\n" +
                        "    datum = ?,\n" +
                        "    ovplyv_zd = ?,\n" +
                        "    mena = ?,\n" +
                        "    suma = ?\n" +
                        "    partner = ?\n" +
                        "    partner_iban = ?\n" +
                        "WHERE\n" +
                        "    id = ?;");

                ps.setInt(1, banka.getId_ucet());
                ps.setInt(2, banka.getRok());
                ps.setString(3,banka.getDatum());
                ps.setString(4, banka.getOvplyv_zd());
                ps.setString(5, banka.getMena());
                ps.setDouble(6, banka.getSuma());
                ps.setString(7, banka.getPartner());
                ps.setString(8, banka.getPartner_iban());
                ps.setInt(9, banka.getId());
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
                PreparedStatement ps = connection.prepareStatement("DELETE FROM banka\n" +
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
