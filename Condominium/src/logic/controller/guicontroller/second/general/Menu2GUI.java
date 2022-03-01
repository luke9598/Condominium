package logic.controller.guicontroller.second.general;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import logic.controller.applicationcontroller.EmailController;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.applicationcontroller.PostController;
import logic.controller.applicationcontroller.UserController;
import logic.controller.guicontroller.first.general.Menu1GUI;
import logic.controller.guicontroller.second.admin.condominium.InfoItemGUI;
import logic.controller.guicontroller.second.admin.condominium.InfoTableGUI;
import logic.controller.guicontroller.second.admin.requests.RequestBottomNavMenuGUI;
import logic.controller.guicontroller.second.general.home.AddPostGUI;
import logic.controller.guicontroller.second.general.home.PostGUI;
import logic.controller.guicontroller.second.owner.RequestMeetingDialogGUI;
import logic.controller.guicontroller.second.resident.ContactDialogGUI;
import logic.engineeringclasses.bean.PostBean;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Post;
import logic.model.User;
import logic.model.UserSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Menu2GUI extends Main2GUI implements Initializable {

    public static final UserSingleton sg = UserSingleton.getInstance();
    private final HBox scrollBox = new HBox();
    private final VBox vbox = new VBox();
    private final PostController postCtrl = new PostController();
    private final UserController usrCtrl = new UserController();
    private final MeetController meet = new MeetController();

    @FXML private ImageView imgUser;
    @FXML private Label lbName;
    @FXML private Label tfCondominiumCode;
    @FXML private ChoiceBox<String> choice;
    private static final String SIGN_OUT = "Sign Out";
    private final ObservableList<String> admin = FXCollections.observableArrayList("Home","Request","Condominium",SIGN_OUT);
    private final ObservableList<String> owner = FXCollections.observableArrayList("Home","Meeting Request","Rate Resident",SIGN_OUT);
    private final ObservableList<String> resident = FXCollections.observableArrayList("Home","Contact Owner","Apartment Info",SIGN_OUT);

    public void setUp(){
        choice.setOnAction(this::getChoice);
        choice.setValue("Home");
        imgUser.setImage(new Image(view.addImage(sg.getRole())));
        ColorAdjust color = new ColorAdjust();
        color.setBrightness(1);
        imgUser.setEffect(color);
        tfCondominiumCode.setText(sg.getAddress());
        switch (sg.getRole()) {
            case ADMINISTRATOR:
                lbName.setText(sg.getAdministrator().getUsrName());
                choice.setItems(admin);
                break;
            case OWNER:
                lbName.setText(sg.getOwner().getUsrName());
                choice.setItems(owner);
                break;
            case RESIDENT:
                lbName.setText(sg.getResident().getUsrName());
                choice.setItems(resident);
                break;
        }
    }

    private void getChoice(javafx.event.ActionEvent actionEvent) {
        switch (choice.getValue()){
            default:
            case "Home":
                btnHomeClick();
                break;
            case "Request":
                btnMeetingClick();
                break;
            case "Condominium":
                btnInfoClick();
                break;
            case SIGN_OUT:
                btnSignOutClick();
                break;
            case "Meeting Request":
                btnMeetingRequest();
                break;
            case "Rate Resident":
                rateResident();
                break;
            case "Contact Owner":
                contactOwner();
                break;
            case "Apartment Info":
                apartmentInfo();
                break;
        }
    }

    private void apartmentInfo() {
        try {
            FXMLLoader loader = view.loader("AptInfo", 2);
            Parent root2 = loader.load();
            secondBorder.setCenter(root2);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void contactOwner() {
        try {
            secondBorder.setCenter(null);
            secondBorder.setRight(null);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/logic/view/second/ContactDialogView.fxml"));
            DialogPane controlDialog = loader.load();
            ContactDialogGUI contact = loader.getController();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(controlDialog);
            Optional<ButtonType> btn = dialog.showAndWait();
            if (btn.isPresent() && btn.get() == ButtonType.OK ){
                List<String> list = contact.getMessage();
                String mail = list.get(0);
                String subject = "Message from "+sg.getResident().getUsrName()+" of apartment "+list.get(1)+" in condominium "+sg.getAddress();
                String body = list.get(2);
                String[] recipients = new String[]{mail};
                if (alert.alertConfirm("Confirmation","Confirm to send email?","Are you sure to send mail to " + list.get(3) + " with text '" + list.get(2) + "'?")) {
                    new EmailController().send(recipients, recipients, subject, body);
                } else {
                    alert.alertInfo("Information", "Mail not sent!","");
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        btnHomeClick();
        choice.setValue("Home");
    }

    private void rateResident() {
        try {
            FXMLLoader loader = view.loader("RateResident",2);
            Parent root = loader.load();
            secondBorder.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void btnMeetingRequest(){
        try {
            secondBorder.setCenter(null);
            secondBorder.setRight(null);
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("/logic/view/second/RequestMeetingDialogView.fxml"));
            DialogPane meetingDialog = loader1.load();
            RequestMeetingDialogGUI request = loader1.getController();
            Dialog<ButtonType> dialog1 = new Dialog<>();
            dialog1.setDialogPane(meetingDialog);
            Optional<ButtonType> btn1 = dialog1.showAndWait();
            if (btn1.isPresent() && btn1.get() == ButtonType.OK ) {
                List<String> list1 = request.getMessage();
                String subject1 = list1.get(0);
                String body1 = list1.get(1);
                if (alert.alertConfirm("Confirmation","Confirm to send meeting request?","Are you sure to send a meeting request to Administrator with text '" + list1.get(1) + "'?")) {
                    meet.addMeeting(sg.getUserID(),sg.getAddress(),subject1,body1);
                } else {
                    alert.alertError("Request cannot be sent","Impossible to send request","For some unexpected error your request cannot be sent, try again in a few minutes");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        btnHomeClick();
        choice.setValue("Home");
    }

    public void btnHomeClick() {
        secondBorder.setBottom(null);
        secondBorder.setRight(null);
        vbox.getChildren().clear();
        scrollBox.getChildren().clear();
        addPost();
        ObservableList<Post> posts = postCtrl.loadPost();
        loadPosts(posts);
        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setOnScroll(event -> {
            if(event.getDeltaX() == 0 && event.getDeltaY() != 0) {
                scroll.setHvalue(scroll.getHvalue() - event.getDeltaY() / scrollBox.getWidth());
            }
        });
        scrollBox.setSpacing(30);
        scroll.setContent(scrollBox);
        vbox.getChildren().add(scroll);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
        vbox.getStylesheets().add("logic/view/second/style.css");
        scroll.getStyleClass().add("scrollPost");
        secondBorder.setCenter(vbox);
    }

    private void addPost(){
        Button btnAdd = new Button("Add Post");
        btnAdd.getStyleClass().add("btnGeneral");
        btnAdd.setOnAction(e->{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/logic/view/first/Dialog.fxml"));
                DialogPane pane = loader.load();
                FXMLLoader addPost = new FXMLLoader(getClass().getResource("/logic/view/second/AddPostView.fxml"));
                Pane post = addPost.load();
                pane.setContent(post);
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(pane);
                Optional<ButtonType> btn = dialog.showAndWait();
                if (btn.isPresent() && btn.get() == ButtonType.OK) {
                    AddPostGUI gui = addPost.getController();
                    if(gui.getMsg() == null || gui.getFile() == null){
                        alert.alertWarn("Condominium/Home/Warning", "Ops... Missing Text/File", "Please insert Text");
                    }else{
                        postCtrl.addPost(sg.getUserID(), gui.getMsg(),gui.getFile(),sg.getAddress());
                        btnHomeClick();
                    }
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        btnAdd.getStyleClass().add("btnGeneral");
        vbox.getChildren().add(btnAdd);
    }

    private void loadPosts(ObservableList<Post> posts) {
        try {
            for (int i = posts.size() - 1; i >= 0; i--) {
                Menu1GUI menu = new Menu1GUI();
                PostBean bean = menu.setUpPost(posts.get(i));
                FXMLLoader loader = view.loader("Post",2);
                Parent root = loader.load();
                PostGUI postgui = loader.getController();
                postgui.setUpPost(bean);
                scrollBox.getChildren().add(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnMeetingClick()  {
        secondBorder.setRight(null);
        try{
            FXMLLoader loader = view.loader("RequestBottomNavMenu", 2);
            Pane root = loader.load();
            RequestBottomNavMenuGUI bottomMenu = loader.getController();
            bottomMenu.setAddress(sg.getAddress());
            bottomMenu.btnRegClick();
            root.setMaxSize(Double.MAX_VALUE, Region.USE_COMPUTED_SIZE);
            secondBorder.setBottom(root);
        }catch(IOException ignore) {
            //Error
        }
    }

    public void btnInfoClick() {
        secondBorder.setCenter(null);
        secondBorder.setRight(null);
        secondBorder.setBottom(null);
        try {
            FXMLLoader loader = view.loader("InfoTable", 2);
            Pane table = loader.load();
            InfoTableGUI tableCtrl = loader.getController();
            tableCtrl.clearChildren();
            ObservableList<User> list = usrCtrl.loadUserList(sg.getAddress());
            for(int i = 0; i<=list.size()-1; i++){
                UserBean bean = setUpBean(list.get(i));
                FXMLLoader item = view.loader("ItemInfo",2);
                Pane root = item.load();
                InfoItemGUI itemCtrl = item.getController();
                itemCtrl.setUp(bean);
                tableCtrl.addChildren(root);
            }
            table.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            secondBorder.setCenter(table);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private UserBean setUpBean(User user){
        UserBean bean = new UserBean();
        bean.setUsrID(user.getUsrID());
        bean.setUsrName(user.getUsrName());
        bean.setUsrEmail(user.getUsrEmail());
        bean.setUsrPwd(user.getUsrPwd());
        bean.setUsrRole(user.getUsrRole());
        bean.setUsrAddr(user.getUsrAddr());
        return bean;
    }

    public void btnSignOutClick() {
        sg.clearState();
        Pane log = view.getPage("Login",2);
        secondBorder.setRight(null);
        secondBorder.setTop(null);
        secondBorder.setCenter(log);
        secondBorder.setBottom(null);
        fullScreen(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
