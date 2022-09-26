package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/board/edit.do")
public class EditController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idx = request.getParameter("idx");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		dto = dao.selectView(idx, false);
		dao.close();
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/Edit.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pass = (String)session.getAttribute("pass");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		String idx = request.getParameter("idx");
		int rs = dao.editBoard(idx, pass, title, content);
		dao.close();
		session.removeAttribute("pass");
		
		if(rs == 1)
			response.sendRedirect("../board/list.do");
	}

}
