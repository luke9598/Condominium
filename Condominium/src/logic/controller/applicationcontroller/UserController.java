package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.bean.UserBean;
import logic.engineeringclasses.dao.UserDAO;
import logic.model.User;
import java.sql.SQLException;

public class UserController {

    private final UserDAO dao = new UserDAO();
    private final FeeController feeCtrl = new FeeController();
    private final ApartmentController aptCtrl = new ApartmentController();

    public ObservableList<User> loadUserList(String address) throws SQLException {
        return dao.loadUserList(address);
    }

    public ObservableList<String> loadMailList(String addr) throws SQLException {
        return dao.loadMailList(addr);
    }

    public void updateInfo(UserBean bean) {
        try {
            dao.updateInfo(bean);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeResident(String usrId)  {
        try{
            String aptId = aptCtrl.loadAptId(usrId);
            if(feeCtrl.checkPastId(aptId)){
                feeCtrl.removeFee(aptId,"pastfee");
            }
            feeCtrl.removeFee(aptId,"fee");
            aptCtrl.removeAptRes(aptId);
            removeUsr(usrId);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUsr(String usrId){
        try{
            dao.removeUsr(usrId);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
