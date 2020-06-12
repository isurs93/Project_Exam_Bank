package com.jsplec.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jsplec.exam.dto.workbookInfoDto;

public class workbookInfoDao {

	DataSource dataSource;
	
	public workbookInfoDao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/exambank");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public workbookInfoDto Load_WhereSeqData(int InfoSeq) {
		
		workbookInfoDto result = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// 수정 필요!!! 구매 목록과 연동해야 한다.
			String query = "select * from tableWorkBookInfo where workbookInfoSeq = ?"; // 나중에 조건 쿼리 넣을것!!!
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, InfoSeq);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				int workbookInfoSeq = resultSet.getInt("workbookInfoSeq");
				int subjectSeq = resultSet.getInt("subjectSeq");
				String workbookNo = resultSet.getString("workbookNo");
				String workbookPrice = resultSet.getString("workbookPrice");		
				
				result = new workbookInfoDto(workbookInfoSeq, subjectSeq, workbookNo, workbookPrice);
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
	
	public int Load_InfoSeq(int subjectSeq, String workbookNo) {
		
		int result = 0;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// 수정 필요!!! 구매 목록과 연동해야 한다.
			String query = "select workbookInfoSeq from tableWorkBookInfo where subjectSeq = ? and workbookNo = ?"; // 나중에 조건 쿼리 넣을것!!!
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, subjectSeq);
			preparedStatement.setString(2, workbookNo);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				result = resultSet.getInt("workbookInfoSeq");	

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
	
}
