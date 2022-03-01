package logic.controller.guicontroller.first.general.home;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.controller.applicationcontroller.PostController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.Menu1GUI;
import logic.model.UserSingleton;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPostGUI implements Initializable{

	private static final Menu1GUI menu = new Menu1GUI();
	private final AlertGUI alert = new AlertGUI();
	private final PostController controller = new PostController();
	private File file;
	UserSingleton sg = UserSingleton.getInstance();
	
    @FXML private TextField txtMsg;
    @FXML private Button btnAddPost;
    @FXML private Button btnPublish;
    
    @FXML private void addFileClick() {
		btnAddPost.setDisable(true);
		this.file = controller.selectFile(1);
		if (this.file != null) {
			btnAddPost.setText(this.file.getName());
		}
		btnAddPost.setDisable(false);
	}

    @FXML private void onPublishClick() {
		if(btnAddPost.getText().equals("Add File")) {
			alert.alertWarn("Condominium/Home/Warning", "Ops... Missing File", "Please select one File");
			txtMsg.setText("");
		}
	     else if(txtMsg.getText().isEmpty()) {
	    	 alert.alertWarn("Condominium/Home/Warning", "Ops... Missing Text", "Please insert Text");
	    	 btnAddPost.setText("Add File");
	    } else {
	    	try {
				controller.addPost(sg.getUserID(), txtMsg.getText(),this.file,sg.getAddress());
				menu.btnHomeClick();
	    	} catch(Exception e) {
	    		alert.alertError("Condominium/Home/Error", "Ops... Something goes wrong", "Please Retry");
	    		e.printStackTrace();
	    	}
	    }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	btnColor(btnPublish);
    	btnColor(btnAddPost);
    }
    
    private void btnColor(Button btn) {
    	btn.setOnMouseEntered(event -> btn.setStyle("-fx-background-color : #0A0E3F"));
    	btn.setOnMouseExited(event -> btn.setStyle("-fx-background-color : #0C39FF"));
    }

}
