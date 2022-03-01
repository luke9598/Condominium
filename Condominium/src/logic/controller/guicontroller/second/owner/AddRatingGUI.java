package logic.controller.guicontroller.second.owner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.RateController;
import logic.model.UserSingleton;

import java.sql.SQLException;
import java.util.List;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class AddRatingGUI {

    private final ApartmentController aptController = new ApartmentController();
    private final RateController rateController = new RateController();
    private final UserSingleton sg = UserSingleton.getInstance();

    @FXML private ToggleGroup rate;
    @FXML private TextArea commentRate;
    @FXML private Button btnRate;
    @FXML private Text resName;

    private String id;

    public void setUp(List<String> list) throws SQLException {
        String resId = aptController.checkUserAptFromNumber(sg.getAddress(),list.get(1),"apt_res");
        resName.setText(list.get(0));
        btnRate.setDisable(true);
        this.id = resId;
    }

    public void submitRate() throws SQLException {
        RadioButton selected = (RadioButton) rate.getSelectedToggle();
        int rateValue = Integer.parseInt(selected.getText());
        String rateText = commentRate.getText();
        rateController.rateUser(this.id, sg.getUserID(),rateValue,rateText);
        secondBorder.setRight(null);
    }

    public void enableButton() {
        btnRate.setDisable(commentRate.getText().matches("( *)"));
    }
}
