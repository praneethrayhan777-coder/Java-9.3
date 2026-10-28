import java.sql.*;

public class db2 {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Change if needed
        String username = "system"; // Your Oracle username
        String password = "manager"; // Your Oracle password

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to the database
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Oracle Database Successfully!\n");

            // Create SQL statement
            stmt = conn.createStatement();

            // Execute query
            String query = "SELECT * FROM users";
            rs = stmt.executeQuery(query);

            // Display the results
            System.out.println("ID\tusername\temail");
            System.out.println("-----------------");

            while (rs.next()) {
                int eno = rs.getInt("id");
                String name = rs.getString("username");
                String email = rs.getString("email");
                System.out.println(eno + "\t" + name + "\t" + email);
            }
            /*
             * String query1 = "update emp set ename='krishna' where eno=2";
             * int n= stmt.executeUpdate(query1);
             */
            String query2 = "delete from users where id=1";
            int n1 = stmt.executeUpdate(query2);
            if (n1 > 0) {
                System.out.println("deleted");
            } else {
                System.out.println("not deleted");
            }

        } catch (ClassNotFoundException e) {
            System.out.println(" Oracle JDBC Driver not found! Add ojdbc8.jar to your classpath.");
        } catch (SQLException e) {
            System.out.println(" Database error!");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
                System.out.println("\n🔒 Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
