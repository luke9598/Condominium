package logic.controller.guicontroller.second.owner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;


public class ListItemGUI {

    private final AlertGUI alert = new AlertGUI();


    @FXML private Text txtName;
    @FXML private Text txtApt;
    @FXML Button btnValues;

    public void setUp(String name,String aptNumber){
        txtName.setText(name);
        txtApt.setText(aptNumber);
    }

    public void getValues() throws IOException, SQLException {
        String resident = txtName.getText();
        String apt = txtApt.getText();
        List<String> list = Arrays.asList(resident,apt);
        if (resident == null){
            alert.alertError("Null item selected","You have selected a non valid item","Please select a valid item");
        }else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/logic/view/second/RateDialogView.fxml"));
            DialogPane rateDialog = loader.load();
            RateDialogGUI rate = loader.getController();
            rate.titleTxt.setText("What you would do with resident "+resident+" of apartment "+apt+"?");
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(rateDialog);
            Optional<ButtonType> btn = dialog.showAndWait();
            if (btn.isPresent() && btn.get() == ButtonType.OK) {
                ViewController view = new ViewController();
                secondBorder.setRight(null);
                switch (rate.submitValue(resident)){
                    case ("Check previous rates"):
                        FXMLLoader prevLoader = view.loader("PreviousRating",2);
                        Parent root = prevLoader.load();
                        PreviousRatingGUI prevController = prevLoader.getController();
                        prevController.setUp(list);
                        secondBorder.setRight(root);
                        break;
                    case ("Add new Rate"):
                        FXMLLoader addLoader = view.loader("AddRating",2);
                        Parent root1 = addLoader.load();
                        AddRatingGUI addRate = addLoader.getController();
                        addRate.setUp(list);
                        secondBorder.setRight(root1);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
