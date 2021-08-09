package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BbsDAO{
	private Connection conn;
	private ResultSet rs;
	
	public BbsDAO() {
		
	try {
		String url="jdbc:mysql://localhost:3306/BBS";
		String id="root";
		String password="1234";
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection(url,id,password);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

}
	public String getDate() {
		
		String sql="SELECT NOW()";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1); //현재 날짜 그대로 반환
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ""; //데이터베이스 오류 
	}
public int getNext() {
		
		String sql="SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1)+1; //현재 날짜 그대로 반환
			}
			return 1; //첫 번째 게시물인 경우 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1; //데이터 베이스 오류 
	}
	
	public int write(String bbsTitle, String userID, String bbsContent) {
		String sql="INSERT INTO BBS VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			return pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;//데이터 베이스 오류 
	}
}