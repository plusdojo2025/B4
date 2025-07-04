package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.FinishBookDAO;
import dao.ProgressDAO;
import dao.RankingDAO;
import dto.Book;
import dto.FinishBook;
import dto.Progress;
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
        int typeId = user.getTypeId(); // タイプ（1＝教師、2=保護者、3=生徒）
        int grade = user.getGrade(); // 学年
        int schoolClass = user.getSchoolClass(); // クラス
		request.setCharacterEncoding("UTF-8");
		
		// POSTからstepを受け取る
		String step = (String) session.getAttribute("step");
		if (step != null) {
		    request.setAttribute("step", step);
		    session.removeAttribute("step");
		}
		//	resltメッセージも受け取る
		Result result = (Result) session.getAttribute("result");
		if (result != null) {
		    request.setAttribute("result", result);
		    session.removeAttribute("result");
		}
		
		FinishBookDAO finDao = new FinishBookDAO();
		List<FinishBook> finishBookNewList = finDao.selectNew(user_id);
		
		//日付取得
		LocalDate today = LocalDate.now();
		Date date = java.sql.Date.valueOf(today); // LocalDate → java.util.Date に変換
		request.setAttribute("today", date);
		
		session.setAttribute("finishBookNewList", finishBookNewList);
		
		RankingDAO dao = new RankingDAO();
	    List<Ranking> rankList = dao.selectBySchool_class(schoolClass);
	    request.setAttribute("RankList", rankList);
	    request.setAttribute("rankingType", "class");
	    request.setAttribute("title", schoolClass + "組の読書ランキング");
	    
	    //おすすめ本上位10冊のうちランダムで一つ取得　5時更新
	    BookDAO bookDAO = new BookDAO();
	    List<Book> top10Books = bookDAO.searchRecommend("", null, 1, 10);
	    Book todayRecommendation = null;

	    if (!top10Books.isEmpty()) {
	        LocalDateTime now = LocalDateTime.now();
	        if (now.getHour() < 5) {
	            now = now.minusDays(1);
	        }
	        LocalDate targetDate = now.toLocalDate();

	        // ユーザーIDと日付からランダム表示
	        int seed = user_id + targetDate.getDayOfYear();
	        int index = seed % top10Books.size();

	        todayRecommendation = top10Books.get(index);

	    request.setAttribute("todayRecommendation", todayRecommendation);
	    }
	    
	    int latestBookId = -1;
	    List<FinishBook> readBookList = finDao.selectNew(user_id);
	    if (!readBookList.isEmpty()) {
	        latestBookId = readBookList.get(0).getBook_id();
	    }
	    
	    //今日の入力状況を確認
	    ProgressDAO proDao = new ProgressDAO();
	    List<Progress> todayProgress = proDao.selectToday(user_id, latestBookId);

	    boolean isTargetDone = todayProgress.stream().anyMatch(p -> p.getTarget_page() > 0);
	    boolean isRecordDone = todayProgress.stream().anyMatch(p -> p.getRead_page() > 0);
	    boolean isAllDone = isTargetDone && isRecordDone;

	    request.setAttribute("isTargetDone", isTargetDone);
	    request.setAttribute("isRecordDone", isRecordDone);
	    request.setAttribute("isAllDone", isAllDone);
	    
	    String readStr = request.getParameter("read_page");

	    if (readStr != null && !readStr.isEmpty() && Integer.parseInt(readStr) > 0) {
	        int read_page = Integer.parseInt(readStr);
	        proDao.update_read(user_id, latestBookId, read_page);

	        int totalRead = proDao.getTotalPagesRead(user_id, latestBookId);
	        int totalPages = proDao.getBookTotalPages(latestBookId);

	        System.out.println("▶ totalRead: " + totalRead + " / totalPages: " + totalPages);

	        if (totalRead >= totalPages) {
	            boolean finished = proDao.insertFinishedBook(user_id, latestBookId);
	        }

	        step = "target";
	    }
	
		request.getRequestDispatcher("/WEB-INF/jsp/studentHome.jsp").forward(request, response);
		
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

	    String targetStr = request.getParameter("target_page");
	    String readStr = request.getParameter("read_page");

	    String step = "target"; // 初期ステップ

	    try {
	        if (readStr != null && !readStr.isEmpty()) {
	            int read_page = Integer.parseInt(readStr);
	            if (read_page < 1 || read_page > 30) {
	                request.setAttribute("errorMessage", "読んだページ数は1～30の数値で入力してください。");
	                doGet(request, response);
	                return;
	            }

	            proDao.update_read(user_id, book_id, read_page);

	            int totalRead = proDao.getTotalPagesRead(user_id, book_id);
	            int totalPages = proDao.getBookTotalPages(book_id);
	            if (totalRead >= totalPages) {
	                proDao.insertFinishedBook(userId, book_id);
	            }

	            step = "target";  // 次は目標入力へ

	        } else if (targetStr != null && !targetStr.isEmpty()) {
	            int target_page = Integer.parseInt(targetStr);
	            if (target_page < 1 || target_page > 30) {
	                request.setAttribute("errorMessage", "目標ページ数は1～30の数値で入力してください。");
	                doGet(request, response);
	                return;
	            }

	            proDao.insert_target(user_id, book_id, target_page);
	            step = "record";  // 次は読書記録入力へ

	        } else {
	            // どちらも空または無効
	            request.setAttribute("errorMessage", "1～30の数値を入力してください。");
	            doGet(request, response);
	            return;
	        }

	    } catch (NumberFormatException e) {
	        request.setAttribute("errorMessage", "数字を入力してください。");
	        doGet(request, response);
	        return;
	    }

	    session.setAttribute("step", step);
	    doGet(request, response);
	}

	}

