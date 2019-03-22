import java.sql.*;

public class UserDao {
    public User get(Long id) throws ClassNotFoundException, SQLException {
        //db가 뭐야? mysql
        //어딧어? 알려줄게
        //mysql드라이버로드
        Class.forName("com.mysql.cj.jdbc.Driver");
        //커넥션맺고
//        Connection connection= DriverManager.getConnection("jdbc:mysql://172.18.102.128/jeju?serverTimezone=UTC","portal","portaljejunu");
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=UTC&useSSL=false","root","");
        //sql쿼리만들고
        PreparedStatement preparedStatement =connection.prepareStatement("select*from userinfo where id=?");
        preparedStatement.setLong(1,id);
        //쿼리실행하고
        ResultSet resultSet=preparedStatement.executeQuery();
        //실행된 쿼리를 오브젝트에 매핑하고
        resultSet.next();
        User user=new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //리턴하기전에 자원해지하고
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //리턴
        return  user;
    }
}
