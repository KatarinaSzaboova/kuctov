package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.connect.ConnectionManager;
import sk.euba.fhi.model.interf.VFData;
import sk.euba.fhi.model.obj.VF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VFDataMySQL implements VFData {
    private static final Logger logger = LoggerFactory.getLogger(VFDataMySQL.class);

    public List<VF> vsetky(long id_firmy, int rok) {
        List<VF> vfList = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id, meno, ulica, mesto, psc FROM ac_vf");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String meno = rs.getString("meno");
                    String ulica = rs.getString("ulica");
                    String mesto = rs.getString("mesto");
                    Integer psc = rs.getInt("psc");
                    logger.debug("Id, meno, ulica, mesto a psc su: {}, {}, {}, {}, {}", id, meno, ulica, mesto, psc);
                    // TODO VF subject = new VF(id, meno, ulica, mesto, psc);
                    VF subject = new VF();
                    vfList.add(subject);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return vfList;
    }

    public VF getVF(long id) {
        return new VF();
    }

    public void vloz(VF vf) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO ac_vf (meno, ulica, mesto, psc) VALUES (?, ?, ?, ?)");
                //ps.setString(1, vf.getMeno());
                //ps.setString(2, vf.getUlica());
                //ps.setString(3, vf.getMesto());
                //ps.setInt(4, vf.getPsc());
                // TODO
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
    public void zmen(VF vf) {
        // TODO
    }

    public void zmaz(long id) {
        // TODO
    }
}
