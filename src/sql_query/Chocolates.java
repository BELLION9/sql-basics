package sql_query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Chocolates {
	
	public void createDatabase() {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			String db = "chocolates";
			String username = "root";
			String password = "9999";
			Connection conn = DriverManager.getConnection(url+db,username,password);
			Statement stm = conn.createStatement();
			String query = "create database chocolates ";
			stm.execute(query);
			System.out.println("Table Created Successfully");
			conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void createTable() {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			String db = "chocolates";
			String username = "root";
			String password = "9999";
			Connection conn = DriverManager.getConnection(url+db,username,password);
			Statement stm = conn.createStatement();
			String query = "create table chocolates (sid int(3),sname varchar(100), scount int(100)) ";
			stm.execute(query);
			System.out.println("Table Created Successfully");
			conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void createData() {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			String db = "chocolates";
			String username = "root";
			String password = "9999";
			Connection conn = DriverManager.getConnection(url+db,username,password);
//			Statement stm = conn.createStatement();
//			String query = "INSERT INTO chocolates (sid, sname, scount) VALUES (3, 'Dairy Milk', 12)";
//			stm.executeUpdate(query);
//			System.out.println("data inserted Successfully");
			String query = "INSERT INTO chocolates (sid, sname, scount) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, 4);
            pstmt.setString(2, "Perk");
            pstmt.setInt(3, 11);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) inserted successfully");
			conn.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void readData() {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			String db = "chocolates";
			String username = "root";
			String password = "9999";
			Connection conn = DriverManager.getConnection(url + db, username, password);
			String query = "Select * from chocolates";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()) {
//				System.out.println("id " +rs.getInt(1));
				System.out.println("Chocolate "+rs.getString(2));
				System.out.println("Eaten "+rs.getInt(3));
			}
			
//			System.out.println("Read Successfully");
		} catch(Exception e){
			e.printStackTrace();
		}
	}	
	public void updateData() {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			String db = "chocolates";
			String username = "root";
			String password = "9999";
			Connection conn = DriverManager.getConnection(url + db, username, password);
			String query = "UPDATE chocolates SET scount = 20 WHERE sname = 'Dairy Milk'";
			Statement stm = conn.createStatement();
			int rowsAffected = stm.executeUpdate(query);
			if (rowsAffected > 0) {
	            System.out.println("Data Updated Successfully");
	        } else {
	            System.out.println("No record found to update");
	        }
//			System.out.println("Read Successfully");
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void deleteData() {
		try {
            String url = "jdbc:mysql://localhost:3306/chocolates";
            String username = "root";
            String password = "9999";
            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "DELETE FROM chocolates WHERE sid = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, 4);
            int rows = pstmt.executeUpdate();
            System.out.println(rows + " row(s) deleted");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
