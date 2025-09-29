package sql_query;

import java.sql.Connection;
import java.sql.Statement;

public class GrantRevoke {
    public static void main(String[] args) {
        try (Connection con = DBUtil.getConnection("")) { 
            Statement stmt = con.createStatement();
            stmt.executeUpdate("CREATE USER IF NOT EXISTS 'testuser'@'localhost' IDENTIFIED BY '1234'");
            stmt.executeUpdate("GRANT SELECT, INSERT ON Bankdb.accounts TO 'user'@'localhost'");
            System.out.println("Privileges granted to user");
            stmt.executeUpdate("REVOKE INSERT ON Bankdb.accounts FROM 'user'@'localhost'");
            System.out.println("INSERT privilege revoked from testuser");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
