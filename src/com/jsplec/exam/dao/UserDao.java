package com.jsplec.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jsplec.exam.dto.UserDto;

public class UserDao {

	DataSource dataSource;
	
	public UserDao() {
		try {
			Context context = new InitialContext();
			dataSource=(DataSource) context.lookup("java:comp/env/jdbc/exambank");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 아이디 중복 체크를 하기 위해 DB에서 ID 정보 불러오기 및 아이디 찾기
	public ArrayList<String> AllUserId(){
		
		ArrayList<String> dtos = new ArrayList<String>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from tableUser ";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString("userId");
								
				dtos.add(userId);
			}		
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}// end AllUserId -----------------------
	
	
	
	// 회원가입을 위해 DB에 입력하기
	public int userInsert(String id, String pw, String name, String telno, String nick, String quiz, String hint){
		Connection connection2 = null; 
		PreparedStatement ps2 = null;
		
		int cnt = 0;
		try {
			
			System.out.println("id  :"+id);
			System.out.println("pw  :"+pw);
			System.out.println("name  :"+name);
			System.out.println("telno  :"+telno);
			System.out.println("nick  :"+nick);
			System.out.println("quiz  :"+quiz);
			System.out.println("hint  :"+hint);
			
			connection2 = dataSource.getConnection();
			String query = "insert into tableUser "
					+ "(userId, userPw, userName, userTelno, userNickName, userHintQuiz, userHint, joinDate) "
					+ "values(?,?,?,?,?,?,?,now()) ";
			
			ps2 = connection2.prepareStatement(query);
			
			ps2.setString(1, id);
			ps2.setString(2, pw);
			ps2.setString(3, name);
			ps2.setString(4, telno);
			ps2.setString(5, nick);
			ps2.setString(6, quiz);
			ps2.setString(7, hint);
			
			ps2.executeUpdate();
			cnt = 1;
			
		}catch(Exception e) {
			e.printStackTrace();
			cnt = 0;
		}finally {
			try {
				if(ps2 != null) ps2.close();
				if(connection2 != null) connection2.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt;
	} // end userInsert ------------------
	
	
	// 회원정보 수정 및 탈퇴를 위한 회원정보 불러오기
	public UserDto userInfo(int userSeq){
		
		UserDto dtos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			String query = "select userSeq, userId, userPw, userName, userTelno, userNickName, userHintQuiz, userHint, joinDate "
					+ "from tableUser where userSeq = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, userSeq);
			
			rs = ps.executeQuery();
			
			System.out.println("UserDao userInfo SQL :"+userSeq);
			
			if(rs.next()) {
				String userId = rs.getString("userId");
				String userPw = rs.getString("userPw");
				String userName = rs.getString("userName");
				String userTelno = rs.getString("userTelno");
				String userNickName = rs.getString("userNickName");
				String userHintQuiz = rs.getString("userHintQuiz");
				String userHint = rs.getString("userHint");					
				Timestamp joinDate = rs.getTimestamp("joinDate");
				
				dtos = new UserDto(userId, userPw, userName, userTelno, userNickName, userHintQuiz, userHint, joinDate);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) rs.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}//end userInfo --------------------
	
	
	// 로그인 비밀번호 체크
	public boolean login(String userid, String userpw) {
		
		boolean Result = false;
		
		String pw = "";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select userPw from tableUser where userId = '" + userid +"' and userPw =  '" + userpw + "' and outdate is null";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				pw = resultSet.getString("userPw");
			}
			
			if(pw.length() != 0) Result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return Result;
	} // end login ------------------------
	
	
	// 로그인 후에 userSeq 저장용
	// 1 / 3 일 정훈 수정분         -------------------------------------->
	public UserDto login_Seqno(String userid, String userpw) {
		
		int seqno = 0;
		int adminSeq = 0;
		
		UserDto dtos = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select userSeq , adminSeq from tableUser where userId = '" + userid +"' and userPw =  '" + userpw + "' and outdate is null";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				seqno = resultSet.getInt("userSeq");
				adminSeq = resultSet.getInt("adminSeq");
				
				dtos = new UserDto(seqno, adminSeq);  
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	} // end login_Seqno ----------------
	
	
	// 회원정보 수정을 위한 메소드
	public int userModify(int userseq, String id, String pw, String name, String telno, String nick, String quiz, String hint){
		
		Connection connection = null;
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			connection = dataSource.getConnection();
			String query = "update tableUser set userId = ?, userPw = ?, userName = ?, userTelno = ?, userNickName = ?, "
					+ "userHintQuiz = ?, userHint = ? where userSeq = ?";
			System.out.println("userUpdate");
			ps = connection.prepareStatement(query);
			
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setString(4, telno);
			ps.setString(5, nick);
			ps.setString(6, quiz);
			ps.setString(7, hint);
			ps.setInt(8, userseq);
			ps.executeUpdate();
			cnt = 1;	
		}catch(Exception e) {
			e.printStackTrace();
			cnt = 0;	
		}finally {
			try {
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt;
	}// end userModify---------------------
	
	
	// 회원 탈퇴를 위한 메소드
	public boolean userDrop(int sseq){
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = dataSource.getConnection();
			String query = "update tableUser set outDate = now() where userSeq = ? ";
		
			ps = connection.prepareStatement(query);
			
	
			ps.setInt(1, sseq);
			ps.executeUpdate();
			result = true;	
		}catch(Exception e) {
			e.printStackTrace();
			result = false;	
		}finally {
			try {
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	// 비밀번호찾기에서 입력한 정보 맞는지 체크
	public boolean userPwCheck(String userid, String username, String usertelno, String userhintquiz, String userhint) {
		
		boolean Result = false;
		
		String name = "";
		String telno = "";
		String hintquiz = "";
		String hint = "";
	
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select userName, userTelno, userHintQuiz, userHint  from tableUser "
						+ "where userId = '" + userid + "'" + "and userName = '" + username + "' "
						+ "and userTelno =  '" + usertelno + "'" + "and userHintQuiz =  '" + userhintquiz + "' "
						+ "and userHint =  '" + userhint + "'" + "and outdate is null";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				name = resultSet.getString("userName");
				telno = resultSet.getString("userTelno");
				hintquiz = resultSet.getString("userHintQuiz");
				hint = resultSet.getString("userHint");
			}
			
			if(name.length() != 0 || telno.length() != 0 || hintquiz.length() != 0 || hint.length() != 0 ) Result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return Result;
	} // end userPwCheck ------------------------
	
	
	// 비밀번호찾기에서 비밀번호 저장하기
	public String userPwSave(String userid, String username, String usertelno, String userhintquiz, String userhint) {
		
		String pw = "";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select userPw from tableUser "
						+ "where userId = '" + userid + "'" + "and userName = '" + username + "' "
						+ "and userTelno =  '" + usertelno + "'" + "and userHintQuiz =  '" + userhintquiz + "' "
						+ "and userHint =  '" + userhint + "'" + "and outdate is null";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				pw = resultSet.getString("userPw");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return pw;
	} // end userPwSave ----------------
	
	
	
	// 아이디 찾기에서 입력한 정보 맞는지 체크
	public boolean userIdCheck(String username, String usertelno) {
		
		boolean Result = false;
		
		String name = "";
		String telno = "";
	
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select userName, userTelno from tableUser "
						+ "where userName = '" + username + "' and userTelno =  '" + usertelno + "'" 
						+ "and outdate is null";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				name = resultSet.getString("userName");
				telno = resultSet.getString("userTelno");
				
			}
			
			if(name.length() != 0 || telno.length() != 0) Result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return Result;
	} // end userIdCheck ------------------------
	
	
	// 아아디 찾기에서 아이디 저장하기
	public String userIdSave(String username, String usertelno) {
		
		String id = "";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select userId from tableUser "
						+ "where userName = '" + username + "' "
						+ "and userTelno =  '" + usertelno + "'" +  "and outdate is null";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				id = resultSet.getString("userId");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return id;
	} // end userIdSave ----------------
	
	
	// 회원정보 수정을 하기 위한 비밀번호 체크
	public boolean userInfoPwCheck(int userseq, String userpw) {
		
		boolean Result = false;
		
		String pw = "";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "select userPw from tableUser where userSeq = '" + userseq +"' and userPw =  '" + userpw + "' and outdate is null";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				pw = resultSet.getString("userPw");
			}
			
			if(pw.length() != 0) Result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null)resultSet.close();
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return Result;
	} // end userInfoPwCheck ------------------------
	
	
	// 회원정보 관리는 위한 리스트 불러오기
	public ArrayList<UserDto> listPage(int seqno, int page){
		
		ArrayList<UserDto> dtos = new ArrayList<UserDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = 
					"select *"
					+ " from tableUser " +
					" limit " + (page*10) + ", " + 10;
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int userSeq = rs.getInt("userSeq");
				String userId = rs.getString("userId");
				Timestamp joinDate = rs.getTimestamp("joinDate");			
				Timestamp outDate = rs.getTimestamp("outDate");
				
				UserDto dto = new UserDto(userSeq, userId, joinDate, outDate);
				dtos.add(dto);
			}		

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
			return dtos;
		}// end listPage ------------------------
	
