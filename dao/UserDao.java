package controller;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 用户DAO类
 */
public class UserDao {
    /**
     * 登录验证
     * @param connection
     * @param user
     * @return
     * @throws SQLException
     */
    public User login(Connection connection, User user) throws SQLException {
        User resUser = null;
        String sql = "select *from users where username=\"**\" and password=\"&&\";";
        sql = sql.replace("**", user.getUsername());
        sql = sql.replace("&&", user.getPassword());
        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(sql);
        //查到结果就将结果存到resUser中返回
        if(res.next()){
             resUser = new User(res.getString("username"),res.getString("password"));
             resUser.setId(res.getInt("id"));
        }
        return resUser;
    }
}
