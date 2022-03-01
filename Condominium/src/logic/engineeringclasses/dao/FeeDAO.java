package logic.engineeringclasses.dao;

import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.query.FeeQuery;
import logic.model.Fee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FeeDAO extends SqlDAO{

    public Fee loadAvailableFees(String address) throws SQLException {
        Fee fee = null;
        try {
            connect();
            ResultSet rs = FeeQuery.loadAvailableFees(stmt,address);
            if(rs.next()) {
                boolean park = rs.getBoolean("con_park");
                boolean elevator = rs.getBoolean("con_elevator");
                boolean pet = rs.getBoolean("con_pet");
                boolean wifi = rs.getBoolean("con_wifi");
                fee = new Fee(park,elevator,pet,wifi);
            }
        } finally {
            disconnect();
        }
        return fee;
    }

    public Fee loadFees(String aptId,String typeFee) throws SQLException{
        Fee fee = null;
        try{
            connect();
            ResultSet rs = FeeQuery.loadFees(stmt,aptId,typeFee);
            while (rs.next()) {
                Double admin = rs.getDouble("fee_admin");
                Double park = rs.getDouble("fee_park");
                Double elevator = rs.getDouble("fee_elevator");
                Double pet = rs.getDouble("fee_pet");
                Double wifi = rs.getDouble("fee_wifi");
                fee = new Fee(aptId,admin, park, elevator, pet, wifi);
            }
        } finally {disconnect();}
        return fee;
    }

    public void addFees(FeeBean fee,String table) throws SQLException{
        try{
            String sql = "INSERT INTO "+table+" (fee_apt,fee_admin,fee_park,fee_elevator,fee_pet,fee_wifi) VALUES (?,?,?,?,?,?)";
            preset = prepConnect(sql);
            preset.setString(1,fee.getApt());
            preset.setDouble(2,fee.getAdmin());
            if(fee.getPark().equals(0.0)) preset.setNull(3, Types.NULL);
            else preset.setDouble(3,fee.getPark());
            if(fee.getElevator().equals(0.0)) preset.setNull(4, Types.NULL);
            else preset.setDouble(4,fee.getElevator());
            if(fee.getPet().equals(0.0)) preset.setNull(5, Types.NULL);
            else preset.setDouble(5,fee.getPet());
            if(fee.getWifi().equals(0.0)) preset.setNull(6, Types.NULL);
            else preset.setDouble(6,fee.getWifi());
            preset.execute();
        } finally {
            disconnect();
        }
    }

    public void updateFee(FeeBean fee, String table) throws SQLException{
        try{
            connect();
            String sql = "UPDATE "+table+" SET fee_admin=?, fee_park=?, fee_elevator=?, fee_pet=?, fee_wifi=? WHERE fee_apt='"+fee.getApt()+"'";
            preset = prepConnect(sql);
            preset.setDouble(1,fee.getAdmin());
            if(fee.getPark().equals(0.0)) preset.setNull(2, Types.NULL);
            else preset.setDouble(2,fee.getPark());
            if(fee.getElevator().equals(0.0)) preset.setNull(3, Types.NULL);
            else preset.setDouble(3,fee.getElevator());
            if(fee.getPet().equals(0.0)) preset.setNull(4, Types.NULL);
            else preset.setDouble(4,fee.getPet());
            if(fee.getWifi().equals(0.0)) preset.setNull(5, Types.NULL);
            else preset.setDouble(5,fee.getWifi());
            preset.execute();
        } finally {
            disconnect();
        }
    }

    public boolean checkPastId(String aptId) throws SQLException {
        try{
            connect();
            ResultSet rs = FeeQuery.checkApt(stmt,aptId);
            return rs.next();
        }finally {
            disconnect();
        }
    }

    public void removeFee(String aptId, String table) throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM "+table+" WHERE fee_apt='"+aptId+"'";
            stmt.executeUpdate(sql);
        }  finally {
            disconnect();
        }
    }
}
