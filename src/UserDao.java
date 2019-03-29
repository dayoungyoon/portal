import java.sql.*;

public abstract class UserDao {
    public User get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection=getConnection();
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

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection =getConnection();
        PreparedStatement preparedStatement =connection.prepareStatement("insert into userinfo (name,password) values (?,?)");
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.executeUpdate();
        preparedStatement=connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        Long id=resultSet.getLong(1);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return  id;
    }
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
