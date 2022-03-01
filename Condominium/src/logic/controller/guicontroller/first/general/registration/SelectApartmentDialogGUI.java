package logic.controller.guicontroller.first.general.registration;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Role;

public class SelectApartmentDialogGUI {

    @FXML private Text txt;
    @FXML private ListView<String> listView;

    public void setUp(ObservableList<String> list, UserBean bean){
        listView.setItems(list);
        switch (Role.valueOf(bean.getUsrRole().toUpperCase())){
            case RESIDENT:
                listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                break;
            case OWNER:
                listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                break;
            default:
                break;
        }
        txt.setText("Please "+bean.getUsrName()+" select an apartment\nAs "+bean.getUsrRole()+" in "+bean.getUsrAddr());
    }

    public ObservableList<String> getApt() {return listView.getSelectionModel().getSelectedItems();}
}
