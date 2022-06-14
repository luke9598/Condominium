package logic.controller.guicontroller.first.general.registration;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.engineeringclasses.bean.UserBean;
import logic.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class RegisterGUI implements Initializable{
	 

	private final ViewController view = new ViewController();
	private final AlertGUI alert = new AlertGUI();
	private final RegisterController ctrl = new RegisterController();

	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private TextField email;
	@FXML private PasswordField password;
	@FXML private PasswordField okPassword;
	@FXML private ComboBox<String> roleBox;
    @FXML private ComboBox<String> addressBox;

	@FXML private void onSignClick() {
		Pane pane = view.getPage("Login",1);
		firstBorder.setCenter(pane);
    }

    @FXML
    void onSignupClick() throws IOException, SQLException {
    	UserBean bean = getUsrBean(name.getText(), surname.getText(), email.getText(), password.getText(), okPassword.getText(),roleBox.getValue(),addressBox.getValue());
		if(ctrl.registration(bean)){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/logic/view/first/SelectApartmentDialog.fxml"));
			DialogPane pane = loader.load();
			SelectApartmentDialogGUI apt = loader.getController();
			ObservableList<String> list = ctrl.loadAddress(bean);
			apt.setUp(list,bean);
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setDialogPane(pane);
			Optional<ButtonType> btn = dialog.showAndWait();
			if(btn.isPresent() && btn.get() == ButtonType.OK && !apt.getApt().isEmpty()) {
				for(String aptName : apt.getApt()){
					String fullName = bean.getUsrName() +" "+ bean.getUsrSurname();
					User user = new User(null,fullName,bean.getUsrEmail(),bean.getUsrPwd(),bean.getUsrAddr());
					ctrl.addRegistrationUser(user,bean.getUsrRole().toUpperCase(),aptName);
				}
				alert.alertInfo("Condominium/Register/Info","Successful Registration" ,
						"Your request has successfully sent to the administrator of the condominium");
				clearState();
			}
		}else{
			alert.alertError("Condominium/Register/Error","Incorrect Data","ERROR TYPE:\nDATABASE NOT CONNECTED\nEmpty Fields\nPASSWORD:least one letter and one number maximum 15 charters\nPassword mismatch");
		}
    }

    public UserBean getUsrBean(String name, String surname, String email, String password, String okPassword, String role, String condominiumCode){
		UserBean user = new UserBean();
		user.setUsrName(name);
		user.setUsrSurname(surname);
		user.setUsrEmail(email);
		user.setUsrPwd(password);
		user.setUsrRole(role);
		user.setOkPassword(okPassword);
		user.setUsrAddr(condominiumCode);
		return user;
	}

    private void clearState() {
    	name.setText("");
    	surname.setText("");
    	email.setText("");
    	password.setText("");
    	okPassword.setText("");
    	roleBox.setValue(null);
    	addressBox.setValue(null);
    }

    private void setUp() {
    	roleBox.getItems().addAll("Resident","Owner");
    }

    @Override
   	public void initialize(URL location, ResourceBundle resources){
       	setUp();
       	try {
			 addressBox.setItems(ctrl.loadAddresses());
		 }catch(Exception e) {
			 alert.alertError("DATA BASE ERROR","DATA BASE not connected ","Please Restart the Application");
			 Platform.exit();
		 }
    }
}


    

	
    
