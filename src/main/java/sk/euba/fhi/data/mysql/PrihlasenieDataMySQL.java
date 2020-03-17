package sk.euba.fhi.data.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.euba.fhi.data.mysql.common.ConnectionManager;
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
        return sessionList;
    }

    public Prihlasenie getSession(int id_pouzivatel) {
        Prihlasenie obj = null;
        Connection connection = new ConnectionManager().createConnection();
        if (connection != null) {
            try {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM prihlasenie WHERE id_pouzivatel = ?;");
                ps.setInt(1, id_pouzivatel);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    obj = new Prihlasenie();
                    obj.setId_pouzivatel(rs.getInt("id_pouzivatel"));
                    obj.setRok(rs.getInt("rok"));
                    obj.setId_firma(rs.getInt("id_firma"));
                    obj.setFirma_nazov(rs.getString("firma_nazov"));
                    obj.setId_ucet(rs.getInt("id_ucet"));
                    obj.setUcet_nazov(rs.getString("ucet_nazov"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        return obj;
    }
}
