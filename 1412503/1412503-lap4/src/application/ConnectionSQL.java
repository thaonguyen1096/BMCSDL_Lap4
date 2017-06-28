package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
	private static Connection con;
	
	public static Connection DBConnection()
	{
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			//Driver driver = new net.sourceforge.jtds.jdbc.Driver();
			//DriverManager.registerDriver(driver);
			
			String conString ="jdbc:jtds:sqlserver://localhost:1433/QLSV;integratedSecurity=true";
			con = DriverManager.getConnection(conString);
			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
}
