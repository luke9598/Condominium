package logic.controller.guicontroller.second.owner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.model.Rate;

public class RateGUI {

    @FXML private Label rateOwnName;
    @FXML private ImageView rateOwnImg;
    @FXML private Label rateValue;
    @FXML private TextArea rateComment;

    public void setupRate(Rate rate) {
        rateOwnImg.setImage(new Image("logic/view/icon/O.png"));
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(1.0);
        rateOwnImg.setEffect(color);
        rateOwnName.setText(rate.getOwnId());
        rateValue.setText(rate.getRateVal());
        rateComment.setText(rate.getRateTxt());
    }
}
