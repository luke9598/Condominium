package logic.controller.guicontroller.second.admin.requests;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.second.admin.requests.meeting.MeetingItemGUI;
import logic.controller.guicontroller.second.admin.requests.meeting.MeetingTableGUI;
import logic.controller.guicontroller.second.admin.requests.registration.RegistrationTableGUI;
import logic.controller.guicontroller.second.admin.requests.registration.RegistrationItemGUI;
import logic.engineeringclasses.bean.MeetRequestBean;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.model.MeetRequest;
import logic.model.Registration;
import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class RequestBottomNavMenuGUI {

    private final ViewController view = new ViewController();
    protected final MeetController meetCtrl = new MeetController();
    protected final RegisterController regCtrl = new RegisterController();
    private String address;

    private RegistrationBean setUpRegistration(Registration registration){
        RegistrationBean bean = new RegistrationBean();
        bean.setID(registration.getRegID());
        bean.setName(registration.getRegName());
        bean.setEmail(registration.getRegEmail());
        bean.setPassword(registration.getRegPwd());
        bean.setRole(registration.getRegRole());
        bean.setAddress(registration.getRegAddr());
        bean.setApartment(registration.getRegApt());
        return bean;
    }

    private MeetRequestBean setUpMeeting(MeetRequest meet){
        MeetRequestBean bean = new MeetRequestBean();
        bean.setId(meet.getId());
        bean.setName(meet.getName());
        bean.setAddress(meet.getAddress());
        bean.setObject(meet.getObject());
        bean.setTextArea(meet.getText());
        return bean;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void btnRegClick() {
        secondBorder.setCenter(null);
        try {
            FXMLLoader loader = view.loader("RegistrationTable", 2);
            Pane table = loader.load();
            RegistrationTableGUI tableCtrl = loader.getController();
            tableCtrl.clearChildren();
            ObservableList<Registration> list = regCtrl.loadRegistration(this.address);
            for(int i = 0; i<=list.size()-1; i++){
                RegistrationBean bean = setUpRegistration(list.get(i));
                FXMLLoader item = view.loader("ItemRegistration",2);
                Pane root = item.load();
                RegistrationItemGUI itemCtrl = item.getController();
                itemCtrl.setUp(bean);
                tableCtrl.addChildren(root);
            }
            table.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            secondBorder.setCenter(table);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void btnMeetClick() {
        secondBorder.setCenter(null);
        try {
            FXMLLoader loader = view.loader("MeetingTable", 2);
            Pane table = loader.load();
            MeetingTableGUI tableCtrl = loader.getController();
            tableCtrl.clearChildren();
            ObservableList<MeetRequest> list = meetCtrl.loadMeetRequest(this.address);
             for (int i = list.size() - 1; i >= 0; i--) {
                 MeetRequestBean bean = setUpMeeting(list.get(i));
                 FXMLLoader item = view.loader("ItemMeeting",2);
                 Pane root = item.load();
                 MeetingItemGUI itemCtrl = item.getController();
                 itemCtrl.setUp(bean);
                 tableCtrl.addChildren(root);
            }
            table.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            secondBorder.setCenter(table);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
