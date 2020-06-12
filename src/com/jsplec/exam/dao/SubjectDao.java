package com.jsplec.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jsplec.exam.dto.ExamProb;
import com.jsplec.exam.dto.SubjectDto;


public class SubjectDao {

	DataSource dataSource;
	
	public SubjectDao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/exambank");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SubjectDto> AllData() {
		
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// 수정 필요!!! 구매 목록과 연동해야 한다.
			String query = "select * from tableWorkBook where workbookDel is null" ; // 나중에 조건 쿼리 넣을것!!!
			preparedStatement = connection.prepareStatement(query);
			//preparedStatement.setString(1, "%" + queryContent + "%");
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int workbookSeq = resultSet.getInt("workbookSeq");
				String workbookNo = resultSet.getString("workbookNo");
				String workbookExam = resultSet.getString("workbookExam");
				String workbookAnswer = resultSet.getString("workbookAnswer");
				String workbookReview = resultSet.getString("workbookReview");			
				
				SubjectDto dto = new SubjectDto(workbookSeq, workbookNo, workbookExam, workbookAnswer, workbookReview);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<String> Load_Answer(int subjectSeq, String str) {
		
		ArrayList<String> dtos = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// 2020.01.10 Modify by psj add is null 			
			String query = "select workbookAnswer from tableWorkBook where subjectSeq = ? and workbookNo = ? and workbookDel is null"; // 나중에 조건 쿼리 넣을것!!!
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, subjectSeq);
			preparedStatement.setString(2, str);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				String workbookAnswer = resultSet.getString("workbookAnswer");						
				dtos.add(workbookAnswer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<SubjectDto> Load_Exam(int subjectSeq, String str) {
		
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// 2020.01.10 Modify by psj add is null
			String query = "select * from tableWorkBook where subjectSeq = ? and workbookNo = ? and workbookDel is null"; // 나중에 조건 쿼리 넣을것!!!
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, subjectSeq);
			preparedStatement.setString(2, str);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int workbookSeq = resultSet.getInt("workbookSeq");
				String workbookNo = resultSet.getString("workbookNo");
				String workbookExam = resultSet.getString("workbookExam");
				String workbookAnswer = resultSet.getString("workbookAnswer");
				String workbookReview = resultSet.getString("workbookReview");			
				
				SubjectDto dto = new SubjectDto(workbookSeq, workbookNo, workbookExam, workbookAnswer, workbookReview);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public ArrayList<String> Preview(int subjectSeq, String str) {
		
		ArrayList<String> dtos = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// 나중에 조건 쿼리 넣을것!!!
			String query = "select workbookExam from tableWorkBook "
					+ "where subjectSeq = ? and workbookNo = ? limit 0, 3"; 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, subjectSeq);
			preparedStatement.setString(2, str);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				String workbookExam = resultSet.getString("workbookExam");						
				dtos.add(workbookExam);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public boolean Submit_Answer(int userSeq, int subjectSeq, int workbookInfoSeq, 
			Timestamp buyDate, Timestamp endDate,
			String examTime, String answerSheet, String score, String paymentType) {
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into "
					+ "tableBuy(userSeq, subjectSeq, workbookInfoSeq, buyDate, endDate, examDate, "
					+ "examTime, answerSheet, score, paymentType) "
					+ "values(?, ?, ?, ?, ?, now(), ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userSeq);
			preparedStatement.setInt(2, subjectSeq);
			preparedStatement.setInt(3, workbookInfoSeq);
			preparedStatement.setTimestamp(4, buyDate);
			preparedStatement.setTimestamp(5, endDate);
			preparedStatement.setString(6, examTime);
			preparedStatement.setString(7, answerSheet);
			preparedStatement.setString(8, score);
			preparedStatement.setString(9, paymentType);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				result = true;
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return result;
	}
	
	public int Load_workbookInfoSeq(int subjectSeq, String workbookNo){
		
		int result = 0;		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			// 나중에 조건 쿼리 넣을것!!!
			String query = "select workbookInfoSeq from tableWorkbookInfo "
					+ "where subjectSeq = ? and workbookNo = ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, subjectSeq);
			preparedStatement.setString(2, workbookNo);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				result = resultSet.getInt(1);																		
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean Submit_Answer_Test(String examTime, String answerSheet, String wrongSheet, String Score, String paymentType) {
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into "
					+ "tableBuy(userSeq, subjectSeq, workbookInfoSeq, buyDate, endDate, examDate, "
					+ "examTime, answerSheet, wrongSheet, score, paymentType) "
					+ "values(?, ?, ?, now(), (SELECT DATE_ADD(NOW(), INTERVAL 3 month)), now(), ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 1);
			preparedStatement.setInt(2, 1);
			preparedStatement.setInt(3, 1);
			preparedStatement.setString(4, examTime);
			preparedStatement.setString(5, answerSheet);
			preparedStatement.setString(6, wrongSheet);
			preparedStatement.setString(7, Score);
			preparedStatement.setString(8, paymentType);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				result = true;
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return result;
	}
	
	public boolean Submit_Answer(int userSeq, int subjectSeq, int worbookInfoSeq, 
			String examTime, String answerSheet, String wrongSheet, String Score) {
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = 
			"insert into " +
			" tableBuy(userSeq, subjectSeq, workbookInfoSeq, "+
			" buyDate, endDate, examDate, " +
			" examTime, answerSheet, wrongSheet, score, paymentType, purchasedprice)"+
			" values(?, ?, ?, " + 
			" (select buyDate from tableBuy b where b.userSeq = ? and b.subjectSeq = ? and b.workbookInfoSeq = ? and b.endDate > now() limit 0,1), "+
			" (select endDate from tableBuy b where b.userSeq = ? and b.subjectSeq = ? and b.workbookInfoSeq = ? and b.endDate > now() limit 0,1), "+ 
			" now(), " + 
			" ?, ?, ?, ?, " + 
			" (select paymentType from tableBuy b where b.userSeq = ? and b.subjectSeq = ? and b.workbookInfoSeq = ? and b.endDate > now() limit 0,1), " +
			" (select purchasedprice from tableBuy b where b.userSeq = ? and b.subjectSeq = ? and b.workbookInfoSeq = ? and b.endDate > now() limit 0,1)) ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userSeq);
			preparedStatement.setInt(2, subjectSeq);
			preparedStatement.setInt(3, worbookInfoSeq);
			
			preparedStatement.setInt(4, userSeq);
			preparedStatement.setInt(5, subjectSeq);
			preparedStatement.setInt(6, worbookInfoSeq);
			
			preparedStatement.setInt(7, userSeq);
			preparedStatement.setInt(8, subjectSeq);
			preparedStatement.setInt(9, worbookInfoSeq);
			
			preparedStatement.setString(10, examTime);
			preparedStatement.setString(11, answerSheet);
			preparedStatement.setString(12, wrongSheet);
			preparedStatement.setString(13, Score);
			
			preparedStatement.setInt(14, userSeq);
			preparedStatement.setInt(15, subjectSeq);
			preparedStatement.setInt(16, worbookInfoSeq);
			
			preparedStatement.setInt(17, userSeq);
			preparedStatement.setInt(18, subjectSeq);
			preparedStatement.setInt(19, worbookInfoSeq);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				result = true;
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
		return result;
	}
	
	public ArrayList<String> Load_WrongProblem(int subjectSeq, String workbookNo, String[] wrongSheet){
		
		ArrayList<String> list = new ArrayList<String>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			for(int index=0; index<wrongSheet.length; index++) {
				
				int wrongNo = Integer.parseInt(wrongSheet[index])-1;
				// 나중에 조건 쿼리 넣을것!!!
				String query = "select workbookExam from tableWorkbook "
						+ "where subjectSeq = ? and workbookNo = ? "
						+ "limit ?,1 ";
	
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, subjectSeq);
				preparedStatement.setString(2, workbookNo);
				preparedStatement.setInt(3, wrongNo);
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					String str = resultSet.getString("workbookExam");								
					list.add(str);								
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<SubjectDto> Load_WrongProblemAndCommentary(int subjectSeq, String workbookNo, String[] wrongSheet){
		
		ArrayList<SubjectDto> list = new ArrayList<SubjectDto>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			for(int index=0; index<wrongSheet.length; index++) {
				
				int wrongNo = Integer.parseInt(wrongSheet[index])-1;
				// 나중에 조건 쿼리 넣을것!!!
				String query = "select workbookExam, workbookAnswer, workbookReview from tableWorkbook "
						+ "where subjectSeq = ? and workbookNo = ?"
						+ "limit ?,1 ";
	
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, subjectSeq);
				preparedStatement.setString(2, workbookNo);
				preparedStatement.setInt(3, wrongNo);
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					String workbookExam = resultSet.getString("workbookExam");								
					String workbookAnswer = resultSet.getString("workbookAnswer");								
					String workbookReview = resultSet.getString("workbookReview");		
					
					SubjectDto dto = new SubjectDto(workbookExam, workbookAnswer, workbookReview);					
					list.add(dto);								
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<SubjectDto> Load_SubjectName() {
		
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from tableSubject"; // 나중에 조건 쿼리 넣을것!!!
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int subjectSeq = resultSet.getInt("subjectSeq");
				String subjectName = resultSet.getString("subjectName");	
				
				SubjectDto dto = new SubjectDto(subjectSeq, subjectName);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public String Load_QuerySubjectName(int subjectSeq) {
		
		String result = "";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select subjectName from tableSubject where subjectSeq = ?" ; // 나중에 조건 쿼리 넣을것!!!
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, subjectSeq);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				result = resultSet.getString("subjectName");	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList<SubjectDto> list(){
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select subjectSeq, subjectName from tableSubject";
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				int subjectSeq = rs.getInt("subjectSeq");
				String subjectName = rs.getString("subjectName");
				
				SubjectDto dto = new SubjectDto(subjectSeq,subjectName);
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
	
	public ArrayList<SubjectDto> examlist(int subjectSeq){
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select workbookInfoSeq, workbookNo, workbookPrice from tableWorkbookInfo where subjectSeq = "+subjectSeq;
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				int workbookInfoSeq = rs.getInt("workbookInfoSeq");
				String workbookNo = rs.getString("workbookNo");
				String workbookPrice = rs.getString("workbookPrice");
				
				SubjectDto dto = new SubjectDto(workbookInfoSeq,workbookNo,workbookPrice);
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
	
	// 과목 관리 눌렀을 때 과목 리스트 나오게 하기
	public ArrayList<SubjectDto> subjectList() {
		
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select * from tableSubject";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int subjectSeq = resultSet.getInt("subjectSeq");
				String subjectName = resultSet.getString("subjectName");
				
				SubjectDto dto = new SubjectDto(subjectSeq, subjectName);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}// end subjectList---------------------
	
	
	// tableSubject 과목명 입력을 위한 메소드
	public int subjectNameInsert(String subjectName){
		Connection connection2 = null; 
		PreparedStatement ps2 = null;
		
		int cnt = 0;
		try {
			
			connection2 = dataSource.getConnection();
			String query = "insert into tableSubject(subjectName) values(?)";
			
			ps2 = connection2.prepareStatement(query);
			
			ps2.setString(1, subjectName);
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
	} // end subjectNameInsert ------------------
	
	
	// 과목 이름 수정을 위한 메소드
	public int subjectNameModify(int subjectSeq, String subjectName){
		
		Connection connection = null;
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			connection = dataSource.getConnection();
			String query = "update tableSubject set subjectName = ? where subjectSeq = ?";
			System.out.println("userUpdate");
			ps = connection.prepareStatement(query);
			
			ps.setString(1, subjectName);
			ps.setInt(2, subjectSeq);
			
			System.out.println(subjectName);
			System.out.println(subjectSeq);
			
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
	}// end subjectNameModify---------------------
	
	
	
	// 과목 정보 눌렀을 때 과목정보 리스트 나오게 하기
	public ArrayList<SubjectDto> subjectInfoList(int subjectseq) {
		
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select s.subjectName, w.workbookNo, w.workbookPrice, s.subjectSeq, w.workbookInfoSeq "
					+ "from tableSubject s, tableWorkbookInfo w " 
					+ "where w.subjectSeq = s.subjectSeq and s.subjectSeq= " + subjectseq;
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				String workbookNo = resultSet.getString("workbookNo");
				String workbookPrice = resultSet.getString("workbookPrice");
				String subjectName = resultSet.getString("subjectName");
				int workbookInfoSeq = resultSet.getInt("workbookInfoSeq");
				int subjectSeq = resultSet.getInt("subjectSeq");
				
				SubjectDto dto = new SubjectDto(workbookInfoSeq, workbookNo, workbookPrice, subjectName, subjectSeq);
				dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}// end subjectInfoList ------------------
	
	// 과목명 불러오기
	public SubjectDto subjectName(int subjectseq){
		
		SubjectDto dtos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			String query = "select * "
					+ "from tableSubject where subjectSeq = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, subjectseq);
			
			rs = ps.executeQuery();
			
			System.out.println("subjectDao subjectName SQL :"+subjectseq);
			
			if(rs.next()) {
				int subjectSeq = rs.getInt("subjectSeq");
				String subjectName = rs.getString("subjectName");
				
				dtos = new SubjectDto(subjectSeq, subjectName);
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
	}//end subjectName --------------------
	
	// tableWorkbookInfo 과목 회차, 가격, 과목번호 입력을 위한 메소드
		public int subjectInfoInsert( int subjectSeq, String workbookNo, String workbookPrice){
			Connection connection2 = null; 
			PreparedStatement ps2 = null;
			int result = 0;
			
			try {
				
				connection2 = dataSource.getConnection();
				String query = "insert into tableWorkbookInfo(subjectSeq, workbookNo, workbookPrice) values(?,?,?)";
				
				ps2 = connection2.prepareStatement(query);
				
				ps2.setInt(1, subjectSeq);
				ps2.setString(2, workbookNo);
				ps2.setString(3, workbookPrice);
				ps2.executeUpdate();
				result = 1;
			}catch(Exception e) {
				e.printStackTrace();
				result = 0;
			}finally {
				try {
					if(ps2 != null) ps2.close();
					if(connection2 != null) connection2.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			return result;
		} // end subjectInfoInsert ------------------
	
	// 과목 정보 불러오기
	public SubjectDto subjectInfo(int workbookInfoseq){
		
		SubjectDto dtos = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			connection = dataSource.getConnection();
			String query = "select s.subjectName , w.workbookNo, w.workbookPrice, w.workbookInfoSeq "
					+ "from tableSubject s, tableWorkbookInfo w where w.subjectSeq = s.subjectSeq "
					+ "and w.workbookInfoSeq = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, workbookInfoseq);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int workbookInfoSeq = rs.getInt("workbookInfoSeq");
				String subjectName = rs.getString("subjectName");
				String workbookNo = rs.getString("workbookNo");
				String workbookPrice = rs.getString("workbookPrice");
				
				dtos = new SubjectDto(workbookInfoSeq, workbookNo, subjectName, workbookPrice);
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
	}//end subjectInfo --------------------
	
	// 과목 이름 수정을 위한 메소드
	public int subjectInfoUpdate(int workbookInfoSeq, String workbookNo, String workbookPrice){
		
		Connection connection = null;
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			connection = dataSource.getConnection();
			String query = "update tableWorkbookInfo set workbookNo = ?, workbookPrice = ? "
					+ "where workbookInfoSeq = ?";
			ps = connection.prepareStatement(query);
			
			ps.setString(1, workbookNo);
			ps.setString(2, workbookPrice);
			ps.setInt(3, workbookInfoSeq);
			
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
	}
	
public ArrayList<SubjectDto> Load_workbookNo(){
		
		ArrayList<SubjectDto> dtos = new ArrayList<SubjectDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select subjectSeq, workbookNo from tableWorkbookInfo";
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				int subjectSeq = rs.getInt("subjectSeq");
				String workbookNo = rs.getString("workbookNo");
				
				SubjectDto asde = new SubjectDto(subjectSeq, workbookNo);
				dtos.add(asde);
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
	
	public boolean Insert_ExamProblem(int subjectSeq, String workbookNo, ArrayList<ExamProb> proList) {
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			for(int index=0; index<proList.size(); index++) {
				String query = 
						"insert into tableWorkbook(subjectSeq, workbookNo, workbookExam, workbookAnswer, workbookReview) "+
						" value(?, ?, ?, ?, ?)";

				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, subjectSeq);
				preparedStatement.setString(2, workbookNo);
				preparedStatement.setString(3, proList.get(index).getProQuestion());
				preparedStatement.setString(4, proList.get(index).getProAns());
				preparedStatement.setString(5, proList.get(index).getProComment());
				preparedStatement.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				result = true;
			} catch (Exception e2) {
				e2.printStackTrace();
				result = false;
			}
		}		
		return result;
	}
	
	public boolean Modify_ExamProblem(ArrayList<SubjectDto> ExamList) {
		
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "";
			for(int index=0; index<ExamList.size(); index++) {
				
				if(ExamList.get(index).getCheckDelete() == 1) {
					query = 
						"update tableWorkbook set workbookExam = ?, workbookAnswer = ?, workbookReview = ?, workbookDel = now() "+
						" where workbookSeq = ?";
				}
				else {
					query = 
							"update tableWorkbook set workbookExam = ?, workbookAnswer = ?, workbookReview = ? "+
							" where workbookSeq = ?";
				}
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, ExamList.get(index).getWorkbookExam());
				preparedStatement.setString(2, ExamList.get(index).getWorkbookAnswer());
				preparedStatement.setString(3, ExamList.get(index).getWorkbookReview());
				preparedStatement.setInt(4, ExamList.get(index).getWorkbookSeq());
				preparedStatement.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				result = true;
			} catch (Exception e2) {
				e2.printStackTrace();
				result = false;
			}
		}		
		return result;
	}// end subjectNameModify---------------------
	
	
}// ===============================================
