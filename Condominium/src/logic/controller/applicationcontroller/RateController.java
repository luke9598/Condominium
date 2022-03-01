package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.dao.RateDAO;
import logic.model.Rate;

import java.sql.SQLException;

public class RateController {

    private final RateDAO rateDAO = new RateDAO();

    public void rateUser(String resId,String ownId,int rate, String comment) throws SQLException {
        rateDAO.rateResident(resId,ownId,rate,comment);
    }

    public ObservableList<Rate> getRatesRes(String userId) throws SQLException {
        return rateDAO.getRatesRes(userId);
    }

    public ObservableList<Rate> getRatesOwn(String ownId) throws SQLException {
        return rateDAO.getRatesOwner(ownId);
    }

    public void deleteRate(String rateId) throws SQLException {
        rateDAO.deleteRate(rateId);
    }
}
