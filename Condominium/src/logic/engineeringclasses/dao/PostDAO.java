package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.PostQuery;
import logic.model.Post;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDAO extends SqlDAO{

    private final UserDAO usrDao = new UserDAO();

    public ObservableList<Post> checkListPost(String address)throws SQLException {
        ObservableList<Post> list = FXCollections.observableArrayList();
        Post post;
        ResultSet rs;
        try {
            connect();
            rs = PostQuery.selectListPost(stmt,address);
            while(rs.next()) {
                String postId = rs.getString("post_id");
                String postUsr = rs.getString("post_usr");
                String postText = rs.getString("post_txt");
                InputStream postImg = rs.getBinaryStream("post_img");
                post = new Post(postId,checkNameByID(postUsr),usrDao.checkRole(postUsr),postText,postImg);
                list.add(post);
            }
        } finally {
            disconnect();
        }
        return list;
    }

    public String checkNameByID(String id)throws SQLException {
        String usrName = "";
        try {
            connect();
            ResultSet rs = PostQuery.selectNameByID(stmt, id);
            if(rs.next()) {
                usrName = rs.getString("user_name");
            }
        } finally {
            disconnect();
        }
        return usrName;
    }

    public void addPost(String usrId, String txt, File file, String address) throws SQLException, IOException {
        try (InputStream input = new FileInputStream(file)){
            String sql = "INSERT INTO posts (post_usr, post_addr, post_txt, post_img) VALUES ( ?, ?, ?, ?)";
            preset = prepConnect(sql);
            preset.setString(1, usrId);
            preset.setString(2, address);
            preset.setString(3, txt);
            preset.setBinaryStream(4, input, (int) file.length());
            preset.execute();
        } finally {
            disconnect();
        }
    }

    public void deletePost(String postId)throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM posts WHERE post_id='"+postId+"'";
            stmt.executeUpdate(sql);
        } finally {
            disconnect();
        }
    }
}
