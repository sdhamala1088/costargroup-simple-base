package costar.testing.db.tests;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CoStarDBTests extends DBBaseTest {
	
	@Test
	public void testTransactionsHaveValidAccounts() throws Exception {
	    Statement stmt = connection.createStatement();
	    ResultSet rs = stmt.executeQuery("""
	        SELECT * FROM transactions
	        WHERE account_id NOT IN (SELECT account_id FROM accounts)
	    """);
	    Assert.assertFalse(rs.next(), "Found transactions with invalid account_id");
	}
	
	
	//Use Case: Ensure unpaid invoices haven’t received any payments.
	@Test
	public void testEmailExistsAndIsCorrectlyFormatted() throws Exception {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("""
				SELECT client_id FROM clients
				WHERE 
				email = '' OR
				email IS NULL OR
				email !~ '^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$'
				""");
		
		List<Integer> clientsWithInvalidEmail = new ArrayList<>();
		
		while(rs.next()) {
			clientsWithInvalidEmail.add(rs.getInt("client_id"));
		}
		
		Assert.assertTrue(clientsWithInvalidEmail.isEmpty(), "Invalid email(s) found for client IDs: " + clientsWithInvalidEmail);
	}
}
