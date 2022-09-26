package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/delete.do")
public class DeleteController extends HttpServlet {
       
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	HttpSession session = request.getSession();
		String pass = (String)session.getAttribute("pass");
		
		BoardDAO dao = new BoardDAO();
		String idx = request.getParameter("idx");
		int rs = dao.deleteBoard(idx, pass);
		dao.close();
		session.removeAttribute("pass");
		
		if(rs == 1)
			response.sendRedirect("../board/list.do");
		
			
		// else 잘못된접근일때
	}

}
