package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FinishBookDAO;
import dao.ProgressDAO;
import dao.RankingDAO;
import dto.FinishBook;
import dto.Ranking;
import dto.Result;
import dto.User;


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
		// ログインさせる処理
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        User user = (User) session.getAttribute("user");

        int user_id = user.getId(); // ユニークID
//        int typeId = user.getTypeId(); // タイプ（1＝教師、2=保護者、3=生徒）
//        int grade = user.getGrade(); // 学年
//        int schoolClass = user.getSchoolClass(); // クラス
		request.setCharacterEncoding("UTF-8");
		
		FinishBookDAO finDao = new FinishBookDAO();
		List<FinishBook> finishBookNewList = finDao.selectNew(user_id);
		
		
		
		session.setAttribute("finishBookNewList", finishBookNewList);
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/studentHome.jsp").forward(request, response);
		
	}

	protected void doGetGenre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String rankingType = request.getParameter("rankingType"); // class or genre
	    String genreIdParam = request.getParameter("genre_id");
	    
	    RankingDAO dao = new RankingDAO();
	    List<Ranking> RankList = new ArrayList<>();
	    String title = "";
	    if("genre".equals(rankingType)) {
	        if (genreIdParam != null && !genreIdParam.isEmpty()) {
	            int genreId = Integer.parseInt(genreIdParam);
	            RankList = dao.selectByGenre(genreId, title);
	            title = "ジャンル別ランキング";
	        } else {
	            title = "ジャンルが選択されていません";
	        }
	   

	    request.setAttribute("RankList", RankList);
	    request.setAttribute("rankingType", rankingType);
	    request.setAttribute("title", title);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/teacherRanking.jsp");
	    dispatcher.forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    HttpSession session = request.getSession();
	    if (session.getAttribute("user") == null) {
	        response.sendRedirect(request.getContextPath() + "/LoginServlet");
	        return;
	    }

	    User user = (User) session.getAttribute("user");
	    int userId = user.getId();

	    FinishBookDAO finDao = new FinishBookDAO();
	    ProgressDAO proDao = new ProgressDAO();
	    List<FinishBook> finishBookList = finDao.selectAll();
	    List<FinishBook> readBookList = finDao.selectNew(userId);

	    int latestReadBookId = -1;
	    if (!readBookList.isEmpty()) {
	        latestReadBookId = readBookList.get(0).getBook_id();
	    }

	    session.setAttribute("finishBookList", finishBookList);
	    session.setAttribute("readBookList", readBookList);

	    int user_id = userId;
	    int book_id = latestReadBookId;

	    // ステップ制御変数
	    String step = "target";

	    try {
	        String targetStr = request.getParameter("target_page");
	        String readStr = request.getParameter("read_page");

	        if (readStr != null && !readStr.isEmpty() && Integer.parseInt(readStr) > 0) {
	            int read_page = Integer.parseInt(readStr);
	            proDao.update_read(user_id, book_id, read_page);

	            int totalRead = proDao.getTotalPagesRead(user_id, book_id);
	            int totalPages = proDao.getBookTotalPages(book_id);
	            if (totalRead >= totalPages ) {
	                proDao.insertFinishedBook(user_id, book_id);
	            }

	            session.setAttribute("result", new Result("登録成功！", "読んだページを登録しました。", "/B4/StudentHomeServlet"));
	            step = "target";  // 次はまた目標入力へ
	        } else if (targetStr != null && !targetStr.isEmpty() && Integer.parseInt(targetStr) > 0) {
	            int target_page = Integer.parseInt(targetStr);
	            proDao.insert_target(user_id, book_id, target_page);

	            session.setAttribute("result", new Result("登録成功！", "目標ページを登録しました。", "/B4/StudentHomeServlet"));
	            step = "record";  // 次は読書記録入力へ
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	    }

	    request.setAttribute("step", step);
	    request.getRequestDispatcher("/WEB-INF/jsp/studentHome.jsp").forward(request, response);
	}


}
