package sql_query;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "9999";
    public static Connection getConnection(String dbName) throws Exception {
        return DriverManager.getConnection(BASE_URL + dbName, USER, PASSWORD);
    }
}
