import java.sql.*;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/android?useSSL=false", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
            //  throw new RuntimeException(e);
        }
        return null;
    }
}
