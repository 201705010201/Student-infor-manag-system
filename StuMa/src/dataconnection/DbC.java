package dataconnection;

import java.sql.*;

/**
 * Title : 锟斤拷锟捷匡拷锟斤拷锟斤拷
 * 
 * @author  成梦月锟斤拷锟斤拷锟斤拷
 *
 *程盟约
 */

public class DbC {
	
	public  DbC() {
		conn();
	}

	private Statement conn() {
		
		try {//锟斤拷锟斤拷锟斤拷锟斤拷
			Class.forName("com.mysql.jdbc.Driver");
		 
			String uri = "jdbc:mysql://localhost:3306/001?useSSL=true";
			String user = "root";
			String passwd = "root";
			
			//锟斤拷锟斤拷锟斤拷锟捷匡拷
			Connection con = null;
			con = DriverManager.getConnection(uri,user,passwd);
			Statement stat = con.createStatement();
			return stat;
			} catch (ClassNotFoundException ex) {
				return null;
			} catch (SQLException ex1) {
				return null;
			}
		}
	
	//锟斤拷询锟斤拷锟捷匡拷
	public ResultSet getRS(String sql) {
		try {
			Statement stat = conn();
			ResultSet rs = stat.executeQuery(sql);
			//System.out.println(rs);
			return rs;
		} catch (Exception ex) {//锟斤拷锟斤拷呛锟缴拷锟�
			System.err.println("------------" + ex.getMessage());
			return null;
		}
		
	}

	//锟斤拷锟斤拷锟斤拷锟捷匡拷
	public int getUpdate(String sql) {
		try {
			Statement stat = conn();
			//executeUpdate(String sql); 锟斤拷执锟斤拷锟斤拷锟斤拷删锟斤拷锟侥ｏ拷锟斤拷锟斤拷执锟斤拷锟杰碉拷影锟斤拷锟斤拷锟斤拷锟斤拷锟�
			int i = stat.executeUpdate(sql);
			return i;
		} catch (Exception ex) {//ex.getMessage():锟斤拷么锟斤拷锟斤拷锟较�
			System.out.println(">>>>>>>>" + ex.getMessage());
			return -1;
		}
	}
}