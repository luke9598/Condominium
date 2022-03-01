package logic.controller.guicontroller.first.owner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.Main1GUI;
import logic.model.UserSingleton;

import java.sql.SQLException;

public class RequestMeetingGUI extends Main1GUI{


    UserSingleton sg = UserSingleton.getInstance();
    private final MeetController meet = new MeetController();
    private final AlertGUI alertGUI = new AlertGUI();


    @FXML private TextArea reasonText;
    @FXML private TextArea objectText;
    @FXML private Button btnSend;

    public void sendMeeting() {
        String subject = objectText.getText();
        String message = reasonText.getText();
        if (alertGUI.alertConfirm("Confirmation","Confirm to send meeting request?","Are you sure to send a meeting request to Administrator with text '" + message + "'?")) {
            try {
                meet.addMeeting(sg.getUserID(),sg.getAddress(),subject,message);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            clearReason();
        }
    }

    public void disableSend() {
        btnSend.setDisable(reasonText.getText().matches("( *)"));
    }

    @FXML void clearReason() {
        objectText.setText("");
        reasonText.setText("");
        btnSend.setDisable(true);
    }
}