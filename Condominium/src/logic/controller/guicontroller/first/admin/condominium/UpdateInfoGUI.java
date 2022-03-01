package logic.controller.guicontroller.first.admin.condominium;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.controller.applicationcontroller.PatternController;
import logic.engineeringclasses.bean.UserBean;

public class UpdateInfoGUI {

    private final PatternController pattern = new PatternController();

    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPwd;

    public void setUp(UserBean bean) {
        tfID.setText(bean.getUsrID());
        tfName.setText(bean.getUsrName());
        tfEmail.setText(bean.getUsrEmail());
        tfPwd.setText(bean.getUsrPwd());
    }

    public boolean checkInfo(){
        return !pattern.isName(tfName.getText()) && pattern.isEmail(tfEmail.getText()) && pattern.isPassword(tfPwd.getText());
    }

    public UserBean getBean(){
        return setBean(tfID.getText(),tfName.getText(),tfEmail.getText(),tfPwd.getText());
    }

    private UserBean setBean(String id,String name, String email, String pwd) {
        UserBean bean = new UserBean();
        bean.setUsrID(id);
        bean.setUsrName(name);
        bean.setUsrEmail(email);
        bean.setUsrPwd(pwd);
        return bean;
    }
}
