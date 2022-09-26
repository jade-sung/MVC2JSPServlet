package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/view.do")
public class ViewController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		String idx = request.getParameter("idx");
		
		dto = dao.selectView(idx, true);
		
		dao.close();
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		request.setAttribute("viewDto", dto);
		request.getRequestDispatcher("../View.jsp").forward(request, response);
		
	}

	


}
