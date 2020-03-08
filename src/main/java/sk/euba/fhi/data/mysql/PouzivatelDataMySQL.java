package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.connect.ConnectionManager;
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
        List<Pouzivatel> pouzivatelList = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id, meno, ulica, mesto, psc FROM ac_pouzivatel");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String meno = rs.getString("meno");
                    String ulica = rs.getString("ulica");
                    String mesto = rs.getString("mesto");
                    Integer psc = rs.getInt("psc");
                    logger.debug("Id, meno, ulica, mesto a psc su: {}, {}, {}, {}, {}", id, meno, ulica, mesto, psc);
                    // TODO Pouzivatel subject = new Pouzivatel(id, meno, ulica, mesto, psc);
                    Pouzivatel subject = new Pouzivatel();
                    pouzivatelList.add(subject);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return pouzivatelList;
    }

    public Pouzivatel getPouzivatel(long id) {
        return new Pouzivatel();
    }

    public void vloz(Pouzivatel pouzivatel) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO ac_pouzivatel (meno, ulica, mesto, psc) VALUES (?, ?, ?, ?)");
                //ps.setString(1, pouzivatel.getMeno());
                //ps.setString(2, pouzivatel.getUlica());
                //ps.setString(3, pouzivatel.getMesto());
                //ps.setInt(4, pouzivatel.getPsc());
                // TODO
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
    public void zmen(Pouzivatel pouzivatel) {
        // TODO
    }

    public void zmaz(long id) {
        // TODO
    }
}
