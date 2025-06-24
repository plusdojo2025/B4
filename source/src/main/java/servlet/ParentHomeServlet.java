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

import dao.FinishBookDAO;
import dao.ProgressDAO;
import dto.FinishBook;
import dto.Progress;

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
		request.setCharacterEncoding("UTF-8");
		
		int user_id = 1;
		int book_id = 6;
		int month = 6;
		int day = 23;
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.selectToday(user_id, book_id, month, day);
		

		FinishBookDAO finDao = new FinishBookDAO();
		List<FinishBook> finishBookList = finDao.selectNew(user_id);
		


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

		
		session.setAttribute("finishBookList", finishBookList);
		
		session.setAttribute("progressList", progressList);
		
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
		
		FinishBookDAO finDao = new FinishBookDAO();
		List<FinishBook> finishBookList = finDao.selectAll();

		session.setAttribute("finishBookList", finishBookList);
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.selectAll();

		// 検索結果をセッションスコープに格納する
		session.setAttribute("progressList", progressList);
		
		// レイアウトページにフォワードする
		request.getRequestDispatcher("/WEB-INF/jsp/parentHome.jsp").forward(request, response);
	}

}
