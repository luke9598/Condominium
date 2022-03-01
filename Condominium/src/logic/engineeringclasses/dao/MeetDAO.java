package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.MeetQuery;
import logic.model.MeetRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetDAO extends SqlDAO{

    private final UserDAO userDao = new UserDAO();

    public ObservableList<MeetRequest> loadMeetList(String address) throws SQLException{
        ObservableList<MeetRequest> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            connect();
            rs = MeetQuery.selectMeetRequests(stmt,address);
            while(rs.next()) {
                list.add(new MeetRequest(rs.getString("meet_id"),userDao.checkNameByID(rs.getString("meet_from")),rs.getString("meet_addr"),rs.getString("meet_obj"), rs.getString("meet_txt")));
            }
        } finally {
            disconnect();
        }
        return list;
    }

    public void deleteMeetRequest(int meetId) throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM meeting WHERE meet_id='"+meetId+"'";
            stmt.executeUpdate(sql);
        } finally {
            disconnect();
        }
    }

    public void addMeeting(String meetFrom, String meetAddr, String meetObj, String meetTxt) throws SQLException{
        try {
            String sql = "INSERT INTO meeting (meet_from,meet_addr,meet_obj,meet_txt) values (?,?,?,?)";
            preset = prepConnect(sql);
            preset.setString(1,meetFrom);
            preset.setString(2,meetAddr);
            preset.setString(3,meetObj);
            preset.setString(4,meetTxt);
            preset.execute();
        } finally {
            disconnect();
        }
    }
}