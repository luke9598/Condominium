package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CondominiumQuery {

	private CondominiumQuery(){}
	
	public static ResultSet selectAddressList(Statement stmt)  throws SQLException{
		String sql = "SELECT con_addr FROM condominiums";
		return stmt.executeQuery(sql);
	}

}
