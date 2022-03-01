package logic.controller.applicationcontroller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.model.Condominium;
import logic.model.Role;
import java.io.IOException;
import java.net.URL;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class ViewController{
	
	private Pane view;
	private static final String FIRST_URL = "/logic/view/first/";
	private static final String SECOND_URL = "/logic/view/second/";
	private static final String FINAL_URL = "View.fxml";

	public Pane getPage(String fileName,int gui) {
		URL fileUrl = null;
		try {
			if (gui == 1){
				fileUrl = Condominium.class.getResource(FIRST_URL +fileName+ FINAL_URL);
			}else if (gui == 2){
				fileUrl = Condominium.class.getResource(SECOND_URL +fileName+ FINAL_URL);
			}
			if(fileUrl == null) {
				throw new java.io.FileNotFoundException("File Not Found");
			}
			new FXMLLoader();
			view = FXMLLoader.load(fileUrl);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return view;
	}	
	
	public FXMLLoader loader(String fileName,int gui){
		FXMLLoader loader = null;
		if (gui == 1){
			loader = new FXMLLoader(getClass().getResource(FIRST_URL +fileName+ FINAL_URL));
		}else if (gui == 2){
			loader = new FXMLLoader(getClass().getResource(SECOND_URL +fileName+ FINAL_URL));
		}
		return loader;
	}

	public String addImage(Role role) {
		String path = "logic/view/Icon/";
		String png = "";
		switch (role){
			case RESIDENT:
				png = path+"R.png";
				break;
			case OWNER:
				png = path+"O.png";
				break;
			case ADMINISTRATOR:
				png = path+"A.png";
				break;
		}
		return png;
	}

	public void secondRight(Parent parent, Button btnAction){
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		Button btnCan = new Button("Cancel");
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(30);
		vBox.getStylesheets().add("/logic/view/second/style.css");
		btnAction.getStyleClass().add("btnGeneral");
		btnCan.getStyleClass().add("btnGeneral");
		btnAction.setMaxWidth(150);
		btnCan.setMaxWidth(150);
		btnCan.setOnAction(e-> secondBorder.setRight(null));
		hBox.getChildren().addAll(btnAction,btnCan);
		vBox.getChildren().addAll(parent,hBox);
		secondBorder.setRight(vBox);
	}
}
