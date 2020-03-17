package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.common.ConnectionManager;
import sk.euba.fhi.model.interf.PouzivatelData;
import sk.euba.fhi.model.obj.Pouzivatel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PouzivatelDataMySQL implements PouzivatelData {
    private static final Logger logger = LoggerFactory.getLogger(PouzivatelDataMySQL.class);

    public List<Pouzivatel> vsetky() {
        List<Pouzivatel> zoznam = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM pouzivatel;");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Pouzivatel obj = new Pouzivatel();
                    obj.setId(rs.getInt("id"));
                    obj.setLogin_meno(rs.getString("login_meno"));
                    obj.setLogin_heslo(rs.getString("login_heslo"));
                    obj.setMeno(rs.getString("meno"));
                    obj.setPriezvisko(rs.getString("priezvisko"));
                    obj.setEmail(rs.getString("email"));
                    obj.setAdresa(rs.getString("adresa"));
                    obj.setPsc(rs.getString("psc"));
                    zoznam.add(obj);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return zoznam;
    }

    public Pouzivatel getPouzivatel(int id) {
        Pouzivatel obj = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM pouzivatel WHERE id = ?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    obj = new Pouzivatel();
                    obj.setId(rs.getInt("id"));
                    obj.setLogin_meno(rs.getString("login_meno"));
                    obj.setLogin_heslo(rs.getString("login_heslo"));
                    obj.setMeno(rs.getString("meno"));
                    obj.setPriezvisko(rs.getString("priezvisko"));
                    obj.setEmail(rs.getString("email"));
                    obj.setAdresa(rs.getString("adresa"));
                    obj.setPsc(rs.getString("psc"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return obj;
    }

    public void vloz(Pouzivatel obj) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO pouzivatel \n" +
                        "(login_meno, login_heslo, meno, priezvisko, email, adresa, psc) \n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?);");
                ps.setString(1, obj.getLogin_meno());
                ps.setString(2, obj.getLogin_heslo());
                ps.setString(3, obj.getMeno());
                ps.setString(4, obj.getPriezvisko());
                ps.setString(5, obj.getEmail());
                ps.setString(6, obj.getAdresa());
                ps.setString(7, obj.getPsc());
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void zmen(Pouzivatel obj) {
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("UPDATE pouzivatel\n" +
                        "SET \n" +
                        "    login_meno = ?, \n" +
                        "    login_heslo = ?, \n" +
                        "    meno = ?,\n" +
                        "    priezvisko = ?,\n" +
                        "    email = ?,\n" +
                        "    adresa = ?,\n" +
                        "    psc = ?\n" +
                        "WHERE\n" +
                        "    id = ?;");
                ps.setString(1, obj.getLogin_meno());
                ps.setString(2, obj.getLogin_heslo());
                ps.setString(3, obj.getMeno());
                ps.setString(4, obj.getPriezvisko());
                ps.setString(5, obj.getEmail());
                ps.setString(6, obj.getAdresa());
                ps.setString(7, obj.getPsc());
                ps.setInt(8, obj.getId());
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
                PreparedStatement ps = connection.prepareStatement("DELETE FROM pouzivatel\n" +
                        "WHERE id = ?;");
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                assert (rows != 1);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public Pouzivatel autentifikuj(String meno, String heslo) {
        Pouzivatel obj = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM pouzivatel\n" +
                        "WHERE login_meno = ?;");
                ps.setString(1, meno);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    obj = new Pouzivatel();
                    obj.setId(rs.getInt("id"));
                    obj.setLogin_meno(rs.getString("login_meno"));
                    obj.setLogin_heslo(rs.getString("login_heslo"));
                    obj.setMeno(rs.getString("meno"));
                    obj.setPriezvisko(rs.getString("priezvisko"));
                    obj.setEmail(rs.getString("email"));
                    obj.setAdresa(rs.getString("adresa"));
                    obj.setPsc(rs.getString("psc"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return heslo.equals(obj.getLogin_heslo()) ? obj : null;
    }
}
