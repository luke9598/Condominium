package logic.controller.applicationcontroller;

import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.dao.FeeDAO;
import logic.model.Fee;

import java.sql.SQLException;

public class FeeController {

    private final FeeDAO dao = new FeeDAO();

    public Fee setUpFees(String address) throws SQLException {
        return dao.loadAvailableFees(address);
    }

    public void addFees(FeeBean bean,String table) {
        try{
            dao.addFees(bean,table);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Fee loadFees(String loadApartmentId,String typeFee) throws SQLException {
        return dao.loadFees(loadApartmentId,typeFee);
    }

    public boolean checkPastId(String aptId) throws SQLException {
        return dao.checkPastId(aptId);
    }

    public void updateFees(FeeBean pastBean,FeeBean newBean) {
        try {
            if (checkPastId(pastBean.getApt())) {
                dao.updateFee(pastBean, "pastfee");
            } else {
                addFees(pastBean, "pastfee");
            }
            dao.updateFee(newBean, "fee");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeFee(String aptId,String table) {
        try {
            dao.removeFee(aptId, table);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
