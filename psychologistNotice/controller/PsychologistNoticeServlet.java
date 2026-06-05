package com.psychologistNotice.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.psychologistNotice.model.PsychologistNoticeService;
import com.psychologistNotice.model.PsychologistNoticeVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/psychologistNotice/psychologistNotice.do")
public class PsychologistNoticeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("psych_notice_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入心理師通知編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/psychologistNotice/select_psychologistNotice_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer psych_notice_id = null;
				try {
					psych_notice_id = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("心理師通知編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/psychologistNotice/select_psychologistNotice_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PsychologistNoticeService psychologistNoticeSvce = new PsychologistNoticeService();
				PsychologistNoticeVO psychologistNoticeVO = psychologistNoticeSvce.getOnePsychologistNotice(psych_notice_id);
				if (psychologistNoticeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/psychologistNotice/select_psychologistNotice_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("psychologistNoticeVO", psychologistNoticeVO); // 資料庫取出的empVO物件,存入req
				String url = "/psychologistNotice/listOnePsychologistNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer psych_notice_id = Integer.valueOf(req.getParameter("psych_notice_id"));
				
				/***************************2.開始查詢資料****************************************/
				PsychologistNoticeService psychologistNoticeSvc = new PsychologistNoticeService();
				PsychologistNoticeVO psychologistNoticeVO = psychologistNoticeSvc.getOnePsychologistNotice(psych_notice_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("psychologistNoticeVO", psychologistNoticeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/psychologistNotice/update_psychologistNotice_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer psych_notice_id = Integer.valueOf(req.getParameter("psych_notice_id").trim());
				
Integer psych_id = null;
				try {
					psych_id=Integer.valueOf(req.getParameter("psych_id").trim());
				}catch(NumberFormatException e) {
					psych_id = null;
					errorMsgs.add("心理師編號請填數字");
				}
				
			

Integer admin_id = null;
				try {
					admin_id=Integer.valueOf(req.getParameter("admin_id").trim());
				}catch(NumberFormatException e) {
					admin_id = null;
					errorMsgs.add("員工編號請填數字");
				}
				
				
				
String notice_content = req.getParameter("notice_content").trim();
				if (notice_content == null || notice_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}	
	
Integer notice_type	= null;
				try {
					notice_type = Integer.valueOf(req.getParameter("notice_type").trim());
				}catch(NumberFormatException e) {
					notice_type = null;
					errorMsgs.add("通知種類請填數字");
				}
				
				
java.sql.Timestamp created_at = null;
				try {
					created_at = java.sql.Timestamp.valueOf(req.getParameter("created_at").trim());
				}catch(Exception  e) {
					created_at = new java.sql.Timestamp(System.currentTimeMillis());
				}
				
String is_read = req.getParameter("is_read");
				Boolean isReadBool;
				if (is_read == null || is_read.trim().isEmpty()) {
				    isReadBool = false;
				} else {
				    isReadBool = is_read.trim().equals("1");
				}
					
				
				
				


PsychologistNoticeVO psychologistNoticeVO = new PsychologistNoticeVO();
psychologistNoticeVO.setPsych_notice_id(psych_notice_id);
psychologistNoticeVO.setPsych_id(psych_id);
psychologistNoticeVO.setAdmin_id(admin_id);
psychologistNoticeVO.setNotice_content(notice_content);
psychologistNoticeVO.setNotice_type(notice_type);
psychologistNoticeVO.setCreated_at(created_at);
psychologistNoticeVO.setIs_read(isReadBool);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("psychologistNoticeVO", psychologistNoticeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/psychologistNotice/update_psychologistNotice_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				
//				/***************************2.開始修改資料*****************************************/
				PsychologistNoticeService psychologistNoticeSvc = new PsychologistNoticeService();
				psychologistNoticeVO = psychologistNoticeSvc.updatePsychologistNotice(psych_notice_id, psych_id, admin_id, notice_content, notice_type, created_at, isReadBool);
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal, comm, deptno)
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("psychologistNoticeVO", psychologistNoticeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/psychologistNotice/listOnePsychologistNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
Integer psych_id = null;
				try {
					psych_id=Integer.valueOf(req.getParameter("psych_id").trim());
				}catch(NumberFormatException e) {
					psych_id = null;
					errorMsgs.add("心理師編號請填數字");
				}
				
			

Integer admin_id = null;
				try {
					admin_id=Integer.valueOf(req.getParameter("admin_id").trim());
				}catch(NumberFormatException e) {
					admin_id = null;
					errorMsgs.add("員工編號請填數字");
				}
				
				
				
String notice_content = req.getParameter("notice_content").trim();
				if (notice_content == null || notice_content.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}	
	
Integer notice_type	= null;
				try {
					notice_type = Integer.valueOf(req.getParameter("notice_type").trim());
				}catch(NumberFormatException e) {
					notice_type = null;
					errorMsgs.add("通知種類請填數字");
				}
				
				
java.sql.Timestamp created_at = null;
				try {
					created_at = java.sql.Timestamp.valueOf(req.getParameter("created_at").trim());
				}catch(Exception  e) {
					created_at = new java.sql.Timestamp(System.currentTimeMillis());
				}
				
String is_read = req.getParameter("is_read");
				Boolean isReadBool;
				if (is_read == null || is_read.trim().isEmpty()) {
				    isReadBool = false;
				} else {
				    isReadBool = is_read.trim().equals("1");
				}
					
				
				
				


				PsychologistNoticeVO psychologistNoticeVO = new PsychologistNoticeVO();
				psychologistNoticeVO.setPsych_id(psych_id);
				psychologistNoticeVO.setAdmin_id(admin_id);
				psychologistNoticeVO.setNotice_content(notice_content);
				psychologistNoticeVO.setNotice_type(notice_type);
				psychologistNoticeVO.setCreated_at(created_at);
				psychologistNoticeVO.setIs_read(isReadBool);
				
				
								// Send the use back to the form, if there were errors
								if (!errorMsgs.isEmpty()) {
				req.setAttribute("psychologistNoticeVO", psychologistNoticeVO); // 含有輸入格式錯誤的empVO物件,也存入req
									RequestDispatcher failureView = req
											.getRequestDispatcher("/psychologistNotice/addPsychologistNotice.jsp");
									failureView.forward(req, res);
									return; //程式中斷
								}
				/***************************2.開始新增資料***************************************/
				PsychologistNoticeService psychologistNoticeSvc = new PsychologistNoticeService();
				psychologistNoticeVO = psychologistNoticeSvc.addPsychologistNotice( psych_id, admin_id, notice_content, notice_type, created_at, isReadBool);
//									
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/psychologistNotice/listAllPsychologistNotice.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer psych_notice_id = Integer.valueOf(req.getParameter("psych_notice_id"));
				
				/***************************2.開始刪除資料***************************************/
				PsychologistNoticeService psychologistNoticeSvc = new PsychologistNoticeService();
				psychologistNoticeSvc.deletePsychologistNotice(psych_notice_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/psychologistNotice/psychologistNotice.do";
				res.sendRedirect(req.getContextPath() + "/psychologistNotice/listAllPsychologistNotice.jsp");
		}
	}
}
