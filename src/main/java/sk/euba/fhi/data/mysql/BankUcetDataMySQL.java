package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.connect.ConnectionManager;
import sk.euba.fhi.model.interf.BankUcetData;
import sk.euba.fhi.model.interf.BankUcetData;
import sk.euba.fhi.model.obj.BankUcet;
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
        List<BankUcet> bankUcetList = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id, meno, ulica, mesto, psc FROM ac_bankUcet");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String meno = rs.getString("meno");
                    String ulica = rs.getString("ulica");
                    String mesto = rs.getString("mesto");
                    Integer psc = rs.getInt("psc");
                    logger.debug("Id, meno, ulica, mesto a psc su: {}, {}, {}, {}, {}", id, meno, ulica, mesto, psc);
                    // TODO BankUcet subject = new BankUcet(id, meno, ulica, mesto, psc);
                    BankUcet subject = new BankUcet();
                    bankUcetList.add(subject);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return bankUcetList;
    }

    public List<BankUcet> preFirmu(long id_firma) {
        return new ArrayList<>(); // TODO
    }


    public BankUcet getBankUcet(long id) {
        return new BankUcet();
    }

    public List<String> nazvyUctov(long firma_id) {
        // TODO
        return new ArrayList<String>();
    }

    public long idUctu(long firma_id, String ucet_nazov) {
        return 0L; // TODO
    }

    public void vloz(BankUcet bankUcet) {

        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO ac_bankUcet (meno, ulica, mesto, psc) VALUES (?, ?, ?, ?)");
                //ps.setString(1, bankUcet.getMeno());
                //ps.setString(2, bankUcet.getUlica());
                //ps.setString(3, bankUcet.getMesto());
                //ps.setInt(4, bankUcet.getPsc());
                // TODO
                int rows = ps.executeUpdate();
                logger.debug("Pocet vlozenych riadkov: {}", rows);
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
    public void zmen(BankUcet bankUcet) {
        // TODO
    }

    public void zmaz(long id) {
        // TODO
    }
}
