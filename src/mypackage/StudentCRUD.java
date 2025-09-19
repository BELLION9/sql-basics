package mypackage;

import java.sql.*;

public class StudentCRUD {

//     CREATE
    public void addStudent(int id, String name, int age) {
        String query = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection("studentsdb");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//     READ
    public void getStudents() {
        String query = "SELECT * FROM students";
        try (Connection conn = DBUtil.getConnection("studentsdb");
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Age: " + rs.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//  UPDATE
    public void updateStudentAge(int id, int newAge) {
        String query = "UPDATE students SET age = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection("studentsdb");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, newAge);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Student updated successfully!");
            else System.out.println("No student found with that ID.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    DELETE
    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBUtil.getConnection("studentsdb");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("Student deleted successfully!");
            else System.out.println("No student found with that ID.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Driver code
    public static void main(String[] args) {
        StudentCRUD crud = new StudentCRUD();
//        crud.addStudent(3, "Nikhil", 24);
//        crud.addStudent(4, "Eashwanth", 21);

          crud.getStudents();
//        crud.updateStudentAge(1, 21);
//        crud.deleteStudent(2);
//        crud.getStudents();
    }
}

