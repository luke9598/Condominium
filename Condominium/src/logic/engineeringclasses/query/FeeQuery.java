package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeeQuery {

    private FeeQuery(){}

    public static ResultSet loadAvailableFees(Statement stmt, String address) throws SQLException {
        String sql = "SELECT * FROM condominiums where con_addr='" + address +"'";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet loadFees(Statement stmt, String aptId, String typeFee) throws SQLException {
        String sql = "Select * from "+typeFee+"  WHERE fee_apt ='"+aptId+"'";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet checkApt(Statement stmt, String aptId) throws SQLException {
        String sql = "SELECT fee_apt FROM pastfee where fee_apt='" + aptId +"'";
        
        return stmt.executeQuery(sql);
    }
}
