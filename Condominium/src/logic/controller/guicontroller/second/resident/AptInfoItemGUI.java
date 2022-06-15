package logic.controller.guicontroller.second.resident;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AptInfoItemGUI {

    @FXML private Label lbName;
    @FXML private Label lbValue;

    public void setUpItem(String name,String value){
        lbName.setText(name);
        lbValue.setText(value);
    }

}
