package logic.controller.guicontroller.second.general;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;

public class Main2GUI extends Application{

    public final ViewController view = new ViewController();
    public final AlertGUI alert = new AlertGUI();
    public static final BorderPane secondBorder = new BorderPane();

    @Override public void start(Stage primaryStage) {
        Pane pane = view.getPage("Login",2);
        secondBorder.setCenter(pane);
        secondBorder.setStyle("-fx-background-color: #121212;");
        Scene scene = new Scene(secondBorder);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/logic/view/icon/outline_apartment_white_24dp.png"));
        primaryStage.show();
    }

    public void fullScreen(Boolean bool){
        Stage stage = (Stage) secondBorder.getScene().getWindow();
        stage.setMaximized(bool);
    }
}
