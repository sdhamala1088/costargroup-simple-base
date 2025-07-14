package costar.testing.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import costar.testing.api.utils.ConfigReader;

public class DBUtil {

    private static ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();
    private static final String DB_CONFIG_PATH = "src/test/resources/db-config.properties";
	static Logger logger = LogManager.getRootLogger();


    public static Connection getConnection() throws Exception {
        if (threadLocalConnection.get() == null || threadLocalConnection.get().isClosed()) {
        	
    		ConfigReader.load(DB_CONFIG_PATH);

            String url = ConfigReader.get("db.url");
            String user = ConfigReader.get("db.username");
            String password = ConfigReader.get("db.password");

            Connection conn = DriverManager.getConnection(url, user, password);
            threadLocalConnection.set(conn);
    		logger.info("Getting database connection {}", conn.toString());
        }

        return threadLocalConnection.get();
    }

    public static void closeConnection() throws Exception {
        Connection conn = threadLocalConnection.get();
        
        if (conn != null && !conn.isClosed()) {
        	logger.info("Closing jdbc database connection");
            conn.close();
            threadLocalConnection.remove();
        }
    }
}
