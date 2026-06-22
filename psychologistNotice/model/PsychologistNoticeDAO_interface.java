package com.psychologistNotice.model;

import java.util.*;

public interface PsychologistNoticeDAO_interface {

	public void insert(PsychologistNoticeVO psychologistNoticeVO);
	public void update(PsychologistNoticeVO psychologistNoticeVO);
	public void delete(Integer psych_notice_id);
	public PsychologistNoticeVO findByPrimaryKey(Integer psych_notice_id);

	public List<PsychologistNoticeVO> getAll();


	// 改成（回傳 List）
	List<PsychologistNoticeVO> findByPA(Integer psych_id, Integer admin_id);

	// 新增（只有 psych_id）
	List<PsychologistNoticeVO> findByPsychId(Integer psych_id);

	// 新增（只有 admin_id）
	List<PsychologistNoticeVO> findByAdminId(Integer admin_id);

	// 新增（給下拉選單用）
	List<Integer> getDistinctPsychIds();
	List<Integer> getDistinctAdminIds();


}
