package logic.controller.guicontroller.second.owner;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class RateDialogGUI{
    @FXML private ToggleGroup group1;
    @FXML public Text titleTxt;


    public String submitValue(String string){
        titleTxt.setText("What would you do with resident "+string+"?");
        RadioButton selected = (RadioButton) group1.getSelectedToggle();
        return selected.getText();
    }
}
