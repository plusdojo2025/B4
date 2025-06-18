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
import dto.Book;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        // 仮のユーザータイプ
        session.setAttribute("userType", "student");

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
        List<Book> bookList = dao.search(title, genreId, page, LIMIT);
        int totalCount = dao.countAllBooks(title, genreId);

        // 検索条件や結果をセット
        request.setAttribute("title", title);
        request.setAttribute("genreId", genreIdStr);
        request.setAttribute("bookList", bookList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) totalCount / LIMIT));

        // JSPへ
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentBookList.jsp");
        dispatcher.forward(request, response);
    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//ユーザのタイプ仮入れ
//		HttpSession session = request.getSession();
//		session.setAttribute("userType", "student");  // "student", "parent", "teacher" のどれか
//		
//		BookDAO dao = new BookDAO();
//        
//        //ページネーション
//        final int LIMIT = 1;
//        int page = 1;
//
//        String pageParam = request.getParameter("page");
//        if (pageParam != null) {
//            page = Integer.parseInt(pageParam);
//        }
//
//        List<Book> bookList = dao.selectAll(page, LIMIT);	//ページネーション付の一覧表示
//        int totalCount = dao.countAllBooks(null, null);  // 総件数を取得
//
//        request.setAttribute("bookList", bookList);
//        request.setAttribute("currentPage", page);
//        request.setAttribute("totalPages", (int)Math.ceil((double)totalCount / LIMIT));
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentBookList.jsp");
//        dispatcher.forward(request, response);
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        // 仮のユーザータイプ
        session.setAttribute("userType", "student");

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
        List<Book> bookList = dao.search(title, genreId, page, LIMIT);
        int totalCount = dao.countAllBooks(title, genreId);

        // 検索条件や結果をセット
        request.setAttribute("title", title);
        request.setAttribute("genreId", genreIdStr);
        request.setAttribute("bookList", bookList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) totalCount / LIMIT));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentBookList.jsp");
		dispatcher.forward(request, response);

//	    // 検索条件を取得
//	    String title = request.getParameter("title");
//	    String genreIdStr = request.getParameter("genreId");
//	    Integer genreId = null;
//	    if (genreIdStr != null && !genreIdStr.isEmpty()) {
//	        genreId = Integer.parseInt(genreIdStr);
//	    }
//
//	    // DAOを使って検索
//	    BookDAO dao = new BookDAO();
//	    //ページネーション
//        final int LIMIT = 1;
//        int page = 1;
//        
//	    List<Book> bookList = dao.search(title, genreId, page, LIMIT);
//	    int totalCount = dao.countAllBooks(title, genreId);  // 総件数を取得
//
//	    // 検索結果をJSPへ渡す
//	    request.setAttribute("bookList", bookList);
//
//	    // 入力値の保持（フォームに再表示させたい場合）
//	    request.setAttribute("title", title);
//	    request.setAttribute("genreId", genreIdStr);
//	    request.setAttribute("bookList", bookList);
//        request.setAttribute("currentPage", page);
//        request.setAttribute("totalPages", (int)Math.ceil((double)totalCount / LIMIT));
//
//	    // JSPへフォワード
//	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentBookList.jsp");
//	    dispatcher.forward(request, response);
	}

}
