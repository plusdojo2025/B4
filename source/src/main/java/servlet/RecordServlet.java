package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FinishBookDAO;
import dao.ProgressDAO;
import dto.FinishBook;
import dto.Progress;
import dto.User;

/**
 * Servlet implementation class RecordServlet
 */
@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
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

      //以下4つは適宜
        int userId = user.getId(); // ユニークID
//              int typeId = user.getTypeId(); // タイプ（1＝教師、2=保護者、3=生徒）
//              int grade = user.getGrade(); // 学年
//              int schoolClass = user.getSchoolClass(); // クラス
//	    ProgressDAO proDao = new ProgressDAO();
//	    List<FinishBook> finishBookList = finDao.selectAll();
//	    List<FinishBook> readBookList = finDao.selectNew(userId);
//        
//        int latestReadBookId = -1;
//	    if (!readBookList.isEmpty()) {
//	        latestReadBookId = readBookList.get(0).getBook_id();
//	    }	    
        // 今読んでいる本の取得
        FinishBookDAO finDao = new FinishBookDAO();
        List<FinishBook> finishBookNewList = finDao.selectNew(userId);
        FinishBook currentBook = finishBookNewList.isEmpty() ? null : finishBookNewList.get(0);

        // 今読んでいる本の当日進捗を取得
        Progress progress = null;
        if (currentBook != null) {
            int bookId = currentBook.getBook_id();  // ← ここでbook_idを取得
            int currentMonth = LocalDateTime.now().getMonthValue();
            int currentDay = LocalDateTime.now().getDayOfMonth();

            ProgressDAO proDao = new ProgressDAO();
            List<Progress> selectTodayList = proDao.selectToday(userId, bookId, currentMonth, currentDay);

            if (!selectTodayList.isEmpty()) {
                progress = selectTodayList.get(0); // 1件だけ取得
            }
        }

        // 画面に渡す
        request.setAttribute("currentBook", currentBook);
        request.setAttribute("todayProgress", progress);
        

        List<FinishBook> finishBookSelectNewList = finDao.selectNewList(userId);
        request.setAttribute("finishBookSelectNewList", finishBookSelectNewList);

        request.getRequestDispatcher("/WEB-INF/jsp/record.jsp").forward(request, response);

        
//		int user_id = 1;
//		int book_id = 6;
//		int month = 6;
//		int day = 17;
//		
//		ProgressDAO proDao = new ProgressDAO();
//		List<Progress> progressList = proDao.selectToday(user_id, book_id, month, day);
//		
//		session.setAttribute("progressList", progressList);
//		
//		request.getRequestDispatcher("/WEB-INF/jsp/record.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインさせる処理
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

        
      //今日読んだ本の詳細情報の取得
      		int currentMonth = LocalDateTime.now().getMonthValue();
      		int currentDay = LocalDateTime.now().getDayOfMonth();
      		String bookIdStr = request.getParameter("book_id");
      		int bookId = Integer.parseInt(bookIdStr);
      		ProgressDAO proDao = new ProgressDAO();
      		List<Progress> selectTodayList = proDao.selectToday(userId, bookId, currentMonth, currentDay);
      		session.setAttribute("selectTodayList", selectTodayList);
//		ProgressDAO proDao = new ProgressDAO();
//		List<Progress> progressList = proDao.selectAll();
//
//		// 検索結果をセッションスコープに格納する
//		session.setAttribute("progressList", progressList);
//		
//		// レイアウトページにフォワードする
//		request.getRequestDispatcher("/WEB-INF/jsp/record.jsp").forward(request, response);
	}

}
