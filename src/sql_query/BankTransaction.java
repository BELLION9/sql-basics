package sql_query;

import java.sql.Connection;
import java.sql.Savepoint;
import java.sql.Statement;

public class BankTransaction {
    public static void main(String[] args) {
        try (Connection con = DBUtil.getConnection("bankdb")) { 
            con.setAutoCommit(false); 
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE accounts SET balance = balance - 1000 WHERE account_id = 1");
            System.out.println("Deducted 1000 from Account 1");
            Savepoint sp = con.setSavepoint("BeforeCredit");

            stmt.executeUpdate("UPDATE accounts SET balance = balance + 1000 WHERE account_id = 2");
            System.out.println("Added 1000 to Account 2");
            boolean error = false;
            if (error) {
                System.out.println("Error occurred! Rolling back to savepoint...");
                con.rollback(sp);
            }
            con.commit();
            System.out.println("Transaction committed successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
