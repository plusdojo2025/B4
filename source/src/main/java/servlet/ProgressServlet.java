package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProgressDAO;
import dto.Progress;
/**
 * Servlet implementation class ProgressServlet
 */
@WebServlet("/ProgressServlet")
public class ProgressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/webapp/LoginServlet");
//			return;
//		}
		request.setCharacterEncoding("UTF-8");
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int month = Integer.parseInt(request.getParameter("month"));
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.select(0, user_id, 0, 0, 0, null, null, month);
		
		request.setAttribute("progressList", progressList);
		
		request.getRequestDispatcher("/WEB-INF/jsp/teacherProgress.jsp").forward(request, response);
		
//		HttpSession session = request.getSession();
//        String userTypes = (String) session.getAttribute("UserTypes");
		
//        String jspPath = "";
        
        
//        if("student".equals(userTypes)) {
//        	request.getRequestDispatcher("/WEB-INF/jsp/studentProgress.jsp").forward(request, response);
//        }
//        else if("parents".equals(userTypes)) {
//        	request.getRequestDispatcher("/WEB-INF/jsp/parentProgress.jsp").forward(request, response);
//        }
//        else if("teacher".equals(userTypes)) {
//        	request.getRequestDispatcher("/WEB-INF/jsp/teacherProgress.jsp").forward(request, response);
 //       }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
