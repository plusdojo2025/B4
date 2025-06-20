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
import dto.Result;

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

		session.setAttribute("progressList", progressList);

		int book_id = Integer.parseInt(request.getParameter("book_id"));
		int target_page = Integer.parseInt(request.getParameter("target_page"));
		int read_page = Integer.parseInt(request.getParameter("read_page"));
		
		if (request.getParameter("submit").equals("OK")) {
			if(proDao.insert_target(book_id, target_page)) {
				request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/B4/StudentHomeServlet"));
			}
		}
		
		if (request.getParameter("submit").equals("OK")) {
			if(proDao.update_read(read_page)) {
				int totalRead = proDao.getTotalPagesRead(book_id);
		        int totalPages = proDao.getBookTotalPages(book_id);
	            // 読了判定（重複登録防止）
	            if (totalRead >= totalPages && !proDao.isAlreadyFinished(book_id)) {
	                proDao.insertFinishedBook(book_id);
	            }
				request.setAttribute("result", new Result("登録成功！", "レコードを登録しました。", "/B4/StudentHomeServlet"));
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/StudentHome.jsp").forward(request, response);
		
	}

}
