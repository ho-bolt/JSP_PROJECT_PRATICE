package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		
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
	
	public int login(String userID,String userPassword) {
		String sql="select userPassword from User where userID=?";
			
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;//로그인 성공
				}
				else {
					return 0;//비밀번호 불일치
				}
			}
			return -1;//아이디가 없음
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -2;//데이터베이스 오류
		
	}
	
}
