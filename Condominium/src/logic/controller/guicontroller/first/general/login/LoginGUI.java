package logic.controller.guicontroller.first.general.login;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import logic.controller.applicationcontroller.LoginController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.Main1GUI;
import logic.engineeringclasses.bean.UserBean;

import java.net.URL;
import java.util.ResourceBundle;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class LoginGUI implements Initializable{

	private final LoginController controller = new LoginController();
	private final ViewController view = new ViewController();
	private final AlertGUI alert = new AlertGUI();
	private final Main1GUI main = new Main1GUI();

	@FXML private TextField tfEmail;
	@FXML private TextField tfPwd;
	@FXML private ComboBox<String> comboBox;

	@FXML private void onSignupClick(){
		Pane pane = view.getPage("Register",1);
		firstBorder.setCenter(pane);
    }

	@FXML private void onSignClick(){
    	UserBean bean = loginBean(tfEmail.getText(), tfPwd.getText(), comboBox.getValue());
		if (controller.login(bean)) {
			Pane menu = view.getPage("Menu",1);
			firstBorder.setLeft(menu);
			main.fullScreen(true);
		} else {
			alert.alertError("Login/Error", "Incorrect Email or Password or Address", "Please Retry");
			error();
		}
	}

    private void error() {
		tfEmail.setText("");
		tfPwd.setText("");
		comboBox.setValue(null);
    }

	public UserBean loginBean(String email, String password, String address) {
		UserBean user = new UserBean();
		user.setUsrEmail(email);
		user.setUsrPwd(password);
		user.setUsrAddr(address);
		return user;
	}

	private void setUp() {
		 try {
			 comboBox.setItems(controller.loadAddresses());
		 }catch(Exception e) {
			 alert.alertError("DATA BASE ERROR","DATA BASE not connected ","Please Restart the Application");
			 Platform.exit();
		 }
	}

	@Override public void initialize(URL location, ResourceBundle resources) {
    	setUp();
    }
}

