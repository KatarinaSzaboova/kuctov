package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.connect.ConnectionManager;
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

    public List<Firma> vsetky(long pouzivatel_id) {
        List<Firma> firmaList = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id, meno, ulica, mesto, psc FROM ac_firma");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String meno = rs.getString("meno");
                    String ulica = rs.getString("ulica");
                    String mesto = rs.getString("mesto");
                    Integer psc = rs.getInt("psc");
                    logger.debug("Id, meno, ulica, mesto a psc su: {}, {}, {}, {}, {}", id, meno, ulica, mesto, psc);
                    // TODO Firma subject = new Firma(id, meno, ulica, mesto, psc);
                    Firma subject = new Firma();
                    firmaList.add(subject);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return firmaList;
    }

    public List<String> nazvyFiriem(long pouzivatel_id) {
        // TODO
        return new ArrayList<String>();
    }

    public long idFirmy(long pouzivatel_id, String firma_nazov) {
        return 0L; // TODO
    }

    public Firma getFirma(long id) {
        return new Firma();
    }

    public void vloz(Firma firma) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO ac_firma (meno, ulica, mesto, psc) VALUES (?, ?, ?, ?)");
                //ps.setString(1, firma.getMeno());
                //ps.setString(2, firma.getUlica());
                //ps.setString(3, firma.getMesto());
                //ps.setInt(4, firma.getPsc());
                // TODO
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
    public void zmen(Firma firma) {
        // TODO
    }

    public void zmaz(long id) {
        // TODO
    }
}
