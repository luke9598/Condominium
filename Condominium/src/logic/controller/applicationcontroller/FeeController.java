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
            dao.addUpdateFee(bean,table,"add");
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
            if (checkPastId(pastBean.getFeeApt())) {
                dao.addUpdateFee(pastBean, "pastfee","update");
            } else {
                addFees(pastBean, "pastfee");
            }
            dao.addUpdateFee(newBean, "fee","update");
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
