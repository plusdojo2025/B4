package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProgressDAO;
import dto.Progress;

/**
 * Servlet implementation class StudentHomeServlet
 */
@WebServlet("/StudentHomeServlet")
public class StudentHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/webapp/LoginServlet");
//			return;
//		}
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		int user_id = 1;
		int month = 6;
		int day = 17;
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.selectToday(user_id, month, day);
		
		session.setAttribute("progressList", progressList);
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/studentHome.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.selectAll();

		// 検索結果をセッションスコープに格納する
		session.setAttribute("progressList", progressList);
		
		int target_page = Integer.parseInt(request.getParameter("target_page"));
		int read_page = Integer.parseInt(request.getParameter("read_page"));
		
		if (request.getParameter("submit").equals("もくひょうOK")) {
			proDao.insert_target(target_page);
		}
		
		if (request.getParameter("submit").equals("きろくOK")) {
			proDao.insert_read(read_page);
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/studentHome.jsp").forward(request, response);
		
	}

}
