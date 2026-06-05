package com.psychologistNotice.model;

import java.util.*;

public interface PsychologistNoticeDAO_interface {

	public void insert(PsychologistNoticeVO psychologistNoticeVO);
	public void update(PsychologistNoticeVO psychologistNoticeVO);
	public void delete(Integer psych_notice_id);
	public PsychologistNoticeVO findByPrimaryKey(Integer psych_notice_id);
	public List<PsychologistNoticeVO> getAll();
}
