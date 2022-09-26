package board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/list.do")
public class ListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		
		String searchMode = request.getParameter("searchMode");
		String searchFiled = request.getParameter("searchFiled");
		
		int totalCount = dao.searchTotalCount(searchMode, searchFiled);
	
		int totalPage = (int) Math.ceil((double)totalCount / 5);
		if (totalPage == 0)
			totalPage = 1;
		
		int pageNum = 1;
		String pageTemp = request.getParameter("pageNum");
		if (pageTemp != null)
			pageNum = Integer.parseInt(pageTemp);
		
		
		int start = (pageNum-1) * 5 + 1;
		int end = pageNum*5;
		
		List<BoardDTO> resultList = dao.pageList(start, end, searchMode, searchFiled);
	
		dao.close();
		
		
		
		
		String paging = "";
		paging += "<a href='../board/list.do?pageNum=1";
		if (searchFiled != null)
			paging += "&searchMode=" + searchMode + "&searchFiled=" + searchFiled;
		paging += "'>[처음]</a>";
		
		//페이징 처리
		//페이지가 1 or 2일 경우
		if (pageNum < 3) 
		{	
			if (pageNum == 1) 
			paging += "1";
			else if (pageNum == 2) 
			{
				paging += "<a href='../board/list.do?pageNum=1";
				if (searchFiled != null)
					paging += "&searchMode=" + searchMode + "&searchFiled=" + searchFiled;
				paging += "'>" + 1 + "</a>";
				paging += "2";
			}
			for (int i = pageNum; i < totalPage; i++)
			{
				String temp = Integer.toString(i+1);
				paging += "<a href='../board/list.do?pageNum=" + temp;
				if (searchFiled != null)
					paging += "&searchMode=" + searchMode + "&searchFiled=" + searchFiled;
				paging += "'>" + temp + "</a>";
				if (Integer.parseInt(temp) == 5)
					break;
			}
		}
		// 페이지가 3이상일 경우
		else
		{
			paging += "<a href='../board/list.do?pageNum=" + (pageNum-2);
			if (searchFiled != null)
				paging += "&searchMode=" + searchMode + "&searchFiled=" + searchFiled;
			paging += "'>" + (pageNum-2) + "</a>";
			paging += "<a href='../board/list.do?pageNum=" + (pageNum-1);
			if (searchFiled != null)
				paging += "&searchMode=" + searchMode + "&searchFiled=" + searchFiled;
			paging += "'>" + (pageNum-1) + "</a>";
			paging += (pageNum);
			if(pageNum < totalPage)
			paging += "<a href='../board/list.do?pageNum=" + (pageNum+1);
			if (searchFiled != null)
				paging += "&searchMode=" + searchMode + "&searchFiled=" + searchFiled;
			paging += "'>" + (pageNum+1) + "</a>";
			if(pageNum+1 < totalPage)
			paging += "<a href='../board/list.do?pageNum=" + (pageNum+2);
			if (searchFiled != null)
				paging += "&searchMode=" + searchMode + "&searchFiled=" + searchFiled;
			paging += "'>" + (pageNum+2) + "</a>";
		}
		
		paging += "<a href='../board/list.do?pageNum=" + totalPage;
		if (searchFiled != null)
			paging += "&searchMode=" + searchMode + "&searchFiled=" + searchFiled;
		paging += "'>[끝]</a>";
		
		request.setAttribute("paging", paging);
		request.setAttribute("resultList", resultList);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/Listview.jsp").forward(request, response);
	}

	

}
