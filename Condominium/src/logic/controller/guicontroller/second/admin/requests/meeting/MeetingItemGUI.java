package logic.controller.guicontroller.second.admin.requests.meeting;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import logic.controller.applicationcontroller.EmailController;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.first.admin.requests.meeting.OrganizeMeetGUI;
import logic.controller.guicontroller.second.admin.requests.RequestBottomNavMenuGUI;
import logic.engineeringclasses.bean.MeetRequestBean;
import java.io.IOException;
import java.sql.SQLException;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class MeetingItemGUI {

    protected final ViewController view = new ViewController();
    protected final MeetController controller = new MeetController();
    private final EmailController ctrlEmail = new EmailController();

    @FXML private Label lbID;
    @FXML private Label lbName;
    @FXML private Label lbAddr;
    @FXML private Label lbObject;
    @FXML private TextArea txtArea;

    public void onAcceptPress() throws IOException {
        Button btnSend = new Button("Send");
        btnSend.getStyleClass().add("btnGeneral");
        FXMLLoader meet = view.loader("OrganizeMeet",1);
        Pane orgMeet = meet.load();
        OrganizeMeetGUI ctrlMeet = meet.getController();
        ctrlMeet.setUp(lbObject.getText());
        btnSend.setOnAction(e->{
            try {
                ObservableList<String> list = controller.loadMailList(lbAddr.getText());
                MeetRequestBean bean = ctrlMeet.getMeetBean();
                ctrlEmail.meetEmail(list,bean);
                remove();
            }catch (SQLException|IOException w){
                w.printStackTrace();
            }
            secondBorder.setRight(null);
        });
        view.secondRight(orgMeet,btnSend);
    }

    public void onDeletePress() throws IOException {
        remove();
    }

    private void remove() throws IOException {
        controller.removeMeetRequest(Integer.parseInt(lbID.getText()));
        reloadPage();
    }

    private void reloadPage() throws IOException {
        FXMLLoader loader = view.loader("RequestBottomNavMenu",2);
        Pane root = loader.load();
        RequestBottomNavMenuGUI bottomMenu = loader.getController();
        bottomMenu.setAddress(lbAddr.getText());
        bottomMenu.btnMeetClick();
        root.setMaxSize(Double.MAX_VALUE, Region.USE_COMPUTED_SIZE);
        secondBorder.setBottom(root);
    }

    public void setUp(MeetRequestBean bean) {
        lbID.setText(bean.getId());
        lbName.setText(bean.getName());
        lbAddr.setText(bean.getAddress());
        lbObject.setText(bean.getObject());
        txtArea.setText(bean.getTextArea());
    }
}
