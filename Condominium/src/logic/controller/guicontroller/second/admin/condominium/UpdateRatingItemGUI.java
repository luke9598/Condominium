package logic.controller.guicontroller.second.admin.condominium;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import logic.controller.applicationcontroller.RateController;
import logic.controller.guicontroller.AlertGUI;
import logic.engineeringclasses.bean.RateBean;
import java.sql.SQLException;
import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class UpdateRatingItemGUI {

    private final RateController controller = new RateController();
    private final AlertGUI alert = new AlertGUI();

    @FXML private Label lbID;
    @FXML private Label lbName;
    @FXML private Label lbVal;
    @FXML private TextArea areaTxt;
    @FXML private ImageView iconDelete;
    private int gui;

    public void onDeletePress() throws SQLException {
        if(alert.alertConfirm("Condominium/Info","Are you sure you want to delete this review?",null)) {
            controller.deleteRate(lbID.getText());
            if (gui == 2) {
                secondBorder.setRight(null);
            }
            alert.alertInfo("Condominium/Info","Review successfully deleted",null);
        }
    }

    public void setUp(RateBean bean,int gui) {
        lbID.setText(bean.getRateId());
        lbName.setText(bean.getResId());
        lbVal.setText(bean.getRateVal());
        areaTxt.setText(bean.getRateTxt());
        this.gui = gui;
        if(this.gui == 1){
            ColorAdjust color = new ColorAdjust();
            color.setBrightness(-1);
            iconDelete.setEffect(color);
        }
    }
}
