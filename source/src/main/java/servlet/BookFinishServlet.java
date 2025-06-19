package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FinishBookDAO;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
        	FinishBookDAO dao = new FinishBookDAO();
            int statusId = dao.getStatusId(userId, bookId);

            if (statusId == 0) {
                dao.insert(userId, bookId); // type_id = 1（未読了）として登録
            }
        }

        response.sendRedirect("BookDetailServlet?bookId=" + bookId);
	}

}
