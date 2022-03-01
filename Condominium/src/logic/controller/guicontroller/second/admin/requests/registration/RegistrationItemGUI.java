package logic.controller.guicontroller.second.admin.requests.registration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.admin.requests.registration.RegistrationTableDetailGUI;
import logic.controller.guicontroller.first.general.FeeInfoGUI;
import logic.controller.guicontroller.second.admin.requests.RequestBottomNavMenuGUI;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.model.Role;

import java.io.IOException;
import java.sql.SQLException;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class RegistrationItemGUI {

    protected final RegisterController controller = new RegisterController();
    protected final ViewController view = new ViewController();
    private final AlertGUI alert = new AlertGUI();

    @FXML private Label lbID;
    @FXML private Label lbName;
    @FXML private Label lbEmail;
    @FXML private Label lbPwd;
    @FXML private Label lbAddr;
    @FXML private Label lbRole;
    @FXML private Label lblApt;

    @FXML private void onAcceptPress() throws IOException {
        switch(Role.valueOf(lbRole.getText())){
            case OWNER:
                addOwner();
                break;
            case RESIDENT:
                addResident();
                break;
            default:
                break;
        }
    }

    @FXML private void onDeletePress() throws IOException {
        remove();
    }

    private void addOwner() throws IOException {
        if(alert.alertConfirm("Registration/Confirm","Are you sure to add "+lbName.getText() +" to apartment "+lblApt.getText()+"?","WARNING:\nAll other requests to apartment "+lblApt.getText()+" will be deleted")){
            controller.addRegistered(getRegistered(), null);
            alert.alertInfo("Registration/Info","User Successfully Registered","Removed all requests to apartment "+lblApt.getText());
            removeAll();
        }
    }

    private void addResident() {
        try {
            Button btnOk = new Button("OK");
            FXMLLoader fee = view.loader("FeeInfo", 1);
            Parent feeInfo = fee.load();
            FeeInfoGUI ctrlFee = fee.getController();
            ctrlFee.setUp(lbAddr.getText());
            btnOk.setOnAction(e->{
                if(ctrlFee.check()){
                    controller.addRegistered(getRegistered(), ctrlFee.getFees());
                    alert.alertInfo("Registration/Info","User Successfully Registered",null);
                    try {
                        remove();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }else{
                    alert.alertError("Registration/Error","Incorrect/Empty Credential","\n-Empty Field\n-More than 4 digits fee\n-Negative Value");
                }
                secondBorder.setRight(null);
            });
            view.secondRight(feeInfo,btnOk);
        }catch (IOException| SQLException e){
            e.printStackTrace();
        }
    }

    private RegistrationBean getRegistered(){
        RegistrationTableDetailGUI reg = new RegistrationTableDetailGUI();
        return reg.regBean(lbName.getText(),lbEmail.getText(),lbPwd.getText(),lbRole.getText(),lbAddr.getText(),lblApt.getText());
    }

    private void removeAll() throws IOException {
        controller.removeAllRegistered(lblApt.getText());
        reloadPage();
    }

    private void remove() throws IOException {
        controller.removeRegistered(Integer.parseInt(lbID.getText()));
        reloadPage();
    }

    private void reloadPage() throws IOException {
        FXMLLoader loader = view.loader("RequestBottomNavMenu",2);
        Pane root = loader.load();
        RequestBottomNavMenuGUI bottomMenu = loader.getController();
        bottomMenu.setAddress(lbAddr.getText());
        bottomMenu.btnRegClick();
        root.setMaxSize(Double.MAX_VALUE, Region.USE_COMPUTED_SIZE);
        secondBorder.setBottom(root);
    }

    public void setUp(RegistrationBean bean){
        lbID.setText(bean.getID());
        lbName.setText(bean.getName());
        lbEmail.setText(bean.getEmail());
        lbPwd.setText(bean.getPassword());
        lbAddr.setText(bean.getAddress());
        lbRole.setText(bean.getRole());
        lblApt.setText(bean.getApartment());
    }
}
