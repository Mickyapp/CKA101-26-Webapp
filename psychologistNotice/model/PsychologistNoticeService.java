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
	
	
	
	
}
