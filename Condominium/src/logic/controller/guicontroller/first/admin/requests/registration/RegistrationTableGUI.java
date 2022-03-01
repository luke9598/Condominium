package logic.controller.guicontroller.first.admin.requests.registration;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.controller.applicationcontroller.ViewController;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.controller.applicationcontroller.RegisterController;
import logic.model.Registration;
import java.io.IOException;
import java.util.Objects;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class RegistrationTableGUI {

	protected final RegisterController controller = new RegisterController();
    protected final ViewController view = new ViewController();

    @FXML private TableView<Registration> registrationTable;
    @FXML private TableColumn<Registration, String> tcID;
    @FXML private TableColumn<Registration, String> tcName;
    @FXML private TableColumn<Registration, String> tcEmail;
    @FXML private TableColumn<Registration, String> tcPwd;
    @FXML private TableColumn<Registration, String> tcRole;
    @FXML private TableColumn<Registration, String> tcAddress;
    @FXML private TableColumn<Registration, String> tcApartment;

    @FXML private void getSelected() throws IOException {
        int index;
        index = registrationTable.getSelectionModel().getSelectedIndex();
        if(index<=-1)return;
        RegistrationBean bean = registrationBean(tcID.getCellData(index),tcName.getCellData(index),tcEmail.getCellData(index),tcPwd.getCellData(index),tcRole.getCellData(index),tcAddress.getCellData(index),tcApartment.getCellData(index));
        FXMLLoader loader = view.loader("RegistrationDetailTable",1);
        Parent root = loader.load();
        RegistrationTableDetailGUI detail = loader.getController();
        detail.setUp(bean);
        style();
        firstBorder.setRight(root);
    }

    private void style() {
        registrationTable.getStylesheets().clear();
        registrationTable.setId("detail-table");
        registrationTable.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/logic/view/first/style.css")).toExternalForm());
    }

    public void setUpRegistration(String address) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("regID"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("regName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("regEmail"));
        tcPwd.setCellValueFactory(new PropertyValueFactory<>("regPwd"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("regRole"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("regAddr"));
        tcApartment.setCellValueFactory(new PropertyValueFactory<>("regApt"));
        try {
	    	ObservableList<Registration> list = controller.loadRegistration(address);
            registrationTable.setItems(list);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public RegistrationBean registrationBean(String id, String name, String email, String password, String role, String address, String apartment) {
        RegistrationBean registered = new RegistrationBean();
        registered.setID(id);
        registered.setName(name);
        registered.setEmail(email);
        registered.setPassword(password);
        registered.setRole(role);
        registered.setAddress(address);
        registered.setApartment(apartment);
        return registered;
    }
}
