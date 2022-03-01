package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterQuery {

    private RegisterQuery() {}

    public static ResultSet selectRegistration(Statement stmt, String email, String address) throws SQLException {
        String sql = "SELECT reg_id from registration where reg_email='" + email + "'and reg_addr='" + address+ "'";
        
        return stmt.executeQuery(sql);
    }
    public static ResultSet selectRegisteredUserList(Statement stmt, String address) throws SQLException {
        String sql = "SELECT * from registration where reg_addr ='" + address + "'";
        
        return stmt.executeQuery(sql);
    }


}
