package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.dao.ApartmentDAO;
import logic.model.Apartment;

import java.sql.SQLException;

public class ApartmentController {

    private final ApartmentDAO dao = new ApartmentDAO();

    public ObservableList<String> loadApartmentResident(String address) throws SQLException {
        return dao.loadApartmentResident(address);
    }

    public ObservableList<String> loadApartmentOwner(String address) throws SQLException {
        return dao.loadApartmentOwner(address);
    }

    public String loadApartmentId(String apartment, String address) throws SQLException{
        return dao.loadApartmentId(apartment,address);
    }

    public void addResident(String apartment,String address) {
        try{
            dao.addResident(apartment,address);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addOwner(String apartment,String address){
        try{
            dao.addOwner(apartment,address);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Apartment checkApartments(String userID, String address, String aptRes) throws SQLException {
        return dao.checkApartments(userID, address, aptRes);
    }

    public String checkMailById(String userId) throws SQLException{
        return dao.checkMailById(userId);
    }

    public String checkUserAptFromNumber(String aptNumber,String condAddr, String userRequired) throws SQLException{
        return dao.checkUserAptFromNumber(aptNumber,condAddr,userRequired);
    }

    public ObservableList<Apartment> checkApartmentsList(String userID, String address, String aptRes) throws SQLException {
        return dao.checkApartmentsList(userID, address, aptRes);
    }

    public String loadAptId(String resID) throws SQLException {
        return dao.loadAptIdRes(resID);
    }

    public String getAptName(String aptId) throws SQLException {
        return dao.loadApartmentName(aptId);
    }

    public void removeAptRes(String aptId) throws SQLException{
        dao.removeAptRes(aptId);
    }
}
