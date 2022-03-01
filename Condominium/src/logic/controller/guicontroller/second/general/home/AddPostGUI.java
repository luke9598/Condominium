package logic.controller.guicontroller.second.general.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.PostController;
import java.io.File;

public class AddPostGUI {

    private final PostController controller = new PostController();
    private File file;

    @FXML private TextField txtMsg;
    @FXML private Button btnAddPost;
    @FXML private Text txtFile;

    @FXML private void addFileClick() {
        btnAddPost.setDisable(true);
        this.file = controller.selectFile(2);
        if (this.file != null) {
            txtFile.setText(this.file.getName());
        }
        btnAddPost.setDisable(false);
    }

    public String getMsg(){
        if(!txtMsg.getText().isEmpty()) {
            return txtMsg.getText();
        }
        return null;
    }

    public File getFile(){
        if(!txtFile.getText().isEmpty()){
            return this.file;
        }
        return null;
    }
}
