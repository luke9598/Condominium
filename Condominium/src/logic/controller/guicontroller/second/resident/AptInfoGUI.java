package logic.controller.guicontroller.second.resident;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.FeeController;
import logic.controller.applicationcontroller.ViewController;
import logic.model.Apartment;
import logic.model.Fee;
import logic.model.UserSingleton;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AptInfoGUI implements Initializable {

    private static final UserSingleton sg = UserSingleton.getInstance();
    private final FeeController controller = new FeeController();
    private final ApartmentController aptController = new ApartmentController();
    private final ViewController view = new ViewController();
    private final ObservableList<String> listFees = FXCollections.observableArrayList("Admin","Park","Elevator","Pet","Wifi");

    @FXML private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Fee boolFee = controller.setUpFees(sg.getAddress());
            final Apartment apartment = aptController.checkApartments(sg.getUserID(),sg.getAddress(),"apt_res");
            Fee currentFee = controller.loadFees(aptController.loadApartmentId(apartment.getNumber(),sg.getAddress()),"fee");
            List<String> aptFee = createList(currentFee,boolFee);
            for(int i = 0; i < listFees.size(); i++){
                FXMLLoader loader = view.loader("AptInfoItem",2);
                Parent root = loader.load();
                AptInfoItemGUI item = loader.getController();
                item.setUpItem(listFees.get(i),aptFee.get(i));
                vBox.getChildren().add(root);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private List<String> createList(Fee currentFee,Fee boolFee) {
        String univ = "Unavailable";
        List<String> list = new ArrayList<>();
        list.add(currentFee.getAdmin().toString());
        if(boolFee.getAvailablePark()) list.add(currentFee.getPark().toString());
        else{list.add(univ);}
        if(boolFee.getAvailableElevator()) list.add(currentFee.getElevator().toString());
        else{list.add(univ);}
        if(boolFee.getAvailablePet()) list.add(currentFee.getPet().toString());
        else{list.add(univ);}
        if(boolFee.getAvailableWifi()) list.add(currentFee.getWifi().toString());
        else{list.add(univ);}
        return list;
    }
}