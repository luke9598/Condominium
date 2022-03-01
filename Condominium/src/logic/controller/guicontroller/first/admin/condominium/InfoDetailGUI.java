package logic.controller.guicontroller.first.admin.condominium;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import logic.controller.applicationcontroller.FeeController;
import logic.controller.applicationcontroller.UserController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.FeeInfoGUI;
import logic.controller.guicontroller.second.admin.condominium.InfoItemGUI;
import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Role;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class InfoDetailGUI {

    private final UserController usrController = new UserController();
    private final FeeController feeController = new FeeController();
    private final ViewController view = new ViewController();
    private final AlertGUI alert = new AlertGUI();
    static final String TITLE = "Condominium/Info";

    @FXML private Button btnRemove;
    @FXML private Button btnRole;
    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPwd;
    @FXML private TextField tfRole;
    @FXML private TextField tfAddr;

    @FXML private void btnX() {
        firstBorder.setRight(null);
    }

    protected void setUp(UserBean bean){
        tfID.setText(bean.getUsrID());
        tfName.setText(bean.getUsrName());
        tfEmail.setText(bean.getUsrEmail());
        tfPwd.setText(bean.getUsrPwd());
        tfRole.setText(bean.getUsrRole());
        tfAddr.setText(bean.getUsrAddr());
        switch (Role.valueOf(bean.getUsrRole())){
            case RESIDENT:
                btnRole.setText("Update Fee");
                break;
            case OWNER:
                btnRole.setText("Update Rating");
                btnRemove.setVisible(false);
                break;
            default:
                break;
        }
    }

    public void btnUpdateClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/first/Dialog.fxml"));
        DialogPane pane = loader.load();
        FXMLLoader loadInfo = view.loader("UpdateInfo",1);
        Parent info = loadInfo.load();
        UpdateInfoGUI ctrlInfo = loadInfo.getController();
        ctrlInfo.setUp(getUserBean());
        pane.setContent(info);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK && ctrlInfo.checkInfo()){
            usrController.updateInfo(ctrlInfo.getBean());
            alert.alertInfo(TITLE,"User Successfully Updated",null);
            btnX();
            reloadPage();
        } else {
            alert.alertError("Condominium/Error","Incorrect Data",null);
        }
    }

    public void btnRemoveClick() throws IOException {
        if(alert.alertConfirm(TITLE,"Are you sure you want to permanently delete user "+tfName.getText()+"?",null)){
            if (Role.valueOf(tfRole.getText()) == Role.RESIDENT) {
                usrController.removeResident(tfID.getText());
            }
            btnX();
            reloadPage();
        }
    }

    public void btnRoleClick() throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/first/Dialog.fxml"));
        DialogPane pane = loader.load();
        switch (Role.valueOf(tfRole.getText())){
            case RESIDENT:
                updateFee(pane);
                break;
            case OWNER:
                removeRating(pane);
                break;
            default:
                break;
        }
        btnX();
    }


    private void removeRating(DialogPane pane) {
        InfoItemGUI info = new InfoItemGUI();
        pane.setContent(info.setUpTableReview(1,tfName.getText(),tfID.getText(),"first"));
        pane.getStylesheets().clear();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        dialog.showAndWait();
    }

    private void updateFee(DialogPane pane) throws SQLException, IOException {
        FXMLLoader feeInfo = view.loader("FeeInfo",1);
        Parent feeParent = feeInfo.load();
        FeeInfoGUI ctrlFee = feeInfo.getController();
        ctrlFee.setUp(tfAddr.getText());
        ctrlFee.loadFeeInfo(tfID.getText());
        FeeBean past = ctrlFee.getFees();
        pane.setContent(feeParent);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK && ctrlFee.check()){
            feeController.updateFees(past,ctrlFee.getFees());
            alert.alertInfo(TITLE,"Fee Successfully Updated",null);
        } else{
            alert.alertError("Condominium/Error","Incorrect/Empty Credential","\n-Empty Field\n-More than 4 digits fee\n-Negative Value");
        }
    }

    private UserBean getUserBean(){
        return setUserBean(tfID.getText(),tfName.getText(),tfEmail.getText(),tfPwd.getText());
    }

    public UserBean setUserBean(String id,String name, String email, String pwd) {
        UserBean bean = new UserBean();
        bean.setUsrID(id);
        bean.setUsrName(name);
        bean.setUsrEmail(email);
        bean.setUsrPwd(pwd);
        return bean;
    }

    private void reloadPage() throws IOException {
        FXMLLoader loader = view.loader("Info",1);
        Parent root = loader.load();
        InfoGUI infoCtrl = loader.getController();
        infoCtrl.setUp(tfAddr.getText());
        firstBorder.setCenter(root);
    }
}
