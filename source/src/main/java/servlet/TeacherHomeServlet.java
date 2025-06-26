package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProgressDAO;
import dto.Progress;
import dto.User;

/**
 * Servlet implementation class TeacherHomeServlet
 */
@WebServlet("/TeacherHomeServlet")
public class TeacherHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

        User user = (User) session.getAttribute("user");
        
        LocalDate today = LocalDate.now();
		Date date = java.sql.Date.valueOf(today); // LocalDate → java.util.Date に変換
		request.setAttribute("today", date);
		

        int user_id = user.getId(); // ユニークID
        int typeId = user.getTypeId(); // タイプ（1＝教師、2=保護者、3=生徒）
        int grade = user.getGrade(); // 学年
	    int school_class = user.getSchoolClass(); // クラス
		request.setCharacterEncoding("UTF-8");
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.selectTeacherHome(grade, school_class);
		
		request.setAttribute("progressList", progressList);
		request.getRequestDispatcher("/WEB-INF/jsp/teacherHome.jsp").forward(request, response);
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

        int user_id = user.getId(); // ユニークID
        int typeId = user.getTypeId(); // タイプ（1＝教師、2=保護者、3=生徒）
        int grade = user.getGrade(); // 学年
	    int school_class = user.getSchoolClass(); // クラス
		request.setCharacterEncoding("UTF-8");
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.selectTeacherHome(grade, school_class);
		
		request.setAttribute("progressList", progressList);
		request.getRequestDispatcher("/WEB-INF/jsp/teacherHome.jsp").forward(request, response);
	}

}
