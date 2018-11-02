package com.jhj.notice;

import java.io.File;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jhj.action.ActionFoward;
import com.jhj.board.BoardDTO;
import com.jhj.board.BoardService;
import com.jhj.file.FileDAO;
import com.jhj.file.FileDTO;
import com.jhj.member.MemberDTO;
import com.jhj.page.MakePager;
import com.jhj.page.Pager;
import com.jhj.page.RowNumber;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeService implements BoardService {
	private NoticeDAO noticeDAO;

	public NoticeService() {
		noticeDAO = new NoticeDAO();
	}

	@Override
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		HttpSession session = request.getSession();

		String method = request.getMethod();
		if (method.equals("POST")) {
			String message = "작성 실패";
			String path = "./noticeList.do";
			// 파일의 크기 byte
			int maxSize = 1024 * 1024 * 10;
			// 파일의 저장공간
			String save = request.getServletContext().getRealPath("upload");
			File file = new File(save);
			if (!file.exists()) {
				file.mkdirs();
			}

			try {
				MultipartRequest multi = new MultipartRequest(request, save, maxSize, "UTF-8",
						new DefaultFileRenamePolicy());
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setTitle(multi.getParameter("title"));
				noticeDTO.setWriter(multi.getParameter("writer"));
				noticeDTO.setContents(multi.getParameter("contents"));
				noticeDTO.setNum(noticeDAO.getNum());
				int result = noticeDAO.insert(noticeDTO);
				if (result > 0) {
					FileDAO fileDAO = new FileDAO();
					// 파일의 파라미터 명을 모은 것
					Enumeration<Object> e = multi.getFileNames();
					while (e.hasMoreElements()) {
						String p = (String) e.nextElement();
						FileDTO fileDTO = new FileDTO();
						fileDTO.setKind("N");
						fileDTO.setNum(noticeDTO.getNum());
						fileDTO.setFname(multi.getFilesystemName(p));
						fileDTO.setOname(multi.getOriginalFileName(p));
						fileDAO.insert(fileDTO);
					}

					message = "작성 성공";
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				} else {
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("message", message);
			request.setAttribute("path", path);

		} else {
			request.setAttribute("board", "notice");
			actionFoward.setCheck(true);
			if (session.getAttribute("member") == null) {
				request.setAttribute("message", "선생님만 작성가능합니다");
				request.setAttribute("path", "./noticeList.do");
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			} else {
				actionFoward.setPath("../WEB-INF/view/board/boardWrite.jsp");
			}
		}
		return actionFoward;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		HttpSession session = request.getSession();

		if (method.equals("POST")) {
			// DB Update
			int max = 1024 * 1024 * 10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}

			try {
				MultipartRequest multi = new MultipartRequest(request, path, max, "UTF-8",
						new DefaultFileRenamePolicy());
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setNum(Integer.parseInt(multi.getParameter("num")));
				noticeDTO.setTitle(multi.getParameter("title"));
				noticeDTO.setContents(multi.getParameter("contents"));
				// update
				int result = noticeDAO.update(noticeDTO);
				if (result > 0) {
					Enumeration<Object> e = multi.getFileNames();
					FileDAO fileDAO = new FileDAO();
					while (e.hasMoreElements()) {
						FileDTO fileDTO = new FileDTO();
						String key = (String) e.nextElement();
						fileDTO.setOname(multi.getOriginalFileName(key));
						fileDTO.setFname(multi.getFilesystemName(key));
						fileDTO.setKind("N");
						fileDTO.setNum(noticeDTO.getNum());
						fileDAO.insert(fileDTO);
					} // while 끝

					request.setAttribute("message", "Update Success");
					request.setAttribute("path", "./noticeList.do");
				} else {
					// update fail
					request.setAttribute("message", "Update Fail");
					request.setAttribute("path", "./noticeList.do");
				}

			} catch (Exception e) {
				request.setAttribute("message", "Update Fail");
				request.setAttribute("path", "./noticeList.do");
				e.printStackTrace();
			}

			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");

		} else {
			// Form
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
			if (memberDTO != null) {
				if (memberDTO.getId().equals(request.getParameter("writer"))) {
					try {
						int num = Integer.parseInt(request.getParameter("num"));
						BoardDTO boardDTO = noticeDAO.selectOne(num);
						FileDAO fileDAO = new FileDAO();
						FileDTO fileDTO = new FileDTO();
						fileDTO.setNum(num);
						fileDTO.setKind("N");
						List<FileDTO> ar = fileDAO.selectList(fileDTO);
						request.setAttribute("dto", boardDTO);
						request.setAttribute("files", ar);
						request.setAttribute("board", "notice");
						actionFoward.setCheck(true);
						actionFoward.setPath("../WEB-INF/view/board/boardUpdate.jsp");
					} catch (Exception e) {
					}
				} else {
					request.setAttribute("message", "작성자만 수정할 수 있습니다");
					request.setAttribute("path", "./noticeList.do");
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}
			} else {
				request.setAttribute("message", "로그인 해주시길 바랍니다");
				request.setAttribute("path", "./noticeList.do");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}
		}
		return actionFoward;
	}

	@Override
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		HttpSession session = request.getSession();

		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO != null) {
			int num = Integer.parseInt(request.getParameter("num"));
			try {
				BoardDTO noticeDTO = noticeDAO.selectOne(num);
				if (memberDTO.getId().equals(noticeDTO.getWriter())) {
					FileDAO fileDAO = new FileDAO();
					fileDAO.deleteAll(num);
					num = noticeDAO.delete(num);

					if (num > 0) {
						request.setAttribute("message", "삭제 성공");
						request.setAttribute("path", "./noticeList.do");
					} else {
						request.setAttribute("message", "삭제 실패");
						request.setAttribute("path", "./noticeList.do");
					}
				} else {
					request.setAttribute("message", "작성자만 삭제할 수 있습니다");
					request.setAttribute("path", "./noticeList.do");
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}
			} catch (Exception e) {
				request.setAttribute("message", "삭제 실패");
				request.setAttribute("path", "./noticeList.do");
				e.printStackTrace();
			}
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		} else {
			request.setAttribute("message", "로그인 해주시길 바랍니다");
			request.setAttribute("path", "./noticeList.do");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		}
		return actionFoward;
	}

	// selectList
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		int curPage = 1;
		try {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");

		MakePager mk = new MakePager(curPage, search, kind);
		RowNumber rowNumber = mk.makeRow();

		try {
			List<BoardDTO> ar = noticeDAO.selectList(rowNumber);
			int totalCount = noticeDAO.getCount(rowNumber.getSearch());
			Pager pager = mk.makePage(totalCount);
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			request.setAttribute("board", "notice");
			actionFoward.setPath("../WEB-INF/view/board/boardList.jsp");
		} catch (Exception e) {
			request.setAttribute("message", "Fail");
			request.setAttribute("path", "../index.jsp");
			actionFoward.setPath("../WEB-INF/common/result.jsp");
			e.printStackTrace();
		}

		actionFoward.setCheck(true);

		return actionFoward;
	}

	// selectOne
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		BoardDTO boardDTO = null;

		try {
			int num = Integer.parseInt(request.getParameter("num"));
			boardDTO = noticeDAO.selectOne(num);
			FileDAO fileDAO = new FileDAO();
			FileDTO fileDTO = new FileDTO();
			fileDTO.setNum(num);
			fileDTO.setKind("N");
			List<FileDTO> ar = fileDAO.selectList(fileDTO);
			request.setAttribute("dto", boardDTO);
			request.setAttribute("files", ar);
			request.setAttribute("board", "notice");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardSelectOne.jsp");
		} catch (Exception e) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./noticeList.do");
			e.printStackTrace();
		}

		if (boardDTO == null) {
			actionFoward.setCheck(false);
			actionFoward.setPath("./noticeList.do");
		}

		return actionFoward;
	}

}
