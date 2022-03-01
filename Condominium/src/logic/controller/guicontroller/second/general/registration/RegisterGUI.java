package logic.controller.guicontroller.second.general.registration;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.second.general.AddressesDialogGUI;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Role;
import logic.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class RegisterGUI {

    private final RegisterController controller = new RegisterController();
    private final ViewController view = new ViewController();
    private final AlertGUI alert = new AlertGUI();

    @FXML private TextField tfName;
    @FXML private TextField tfSurname;
    @FXML private TextField tfEmail;
    @FXML private PasswordField tfPassword;
    @FXML private PasswordField tfOkPwd;
    @FXML private ToggleGroup radio;
    @FXML private Text txtAddress;


    public void addressBtnClick() {
        AddressesDialogGUI address = new AddressesDialogGUI();
        txtAddress.setText(address.loadAddresses());
    }

    public void onSignClick() {
        Pane pane = view.getPage("Login",2);
        secondBorder.setCenter(pane);
    }

    public void onSignupClick(){
        logic.controller.guicontroller.first.general.registration.RegisterGUI registerGUI = new logic.controller.guicontroller.first.general.registration.RegisterGUI();
        RadioButton selectedRadioButton = (RadioButton) radio.getSelectedToggle();
        String radioTxt = selectedRadioButton.getText();
        UserBean bean = registerGUI.getUsrBean(tfName.getText(),tfSurname.getText(),tfEmail.getText(),tfPassword.getText(),tfOkPwd.getText(),radioTxt,txtAddress.getText());
        if(controller.registration(bean)){
            loadApartments(bean);
        }else{
            alert.alertError("Condominium/Register/Error","Incorrect Data","ERROR TYPE:\nDATABASE NOT CONNECTED\nEmpty Fields\nPASSWORD:least one letter and one number maximum 15 charters\nPassword mismatch");
        }
    }

    private void loadApartments(UserBean bean) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/logic/view/first/SelectApartmentDialog.fxml"));
            DialogPane pane = loader.load();
            ObservableList<String> list = controller.loadAddress(bean);
            ObservableList<String> apartments = FXCollections.observableArrayList();
            switch (Role.valueOf(bean.getUsrRole().toUpperCase())) {
                case RESIDENT:
                    apartments = viewResident(list,pane);
                    break;
                case OWNER:
                    apartments = viewOwner(list,pane);
                    break;
                default:
                    break;
            }
            if(!apartments.isEmpty()){
                for(String aptName : apartments){
                    String name = bean.getUsrName() +" "+ bean.getUsrSurname();
                    User user = new User(null,name,bean.getUsrEmail(),bean.getUsrPwd(),bean.getUsrAddr());
                    controller.addRegistrationUser(user,bean.getUsrRole().toUpperCase(),aptName);
                }
                alert.alertInfo("Condominium/Register/Info","Successful Registration" ,
                        "Your request has successfully sent to the administrator of the condominium");
                clearState();
            }
        }catch(SQLException | NullPointerException |IOException ignored){
            alert.alertError("Condominium/Register/Error","No Apartments Available","ERROR TYPE:\nDATABASE NOT CONNECTED");
        }
    }

    private void clearState() {
        tfName.setText("");
        tfSurname.setText("");
        tfEmail.setText("");
        tfPassword.setText("");
        tfOkPwd.setText("");
        txtAddress.setText("");
    }

    private ObservableList<String> viewResident(ObservableList<String> list, DialogPane pane) {
        final ToggleGroup group = new ToggleGroup();
        ObservableList<String> apartments = FXCollections.observableArrayList();
        ListView<RadioButton> listRadio = new ListView<>();
        for(String apartment : list){
            RadioButton btn = new RadioButton(apartment);
            btn.setToggleGroup(group);
            btn.setUserData(apartment);
            listRadio.getItems().add(btn);
        }
        pane.setContent(listRadio);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK) {
            apartments.add(group.getSelectedToggle().getUserData().toString());
        }
        return apartments;
    }

    private ObservableList<String> viewOwner(ObservableList<String> list,DialogPane pane) {
        ListView<CheckBox> listCheck = new ListView<>();
        ObservableList<String> apartments = FXCollections.observableArrayList();
        for(String apartment : list){
            CheckBox btn = new CheckBox(apartment);
            listCheck.getItems().add(btn);
        }
        pane.setContent(listCheck);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK) {
            for(CheckBox box: listCheck.getItems()){
                if(box.isSelected()){
                    apartments.add(box.getText());
                }
            }
        }
        return apartments;
    }
}
