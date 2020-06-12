package com.jsplec.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jsplec.exam.dto.NoticDto;

public class NoticDao {
	
DataSource dataSource;
	
	public NoticDao() {
		try {
			Context context = new InitialContext();
			dataSource=(DataSource) context.lookup("java:comp/env/jdbc/exambank");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<NoticDto> list() {
		
		ArrayList<NoticDto> dtos = new ArrayList<NoticDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			
			String SQL = "select postSeq,title,content,userNickName,insertDate,deleteDate,hit,boardSeq, u.userSeq from tableUser u, tablePost p where u.userSeq = p.userSeq and deleteDate is null and boardSeq = 1";
				
			preparedStatement = connection.prepareStatement(SQL);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				int userSeq = rs.getInt("userSeq");
				int boardSeq = rs.getInt("boardSeq");
				int postSeq = rs.getInt("postSeq");
				int hit = rs.getInt("hit");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String userNickName = rs.getString("userNickName");
				Timestamp insertDate = rs.getTimestamp("insertDate");
				Timestamp deleteDate = rs.getTimestamp("deleteDate");

				NoticDto dto = new NoticDto(boardSeq, postSeq, userSeq, title, content, insertDate, deleteDate, hit, userNickName);
				
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	
	public void hitUpdate(int postSeq) {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				String query = "update tablePost set hit = ifnull(hit, 0) + 1 where postSeq = ? ";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, postSeq);
		
				
				preparedStatement.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(preparedStatement != null)preparedStatement.close();
					if(connection != null) connection.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		
	public void NoticeUpdate(String title, String content, int postSeq) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update tablePost set title = ?, content = ? where postSeq = ? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, content);
			preparedStatement.setInt(3, postSeq);
			
	
			System.out.println("디비 포스트에스"+postSeq);
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public void NoticeDelete(int postSeq) {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				String query = "update tablePost set deleteDate = now() where postSeq = ? ";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, postSeq);
				
				preparedStatement.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(preparedStatement != null)preparedStatement.close();
					if(connection != null) connection.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
	
	
	
	
	public NoticDto contentView(int seq) {
		
		NoticDto dtos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			
			String SQL = "select postSeq,title,content,userNickName,insertDate,deleteDate,hit,boardSeq, u.userSeq from tableUser u, tablePost p where u.userSeq = p.userSeq and p.postSeq = ? ";
				
			ps = connection.prepareStatement(SQL);
			ps.setInt(1, seq);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				int userSeq = rs.getInt("userSeq");
				int boardSeq = rs.getInt("boardSeq");
				int postSeq = rs.getInt("postSeq");
				int hit = rs.getInt("hit");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String userNickName = rs.getString("userNickName");
				System.out.println("유절닉넴"+userNickName);
				Timestamp insertDate = rs.getTimestamp("insertDate");
				Timestamp deleteDate = rs.getTimestamp("deleteDate");
				
				dtos = new NoticDto(boardSeq, postSeq, userSeq, title, content, insertDate, deleteDate, hit, userNickName);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	
	
	public NoticDto NoticeInsert(String title, String content) {
		
		NoticDto dtos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			System.out.println("타이틀 :"+title);
			System.out.println("내용 :"+content);
			connection = dataSource.getConnection();
			
			String SQL = "insert into tablePost(title,content,boardSeq,insertDate,userSeq) values(?,?,1,now(),1)";
				
			ps = connection.prepareStatement(SQL);
			ps.setString(1, title);
			ps.setString(2, content);
			//ps.setInt(3, userSeq);
			
			ps.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	
	public ArrayList<NoticDto> NoticeSearch(String select, String Query){
		
		ArrayList<NoticDto> dtos = new ArrayList<NoticDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			
			String SQL = "select postSeq,title,content,userNickName,insertDate,deleteDate,hit,boardSeq, u.userSeq from tableUser u, "
					+ "tablePost p where u.userSeq = p.userSeq and deleteDate is null and boardSeq = 1 and "+select+" like '%"+Query+"%'";
				
			preparedStatement = connection.prepareStatement(SQL);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				int userSeq = rs.getInt("userSeq");
				int boardSeq = rs.getInt("boardSeq");
				int postSeq = rs.getInt("postSeq");
				int hit = rs.getInt("hit");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String userNickName = rs.getString("userNickName");
				Timestamp insertDate = rs.getTimestamp("insertDate");
				Timestamp deleteDate = rs.getTimestamp("deleteDate");
				
				System.out.println("디바 :"+title);

				NoticDto dto = new NoticDto(boardSeq, postSeq, userSeq, title, content, insertDate, deleteDate, hit, userNickName);
				
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<NoticDto> QnaSearch(String select, String Query){
		
		ArrayList<NoticDto> dtos = new ArrayList<NoticDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			
			String SQL = "select postSeq,title,content,userNickName,insertDate,deleteDate,hit,boardSeq, u.userSeq from tableUser u, "
					+ "tablePost p where u.userSeq = p.userSeq and deleteDate is null and boardSeq = 2 and "+select+" like '%"+Query+"%'";
				
			preparedStatement = connection.prepareStatement(SQL);
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				int userSeq = rs.getInt("userSeq");
				int boardSeq = rs.getInt("boardSeq");
				int postSeq = rs.getInt("postSeq");
				int hit = rs.getInt("hit");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String userNickName = rs.getString("userNickName");
				Timestamp insertDate = rs.getTimestamp("insertDate");
				Timestamp deleteDate = rs.getTimestamp("deleteDate");
				

				NoticDto dto = new NoticDto(boardSeq, postSeq, userSeq, title, content, insertDate, deleteDate, hit, userNickName);
				
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	 
	//int userSeq 받아와야함~
	//1.9 정훈 수정~!
	public NoticDto QnaInsert(int userSeq, String title, String content) {
		
		NoticDto dtos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			System.out.println("타이틀 :"+title);
			System.out.println("내용 :"+content);
			connection = dataSource.getConnection();
			
			String SQL = "insert into tablePost(title,content,boardSeq,insertDate,userSeq) values(?,?,2,now(),?)";
				
			ps = connection.prepareStatement(SQL);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, userSeq);
			
			ps.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}


	public ArrayList<NoticDto> QnaList() {
	
		ArrayList<NoticDto> dtos = new ArrayList<NoticDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			
			String SQL = "select postSeq,title,content,userNickName,insertDate,deleteDate,hit,boardSeq, u.userSeq from tableUser u, tablePost p where u.userSeq = p.userSeq and deleteDate is null and boardSeq = 2";
				
			preparedStatement = connection.prepareStatement(SQL);
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				int userSeq = rs.getInt("userSeq");
				int boardSeq = rs.getInt("boardSeq");
				int postSeq = rs.getInt("postSeq");
				int hit = rs.getInt("hit");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String userNickName = rs.getString("userNickName");
				Timestamp insertDate = rs.getTimestamp("insertDate");
				Timestamp deleteDate = rs.getTimestamp("deleteDate");
	
				NoticDto dto = new NoticDto(boardSeq, postSeq, userSeq, title, content, insertDate, deleteDate, hit, userNickName);
				
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}


	public NoticDto QnaView(int seq) {
		
		NoticDto dtos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			
			String SQL = "select postSeq,title,content,userNickName,insertDate,deleteDate,hit,boardSeq, u.userSeq from tableUser u, tablePost p where u.userSeq = p.userSeq and p.postSeq = ? ";
				
			ps = connection.prepareStatement(SQL);
			ps.setInt(1, seq);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				
				int userSeq = rs.getInt("userSeq");
				int boardSeq = rs.getInt("boardSeq");
				int postSeq = rs.getInt("postSeq");
				int hit = rs.getInt("hit");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String userNickName = rs.getString("userNickName");
				Timestamp insertDate = rs.getTimestamp("insertDate");
				Timestamp deleteDate = rs.getTimestamp("deleteDate");
				
				dtos = new NoticDto(boardSeq, postSeq, userSeq, title, content, insertDate, deleteDate, hit, userNickName);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public void QnaUpdate(String title, String content, int postSeq) {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update tablePost set title = ?, content = ? where postSeq = ? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, content);
			preparedStatement.setInt(3, postSeq);
			
	
			System.out.println("디비 포스트에스"+postSeq);
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}	
	
	public void QnaDelete(int postSeq) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update tablePost set deleteDate = now() where postSeq = ? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, postSeq);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public int NoticeCount(){
		
		int result = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select count(*) from tablePost where boardSeq = 1 and DeleteDate is null";
				
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
	
	
	public int QnaCount(){
			
			int result = 0;
			
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				connection = dataSource.getConnection();
				String query = "select count(*) from tablePost where boardSeq = 2 and DeleteDate is null";
					
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
	
	
	public int NoticeQueryCount(String select, String query){
		
		int result = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//and u.userSeq = p.userSeq 
		
		try {
			connection = dataSource.getConnection();
			String StrQuery = "select count(*) from tablePost p, tableUser u where u.userSeq = p.userSeq and boardSeq = 1 and DeleteDate is null"
							+ " and " + select + " like '%"+ query + "%'";
				
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
	
	public int QnaQueryCount(String select, String query){
		
		int result = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//and u.userSeq = p.userSeq 
	
		try {
			connection = dataSource.getConnection();
			
			String StrQuery = "select count(*) from tablePost p, tableUser u where u.userSeq = p.userSeq and boardSeq = 2 and DeleteDate is null "
							+ " and " + select + " like '%"+ query + "%'";
				
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

	public ArrayList<NoticDto> listPage(int seqno, int page){
	
	ArrayList<NoticDto> dtos = new ArrayList<NoticDto>();
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		connection = dataSource.getConnection();
		String query = 
				"select boardSeq, postSeq, u.userSeq, title, content, insertDate, deleteDate, hit, userNickName "
				+ " from tableUser u, tablePost p where u.userSeq = p.userSeq and deleteDate is null and boardSeq = " + seqno +
				" order by postSeq desc limit " + (page*10) + ", " + 10 ;
		ps = connection.prepareStatement(query);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			int boardSeq = rs.getInt("boardSeq");
			int postSeq = rs.getInt("postSeq");
			
			int userSeq = rs.getInt("u.userSeq");
			
			String title = rs.getString("title");
			String content = rs.getString("content");
			
			Timestamp insertDate = rs.getTimestamp("insertDate");			
			Timestamp deleteDate = rs.getTimestamp("deleteDate");
			
			int hit = rs.getInt("hit");
			
			String userNickName = rs.getString("userNickName");
			
			NoticDto dto = new NoticDto(boardSeq, postSeq, userSeq, title, content, insertDate, deleteDate, hit, userNickName);
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
	}
	
	public ArrayList<NoticDto> listPageQuery(int seqno, int page, String select, String query){
		
		ArrayList<NoticDto> dtos = new ArrayList<NoticDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String StrQuery = "";
		
		try {
			connection = dataSource.getConnection();
	
			StrQuery =  "select boardSeq, postSeq, u.userSeq, title, content, insertDate, deleteDate, hit, userNickName "
						+ " from tableUser u, tablePost p where u.userSeq = p.userSeq and deleteDate is null and boardSeq = " + seqno 
						+ " and " + select + " like '%"+ query + "%'" +
						" order by postSeq desc limit " + (page*10) + ", " + 10 ;
	
			ps = connection.prepareStatement(StrQuery);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int boardSeq = rs.getInt("boardSeq");
				int postSeq = rs.getInt("postSeq");
				
				int userSeq = rs.getInt("u.userSeq");
				
				String title = rs.getString("title");
				String content = rs.getString("content");
				
				Timestamp insertDate = rs.getTimestamp("insertDate");			
				Timestamp deleteDate = rs.getTimestamp("deleteDate");
				
				int hit = rs.getInt("hit");
				
				String userNickName = rs.getString("userNickName");
				
				NoticDto dto = new NoticDto(boardSeq, postSeq, userSeq, title, content, insertDate, deleteDate, hit, userNickName);
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
	}
	
	
	
}
