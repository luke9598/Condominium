package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RateQuery {


    private RateQuery() {}

    public static ResultSet getRates(Statement stmt, String userId) throws SQLException {
        String sql = "SELECT * FROM rating WHERE rate_res = '"+userId+"' ";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet getRatesOwner(Statement stmt, String ownerID) throws SQLException {
        String sql = "SELECT * FROM rating WHERE rate_own = '"+ownerID+"' ";
        
        return stmt.executeQuery(sql);
    }
}
