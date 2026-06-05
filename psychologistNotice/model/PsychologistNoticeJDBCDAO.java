package com.psychologistNotice.model;

import java.util.*;
import java.sql.*;

public class PsychologistNoticeJDBCDAO implements PsychologistNoticeDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test_db2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO psychologist_notice(psych_id,admin_id,notice_content,notice_type,created_at,is_read)"
			+ " VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT psych_notice_id,psych_id,admin_id,notice_content,notice_type,created_at,is_read "
			+ " FROM psychologist_notice";
	private static final String GET_ONE_STMT =
			"SELECT psych_notice_id,psych_id,admin_id,notice_content,notice_type,created_at,is_read "
			+ "FROM psychologist_notice where psych_notice_id=?";//哪個種類
	private static final String DELETE =
			"DELETE FROM psychologist_notice where psych_notice_id = ?";
	private static final String UPDATE =
			"UPDATE psychologist_notice "
			+ " set psych_id=?,admin_id=?,notice_content=?,notice_type=?,created_at=?,is_read=?"
			+ " where psych_notice_id=?";
	
//=======================================================================================================================================
	
	@Override
	public void insert(PsychologistNoticeVO psychologistNoticeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, psychologistNoticeVO.getPsych_id());
			pstmt.setInt(2, psychologistNoticeVO.getAdmin_id());
			pstmt.setString(3, psychologistNoticeVO.getNotice_content());
			pstmt.setInt(4, psychologistNoticeVO.getNotice_type());
			pstmt.setTimestamp(5, psychologistNoticeVO.getCreated_at());
			pstmt.setBoolean(6, psychologistNoticeVO.getIs_read());
					
			pstmt.executeUpdate();			
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(PsychologistNoticeVO psychologistNoticeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setInt(1, psychologistNoticeVO.getPsych_id());
			pstmt.setInt(2, psychologistNoticeVO.getAdmin_id());
			pstmt.setString(3, psychologistNoticeVO.getNotice_content());
			pstmt.setInt(4, psychologistNoticeVO.getNotice_type());
			pstmt.setTimestamp(5, psychologistNoticeVO.getCreated_at());
			pstmt.setBoolean(6, psychologistNoticeVO.getIs_read());
			pstmt.setInt(7, psychologistNoticeVO.getPsych_notice_id());					
			
			pstmt.executeUpdate();			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void delete(Integer psych_notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,psych_notice_id);
			
			pstmt.executeUpdate();
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}
	
	@Override
	public PsychologistNoticeVO findByPrimaryKey(Integer psych_notice_id) {
		PsychologistNoticeVO psychologistNoticeVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,psych_notice_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				psychologistNoticeVO = new PsychologistNoticeVO();
				psychologistNoticeVO.setPsych_notice_id(rs.getInt("psych_notice_id"));
				psychologistNoticeVO.setPsych_id(rs.getInt("psych_id"));  
				psychologistNoticeVO.setAdmin_id(rs.getInt("admin_id"));  
				psychologistNoticeVO.setNotice_content(rs.getString("notice_content"));  
				psychologistNoticeVO.setNotice_type(rs.getInt("notice_type"));    
				psychologistNoticeVO.setCreated_at(rs.getTimestamp("created_at"));  
				psychologistNoticeVO.setIs_read(rs.getBoolean("is_read"));  
			}
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return psychologistNoticeVO;
	}
	@Override
	public List<PsychologistNoticeVO> getAll(){
		List<PsychologistNoticeVO> list = new ArrayList<PsychologistNoticeVO>();
		PsychologistNoticeVO psychologistNoticeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				psychologistNoticeVO = new PsychologistNoticeVO();
				psychologistNoticeVO.setPsych_notice_id(rs.getInt("psych_notice_id"));
				psychologistNoticeVO.setPsych_id(rs.getInt("psych_id"));  
				psychologistNoticeVO.setAdmin_id(rs.getInt("admin_id"));  
				psychologistNoticeVO.setNotice_content(rs.getString("notice_content"));  
				psychologistNoticeVO.setNotice_type(rs.getInt("notice_type"));    
				psychologistNoticeVO.setCreated_at(rs.getTimestamp("created_at"));  
				psychologistNoticeVO.setIs_read(rs.getBoolean("is_read"));  
			
				list.add(psychologistNoticeVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		PsychologistNoticeJDBCDAO dao = new PsychologistNoticeJDBCDAO();
		
		
		
		
		
		List<PsychologistNoticeVO> list = dao.getAll();
		for(PsychologistNoticeVO aPN : list) {
			 System.out.print(aPN.getPsych_id() + ","); 
			 System.out.print(aPN.getAdmin_id() + ","); 
			 System.out.print(aPN.getNotice_content() + ","); 
			 System.out.print(aPN.getNotice_type() + ","); 
			 System.out.print(aPN.getCreated_at() + ","); 
			 System.out.print(aPN.getIs_read() + ","); 
			 System.out.print(aPN.getPsych_notice_id() );
			 System.out.println();
		}
	}
	
	
	
	
	
	
	
	
	
	
}
