package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.connect.ConnectionManager;
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

    public List<Banka> vsetky(long id_firmy, long id_uctu, int rok) {
        List<Banka> bankaia = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id, meno, ulica, mesto, psc FROM ac_banka");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String meno = rs.getString("meno");
                    String ulica = rs.getString("ulica");
                    String mesto = rs.getString("mesto");
                    Integer psc = rs.getInt("psc");
                    logger.debug("Id, meno, ulica, mesto a psc su: {}, {}, {}, {}, {}", id, meno, ulica, mesto, psc);
                    // TODO Banka subject = new Banka(id, meno, ulica, mesto, psc);
                    Banka subject = new Banka();
                    bankaia.add(subject);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return bankaia;
    }

    public Banka getBanka(long id) {
        return new Banka();
    }

    public void vloz(Banka banka) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO ac_banka (meno, ulica, mesto, psc) VALUES (?, ?, ?, ?)");
                //ps.setString(1, banka.getMeno());
                //ps.setString(2, banka.getUlica());
                //ps.setString(3, banka.getMesto());
                //ps.setInt(4, banka.getPsc());
                // TODO
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
    public void zmen(Banka banka) {
        // TODO
    }

    public void zmaz(long id) {
        // TODO
    }

}
