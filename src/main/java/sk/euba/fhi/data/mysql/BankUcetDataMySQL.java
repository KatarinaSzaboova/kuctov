package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.common.ConnectionManager;
import sk.euba.fhi.model.interf.BankUcetData;
import sk.euba.fhi.model.obj.BankUcet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankUcetDataMySQL implements BankUcetData {
    private static final Logger logger = LoggerFactory.getLogger(BankUcetDataMySQL.class);

    public List<BankUcet> vsetky() {
        List<BankUcet> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM bank_ucet;");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    BankUcet obj = new BankUcet();
                    obj.setId(rs.getInt("id"));
                    obj.setId_firma(rs.getInt("id_firma"));
                    obj.setNazov(rs.getString("nazov"));
                    obj.setBic(rs.getString("bic"));
                    obj.setIban(rs.getString("iban"));
                    obj.setMena(rs.getString("mena"));
                    zoznam.add(obj);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return zoznam;
    }

    public List<BankUcet> preFirmu(int id_firma)
    {
        List<BankUcet> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM bank_ucet WHERE id_firma = ?;");
                ps.setInt(1, id_firma);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    BankUcet obj = new BankUcet();
                    obj.setId(rs.getInt("id"));
                    obj.setId_firma(rs.getInt("id_firma"));
                    obj.setNazov(rs.getString("nazov"));
                    obj.setBic(rs.getString("bic"));
                    obj.setIban(rs.getString("iban"));
                    obj.setMena(rs.getString("mena"));
                    zoznam.add(obj);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return zoznam;
    }


    public BankUcet getBankUcet(int id) {
        BankUcet bankucet = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM bank_ucet WHERE id = ?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    bankucet = new BankUcet();
                    bankucet.setId(rs.getInt("id"));
                    bankucet.setId_firma(rs.getInt("id_firma"));
                    bankucet.setNazov(rs.getString("nazov"));
                    bankucet.setBic(rs.getString("bic"));
                    bankucet.setIban(rs.getString("iban"));
                    bankucet.setMena(rs.getString("mena"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return bankucet;
        }

    public List<String> nazvyUctov(int id_firma) {
        List<String> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT nazov FROM bank_ucet WHERE id_firma = ?");
                ps.setInt(1, id_firma);
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

    public int idUctu(int id_firma, String nazov) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id FROM bank_ucet WHERE id_firma = ? AND nazov = ?");
                ps.setInt(1, id_firma);
                ps.setString(2, nazov);
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

    public void vloz(BankUcet bankUcet) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO bank_ucet (id_firma, nazov, bic, iban, mena) VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, bankUcet.getId_firma());
                ps.setString(2, bankUcet.getNazov());
                ps.setString(3, bankUcet.getBic());
                ps.setString(4, bankUcet.getIban());
                ps.setString(5, bankUcet.getMena());
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void zmen(BankUcet bankUcet) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE bank_ucet\n" +
                        "SET \n" +
                        "    id_firma = ?, \n" +
                        "    nazov = ?, \n" +
                        "    bic = ?,\n" +
                        "    iban = ?,\n" +
                        "    mena = ?\n" +
                        "WHERE\n" +
                        "    id = ?;");

                ps.setInt(1, bankUcet.getId_firma());
                ps.setString(2, bankUcet.getNazov());
                ps.setString(3, bankUcet.getBic());
                ps.setString(4, bankUcet.getIban());
                ps.setString(5, bankUcet.getMena());
                ps.setInt(6, bankUcet.getId());
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
                PreparedStatement ps = connection.prepareStatement("DELETE FROM bank_ucet\n" +
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
