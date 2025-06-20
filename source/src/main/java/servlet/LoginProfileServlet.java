package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;

@WebServlet("/LoginProfileServlet")
public class LoginProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginProfile.jsp");
        dispatcher.forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userType = request.getParameter("type_id");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && userType != null) {
            user.setTypeId(Integer.parseInt(userType));
            session.setAttribute("user", user);

            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "タイプが選択されていません。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginProfile.jsp");
            dispatcher.forward(request, response);
        }
    }
}