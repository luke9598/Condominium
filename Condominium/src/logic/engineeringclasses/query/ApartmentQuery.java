package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApartmentQuery {

    private ApartmentQuery(){}

    public static ResultSet selectApartmentResident(Statement stmt, String address) throws SQLException {
        String sql = "SELECT * FROM apartment where apt_addr='"+address+"' and apt_res is NULL and apt_own is not NULL";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectApartmentOwner(Statement stmt, String address) throws SQLException{
        String sql = "SELECT * FROM apartment where apt_addr='"+address+"' and  apt_own is NULL";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectApartmentId(Statement stmt, String apartment, String address) throws SQLException{
        String sql = "SELECT apt_id FROM apartment where apt_name='"+apartment+"' and  apt_addr='"+address+"'";
        return stmt.executeQuery(sql);
    }
		
    public static ResultSet selectAptInfo(Statement stmt, String  userId,String condAddr,String typeUsr) throws SQLException {
        String sql = "SELECT * from apartment where "+typeUsr+"='"+userId+"' and apt_addr='"+condAddr+"' and apt_res is not NULL ";
        return stmt.executeQuery(sql);
    }

    public static ResultSet loadApt(Statement stmt, String address) throws SQLException{
        String sql = "SELECT * FROM apartment WHERE apt_addr='"+address+"'";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectNameByID(Statement stmt, String id)  throws SQLException {
        String sql= "SELECT user_name FROM users WHERE user_id='"+id+"'";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet checkApartmentFromNumber(Statement stmt, String condAddr, String aptNumber, String userRequired) throws SQLException {
        String sql= "SELECT "+userRequired+" FROM apartment WHERE apt_name='"+aptNumber+"' and apt_addr='"+condAddr+"'";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectEmail(Statement stmt, String userID) throws SQLException {
        String sql= "SELECT user_email  from users where user_id ='"+userID+"'";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectAptIdByRes(Statement stmt, String resID) throws SQLException{
        String sql = "SELECT apt_id FROM apartment WHERE apt_res ='"+resID+"'";
        
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectAptName(Statement stmt, String aptId) throws SQLException{
        String sql = "SELECT apt_name FROM apartment WHERE apt_id ='"+aptId+"'";
        
        return stmt.executeQuery(sql);
    }
}
