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
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		int user_id = 1;
		int month = 6;
		int day = 17;
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.selectToday(user_id, month, day);
		
		session.setAttribute("progressList", progressList);
		
		request.getRequestDispatcher("/WEB-INF/jsp/parentHome.jsp").forward(request, response);
		
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/webapp/LoginServlet");
//			return;
//		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.selectAll();

		// 検索結果をセッションスコープに格納する
		session.setAttribute("progressList", progressList);
		
		// レイアウトページにフォワードする
		request.getRequestDispatcher("/WEB-INF/jsp/parentHome.jsp").forward(request, response);
	}

}
