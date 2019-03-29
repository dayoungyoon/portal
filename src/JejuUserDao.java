import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JejuUserDao extends UserDao {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://172.18.102.128/jeju?serverTimezone=UTC","portal","portaljejunu");
        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=UTC&useSSL=false","root","");
    }
}
