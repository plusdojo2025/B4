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

/**
 * Servlet implementation class BookRecommendServlet
 */
@WebServlet("/BookRecommendServlet")
public class BookRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookRecommendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
     // ログインさせる処理
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

        // 検索条件と現在ページを保持
        session.setAttribute("title", title);
        session.setAttribute("genreId", genreIdStr);
        session.setAttribute("currentPage", page);
        
      //user_typeを参照して遷移先の分岐        
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	        
	        request.setCharacterEncoding("UTF-8");
	     // ログインさせる処理
	    	HttpSession session = request.getSession();
	        if (session.getAttribute("user") == null) {
	            response.sendRedirect(request.getContextPath() + "/LoginServlet");
	            return;
	        }
	        
	        int bookId = Integer.parseInt(request.getParameter("bookId"));
	        String comment = request.getParameter("comment");
	        Integer userId = (Integer) session.getAttribute("userId");
	        
	        if (userId != null && comment != null && !comment.trim().isEmpty()) {
	            BookRecommendDAO dao = new BookRecommendDAO();

	            // すでにおすすめしていない場合だけ登録
	            if (!dao.hasAlreadyRecommended(userId, bookId)) {
	                dao.insert(userId, bookId, comment);
	            }
	        }

	        response.sendRedirect("BookDetailServlet?bookId=" + bookId);
	}
}
