package com.jhj.memo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhj.action.ActionFoward;
import com.jhj.page.MakePager;
import com.jhj.page.Pager;
import com.jhj.page.RowNumber;

public class MemoService {
	private MemoDAO memoDAO;

	public MemoService() {
		memoDAO = new MemoDAO();
	}

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
			List<MemoDTO> ar = memoDAO.SelectList(rowNumber);
			Pager pager = mk.makePage(memoDAO.totalCount());
			request.setAttribute("list", ar);
			request.setAttribute("pager", pager);
			actionFoward.setPath("../WEB-INF/view/memo/memoList.jsp");
		} catch (Exception e) {
			actionFoward.setPath("../index.jsp");
			e.printStackTrace();
		}
		actionFoward.setCheck(true);
		
		return actionFoward;
	}
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		MemoDTO memoDTO = new MemoDTO();
		memoDTO.setContents(request.getParameter("contents"));
		memoDTO.setWriter(request.getParameter("writer"));
		try {
		int result = memoDAO.insert(memoDTO);
		String message = "fail";
		if(result > 0) {
			message = "Success";
		}
			request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
		
		return actionFoward;
	}
	public ActionFoward delete(HttpServletResponse response, HttpServletRequest request) {
		ActionFoward actionFoward = new ActionFoward();
		
		try {
			memoDAO.delete(Integer.parseInt(request.getParameter("num")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/resultAjax.jsp");
			
		return actionFoward;
	}
}
