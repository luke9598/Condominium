package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MeetQuery {

    private MeetQuery() {}

    public static ResultSet selectMeetRequests(Statement stmt, String address) throws SQLException{
        String sql = "SELECT * from meeting where meet_addr ='" + address + "'";
        
        return stmt.executeQuery(sql);
    }
}