	// 회원정보 관리는 위한 리스트 불러오기
	public ArrayList<UserDto> listPageQuery(int seqno, int page, String select, String query){
		
		ArrayList<UserDto> dtos = new ArrayList<UserDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String StrQuery = "";
		
		try {
			connection = dataSource.getConnection();

			StrQuery =  "select * "
						+ " from tableUser where " + select + " like '%"+ query + "%'" +
						" limit " + (page*10) + ", " + 10 ;

			ps = connection.prepareStatement(StrQuery);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int userSeq = rs.getInt("userSeq");
				String userId = rs.getString("userId");
				Timestamp joinDate = rs.getTimestamp("joinDate");			
				Timestamp outDate = rs.getTimestamp("outDate");
				
				UserDto dto = new UserDto(userSeq, userId, joinDate, outDate);
				dtos.add(dto);
			}		

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dtos;
	}// end listPageQuery ------------------------
	
	
	// 회원정보 관리는 위한 리스트 불러오기
	public int UserQueryCount(String select, String query){
		
		int result = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//and u.userSeq = p.userSeq 
		
		try {
			connection = dataSource.getConnection();
			String StrQuery = "select count(*) from tableUser where "+ select + " like '%"+ query + "%'";
				
			ps = connection.prepareStatement(StrQuery);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);	
			}		
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	// 회원정보 관리는 위한 리스트 불러오기
	public int UserCount(){
		
		int result = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select count(*) from tableUser";
				
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);	
			}		
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	// 회원정보관리 회원정보 불러오기
	public UserDto userInfoContent(int userSeq){
		
		UserDto dtos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			String query = "select userSeq, userId, userPw, userName, userTelno, userNickName, userHintQuiz, userHint, joinDate, outDate, adminSeq "
					+ "from tableUser where userSeq = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, userSeq);
			
			rs = ps.executeQuery();
			
			System.out.println("UserDao userInfo SQL :"+userSeq);
			
			if(rs.next()) {
				String userId = rs.getString("userId");
				String userPw = rs.getString("userPw");
				String userName = rs.getString("userName");
				String userTelno = rs.getString("userTelno");
				String userNickName = rs.getString("userNickName");
				String userHintQuiz = rs.getString("userHintQuiz");
				String userHint = rs.getString("userHint");					
				Timestamp joinDate = rs.getTimestamp("joinDate");
				Timestamp outDate = rs.getTimestamp("outDate");
				int adminSeq = rs.getInt("adminSeq");
				int userSeqq = rs.getInt("userSeq");
				
				dtos = new UserDto(userSeqq, userId, userPw, userName, userTelno, userNickName, userHintQuiz, userHint, joinDate, outDate, adminSeq);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) rs.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	
	public int userModifyUpdate(int userseq, String id, String pw, String name, String telno, String nick, String quiz, String hint){
		
		Connection connection = null;
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			connection = dataSource.getConnection();
			String query = "update tableUser set userId = ?, userPw = ?, userName = ?, userTelno = ?, userNickName = ?, "
					+ "userHintQuiz = ?, userHint = ? where userSeq = ?";
			System.out.println("userUpdate");
			ps = connection.prepareStatement(query);
			
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, name);
			ps.setString(4, telno);
			ps.setString(5, nick);
			ps.setString(6, quiz);
			ps.setString(7, hint);
			ps.setInt(8, userseq);
			ps.executeUpdate();
			cnt = 1;	
		}catch(Exception e) {
			e.printStackTrace();
			cnt = 0;	
		}finally {
			try {
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt;
		
	}// end userModify---------------------
	
	
	//관리자 회원 탈퇴를 위한 메소드
	public boolean userManagementDrop(int sseq){
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = dataSource.getConnection();
			String query = "update tableUser set outDate = now() where userSeq = ? ";
		
			ps = connection.prepareStatement(query);
			
	
			ps.setInt(1, sseq);
			System.out.println(sseq + "번 탈퇴 ");
			ps.executeUpdate();
			result = true;	
		}catch(Exception e) {
			e.printStackTrace();
			result = false;	
		}finally {
			try {
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
		//관리자 회원탈퇴한 회원 복원을 위한 메소드
		public boolean UserManagementDropCancleCommand(int sseq){
			
			boolean result = false;
			Connection connection = null;
			PreparedStatement ps = null;
			try {
				connection = dataSource.getConnection();
				String query = "update tableUser set outDate = ? where userSeq = ? ";
			
				ps = connection.prepareStatement(query);
				
		
				ps.setNull(1, Types.TIMESTAMP);
				ps.setInt(2, sseq);
				ps.executeUpdate();
				result = true;	
			}catch(Exception e) {
				e.printStackTrace();
				result = false;	
			}finally {
				try {
					if(ps != null) ps.close();
					if(connection != null) connection.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			return result;
		}
		
		
	
		//관리자 회원탈퇴한 관리자 부여을 위한 메소드
		public boolean userManagementAuthorization(int sseq){
			
			boolean result = false;
			Connection connection = null;
			PreparedStatement ps = null;
			try {
				connection = dataSource.getConnection();
				String query = "update tableUser set adminSeq = 1 where userSeq = ? ";
			
				ps = connection.prepareStatement(query);
				
		
			
				ps.setInt(1, sseq);
				ps.executeUpdate();
				result = true;	
			}catch(Exception e) {
				e.printStackTrace();
				result = false;	
			}finally {
				try {
					if(ps != null) ps.close();
					if(connection != null) connection.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			return result;
		}
		
		
		//관리자 권한 부여 취소
	 public boolean userManagementAuthorizationCancel(int sseq){
			
			boolean result = false;
			Connection connection = null;
			PreparedStatement ps = null;
			try {
				connection = dataSource.getConnection();
				String query = "update tableUser set adminSeq = 0 where userSeq = ? ";
			
				ps = connection.prepareStatement(query);
				
		
			
				ps.setInt(1, sseq);
				ps.executeUpdate();
				result = true;	
			}catch(Exception e) {
				e.printStackTrace();
				result = false;	
			}finally {
				try {
					if(ps != null) ps.close();
					if(connection != null) connection.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			return result;
		}
	 
	 

}// end UserDao================ 
