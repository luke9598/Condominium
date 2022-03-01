package logic.controller.guicontroller.first.admin.requests.meeting;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.applicationcontroller.ViewController;
import logic.engineeringclasses.bean.MeetRequestBean;
import logic.model.MeetRequest;
import java.io.IOException;
import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class MeetingTableGUI  {

    protected final MeetController controller = new MeetController();
    protected final ViewController view = new ViewController();

    @FXML private TableView<MeetRequest> tableMeeting;
    @FXML private TableColumn<MeetRequest,String> tcId;
    @FXML private TableColumn<MeetRequest,String> tcName;
    @FXML private TableColumn<MeetRequest,String> tcAddr;
    @FXML private TableColumn<MeetRequest,String> tcObj;
    @FXML private TableColumn<MeetRequest,String> tcTxt;

    @FXML private void getSelected() throws IOException {
        int index;
        index = tableMeeting.getSelectionModel().getSelectedIndex();
        if(index<=-1)return;
        MeetRequestBean bean = meetRequestBean(tcId.getCellData(index),tcName.getCellData(index),tcAddr.getCellData(index),tcObj.getCellData(index),tcTxt.getCellData(index));
        FXMLLoader loader = view.loader("OrganizeMeetDetail",1);
        Parent root = loader.load();
        OrganizeMeetDetailGUI detail = loader.getController();
        detail.setUp(bean);
        firstBorder.setRight(root);
    }

    private MeetRequestBean meetRequestBean(String id, String name, String address, String object, String text) {
        MeetRequestBean meet = new MeetRequestBean();
        meet.setId(id);
        meet.setName(name);
        meet.setAddress(address);
        meet.setObject(object);
        meet.setTextArea(text);
        return meet;
    }

    public void setUpMeeting(String address) {
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcObj.setCellValueFactory(new PropertyValueFactory<>("object"));
        tcTxt.setCellValueFactory(new PropertyValueFactory<>("text"));
        try {
            ObservableList<MeetRequest> list = controller.loadMeetRequest(address);
            tableMeeting.setItems(list);
        }catch(Exception e) {
           e.printStackTrace();
        }
    }
}
