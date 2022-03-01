package logic.controller.guicontroller.second.general;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.guicontroller.AlertGUI;
import java.util.Optional;

public class AddressesDialogGUI {

    private final RegisterController controller = new RegisterController();
    private final AlertGUI alert = new AlertGUI();

    public String loadAddresses() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/logic/view/first/Dialog.fxml"));
            DialogPane pane = loader.load();
            ListView<String> list = new ListView<>();
            list.setItems(controller.loadAddresses());
            pane.setContent(list);
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Optional<ButtonType> btn = dialog.showAndWait();
            if (btn.isPresent() && btn.get() == ButtonType.OK) {
                String address = list.getSelectionModel().getSelectedItem();
                if (address != null) {
                    return address;
                }
            }
        } catch (Exception e) {
            alert.alertError("DATA BASE ERROR", "DATA BASE not connected ", "Please Restart the Application");
            Platform.exit();
        }
        return null;
    }
}
