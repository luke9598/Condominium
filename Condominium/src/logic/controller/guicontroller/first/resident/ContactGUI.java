package logic.controller.guicontroller.first.resident;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.EmailController;
import logic.controller.applicationcontroller.PatternController;
import logic.controller.guicontroller.AlertGUI;
import logic.model.UserSingleton;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactGUI implements Initializable{

	UserSingleton sg = UserSingleton.getInstance();
	private final ApartmentController aptCtrl = new ApartmentController();
	private final PatternController pattern = new PatternController();
	private final AlertGUI alert = new AlertGUI();


	@FXML private TextArea reasonText;
	@FXML private Button btnSend;


	@Override
	public void initialize(URL location, ResourceBundle resources){
		btnSend.setDisable(true);
		reasonText.setPromptText("What do you want to communicate to your apartment Owner?");
	}


	@FXML void clearReason() {
		reasonText.setText("");
		btnSend.setDisable(true);
	}

	@FXML void sendMail()  throws SQLException {
		String mail;
		mail = aptCtrl.checkMailById(aptCtrl.checkUserAptFromNumber(sg.getAddress(),aptCtrl.checkApartments(sg.getUserID(),sg.getAddress(),"apt_res").getNumber(),"apt_own"));
		String subject = "Message Request from "+sg.getResident();
		String body = reasonText.getText();
		if(pattern.isText(body)) {
			alert.alertError("Error","Incorrect Text!","You cannot insert more that one consecutive space, please control text before send");
		}
		else{
			String[] recipients = new String[]{mail};

			if (alert.alertConfirm("Confirmation","Confirm to send email?","Are you sure to send mail to your Owner with text '" + reasonText.getText() + "'?")) {
				new EmailController().send(recipients, recipients, subject, body);
				clearReason();
			}
		}
	}

	public void disableSend() {
		btnSend.setDisable(reasonText.getText().matches("( *)"));
	}

}