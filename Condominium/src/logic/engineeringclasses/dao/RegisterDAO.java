package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.engineeringclasses.query.RegisterQuery;
import logic.model.Registration;
import logic.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDAO extends SqlDAO{

    public boolean checkRegistration(String email,String condominiumCode) throws SQLException {
        try {
            connect();
            ResultSet rs = RegisterQuery.selectRegistration(stmt, email, condominiumCode);
            if(rs.next()) {
                return false;
            }
        } finally {
            disconnect();
        }
        return true;
    }

    public void addRegistrationUser(User user, String role, String apt) throws SQLException{
        try{
            String sql= "INSERT INTO registration (reg_name, reg_email, reg_pwd, reg_role, reg_addr,reg_apt) VALUES (?,?,?,?,?,?)";
            preset = prepConnect(sql);
            preset.setString(1, user.getUsrName());
            preset.setString(2, user.getUsrEmail());
            preset.setString(3, user.getUsrPwd());
            preset.setString(4, role);
            preset.setString(5, user.getUsrAddr());
            preset.setString(6,apt);
            preset.execute();
        } finally {
            disconnect();
        }
    }

    public ObservableList<Registration> loadRegistrationList(String address) throws SQLException{
        ObservableList<Registration> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            connect();
            rs = RegisterQuery.selectRegisteredUserList(stmt,address);
            while(rs.next()) {
                list.add(new Registration(rs.getString("reg_id"),rs.getString("reg_name"),rs.getString("reg_email"),rs.getString("reg_pwd"),rs.getString("reg_role"),rs.getString("reg_addr"), rs.getString("reg_apt")));
            }
        } finally {
            disconnect();
        }
        return list;
    }

    public void deleteRegistered(int registerId)throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM registration WHERE reg_id='"+registerId+"'";
            stmt.executeUpdate(sql);
        } finally {
            disconnect();
        }
    }

    public void deleteAllRegistered(String apartment)throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM registration WHERE reg_apt='"+apartment+"'";
            stmt.executeUpdate(sql);
        } finally {
            disconnect();
        }
    }

    public void addRegistered(RegistrationBean reg) throws SQLException{
        try{
            connect();
            String sql = "INSERT INTO users (user_name,user_email,user_pwd,user_role,user_addr) VALUES (?,?,?,?,?)";
            preset = prepConnect(sql);
            preset.setString(1, reg.getName());
            preset.setString(2, reg.getEmail());
            preset.setString(3, reg.getPassword());
            preset.setString(4, reg.getRole());
            preset.setString(5, reg.getAddress());
            preset.execute();
        } finally {
            disconnect();
        }
    }
}
