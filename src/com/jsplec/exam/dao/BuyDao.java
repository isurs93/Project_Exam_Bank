package com.jsplec.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jsplec.exam.dto.BuyDto;

public class BuyDao {
	
	DataSource dataSource;
	
	public BuyDao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/exambank");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String Load_WrongSheet(int buySeq) {
		
		String result = "";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			// 나중에 조건 쿼리 넣을것!!!
			String query = "select wrongSheet from tableBuy "
					+ "where buySeq = ?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, buySeq);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				result = resultSet.getString("wrongSheet");										
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
	
	public void buyinfoinsert(int userSeq, int subjectSeq, int workbookInfoSeq, String paymentType, String purchasedprice) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into tableBuy(userSeq, subjectSeq, workbookInfoSeq ,buyDate, endDate, paymentType, purchasedprice) "
					+ "values(?,?,?,now(),Date_Add(now(), interval 3 MONTH),?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, userSeq);
			preparedStatement.setInt(2, subjectSeq);
			preparedStatement.setInt(3, workbookInfoSeq);
			preparedStatement.setString(4, paymentType);
			preparedStatement.setString(5, purchasedprice);
			
			preparedStatement.executeUpdate();
			
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
	
	public ArrayList<BuyDto> buyinfo(int WorkbookInfoSeq){
		ArrayList<BuyDto> dtos = new ArrayList<BuyDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select w.workbookInfoSeq, s.subjectSeq ,w.workbookNo, w.workbookPrice, s.subjectName from tableWorkbookInfo as w, tableSubject as s where w.workbookInfoSeq = "+WorkbookInfoSeq +" and w.subjectSeq=s.subjectSeq";
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				int subjectSeq = rs.getInt("subjectSeq");
				int workbookInfoSeq = rs.getInt("workbookInfoSeq");
				String subjectName = rs.getString("subjectName");
				String workbookNo = rs.getString("workbookNo");
				String workbookPrice = rs.getString("workbookPrice");
				
				BuyDto dto = new BuyDto(subjectSeq, workbookInfoSeq,workbookNo,workbookPrice,subjectName);
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
	
	public ArrayList<BuyDto> Load_WrongList(int userSeqno){
		ArrayList<BuyDto> dtos = new ArrayList<BuyDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select b.buySeq, b.subjectSeq, b.workbookInfoSeq, b.endDate, b.examDate, b.wrongSheet, wb.workbookNo, s.subjectName, b.score  " 
					+ "from tableBuy b, tableWorkbookInfo wb, tableSubject s "
					+ "where b.userSeq = ? and wb.workbookInfoSeq = b.workbookInfoSeq and b.subjectSeq = s.subjectSeq and examDate is not null";
			
			ps = connection.prepareStatement(query);
			ps.setInt(1, userSeqno);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				int buySeq = rs.getInt(1);
				int subjectSeq = rs.getInt(2);
				int workbookInfoSeq = rs.getInt(3);
				Timestamp endDate = rs.getTimestamp(4);
				Timestamp examDate = rs.getTimestamp(5);
				String wrongSheet = rs.getString(6);
				String workbookNo = rs.getString(7);
				String subjectName = rs.getString(8);
				String score = rs.getString(9);
				
				BuyDto dto = new BuyDto(buySeq, subjectSeq, workbookInfoSeq, endDate, examDate, wrongSheet, workbookNo, subjectName, score);
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
	
	public ArrayList<BuyDto> Buyinfolist(int userseq){
		ArrayList<BuyDto> dtos = new ArrayList<BuyDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select s.subjectName, w.workbookNo, b.buyDate, b.endDate, b.subjectSeq, b.buySeq, b.userSeq from tableBuy as b, tableWorkbookInfo as w, tableSubject as s "
					+" where userSeq = "+userseq+" and b.subjectSeq=s.subjectSeq and b.workbookInfoSeq=w.workbookInfoSeq and b.refundDate is null";
			
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				String subjectName = rs.getString("subjectName");
				String workbookNo = rs.getString("workbookNo");
				Timestamp buyDate = rs.getTimestamp("buyDate");
				Timestamp endDate = rs.getTimestamp("endDate");
				int subjectSeq = rs.getInt("subjectSeq");
				int buySeq = rs.getInt("buySeq");
				int userSeq = rs.getInt("userSeq");
				
				BuyDto dto = new BuyDto(buySeq, subjectSeq, buyDate, endDate, workbookNo, subjectName, userSeq);
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
	
	// 2020.01.10 psj add group by and delete buySeq, userSeq
	public ArrayList<BuyDto> Load_Buyinfo(int userseq){
		ArrayList<BuyDto> dtos = new ArrayList<BuyDto>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = 
					" select distinct(s.subjectName), w.workbookNo, b.buyDate, b.endDate, b.subjectSeq from tableBuy as b, tableWorkbookInfo as w, tableSubject as s "
					+" where userSeq = "+userseq+" and b.subjectSeq=s.subjectSeq and b.workbookInfoSeq=w.workbookInfoSeq and b.refundDate is null "
							+ " group by s.subjectName, w.workbookNo, b.buyDate, b.endDate, b.subjectSeq";
//			"select s.subjectName, w.workbookNo, b.buyDate, b.endDate, b.subjectSeq, b.buySeq from tableBuy as b, tableWorkbookInfo as w, tableSubject as s " + 
//			"where userSeq = 29 " + 
//			"and b.subjectSeq=s.subjectSeq and b.workbookInfoSeq=w.workbookInfoSeq and b.refundDate is null group by s.subjectName, w.workbookNo";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				String subjectName = rs.getString("subjectName");
				String workbookNo = rs.getString("workbookNo");
				Timestamp buyDate = rs.getTimestamp("buyDate");
				Timestamp endDate = rs.getTimestamp("endDate");
				int subjectSeq = rs.getInt("subjectSeq");
				
				BuyDto dto = new BuyDto(buyDate, endDate, subjectName, workbookNo, subjectSeq);
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
	
	public int Load_BuySeq(int userSeq, int subjectSeq, int workbookInfoSeq){

		int result = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select buySeq from tablebuy " + 
					" where userSeq = ? and subjectSeq = ? and workbookInfoSeq = ? " +
					" order by buySeq desc limit 0,1 ";
			
			ps = connection.prepareStatement(query);
			ps.setInt(1, userSeq);
			ps.setInt(2, subjectSeq);
			ps.setInt(3, workbookInfoSeq);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				result = rs.getInt("buySeq");
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
	public void examrefund(int buySeq) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update tableBuy set refundDate = now() where buySeq = ? ";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, buySeq);
			
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
	
	
	
	
	// 이미 구매 했는지 체크
	public int BuyInfoCheck(int userSeq, int subjectSeq, int workbookInfoSeq) {
		
		int cnt = 0; 
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			
			String query = "select count(*) from tableBuy "
					+ "where userSeq = " + userSeq +" and subjectSeq = " + subjectSeq + " and "
							+ "workbookInfoSeq = " + workbookInfoSeq;
				
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);	
			}		
	
		}catch(Exception e) {
			cnt = 0;
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
		return cnt;
	}
	
}
