package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.ApartmentQuery;
import logic.model.Apartment;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentDAO extends SqlDAO{

    private final UserDAO userDao = new UserDAO();
    private static final String APARTMENT = "apt_name";
    private static final String APARTMENT_ADDRESS = "apt_addr";
    private static final String APARTMENT_OWNER = "apt_own";
    private static final String APARTMENT_RESIDENT = "apt_res";

    public String loadApartmentId(String apartment, String address) throws SQLException {
        String id = null;
        ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectApartmentId(stmt,apartment,address);
            if(rs.next()) {
                id = rs.getString("apt_id");
            }
        }finally{
            disconnect();
        }
        return id;
    }

    public ObservableList<String> loadApartmentResident(String address) throws SQLException{
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectApartmentResident(stmt,address);
            while(rs.next()) {
                list.add(rs.getString(APARTMENT));
            }
        }finally{
            disconnect();
        }
        return list;
    }

    public ObservableList<String> loadApartmentOwner(String address) throws SQLException{
        ObservableList<String> list = FXCollections.observableArrayList();
		ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectApartmentOwner(stmt,address);
            while(rs.next()) {
                list.add(rs.getString(APARTMENT));
            }
        }finally{
            disconnect();
        }
        return list;
    }

    public ObservableList<Apartment> loadApartments(String address) throws SQLException {
        ObservableList<Apartment> apartments = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            connect();
            rs = ApartmentQuery.loadApt(stmt,address);
            while(rs.next()) {
                String aptID = rs.getString(APARTMENT);
                String aptAdd = rs.getString(APARTMENT_ADDRESS);
                String aptOwn = rs.getString(APARTMENT_OWNER);
                String aptRes = rs.getString(APARTMENT_RESIDENT);
                Apartment apartment = new Apartment(aptID, aptAdd, userDao.checkNameByID(aptOwn), userDao.checkNameByID(aptRes), "0");
                apartments.add(apartment);
            }
        } finally {
            disconnect();
        }
        return apartments;
    }

    public Apartment checkApartments(String userId,String condAddress, String typeUsr) throws SQLException{
        Apartment apartment = null;
        ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectAptInfo(stmt,userId,condAddress,typeUsr);
            while(rs.next()) {
                String aptID = rs.getString(APARTMENT);
                String aptAdd = rs.getString(APARTMENT_ADDRESS);
                String aptOwn = rs.getString(APARTMENT_OWNER);
                String aptRes = rs.getString(APARTMENT_RESIDENT);
                apartment = new Apartment(aptID, aptAdd, userDao.checkNameByID(aptOwn), userDao.checkNameByID(aptRes),"0");
            }
        }finally{
            disconnect();
        }
        return apartment;
    }

    public ObservableList<Apartment> checkApartmentsList(String userId,String condAddress, String typeUsr) throws SQLException{
        ObservableList<Apartment> list = FXCollections.observableArrayList();
        ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectAptInfo(stmt,userId,condAddress,typeUsr);
            while(rs.next()) {
                String aptID = rs.getString(APARTMENT);
                String aptAdd = rs.getString(APARTMENT_ADDRESS);
                String aptOwn = rs.getString(APARTMENT_OWNER);
                String aptRes = rs.getString(APARTMENT_RESIDENT);
                Apartment apartment = new Apartment(aptID, aptAdd, userDao.checkNameByID(aptOwn), userDao.checkNameByID(aptRes),"0");
                list.add(apartment);
            }
        }finally{
            disconnect();
        }
        return list;
    }

    public void addResident(String apartment,String address) throws SQLException{
        try{
            connect();
            String sql = "UPDATE apartment SET apt_res=? where apt_name=? and apt_addr=?";
            preset = prepConnect(sql);
            preset.setString(1, loadLatestId("users","user_id"));
            preset.setString(2,apartment);
            preset.setString(3,address);
            preset.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public void addOwner(String apartment, String address) throws SQLException{
        try{
            connect();
            String sql = "UPDATE apartment SET apt_own=? where apt_name=? and apt_addr=?";
            preset = prepConnect(sql);
            preset.setString(1, loadLatestId("users","user_id"));
            preset.setString(2,apartment);
            preset.setString(3,address);
            preset.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public String checkUserAptFromNumber(String aptNumber,String condAddr, String userRequired) throws SQLException{
        String usrId = null;
        try {
            connect();
            ResultSet rs = ApartmentQuery.checkApartmentFromNumber(stmt,aptNumber,condAddr,userRequired);
            if(rs.next()) {
                usrId = rs.getString(userRequired);
            }
        } finally {
            disconnect();
        }
        return usrId;
    }

    public String checkMailById(String userId) throws SQLException {
        String userEmail = null;
        try {
            connect();
            ResultSet rs = ApartmentQuery.selectEmail(stmt, userId);
            if(rs.next()) {
                userEmail = rs.getString("user_email");
            }
        } finally {
            disconnect();
        }
        return userEmail;
    }

    public String loadAptIdRes(String resID) throws SQLException {
        String aptId = null;
        try {
            connect();
            ResultSet rs = ApartmentQuery.selectAptIdByRes(stmt, resID);
            if(rs.next()) {
                aptId = rs.getString("apt_id");
            }
        } finally {
            disconnect();
        }
        return aptId;
    }

    public String loadApartmentName(String aptId) throws SQLException {
        try {
            connect();
            ResultSet rs = ApartmentQuery.selectAptName(stmt, aptId);
            if(rs.next()) {
                aptId = rs.getString(APARTMENT);
            }
        } finally {
            disconnect();
        }
        return aptId;
    }

    public void removeAptRes(String aptId) throws SQLException{
        try {
            connect();
            String sql ="UPDATE apartment SET apt_res = NULL WHERE apt_id='"+aptId+"'";
            preset = prepConnect(sql);
            preset.executeUpdate();
        } finally {
            disconnect();
        }
    }
}