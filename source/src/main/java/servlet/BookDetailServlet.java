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
import dao.BookRecommendDAO;
import dao.FinishBookDAO;
import dto.Book;
import dto.User;

@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookDetailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインさせる処理
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        User user = (User) session.getAttribute("user");

//        int userId = user.getId(); // ユニークID
//        int typeId = user.getTypeId(); // タイプ（1＝教師、2=保護者、3=生徒）
//        int grade = user.getGrade(); // 学年
//        int schoolClass = user.getSchoolClass(); // クラス

		// パラメータ取得
		String bookIdStr = request.getParameter("bookId");
		int bookId = Integer.parseInt(bookIdStr);
		String title = request.getParameter("title");
		String genreIdStr = request.getParameter("genreId");
		String pageStr = request.getParameter("page");
		String lastList = request.getParameter("lastList");
		if (lastList != null && !lastList.isEmpty()) {
		    session.setAttribute("lastList", lastList);
		}


		// nullチェック(きろく用)
		if (title != null) session.setAttribute("title", title);
		if (genreIdStr != null) session.setAttribute("genreId", genreIdStr);
		if (pageStr != null) session.setAttribute("currentPage", pageStr);
		if (lastList != null) session.setAttribute("lastList", lastList);

		session.setAttribute("title", title);
		session.setAttribute("genreId", genreIdStr);
		session.setAttribute("currentPage", pageStr);
		session.setAttribute("lastList", lastList);
		int userId = user.getId();

		// 読書状態
		FinishBookDAO finishDao = new FinishBookDAO();
		int typeId = finishDao.getTypeId(userId, bookId);
		request.setAttribute("typeId", typeId);

		// おすすめ済みかどうか
		BookRecommendDAO recommendDao = new BookRecommendDAO();
		boolean alreadyRecommended = recommendDao.hasAlreadyRecommended(userId, bookId);
		request.setAttribute("alreadyRecommended", alreadyRecommended);

		// 本の詳細情報
		Book book = BookDAO.findById(bookId);
		request.setAttribute("book", book);
		
		FinishBookDAO finDao = new FinishBookDAO();
		Integer latestReadingBookId = finDao.selectLatestReadingBookId(user.getId());
		request.setAttribute("latestReadingBookId", latestReadingBookId);
		
		String view = "/WEB-INF/jsp/studentBookDetail.jsp";
        
        if (user != null) {
            switch (user.getTypeId()) {
                case 1:
                    view = "/WEB-INF/jsp/teacherBookDetail.jsp";
                    break;
                case 2:
                    view = "/WEB-INF/jsp/parentBookDetail.jsp";
                    break;
                case 3:
                    view = "/WEB-INF/jsp/studentBookDetail.jsp";
                    break;
                default:
                    view = "/WEB-INF/jsp/studentBookDetail.jsp";
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインさせる処理
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        
      //user_typeを参照して遷移先の分岐        
        String view = "/WEB-INF/jsp/studentBookDetail.jsp";
        
        if (user != null) {
            switch (user.getTypeId()) {
                case 1:
                    view = "/WEB-INF/jsp/teacherBookDetail.jsp";
                    break;
                case 2:
                    view = "/WEB-INF/jsp/parentBookDetail.jsp";
                    break;
                case 3:
                    view = "/WEB-INF/jsp/studentBookDetail.jsp";
                    break;
                default:
                    view = "/WEB-INF/jsp/studentBookDetail.jsp";
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
	}
}

