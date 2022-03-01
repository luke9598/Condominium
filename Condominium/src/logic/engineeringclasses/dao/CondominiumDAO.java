package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.CondominiumQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CondominiumDAO extends SqlDAO{
    public ObservableList<String> checkAddressesList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            connect();
            rs = CondominiumQuery.selectAddressList(stmt);
            while(rs.next()) {
                list.add(rs.getString("con_addr"));
            }
        } finally {
            disconnect();
        }
        return list;
    }
}
