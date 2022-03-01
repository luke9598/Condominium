package logic.controller.guicontroller.second.resident;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import logic.controller.applicationcontroller.ApartmentController;
import logic.model.Apartment;
import logic.model.UserSingleton;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ContactDialogGUI{


    UserSingleton sg = UserSingleton.getInstance();
    private final ApartmentController aptController = new ApartmentController();

    @FXML private TextArea mailText;

    public List<String> getMessage() throws SQLException {
        Apartment apt = aptController.checkApartments(sg.getUserID(), sg.getAddress(), "apt_res");
        String mail = aptController.checkMailById(aptController.checkUserAptFromNumber(sg.getAddress(),apt.getNumber(),"apt_own"));
        return Arrays.asList(mail, apt.getNumber(),mailText.getText(),apt.getOwner());
    }

}