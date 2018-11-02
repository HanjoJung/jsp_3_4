package com.jhj.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jhj.action.ActionFoward;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberService {
	private MemberDAO memberDAO;

	public MemberService() {
		memberDAO = new MemberDAO();
	}

	public ActionFoward checkId(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		
		String id = request.getParameter("id");
		boolean check=true;
		String result="1";//사용가능, no
		
		try {
			check = memberDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check) {
			result="2";
		}
		request.setAttribute("result", result);
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/member/memberCheckId.jsp");
		return actionFoward;
	}

	// join
	public ActionFoward join(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();

		if (method.equals("POST")) {
			int max = 1024 * 1024 * 10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}

			try {
				// 파일저장
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8",
						new DefaultFileRenamePolicy());
				// Member Data
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(multi.getParameter("id"));
				memberDTO.setPw(multi.getParameter("pw2"));
				memberDTO.setName(multi.getParameter("name"));
				memberDTO.setEmail(multi.getParameter("email"));
				memberDTO.setKind(multi.getParameter("kind"));
				memberDTO.setClassMate("cm" + multi.getParameter("grade") + multi.getParameter("ban"));
				memberDTO.setFname(multi.getFilesystemName("f"));
				memberDTO.setOname(multi.getOriginalFileName("f"));
				int result = memberDAO.join(memberDTO);
				if (result > 0) {
					request.setAttribute("message", "가입 완료");
					request.setAttribute("path", "../index.jsp");
				} else {
					request.setAttribute("message", "가입 실패");
					request.setAttribute("path", "./memberJoin.do");
				}
			} catch (Exception e) {
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/member/memberJoin.jsp");
				e.printStackTrace();
			}

			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		} else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		}

		return actionFoward;
	}

	public ActionFoward login(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		MemberDTO memberDTO = new MemberDTO();

		if (method.equals("POST")) {

			try {
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setPw(request.getParameter("pw"));
				memberDTO.setKind(request.getParameter("kind"));
				memberDTO = memberDAO.login(memberDTO);
			} catch (Exception e) {
				memberDTO = null;
				e.printStackTrace();
			}
			if (memberDTO != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", memberDTO);
				actionFoward.setCheck(false);
				actionFoward.setPath("../index.jsp");
			} else {
				request.setAttribute("message", "로그인 실패");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/member/memberLogin.jsp");
			}

		} else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberLogin.jsp");
		}

		return actionFoward;
	}

	public ActionFoward logout(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();

		HttpSession session = request.getSession();
		session.invalidate();

		actionFoward.setCheck(false);
		actionFoward.setPath("../index.jsp");
		return actionFoward;
	}

	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();

		if (method.equals("POST")) { // DB Update
			request.setAttribute("message", "수정 실패");
			request.setAttribute("path", "./memberUpdate.do");
			int max = 1024 * 1024 * 10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}

			try {
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8",
						new DefaultFileRenamePolicy());
				HttpSession session = request.getSession();
				MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
				memberDTO.setId(multi.getParameter("id"));
				memberDTO.setPw(multi.getParameter("pw2"));
				memberDTO.setName(multi.getParameter("name"));
				memberDTO.setEmail(multi.getParameter("email"));
				file = multi.getFile("f");
				if (file != null) {
					file = new File(path, memberDTO.getFname());
					file.delete();
					memberDTO.setFname(multi.getFilesystemName("f"));
					memberDTO.setOname(multi.getOriginalFileName("f"));
				}
				int result = memberDAO.update(memberDTO);
				if (result > 0) {
					request.setAttribute("message", "수정 성공");
					request.setAttribute("path", "./memberSelectOne.do");
					session.setAttribute("member", memberDTO);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");

		} else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberUpdate.jsp");
		}

		return actionFoward;

	}

	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		HttpSession session = request.getSession();
		String path = request.getServletContext().getRealPath("upload");
		File file = new File(path + "/" + request.getParameter("fname"));
		if (file.delete()) {
		}

		try {
			int result = memberDAO.delete(request.getParameter("id"));
			if (result > 0) {
				request.setAttribute("message", "탈퇴 성공");
				request.setAttribute("path", "../index.jsp");
				session.invalidate();
			} else {
				request.setAttribute("message", "탈퇴 실패");
				request.setAttribute("path", "./memberSelectOne.do");
			}
		} catch (Exception e) {
			request.setAttribute("message", "탈퇴 실패");
			request.setAttribute("path", "./memberSelectOne.do");
			e.printStackTrace();
		}
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/result.jsp");

		return actionFoward;
	}

	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		HttpSession session = request.getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO != null) {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberSelectOne.jsp");
		} else {
			request.setAttribute("message", "잘못된 접근입니다");
			request.setAttribute("path", "../index.jsp");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		}
		return actionFoward;
	}

}
