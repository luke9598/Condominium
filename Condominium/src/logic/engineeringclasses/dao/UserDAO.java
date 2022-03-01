package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.bean.UserBean;
import logic.engineeringclasses.exception.InputException;
import logic.engineeringclasses.query.ApartmentQuery;
import logic.engineeringclasses.query.UserQuery;
import logic.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends SqlDAO{

    private String usrName;
    private static final String USER_NAME = "user_name";
    private static final String USER_PASSWORD = "user_pwd";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_ADDRESS = "user_addr";

    public String checkNameByID(String id)throws SQLException {
        try {
            connect();
            ResultSet rs = ApartmentQuery.selectNameByID(stmt, id);
            if(rs.next()) {
                this.usrName = rs.getString(USER_NAME);
            }
        } finally {
            disconnect();
        }
        return this.usrName;
    }

    public String checkUserID(String email,String address) throws SQLException{
        String getID = "";
        try {
            connect();
            ResultSet rs = UserQuery.selectUserID(stmt, email,address);
            if(rs.next()) {
                getID = rs.getString("user_id");
            }
        } finally {
            disconnect();
        }
        return getID;
    }

    public Role checkRole(String id) throws SQLException {
        Role role = null;
        try {
            connect();
            ResultSet rs = UserQuery.selectRole(stmt,id);
            if(rs.next()) {
                role = Role.valueOf(rs.getString("user_role"));
            }
        } finally {
            disconnect();
        }
        return role;
    }

    public  String checkLogin(String email,String condominiumCode) throws SQLException {
        String pwd = "";
        try {
            connect();
            ResultSet rs = UserQuery.selectLogin(stmt, email, condominiumCode);
            if(rs.next()) {
                pwd = rs.getString(USER_PASSWORD);
            }
        } finally {
            disconnect();
        }
        return pwd;
    }

    //USER INITIALIZATION
    public Administrator loadAdminID(String id) throws SQLException {
        Administrator admin = null;
        try {
            connect();
            ResultSet rs = UserQuery.loadUserByID(stmt,id);
            if(rs.next()) {
                String userName = rs.getString(USER_NAME);
                String userEmail = rs.getString(USER_EMAIL);
                String userPwd = rs.getString(USER_PASSWORD);
                String userAddr = rs.getString(USER_ADDRESS);
                admin = new Administrator(id,userName,userEmail,userPwd,userAddr);
            }
        } finally {
            disconnect();
        }
        return admin;
    }

    public Owner loadOwnerID(String id) throws SQLException {
        Owner owner = null;
        try {
            connect();
            ResultSet rs = UserQuery.loadUserByID(stmt,id);
            if(rs.next()) {
                String userName = rs.getString(USER_NAME);
                String userEmail = rs.getString(USER_EMAIL);
                String userPwd = rs.getString(USER_PASSWORD);
                String userAddr = rs.getString(USER_ADDRESS);
                owner = new Owner(id,userName,userEmail,userPwd,userAddr);
            }
        } finally {
            disconnect();
        }
        return owner;
    }

    public Resident loadResidentID(String id) throws SQLException, InputException {
        Resident resident = null;
        try {
            connect();
            ResultSet rs = UserQuery.loadUserByID(stmt,id);
            if(rs.next()) {
                String userName = rs.getString(USER_NAME);
                String userEmail = rs.getString(USER_EMAIL);
                String userPwd = rs.getString(USER_PASSWORD);
                String userAddr = rs.getString(USER_ADDRESS);
                resident = new Resident(id,userName,userEmail,userPwd,userAddr);
            }
        } finally {
            disconnect();
        }
        return resident;
    }

    public ObservableList<User> loadUserList(String address) throws SQLException{
        ObservableList<User> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            connect();
            rs = UserQuery.selectUserList(stmt,address);
            while(rs.next()) {
                list.add(new User(rs.getString("user_id"),rs.getString(USER_NAME),rs.getString(USER_EMAIL),rs.getString(USER_PASSWORD),rs.getString("user_role"),rs.getString(USER_ADDRESS)));
            }
        } finally {
            disconnect();
        }
        return list;
    }

    public ObservableList<String> loadMailList(String address) throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;
        try{
            connect();
            rs = UserQuery.selectMailList(stmt,address);
            while(rs.next()) {
                list.add(rs.getString(USER_EMAIL));
            }
        }finally{
            disconnect();
        }
        return list;
    }

    public void updateInfo(UserBean bean) throws SQLException {
        try{
            connect();
            String sql = "UPDATE users SET user_name=?,user_email=?,user_pwd=? WHERE user_id='"+bean.getUsrID()+"'";
            preset = prepConnect(sql);
            preset.setString(1,bean.getUsrName());
            preset.setString(2,bean.getUsrEmail());
            preset.setString(3,bean.getUsrPwd());
            preset.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void removeUsr(String usrId) throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM users WHERE user_id='"+usrId+"'";
            stmt.executeUpdate(sql);
        }  finally {
            disconnect();
        }
    }
}
