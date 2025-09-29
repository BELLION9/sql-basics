package sql_query;

import java.sql.*;
//DDL (Create),Constraints ,DML (INSERT),INNER JOIN
public class InnerJoin {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection("InnerJoin");
             Statement st = conn.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(150) UNIQUE)");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS courses (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "title VARCHAR(100) NOT NULL, " +
                    "credits INT DEFAULT 3)");

            st.executeUpdate("CREATE TABLE IF NOT EXISTS enrollments (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "student_id INT NOT NULL, " +
                    "course_id INT NOT NULL, " +
                    "grade VARCHAR(2), " +
                    "FOREIGN KEY(student_id) REFERENCES students(id), " +
                    "FOREIGN KEY(course_id) REFERENCES courses(id))");
//            st.executeUpdate("INSERT INTO students(name,email) VALUES('Krish','krish.gotte@caelius')");
//            st.executeUpdate("INSERT INTO students(name,email) VALUES('Prinshu','priyanshu@caelius')");
//            st.executeUpdate("INSERT INTO courses(title,credits) VALUES('DB',4)");
//            st.executeUpdate("INSERT INTO courses(title,credits) VALUES('OS',3)");
//            st.executeUpdate("INSERT INTO enrollments(student_id,course_id,grade) VALUES(1,1,'A')");
//            st.executeUpdate("INSERT INTO enrollments(student_id,course_id,grade) VALUES(2,1,'B')");
//            st.executeUpdate("INSERT INTO enrollments(student_id,course_id,grade) VALUES(2,2,'C')");
            ResultSet rs = st.executeQuery(
                "SELECT s.name, c.title, e.grade " +
                "FROM enrollments e " +
                "INNER JOIN students s ON e.student_id = s.id " +
                "INNER JOIN courses c ON e.course_id = c.id");

            System.out.println("INNER JOIN:");
            while (rs.next()) {
                System.out.printf("%s -> %s : %s%n",
                        rs.getString("name"), rs.getString("title"), rs.getString("grade"));
            }
            String deleteDuplicateEnrollments =
                    "DELETE e1 FROM enrollments e1 " +
                    "INNER JOIN enrollments e2 " +
                    "WHERE e1.id > e2.id " +
                    "AND e1.student_id = e2.student_id " +
                    "AND e1.course_id = e2.course_id";
            int deleted = st.executeUpdate(deleteDuplicateEnrollments);
            System.out.println("Duplicate enrollments deleted: " + deleted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
