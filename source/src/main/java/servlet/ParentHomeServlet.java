package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProgressDAO;
import dto.Progress;

/**
 * Servlet implementation class ParentHomeServlet
 */
@WebServlet("/ParentHomeServlet")
public class ParentHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParentHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/webapp/LoginServlet");
//			return;
//		}
		request.getRequestDispatcher("/WEB-INF/jsp/parentHome.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int target_page = Integer.parseInt(request.getParameter("target_page"));
		int read_page = Integer.parseInt(request.getParameter("read_page"));
		
		ProgressDAO proDao = new ProgressDAO();
		proDao.insert(new Progress(0, 0, 0, target_page, read_page, LocalDateTime.now(), LocalDateTime.now(), 0));
		
		request.getRequestDispatcher("/WEB-INF/jsp/studentHome.jsp").forward(request, response);
	}

}
