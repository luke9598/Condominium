package logic.controller.guicontroller.first.admin.condominium;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.controller.applicationcontroller.UserController;
import logic.controller.applicationcontroller.ViewController;
import logic.engineeringclasses.bean.UserBean;
import logic.model.User;
import java.io.IOException;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;

public class InfoGUI {

    private final UserController controller = new UserController();
    private final ViewController view = new ViewController();

    @FXML private TableView<User> infoTableView;
    @FXML private TableColumn<User, String> tcID;
    @FXML private TableColumn<User, String> tcName;
    @FXML private TableColumn<User, String> tcEmail;
    @FXML private TableColumn<User, String> tcPwd;
    @FXML private TableColumn<User, String> tcRole;
    @FXML private TableColumn<User, String> tcAddress;

    @FXML private void getSelected() throws IOException{
        int index;
        index = infoTableView.getSelectionModel().getSelectedIndex();
        if(index<=-1)return;
        UserBean bean = usrBean(tcID.getCellData(index),tcName.getCellData(index),tcEmail.getCellData(index),tcPwd.getCellData(index),tcRole.getCellData(index),tcAddress.getCellData(index));
        firstBorder.setRight(loadInfo(bean));
    }

    private Parent loadInfo(UserBean bean) throws IOException {
        FXMLLoader loader = view.loader("InfoDetail",1);
        Parent usrInfo = loader.load();
        InfoDetailGUI ctrlInfo = loader.getController();
        ctrlInfo.setUp(bean);
        return usrInfo;
    }

    private UserBean usrBean(String id, String name, String email, String password, String role, String address) {
        UserBean bean = new UserBean();
        bean.setUsrID(id);
        bean.setUsrName(name);
        bean.setUsrEmail(email);
        bean.setUsrPwd(password);
        bean.setUsrRole(role);
        bean.setUsrAddr(address);
        return bean;
    }

    public void setUp(String address) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("usrID"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("usrName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("usrEmail"));
        tcPwd.setCellValueFactory(new PropertyValueFactory<>("usrPwd"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("usrRole"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("usrAddr"));
        try {
            ObservableList<User> list = controller.loadUserList(address);
            infoTableView.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
