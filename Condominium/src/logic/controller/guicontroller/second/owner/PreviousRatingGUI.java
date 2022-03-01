package logic.controller.guicontroller.second.owner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.RateController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.model.Rate;
import logic.model.UserSingleton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PreviousRatingGUI {

    private final ApartmentController aptController = new ApartmentController();
    private final RateController rateCtrl = new RateController();
    private final UserSingleton sg = UserSingleton.getInstance();

    @FXML private VBox vbox;

    public void setUp(List<String> list) throws SQLException, IOException {
        String id = aptController.checkUserAptFromNumber(sg.getAddress(),list.get(1),"apt_res");
        ObservableList<Rate> rates = FXCollections.observableArrayList(rateCtrl.getRatesRes(id));
        vbox.getChildren().clear();
        if (!rates.isEmpty()) {
            VBox ratesPane = new VBox();
            ratesPane.setSpacing(20);
            for (int i = rates.size() - 1; i >= 0; i--){
                ViewController view = new ViewController();
                FXMLLoader loader1 = view.loader("Rate",2);
                Parent root1 = loader1.load();
                RateGUI pastRate = loader1.getController();
                pastRate.setupRate(rates.get(i));
                ratesPane.getChildren().add(root1);
            }
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(ratesPane);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
            vbox.getChildren().add(scrollPane);
        }else{
            AlertGUI alert = new AlertGUI();
            alert.alertInfo("Rates/Error","This user has no rates","No rates found on the user "+list.get(0));
        }
    }
}
