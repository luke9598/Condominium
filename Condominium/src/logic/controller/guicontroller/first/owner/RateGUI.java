package logic.controller.guicontroller.first.owner;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import logic.model.Rate;

public class RateGUI {

    @FXML private Text rate;
    @FXML private TextArea rateComment;
    @FXML private Text rateName;

    public void setupRate(Rate rate) {
        rateName.setText(rate.getOwnId());
        this.rate.setText(rate.getRateVal());
        rateComment.setText(rate.getRateTxt());
    }
}