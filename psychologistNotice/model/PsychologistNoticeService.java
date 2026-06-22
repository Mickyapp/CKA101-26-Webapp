package com.psychologistNotice.model;

import java.util.List;
import java.sql.Timestamp;
public class PsychologistNoticeService {

	private PsychologistNoticeDAO_interface dao;
	
	public PsychologistNoticeService() {
		dao = new PsychologistNoticeJDBCDAO();
	}
	
	public PsychologistNoticeVO addPsychologistNotice(Integer psych_id,Integer admin_id,String notice_content,Integer notice_type,java.sql.Timestamp created_at,Boolean is_read) {
		
		PsychologistNoticeVO psychologistNoticeVO = new PsychologistNoticeVO();
		
		psychologistNoticeVO.setPsych_id(psych_id);  
		psychologistNoticeVO.setAdmin_id(admin_id);  
		psychologistNoticeVO.setNotice_content(notice_content);  
		psychologistNoticeVO.setNotice_type(notice_type);    
		psychologistNoticeVO.setCreated_at(created_at);  
		psychologistNoticeVO.setIs_read(is_read);
		
		dao.insert(psychologistNoticeVO);
		
		return psychologistNoticeVO;
		
	}
	
	public PsychologistNoticeVO updatePsychologistNotice(Integer psych_notice_id,Integer psych_id,Integer admin_id,String notice_content,Integer notice_type,java.sql.Timestamp created_at,Boolean is_read) {
		
		PsychologistNoticeVO psychologistNoticeVO = new PsychologistNoticeVO();
		
		psychologistNoticeVO.setPsych_notice_id(psych_notice_id);
		psychologistNoticeVO.setPsych_id(psych_id);  
		psychologistNoticeVO.setAdmin_id(admin_id);  
		psychologistNoticeVO.setNotice_content(notice_content);  
		psychologistNoticeVO.setNotice_type(notice_type);    
		psychologistNoticeVO.setCreated_at(created_at);  
		psychologistNoticeVO.setIs_read(is_read);
		
		dao.update(psychologistNoticeVO);
		
		return psychologistNoticeVO;
		
	}
	
	public void deletePsychologistNotice(Integer psych_notice_id) {
		dao.delete(psych_notice_id);
	}
	
	public PsychologistNoticeVO getOnePsychologistNotice(Integer psych_notice_id) {
		return dao.findByPrimaryKey(psych_notice_id);
	}
	
	
	public List<PsychologistNoticeVO> getAll(){
		return dao.getAll();
	}
	
	

	// 改成（回傳 List）
	public List<PsychologistNoticeVO> getTwoPsychologistNotice(Integer psych_id, Integer admin_id) {
	    return dao.findByPA(psych_id, admin_id);
	}

	// 新增
	public List<PsychologistNoticeVO> getByPsychId(Integer psych_id) {
	    return dao.findByPsychId(psych_id);
	}

	public List<PsychologistNoticeVO> getByAdminId(Integer admin_id) {
	    return dao.findByAdminId(admin_id);
	}

	
	
	public List<Integer> getDistinctPsychIds() {
	    PsychologistNoticeDAO_interface dao = new PsychologistNoticeJDBCDAO();
	    return dao.getDistinctPsychIds();
	}

	public List<Integer> getDistinctAdminIds() {
	    PsychologistNoticeDAO_interface dao = new PsychologistNoticeJDBCDAO();
	    return dao.getDistinctAdminIds();
	}
	
	
}
