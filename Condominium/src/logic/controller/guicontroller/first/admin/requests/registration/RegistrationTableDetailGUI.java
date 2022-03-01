package logic.controller.guicontroller.first.admin.requests.registration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.first.admin.requests.TabOrganizeGUI;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.FeeInfoGUI;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.model.Role;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class RegistrationTableDetailGUI  {

    protected final RegisterController controller = new RegisterController();
    protected final ViewController view = new ViewController();
    private final AlertGUI alert = new AlertGUI();

    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPwd;
    @FXML private TextField tfRole;
    @FXML private TextField tfAddr;
    @FXML private TextField tfApartment;

    @FXML private void btnX() {
        firstBorder.setRight(null);
    }

    @FXML private void btnAddClick() throws IOException, SQLException {
        switch(Role.valueOf(tfRole.getText())){
            case RESIDENT:
                residentAdd();
                break;
            case OWNER:
                ownerAdd();
                break;
            default:
                break;
        }
    }

    @FXML private void btnDeleteClick() throws IOException {
        if(alert.alertConfirm("Title","Are you sure?","")){
            remove();
        }
    }

    private void residentAdd() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/first/Dialog.fxml"));
        DialogPane pane = loader.load();
        FXMLLoader fee = view.loader("FeeInfo",1);
        Parent feeInfo = fee.load();
        FeeInfoGUI ctrlFee = fee.getController();
        ctrlFee.setUp(tfAddr.getText());
        pane.setContent(feeInfo);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK && ctrlFee.check()){
            controller.addRegistered(getRegistered(), ctrlFee.getFees());
            alert.alertInfo("Registration/Info","User Successfully Registered",null);
            remove();
        } else{
            alert.alertError("Registration/Error","Incorrect/Empty Credential","\n-Empty Field\n-More than 4 digits fee\n-Negative Value");
        }
    }

    private void ownerAdd() throws IOException {
        if(alert.alertConfirm("Registration/Confirm","Are you sure to add "+tfName.getText() +" to apartment "+tfApartment.getText()+"?","WARNING:\nAll other requests to apartment "+tfApartment.getText()+" will be deleted")){
            controller.addRegistered(getRegistered(), null);
            alert.alertInfo("Registration/Info","User Successfully Registered","Removed all requests to apartment "+tfApartment.getText());
            removeAll();
        }
    }

    private RegistrationBean getRegistered(){
        return regBean(tfName.getText(),tfEmail.getText(),tfPwd.getText(),tfRole.getText(),tfAddr.getText(),tfApartment.getText());
    }

    public RegistrationBean regBean(String name, String email, String pwd, String role, String addr, String apt){
        RegistrationBean bean = new RegistrationBean();
        bean.setName(name);
        bean.setEmail(email);
        bean.setPassword(pwd);
        bean.setRole(role);
        bean.setAddress(addr);
        bean.setApartment(apt);
        return bean;
    }

    private void removeAll() throws IOException {
        controller.removeAllRegistered(tfApartment.getText());
        btnX();
        reloadPage();
    }

    private void remove() throws IOException {
        controller.removeRegistered(Integer.parseInt(tfID.getText()));
        btnX();
        reloadPage();
    }

    private void reloadPage() throws IOException {
        FXMLLoader loader = view.loader("TabOrganize",1);
        Parent root = loader.load();
        firstBorder.setCenter(root);
        TabOrganizeGUI tab = loader.getController();
        tab.selectTab(0);
    }

    protected void setUp(RegistrationBean bean){
        tfID.setText(bean.getID());
        tfName.setText(bean.getName());
        tfEmail.setText(bean.getEmail());
        tfPwd.setText(bean.getPassword());
        tfRole.setText(bean.getRole());
        tfAddr.setText(bean.getAddress());
        tfApartment.setText(bean.getApartment());
    }
}
