package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ParentDAO;
import dao.ProgressDAO;
import dto.FinishBook;
import dto.Progress;
import dto.User;

/**
 * Servlet implementation class ParentHomeServlet
 */
@WebServlet("/ParentHomeServlet")
public class ParentHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParentHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        User user = (User) session.getAttribute("user");

       String users_id = user.getUsers_id(); 
//        int grade = user.getGrade(); // 学年
//        int schoolClass = user.getSchoolClass(); // クラス
       

       ParentDAO parDao = new ParentDAO();
       List<FinishBook> finishBookNewList = parDao.selectNewParent(users_id);
       FinishBook currentBook = finishBookNewList.isEmpty() ? null : finishBookNewList.get(0);

       Progress progress = null;
       if (currentBook != null) {
           int book_id = currentBook.getBook_id();  // ← ここでbook_idを取得

           ProgressDAO proDao = new ProgressDAO();
           List<Progress> selectTodayList = parDao.selectTodayParent(users_id, book_id);
           
           List<Progress> progressList = parDao.selectParent(users_id);
   		
           List<Integer> labels = new ArrayList<>();
           List<Integer> readData = new ArrayList<>();
           List<Integer> targetData = new ArrayList<>();
   		
   		for(Progress pro : progressList) {
   			labels.add(pro.getDay());
   			targetData.add(pro.getTarget_page());
   			readData.add(pro.getRead_page());
   		}
   		
   		Map<String, Object> chartData = new HashMap<>();
        chartData.put("labels", labels);
        chartData.put("readData", readData);
        chartData.put("targetData", targetData);

        String json = new Gson().toJson(chartData);

        session.setAttribute("chartData", json);

           if (!selectTodayList.isEmpty()) {
               progress = selectTodayList.get(0); // 1件だけ取得
           }
       }
       

       List<FinishBook> finishBookSelectNewList = parDao.selectNewListParent(users_id);
       request.setAttribute("finishBookSelectNewList", finishBookSelectNewList);
		

        request.setAttribute("currentBook", currentBook);
        request.setAttribute("todayProgress", progress);
		
		request.getRequestDispatcher("/WEB-INF/jsp/parentHome.jsp").forward(request, response);
		
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/webapp/LoginServlet");
//			return;
//		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        User user = (User) session.getAttribute("user");
        String user_id = user.getUsers_id(); 

        
      //今日読んだ本の詳細情報の取得
      		String bookIdStr = request.getParameter("book_id");
      		int book_id = Integer.parseInt(bookIdStr);
      		ParentDAO parDao = new ParentDAO();
      		List<Progress> progressToday = parDao.selectTodayParent(user_id, book_id);

		// 検索結果をセッションスコープに格納する
		session.setAttribute("progressToday", progressToday);
		
		// レイアウトページにフォワードする
		request.getRequestDispatcher("/WEB-INF/jsp/parentHome.jsp").forward(request, response);
	}

}
