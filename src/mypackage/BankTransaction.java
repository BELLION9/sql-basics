package mypackage;

import java.sql.Connection;
import java.sql.Savepoint;
import java.sql.Statement;

public class BankTransaction {

	public static void main(String[] args) {
		try (Connection con = DBUtil.getConnection("Bankdb")) {
			Statement stmt = con.createStatement();
			String createTable = "CREATE TABLE IF NOT EXISTS accounts (" + "account_id INT PRIMARY KEY, "
					+ "balance INT)";
			stmt.execute(createTable);
			System.out.println("Table 'accounts' ready.");

			stmt.executeUpdate("INSERT INTO accounts(account_id, balance) VALUES (1, 5000), (2, 3000)");
			System.out.println("Sample data inserted: Account 1 = 5000, Account 2 = 3000");

			int rows1 = stmt.executeUpdate("UPDATE accounts SET balance = balance - 1000 WHERE account_id = 1");
			System.out.println("Deducted 1000 from Account 1 (rows affected: " + rows1 + ")");
			Savepoint sp = con.setSavepoint("BeforeCredit");
			int rows2 = stmt.executeUpdate("UPDATE accounts SET balance = balance + 1000 WHERE account_id = 2");
			System.out.println("Added 1000 to Account 2 (rows affected: " + rows2 + ")");
			boolean error = true;
			if (error) {
				System.out.println("Error occurred! Rolling back to savepoint...");
				con.rollback(sp);
			}
			con.commit();
			System.out.println("Transaction committed successfully");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
