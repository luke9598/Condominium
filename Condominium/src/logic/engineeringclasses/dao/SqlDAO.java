package logic.engineeringclasses.dao;

import java.sql.*;

public class SqlDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/condominium_db";
	private static final String USER = "condominium";
	private static final String PASSWORD = "ispw2021";

	private Connection conn;

	PreparedStatement preset;
	Statement stmt;
	
	public SqlDAO() {
		this.stmt = null;
		this.conn = null;
		this.preset = null;
	}
	
	void connect() throws SQLException 	{
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		 conn = DriverManager.getConnection(URL,USER,PASSWORD);
		 stmt = conn.createStatement();
	}
	
	PreparedStatement prepConnect(String sql) throws SQLException{
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		 conn = DriverManager.getConnection(URL,USER,PASSWORD);
		 return conn.prepareStatement(sql);
	}
	
	void disconnect() throws SQLException{
        if (stmt != null)
            stmt.close();
        if (conn != null)
            conn.close();
	}

	public String loadLatestId(String table,String column) throws SQLException{
		String lastId = "";
		ResultSet rs;
		try{
			connect();
			rs = selectLastId(stmt,table,column);
			if(rs.next()) {
				lastId = rs.getString(column);
			}
		} finally {
			disconnect();
		}
		return lastId;
	}

	public static ResultSet selectLastId(Statement stmt, String table, String column) throws SQLException{
		String sql="SELECT "+column+" FROM "+table+" ORDER BY "+column+" DESC LIMIT 1";
		return stmt.executeQuery(sql);
	}
}
	
