package logic.controller.guicontroller.first.admin.requests.meeting;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.applicationcontroller.EmailController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.first.admin.requests.TabOrganizeGUI;
import logic.controller.guicontroller.AlertGUI;
import logic.engineeringclasses.bean.MeetRequestBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class OrganizeMeetDetailGUI {

    private final AlertGUI alert = new AlertGUI();
    protected final MeetController controller = new MeetController();
    protected final ViewController view = new ViewController();
    private final EmailController ctrlEmail = new EmailController();

    @FXML private TextField tfID;
    @FXML private TextField tfAddr;
    @FXML private TextField tfName;
    @FXML private TextField tfObject;
    @FXML private TextArea txtArea;

    public void btnX() {
        firstBorder.setRight(null);
    }

    public void btnOrganizeClick() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/first/Dialog.fxml"));
        DialogPane pane = loader.load();
        FXMLLoader meet = view.loader("OrganizeMeet",1);
        Parent orgMeet = meet.load();
        OrganizeMeetGUI ctrlMeet = meet.getController();
        ctrlMeet.setUp(tfObject.getText());
        pane.setContent(orgMeet);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK){
            ObservableList<String> list = controller.loadMailList(tfAddr.getText());
            MeetRequestBean bean = ctrlMeet.getMeetBean();
            ctrlEmail.meetEmail(list,bean);
            alert.alertConfirm("MeetRequest","Mail successfully sent to all OWNERS in your condominium",null);
            remove();
        }
    }

    public void btnDeleteClick() throws IOException {
        if(alert.alertConfirm("Title","Are you sure?","")){
            remove();
        }
    }

    private void remove() throws IOException {
        controller.removeMeetRequest(Integer.parseInt(tfID.getText()));
        btnX();
        reloadPage();
    }

    private void reloadPage() throws IOException {
        FXMLLoader loader = view.loader("TabOrganize",1);
        Parent root = loader.load();
        firstBorder.setCenter(root);
        TabOrganizeGUI tab = loader.getController();
        tab.selectTab(1);
    }

    public void setUp(MeetRequestBean bean){
        tfID.setText(bean.getId());
        tfAddr.setText(bean.getAddress());
        tfName.setText(bean.getName());
        tfObject.setText(bean.getObject());
        txtArea.setText(bean.getTextArea());
    }
}
