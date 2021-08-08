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
	
	//데이터 접근 객체
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
	
	public int join(User user) {
		String sql="INSERT INTO USER VALUES(?, ?, ?, ?, ?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			//insert 문장을 실행한 경우 0이상의 숫자가 반환된다. 
			return pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;//데이터 베이스 오류
		
		}
}
