package logic.controller.guicontroller.second.admin.condominium;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UpdateRatingTableGUI {

    @FXML private Label ownName;
    @FXML private VBox scrollBox;

    public void addChildren(Node node){
        scrollBox.getChildren().add(node);
    }

    public void clearChildren(){
        scrollBox.getChildren().clear();
    }

    public void setOwnName(String name){
        ownName.setText(name+"\tRating table");
    }
}
