package sk.euba.fhi.data.mysql.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    public Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("Chyba ovládač pre MySQL JDBC!");
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("sk.euba.fhi.data:mysql://35.198.174.32:3306/SZABOOVA", "szaboova", "szaboova");
        } catch (SQLException ex) {
            logger.error("Chyba - nebolo možné sa pripojiť na databázu!", ex);
        }
        return connection;
    }
}
