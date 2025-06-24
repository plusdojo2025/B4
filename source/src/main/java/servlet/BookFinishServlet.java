package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FinishBookDAO;
import dto.User;

/**
 * Servlet implementation class BookFinishServlet
 */
@WebServlet("/BookFinishServlet")
public class BookFinishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookFinishServlet() {
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
        
        doPost(request, response);
        
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
	    
	    int bookId = Integer.parseInt(request.getParameter("bookId"));
	    System.out.println("bookId = " + bookId);
	    System.out.println("userId = " + userId);

	    FinishBookDAO dao = new FinishBookDAO();
	    int statusId = dao.getTypeId(userId, bookId);
	    System.out.println("statusId = " + statusId);
	    
	    System.out.println("=== BookFinishServlet 到達 ===");


//	    if (statusId == 0) {
//	        boolean success = dao.insert(userId, bookId);
//	        System.out.println("insert success? → " + success);
//	    } else {
//	        System.out.println("すでに登録済みのため、スキップしました");
//	    }
	    if (statusId == 0) {
	        dao.insert(userId, bookId); // 新規登録
	        System.out.println("insert success");
	    } else {
	        dao.updateTimestamp(userId, bookId); // 登録済 → 日時更新
	        System.out.println("timestamp updated");
	    }


//	    String view = "/WEB-INF/jsp/StudentHomeServlet.jsp";
	    switch (user.getTypeId()) {
	        case 1: response.sendRedirect(request.getContextPath() + "/TeacherHomeServlet");
	        break;
	        case 2: response.sendRedirect(request.getContextPath() + "/ParentHomeServlet");
	        break;
	        case 3: response.sendRedirect(request.getContextPath() + "/StudentHomeServlet");
	        break;
	    }

//	    RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	    
//	    dispatcher.forward(request, response);
	}


}
