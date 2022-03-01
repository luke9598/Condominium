package logic.controller.applicationcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.engineeringclasses.dao.PostDAO;
import logic.model.Post;
import logic.model.UserSingleton;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import static logic.controller.guicontroller.first.general.Main1GUI.firstBorder;
import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class PostController {

	UserSingleton sg = UserSingleton.getInstance();
	private final PostDAO post = new PostDAO();

	public File selectFile(int gui) {
			Stage stage = new Stage();
			switch (gui){
				case 1:
					stage.initOwner(firstBorder.getScene().getWindow());
					break;
				case 2:
					stage.initOwner(secondBorder.getScene().getWindow());
					break;
				default:
					break;
			}
			stage.initModality(Modality.WINDOW_MODAL);
			FileChooser fc = new FileChooser();
			FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JPEG/JPG/PNG/GIF file", "*.jpg", "*.jpeg", "*.png", "*.gif");
			fc.getExtensionFilters().add(fileExtensions);
			fc.setTitle("Condominium/Home/AddImageToPost");
		return fc.showOpenDialog(stage);
	}

	public ObservableList<Post> loadPost() {
		ObservableList<Post> list = FXCollections.observableArrayList();
		try {
			list = post.checkListPost(sg.getAddress());
			return list ;
		}catch(Exception e) {
			return list;
		}
	}

	public void addPost(String userID, String text, File file, String address) throws SQLException, IOException {
		post.addPost(userID, text, file, address);
	}

	public void deletePost(String postID) throws SQLException {
		post.deletePost(postID);
	}

	public Image setPostImage(InputStream input){
		try {
			if (input != null && input.available() > 1) {
				return new Image(input);
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}
}
