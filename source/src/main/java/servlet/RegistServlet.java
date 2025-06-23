package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dto.Book;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインさせる処理
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
		// フォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/teacherRegist.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		// ログインさせる処理
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
           
	    // リクエストパラメータ取得
	    request.setCharacterEncoding("UTF-8");
	    String title = request.getParameter("title");
	    String author = request.getParameter("author");
	    String publisher = request.getParameter("publisher");
	    String gets = request.getParameter("gets");
	    String pageStr = request.getParameter("page");
	    String genre_name = request.getParameter("genre_name");
	    String cover = request.getParameter("cover");

	    int page = 0;
	    try {
	        page = Integer.parseInt(pageStr);
	    } catch (NumberFormatException e) {
	        request.setAttribute("message", "ページ数は数値で入力してください");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/teacherRegist.jsp");
	        dispatcher.forward(request, response);
	        return;
	    }

	    // 登録処理
	    BookDAO bDao = new BookDAO();
	    Book book = new Book(0, title, author, publisher, gets, page, genre_name, cover, null, null);
	    boolean success = bDao.insert(book);

	    if (success) {
	        request.setAttribute("message", "登録成功");
	    } else {
	        request.setAttribute("message", "登録失敗");
	    }

	    // 結果ページにフォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/teacherRegist.jsp");
	    dispatcher.forward(request, response);
	}
}