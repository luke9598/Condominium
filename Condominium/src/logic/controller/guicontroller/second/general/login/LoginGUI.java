package logic.controller.guicontroller.second.general.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.LoginController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.second.general.AddressesDialogGUI;
import logic.controller.guicontroller.second.general.Main2GUI;
import logic.engineeringclasses.bean.UserBean;
import java.net.URL;
import java.util.ResourceBundle;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class LoginGUI implements Initializable {

    private final LoginController controller = new LoginController();
    private final ViewController view = new ViewController();
    private final AlertGUI alert = new AlertGUI();
    private final Main2GUI main = new Main2GUI();

    @FXML private Text txtAddress;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPwd;

    public void onSelectCondominium() {
        AddressesDialogGUI address = new AddressesDialogGUI();
        txtAddress.setText(address.loadAddresses());
    }

    public void onSignClick() {
        UserBean bean = loginBean(tfEmail.getText(), tfPwd.getText(), txtAddress.getText());
        if (controller.login(bean)) {
            Pane menu = view.getPage("Menu2",2);
            secondBorder.setTop(menu);
            main.fullScreen(true);
        } else {
            alert.alertError("Login/Error", "Incorrect Email or Password or Address", "Please Retry");
            error();
        }
    }

    public void onSignupClick() {
        Pane pane = view.getPage("Register",2);
        secondBorder.setCenter(pane);
    }

    private void setUp() {
        tfEmail.setText("admin");
        tfPwd.setText("admin");
    }

    private void error() {
        tfEmail.setText("");
        tfPwd.setText("");
    }

    public UserBean loginBean(String email, String password, String address) {
        UserBean user = new UserBean();
        user.setUsrEmail(email);
        user.setUsrPwd(password);
        user.setUsrAddr(address);
        return user;
    }

    @Override public void initialize(URL location, ResourceBundle resources) {
        setUp();
    }
}
