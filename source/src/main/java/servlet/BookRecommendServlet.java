package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.BookRecommendDAO;
import dto.Book;
import dto.User;

@WebServlet("/BookRecommendServlet")
public class BookRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookRecommendServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		User user = (User) session.getAttribute("user");

		// 検索条件の取得
		String title = request.getParameter("title");
		String genreIdStr = request.getParameter("genreId");
		Integer genreId = null;
		if (genreIdStr != null && !genreIdStr.isEmpty()) {
			genreId = Integer.parseInt(genreIdStr);
		}

		// ページ番号
		final int LIMIT = 10;
		int page = 1;
		String pageParam = request.getParameter("page");
		if (pageParam != null && !pageParam.isEmpty()) {
			page = Integer.parseInt(pageParam);
		}

		BookDAO dao = new BookDAO();
		List<Book> bookList = dao.searchRecommend(title, genreId, page, LIMIT);
		int totalCount = dao.countAllBooks(title, genreId);

		// 検索条件や結果をセット
		request.setAttribute("title", title);
		request.setAttribute("genreId", genreIdStr);
		request.setAttribute("bookList", bookList);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", (int) Math.ceil((double) totalCount / LIMIT));

		// 検索条件をセッションに保存（詳細画面で戻るときに使う）
		session.setAttribute("title", title);
		session.setAttribute("genreId", genreIdStr);
		session.setAttribute("currentPage", page);
		session.setAttribute("lastList", "BookRecommendServlet");  // ← ★ 追加（重要）

		// user_type を参照してビューを切り替え
		String view = "/WEB-INF/jsp/studentBookRecommend.jsp";
		if (user != null) {
			switch (user.getTypeId()) {
				case 1:
					view = "/WEB-INF/jsp/teacherBookRecommend.jsp";
					break;
				case 2:
					view = "/WEB-INF/jsp/parentBookRecommend.jsp";
					break;
				case 3:
					view = "/WEB-INF/jsp/studentBookRecommend.jsp";
					break;
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		User user = (User) session.getAttribute("user");
		int userId = user.getId();

		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String comment = request.getParameter("comment");

		if (comment != null && !comment.trim().isEmpty()) {
			BookRecommendDAO dao = new BookRecommendDAO();
			if (!dao.hasAlreadyRecommended(userId, bookId)) {
				boolean success = dao.insert(userId, bookId, comment);
				System.out.println("insert success = " + success);
			} else {
				System.out.println("already recommended");
			}
		} else {
			System.out.println("empty comment");
		}

		response.sendRedirect("BookDetailServlet?bookId=" + bookId);
	}
}
