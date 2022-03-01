package logic.controller.guicontroller.second.owner;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Arrays;
import java.util.List;

public class RequestMeetingDialogGUI {

    @FXML private TextArea mailText;
    @FXML private TextArea mailObj;

    public List<String> getMessage(){
        return Arrays.asList(mailObj.getText(),mailText.getText());
    }
}
