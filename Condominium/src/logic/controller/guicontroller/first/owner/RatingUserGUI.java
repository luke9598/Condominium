package logic.controller.guicontroller.first.owner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.RateController;
import logic.engineeringclasses.bean.UserBean;
import logic.model.UserSingleton;

import java.sql.SQLException;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class    RatingUserGUI {

    private final RateController rateController = new RateController();
    private final UserSingleton sg = UserSingleton.getInstance();
    private final UserBean bean = new UserBean();


    @FXML public TextArea rateComment;
    @FXML public Button rate;
    @FXML private Text residentName;
    @FXML private Slider sliderRating;


    public void setUp(String name, String id) {
        residentName.setText(name);
        rate.setDisable(true);
        sliderRating.setStyle("-fx-font-size: 20");
        setBean(id);
    }

    public void submitRating() throws SQLException {
        int rateVal = (int) sliderRating.getValue();
        String commentRate = rateComment.getText();
        String id = bean.getUsrID();
        rateController.rateUser(id,sg.getUserID(),rateVal,commentRate);
        firstBorder.setRight(null);
    }

    public void enableButton() {
        rate.setDisable(rateComment.getText().matches("( *)"));
    }



    public void setBean(String id){
        bean.setUsrID(String.valueOf(id));
    }
}
