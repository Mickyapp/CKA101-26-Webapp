package com.psychologistNotice.model;

import java.sql.Timestamp;

public class PsychologistNoticeVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer psych_notice_id;
	private Integer psych_id;
	private Integer admin_id;
	private String notice_content;
	private Integer notice_type;
	private Timestamp created_at;
	private Boolean is_read;
	
	
	
	
	public Integer getPsych_notice_id() {
		return psych_notice_id;
	}
	public void setPsych_notice_id(Integer psych_notice_id) {
		this.psych_notice_id = psych_notice_id;
	}
	public Integer getPsych_id() {
		return psych_id;
	}
	public void setPsych_id(Integer psych_id) {
		this.psych_id = psych_id;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public Integer getNotice_type() {
		return notice_type;
	}
	
	public String getNotice_typemsg() {
		String notice_typemsg="";
		Integer n=this.getNotice_type();
		switch(n) {
		case 0:
			notice_typemsg="文章審核通知";break;
		case 1:
			notice_typemsg="預約通知";break;
		case 2:
			notice_typemsg="課程通知";break;
		default:
			notice_typemsg="未定義通知";break;
			
		}return notice_typemsg;
	}
	
	
	public void setNotice_type(Integer notice_type) {
		this.notice_type = notice_type;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Boolean getIs_read() {
		return is_read;
	}
	public void setIs_read(Boolean is_read) {
		this.is_read = is_read;
	}
	
	
}
