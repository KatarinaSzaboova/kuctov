package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.connect.ConnectionManager;
import sk.euba.fhi.model.interf.PrihlasenieData;
import sk.euba.fhi.model.obj.Prihlasenie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrihlasenieDataMySQL implements PrihlasenieData {
    private static final Logger logger = LoggerFactory.getLogger(PrihlasenieDataMySQL.class);

    public List<Prihlasenie> vsetky() {
        List<Prihlasenie> sessionList = new ArrayList<>();
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT id, meno, ulica, mesto, psc FROM ac_session");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String meno = rs.getString("meno");
                    String ulica = rs.getString("ulica");
                    String mesto = rs.getString("mesto");
                    Integer psc = rs.getInt("psc");
                    logger.debug("Id, meno, ulica, mesto a psc su: {}, {}, {}, {}, {}", id, meno, ulica, mesto, psc);
                    // TODO Prihlasenie subject = new Prihlasenie(id, meno, ulica, mesto, psc);
                    Prihlasenie subject = new Prihlasenie();
                    sessionList.add(subject);
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return sessionList;
    }

    public Prihlasenie getSession(long pouzivatel_id) {
        return new Prihlasenie();
    }
}
