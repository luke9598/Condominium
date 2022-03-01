package logic.controller.guicontroller.first.admin.requests.meeting;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.engineeringclasses.bean.MeetRequestBean;

public class OrganizeMeetGUI {

    @FXML private Text txt;
    @FXML private TextField txtObj;
    @FXML private TextArea txtArea;

    public void setUp(String object){
        txt.setText("You are currently organize meeting\nRemember the mail will sent to all OWNERS in your condominium");
        txtObj.setText(object);
    }

    public MeetRequestBean getMeetBean(){
        return getBean(txtObj.getText(),txtArea.getText());
    }

    private MeetRequestBean getBean(String obj,String txtArea){
        MeetRequestBean bean = new MeetRequestBean();
        bean.setObject(obj);
        bean.setTextArea(txtArea);
        return bean;
    }
}
