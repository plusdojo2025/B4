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
import dto.User;

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
    	
    	// ログインさせる処理
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

//        int userId = user.getId(); // ユニークID
//        int typeId = user.getTypeId(); // タイプ（1＝教師、2=保護者、3=生徒）
//        int grade = user.getGrade(); // 学年
//        int schoolClass = user.getSchoolClass(); // クラス
        
        request.setCharacterEncoding("UTF-8");
        
        session.setAttribute("lastList", "BookListServlet");

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
        //　条件の保持
        session.setAttribute("title", title);
        session.setAttribute("genreId", genreIdStr);
        session.setAttribute("currentPage", page);
        
        String view = "/WEB-INF/jsp/studentBookList.jsp";
        
        if (user != null) {
            switch (user.getTypeId()) {
                case 1:
                    view = "/WEB-INF/jsp/teacherBookList.jsp";
                    break;
                case 2:
                    view = "/WEB-INF/jsp/parentBookList.jsp";
                    break;
                case 3:
                    view = "/WEB-INF/jsp/studentBookList.jsp";
                    break;
                default:
                    view = "/WEB-INF/jsp/studentBookList.jsp";
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
        
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

//	      String name = user.getName();	//ログインユーザの名前
//        int userId = user.getId(); // ユニークID
//        int typeId = user.getTypeId(); // タイプ（1＝教師、2=保護者、3=生徒）
//        int grade = user.getGrade(); // 学年
//        int schoolClass = user.getSchoolClass(); // クラス
        
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
		
		String view = "/WEB-INF/jsp/studentBookList.jsp";
        
        if (user != null) {
            switch (user.getTypeId()) {
                case 1:
                    view = "/WEB-INF/jsp/teacherBookList.jsp";
                    break;
                case 2:
                    view = "/WEB-INF/jsp/parentBookList.jsp";
                    break;
                case 3:
                    view = "/WEB-INF/jsp/studentBookList.jsp";
                    break;
                default:
                    view = "/WEB-INF/jsp/studentBookList.jsp";
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
	}
	
}
