package application;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import org.apache.commons.codec.digest.DigestUtils;

import application.ConnectionSQL;

public class QueryGetData {
	//open connection
	private static Connection conn = ConnectionSQL.DBConnection();
	//check username on database
	public static boolean checkUserName(String userName) throws ClassNotFoundException, SQLException{
		Statement statement = conn.createStatement();
		String sql = "select * from NHANVIEN where TENDN = '" + userName + "'";
		String sql1 = "select * from SINHVIEN where TENDN = '" + userName + "'";
		
		ResultSet rs = statement.executeQuery(sql);
		int i = 0;
		while(rs.next()){
			System.out.println(rs.getString(1));
			i++;
		}
		if(i != 0){
			return false;
		}
		
		ResultSet rs1 = statement.executeQuery(sql1);
		int i1 = 0;
		while(rs1.next()){
			i1++;
		}		
		if(i1 != 0){
			return false;
		}
		
		return true;
	}
	
	//get password of username
	public static byte[] getData(String userName) throws ClassNotFoundException, SQLException {
		//Connection connection = ConnectionSQL.DBConnection();
		int i =0, i1 = 0;
		byte[] b1 = null, b2 = null;
		Statement statement = conn.createStatement();
		//username NHANVIEN
		String sql = "select MATKHAU from NHANVIEN where TENDN = '" + userName +"'";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			b1 = rs.getBytes(1);
			i++;
		}
		
		//username SINHVIEN
		String sql1 = "select MATKHAU from SINHVIEN where TENDN = '" + userName +"'";
		ResultSet rs1 = statement.executeQuery(sql1);
		while(rs1.next()){
			b2 = rs1.getBytes(1);
			i1++;
		}
		
		if(i!=0)
			return b1;
		else if(i1!=0)
			return b2;
		return null;
	}
	
	public static List<staff> getStaff(){
		try {
			//Cau lenh goi proc
			String sql = "{call SP_SEL_ENCRYPT_NHANVIEN()}";
			//Tao mot doi tuong CallableStatement.
		    CallableStatement cstm = conn.prepareCall(sql);
		    cstm.execute();
		    ResultSet rs = cstm.getResultSet();
			List<staff> s = new ArrayList<staff>();
			byte[] salary;
			String kq = null;
			while (rs.next()){
				salary = rs.getBytes(4);
				try {
					kq = aes.decrypt(salary, "1412503");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s.add( new staff(rs.getString(1), rs.getString(2), rs.getString(3), kq));
			}
			return s;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void addStaff(String id_staff, String name, String email, String salary, String username, String pass){
		//Cau lenh goi proc
		String sql = "{call SP_INS_ENCRYPT_NHANVIEN(?,?,?,?,?,?)}";
		//Tao mot doi tuong CallableStatement.
	    CallableStatement cstm;
		try {
			cstm = conn.prepareCall(sql);
			cstm.setString(1, id_staff);
		    cstm.setString(2, name);
		    cstm.setString(3, email);
		    cstm.setBytes(4, aes.encrypt(salary, "1412503"));
		    cstm.setString(5, username);
		    cstm.setBytes(6, DigestUtils.sha1(pass));
		    cstm.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int checkId_Username(String id, String user){
		String sql = "select MANV, TENDN from NHANVIEN";
		try {
			Statement statement = conn.createStatement();
			ResultSet rs;
			rs = statement.executeQuery(sql);
			while(rs.next()){
				if(rs.getString(1).compareTo(id)==0){
					return 0;
				}
				if(rs.getString(2).compareTo(user)==0){
					return 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;
	}
	//close connection
	public static void closeConn(){
		try {
			System.out.println("Close Connect");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
