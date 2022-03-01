package logic.controller.guicontroller.first.general;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.controller.applicationcontroller.PostController;
import logic.controller.guicontroller.first.admin.condominium.InfoGUI;
import logic.controller.guicontroller.first.admin.requests.TabOrganizeGUI;
import logic.controller.guicontroller.first.general.home.Post1GUI;
import logic.engineeringclasses.bean.PostBean;
import logic.model.Post;
import logic.model.UserSingleton;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu1GUI extends Main1GUI implements Initializable {

    public static final UserSingleton sg = UserSingleton.getInstance();
    private final PostController controller = new PostController();
    private final VBox scrollBox = new VBox();

    @FXML private Button btnSign;
    @FXML private Button btnApartment;
    @FXML private Label lbName;
    @FXML private Button btnMeeting;
    @FXML private Button btnHome;
    @FXML private Label tfCondominiumCode;
    @FXML private ImageView imgUser;

    @FXML public void btnHomeClick() {
        scrollBox.getChildren().clear();
        Pane addPost = view.getPage("AddPost",1);
        scrollBox.getChildren().add(addPost);
        ObservableList<Post> posts = controller.loadPost();
        loadPosts(posts);
        firstBorder.setRight(null);
        firstBorder.setCenter(new ScrollPane(scrollBox));
    }

    @FXML public void btnMeetingClick() {
        firstBorder.setRight(null);
        try{
            switch (sg.getRole()) {
                case ADMINISTRATOR:
                    FXMLLoader loader = view.loader("TabOrganize",1);
                    Parent root = loader.load();
                    TabOrganizeGUI tab = loader.getController();
                    tab.selectTab(0);
                    firstBorder.setCenter(root);
                    break;
                case OWNER:
                    scrollBox.getChildren().clear();
                    Pane requestMeet = view.getPage("RequestMeeting",1);
                    scrollBox.getChildren().add(requestMeet);
                    firstBorder.setCenter(new ScrollPane(scrollBox));
                    break;
                case RESIDENT:
                    scrollBox.getChildren().clear();
                    Pane contact = view.getPage("Contact",1);
                    scrollBox.getChildren().add(contact);
                    firstBorder.setCenter(new ScrollPane(scrollBox));
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML public void btnInfoClick() {
        firstBorder.setRight(null);
        try{
            switch (sg.getRole()){
                case ADMINISTRATOR:
                    FXMLLoader loader = view.loader("Info",1);
                    Parent root = loader.load();
                    InfoGUI info = loader.getController();
                    info.setUp(sg.getAddress());
                    firstBorder.setCenter(root);
                    break;
                case OWNER:
                    Pane rate = view.getPage("RateResident",1);
                    firstBorder.setCenter(rate);
                    break;
                case RESIDENT:
                    Pane aptInfo = view.getPage("AptInfo",1);
                    firstBorder.setCenter(aptInfo);
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML private void btnSignOutClick() {
        sg.clearState();
        Pane log = view.getPage("Login",1);
        firstBorder.setRight(null);
        firstBorder.setLeft(null);
        firstBorder.setCenter(log);
        fullScreen(false);
    }

    private void loadPosts(ObservableList<Post> posts) {
        try {
            for (int i = posts.size() - 1; i >= 0; i--) {
                PostBean bean = setUpPost(posts.get(i));
                FXMLLoader loader = view.loader("Post",1);
                Parent root = loader.load();
                Post1GUI post = loader.getController();
                post.setUpPost(bean);
                scrollBox.getChildren().add(root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PostBean setUpPost(Post curr) {
        PostBean post = new PostBean();
        post.setId(curr.getId());
        post.setImage(curr.getImage());
        post.setRole(curr.getRole());
        post.setText(curr.getText());
        post.setUser(curr.getUser());
        return post;
    }

    private void btnColor(Button btn) {
        btn.setOnMouseEntered(event -> btn.setStyle("-fx-background-color : #0A0E3F"));
        btn.setOnMouseExited(event -> btn.setStyle("-fx-background-color : #0C39FF"));
    }

    public void setUp() {
        btnHomeClick();
        btnColor(btnHome);
        btnColor(btnMeeting);
        btnColor(btnSign);
        btnColor(btnApartment);
        imgUser.setImage(new Image(view.addImage(sg.getRole())));
        tfCondominiumCode.setText(sg.getAddress());
        switch (sg.getRole()) {
            case ADMINISTRATOR:
                lbName.setText(sg.getAdministrator().getUsrName());
                btnApartment.setText("Info");
                btnMeeting.setText("Requests");
                break;
            case OWNER:
                lbName.setText(sg.getOwner().getUsrName());
                btnMeeting.setText("Request Meeting");
                btnApartment.setText("Rate Resident");
                break;
            case RESIDENT:
                lbName.setText(sg.getResident().getUsrName());
                btnMeeting.setText("Contact");
                btnApartment.setText("Apartment Info");
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            setUp();
    }
}
