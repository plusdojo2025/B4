package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.BookRecommendDAO;
import dao.FinishBookDAO;
import dto.Book;

@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookDetailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		// 仮のユーザータイプ
        session.setAttribute("userType", "student");
        //仮のユーザID
        session.setAttribute("userId", 1);
        
        // パラメータ取得
		String bookIdStr = request.getParameter("bookId");
		
		int bookId = Integer.parseInt(bookIdStr);
		String title = request.getParameter("title");
		String genreIdStr = request.getParameter("genreId");
		String pageStr = request.getParameter("page");
		String lastList = request.getParameter("lastList");

		session.setAttribute("title", title);
		session.setAttribute("genreId", genreIdStr);
		session.setAttribute("currentPage", pageStr);
		session.setAttribute("lastList", lastList);
		Integer userId = 1;
//				(Integer) request.getSession().getAttribute("userId");

		// 読書状態
		FinishBookDAO finishDao = new FinishBookDAO();
		int statusId = finishDao.getStatusId(userId, bookId);
		request.setAttribute("statusId", statusId);

		// おすすめ済みかどうか
		BookRecommendDAO recommendDao = new BookRecommendDAO();
		boolean alreadyRecommended = recommendDao.hasAlreadyRecommended(userId, bookId);
		request.setAttribute("alreadyRecommended", alreadyRecommended);

		// 本の詳細情報
		Book book = BookDAO.findById(bookId);
		request.setAttribute("book", book);

		request.getRequestDispatcher("/WEB-INF/jsp/studentBookDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

