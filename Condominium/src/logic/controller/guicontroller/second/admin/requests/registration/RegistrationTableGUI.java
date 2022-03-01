package logic.controller.guicontroller.second.admin.requests.registration;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class RegistrationTableGUI {

    @FXML private VBox scrollBox;

    public void addChildren(Node node){
        scrollBox.getChildren().add(node);
    }

    public void clearChildren(){
        scrollBox.getChildren().clear();
    }
}
