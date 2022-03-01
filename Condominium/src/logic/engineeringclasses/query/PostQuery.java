package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostQuery {

    private PostQuery() {}

    public static ResultSet selectListPost(Statement stmt, String address) throws SQLException {
        String sql = "SELECT post_id,post_usr,post_txt,post_img FROM posts where post_id IN (SELECT  post_id FROM `posts` WHERE post_addr='"+ address + "')";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectNameByID(Statement stmt, String id) throws SQLException {
        String sql = "SELECT user_name FROM users WHERE user_id='" + id + "'";
        
        return stmt.executeQuery(sql);
    }
}
