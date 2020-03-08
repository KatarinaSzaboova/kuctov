package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.connect.ConnectionManager;
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

    public List<DF> vsetky(long id_firmy, int rok) {
        List<DF> vfList = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id, meno, ulica, mesto, psc FROM ac_df");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String meno = rs.getString("meno");
                    String ulica = rs.getString("ulica");
                    String mesto = rs.getString("mesto");
                    Integer psc = rs.getInt("psc");
                    logger.debug("Id, meno, ulica, mesto a psc su: {}, {}, {}, {}, {}", id, meno, ulica, mesto, psc);
                    // TODO DF subject = new DF(id, meno, ulica, mesto, psc);
                    DF subject = new DF();
                    vfList.add(subject);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return vfList;
    }

    public DF getDF(long id) {
        return new DF();
    }

    public void vloz(DF df) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO ac_df (meno, ulica, mesto, psc) VALUES (?, ?, ?, ?)");
                //ps.setString(1, df.getMeno());
                //ps.setString(2, df.getUlica());
                //ps.setString(3, df.getMesto());
                //ps.setInt(4, df.getPsc());
                // TODO
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
    public void zmen(DF df) {
        // TODO
    }

    public void zmaz(long id) {
        // TODO
    }
}