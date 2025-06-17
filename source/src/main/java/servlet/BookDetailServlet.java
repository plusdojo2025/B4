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
 * Servlet implementation class BookDetailServlet
 */
@WebServlet("/BookDetailServlet")
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    // セッションからユーザタイプ取得（未設定時の対策あり）
		    HttpSession session = request.getSession();
		    String userType = (String) session.getAttribute("userType");

		    // 遷移先の決定（null時はstudent扱い）
		    String jspPath;
		    if ("parent".equals(userType)) {
		        jspPath = "/WEB-INF/jsp/parentBookDetail.jsp";
		    } else if ("teacher".equals(userType)) {
		        jspPath = "/WEB-INF/jsp/teacherBookDetail.jsp";
		    } else {
		        jspPath = "/WEB-INF/jsp/studentBookDetail.jsp";
		    }

		    // 本のID取得とチェック
		    String idStr = request.getParameter("id");
		    if (idStr == null || idStr.isEmpty()) {
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "本のIDが指定されていません");
		        return;
		    }

		    int id;
		    try {
		        id = Integer.parseInt(idStr);
		    } catch (NumberFormatException e) {
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "IDの形式が不正です");
		        return;
		    }

		    // DBから本を取得
		    BookDAO dao = new BookDAO();
		    Book book = dao.findById(id);
		    if (book == null) {
		        response.sendError(HttpServletResponse.SC_NOT_FOUND, "指定された本が見つかりません");
		        return;
		    }

		    // JSPに渡す
		    request.setAttribute("book", book);
		    RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
		    dispatcher.forward(request, response);
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        //ユーザーID仮置き
	        HttpSession session = request.getSession();
	        session.setAttribute("userId", 1);
	        
	        // 本のID取得
	        int bookId = Integer.parseInt(request.getParameter("book_id"));

	        // 種別（読了：2を想定）
	        int typeId = 2;

	        // セッションからユーザーIDを取得（ログイン処理で設定されている前提）
	        Integer userId = (Integer) session.getAttribute("userId");

//	        if (userId == null) {
//	            response.sendRedirect("LoginServlet");
//	            return;
//	        }

	        // DAOで登録処理
	        BookDAO dao = new BookDAO();
	        boolean success = dao.insert(bookId, userId, typeId);

	        // フォワード（またはリダイレクト）
	        request.setAttribute("result", success);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/BookList.jsp");
	        dispatcher.forward(request, response);
	    }	
}
