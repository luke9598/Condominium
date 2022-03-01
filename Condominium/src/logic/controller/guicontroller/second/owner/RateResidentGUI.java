package logic.controller.guicontroller.second.owner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.ViewController;
import logic.model.Apartment;
import logic.model.UserSingleton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RateResidentGUI implements Initializable{

    private final ApartmentController aptController = new ApartmentController();
    private final UserSingleton sg = UserSingleton.getInstance();

    @FXML ListView<AnchorPane> residentList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ViewController view = new ViewController();
            FXMLLoader loader1 = view.loader("ResidentListItem",2);
            AnchorPane root1 = loader1.load();
            ListItemGUI item = loader1.getController();
            item.btnValues.setDisable(true);
            item.btnValues.setVisible(false);
            residentList.getItems().add(root1);
            ObservableList<Apartment> ownerApt = FXCollections.observableArrayList(aptController.checkApartmentsList(sg.getUserID(),sg.getAddress(),"apt_own"));
            for (Apartment apartment : ownerApt) {
                loader1 = view.loader("ResidentListItem",2);
                root1 = loader1.load();
                ListItemGUI listItem = loader1.getController();
                listItem.setUp(apartment.getResident(),apartment.getNumber());
                residentList.getItems().add(root1);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
