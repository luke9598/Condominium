package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.RateQuery;
import logic.model.Rate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateDAO extends SqlDAO{

    private final UserDAO userDao = new UserDAO();

    public void rateResident(String resId, String ownId, int rate, String comment) throws SQLException {
        try {
            String sql = "INSERT INTO rating (rate_res,rate_own,rate_val,rate_txt) values (?,?,?,?)";
            preset = prepConnect(sql);
            preset.setString(1, resId);
            preset.setString(2, ownId);
            preset.setInt(3, rate);
            preset.setString(4, comment);
            preset.execute();
        } finally {
            disconnect();
        }
    }

    public ObservableList<Rate> getRatesRes(String userId) throws SQLException {
        ObservableList<Rate> rates = FXCollections.observableArrayList();
        try {
            connect();
            ResultSet rs = RateQuery.getRates(stmt,userId);
            while(rs.next()) {
                String rateId = rs.getString("rate_id");
                String rateRes = rs.getString("rate_res");
                String rateOwn = rs.getString("rate_own");
                String rateVal = rs.getString("rate_val");
                String rateTxt = rs.getString("rate_txt");
                Rate rate = new Rate(rateId,rateRes,userDao.checkNameByID(rateOwn),rateVal,rateTxt);
                rates.add(rate);
            }
        }finally {
            disconnect();
        }
        return rates;
    }


    public ObservableList<Rate> getRatesOwner(String userId) throws SQLException {
        ObservableList<Rate> rates = FXCollections.observableArrayList();
        try {
            connect();
            ResultSet rs = RateQuery.getRatesOwner(stmt,userId);
            while(rs.next()) {
                String rateId = rs.getString("rate_id");
                String rateRes = rs.getString("rate_res");
                String rateVal = rs.getString("rate_val");
                String rateTxt = rs.getString("rate_txt");
                Rate rate = new Rate(rateId,userDao.checkNameByID(rateRes),rateVal,rateTxt);
                rates.add(rate);
            }
        }finally {
            disconnect();
        }
        return rates;
    }

    public void deleteRate(String rateId) throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM rating WHERE rate_id='"+rateId+"'";
            stmt.executeUpdate(sql);
        } finally {
            disconnect();
        }
    }
}
