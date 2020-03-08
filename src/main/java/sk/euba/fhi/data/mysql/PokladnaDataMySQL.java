package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.connect.ConnectionManager;
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

    public List<Pokladna> vsetky(long id_firmy, int rok) {
        List<Pokladna> pokladnaList = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id, meno, ulica, mesto, psc FROM ac_pokladna");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String meno = rs.getString("meno");
                    String ulica = rs.getString("ulica");
                    String mesto = rs.getString("mesto");
                    Integer psc = rs.getInt("psc");
                    logger.debug("Id, meno, ulica, mesto a psc su: {}, {}, {}, {}, {}", id, meno, ulica, mesto, psc);
                    // TODO Pokladna subject = new Pokladna(id, meno, ulica, mesto, psc);
                    Pokladna subject = new Pokladna();
                    pokladnaList.add(subject);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return pokladnaList;
    }

    public Pokladna getPokladna(long id) {
        return new Pokladna();
    }

    public void vloz(Pokladna pokladna) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO ac_pokladna (meno, ulica, mesto, psc) VALUES (?, ?, ?, ?)");
                //ps.setString(1, pokladna.getMeno());
                //ps.setString(2, pokladna.getUlica());
                //ps.setString(3, pokladna.getMesto());
                //ps.setInt(4, pokladna.getPsc());
                // TODO
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void zmen(Pokladna pokladna) {
        // TODO
    }

    public void zmaz(long id) {
        // TODO
    }
}
