package costar.testing.db.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtil {

    private static ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();
	static Logger logger = LogManager.getRootLogger();


    public static Connection getConnection() throws Exception {
        if (threadLocalConnection.get() == null || threadLocalConnection.get().isClosed()) {
        	
            Properties props = new Properties();
            props.load(new FileInputStream("src/test/resources/dbconfig.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            Connection conn = DriverManager.getConnection(url, user, password);
            threadLocalConnection.set(conn);
    		logger.log(Level.INFO, String.format("Getting database connection with properities %s", props.toString()));
        }

        return threadLocalConnection.get();
    }

    public static void closeConnection() throws Exception {
        Connection conn = threadLocalConnection.get();
        
        if (conn != null && !conn.isClosed()) {
        	logger.log(Level.INFO, "Closing jdbc database connection");
            conn.close();
            threadLocalConnection.remove();
        }
    }
}
