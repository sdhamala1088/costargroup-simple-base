package costar.testing.db.tests;

import costar.testing.db.utils.DBUtil;
import java.sql.Connection;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class DBBaseTest {
	
    protected Connection connection;

    @BeforeClass
    public void setup() throws Exception {
        connection = DBUtil.getConnection();
    }

    @AfterClass
    public void tearDown() throws Exception {
        DBUtil.closeConnection();
    }
}