package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IdPwDAO;
import dao.UserDAO;
import dto.IdPw;
import dto.User;  

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String usersId = request.getParameter("users_id");
        String pw = request.getParameter("password");
        System.out.println("[ログ] 入力されたユーザーID: " + usersId);
        System.out.println("[ログ] 入力されたパスワード: " + pw);

        if (usersId == null || usersId.isEmpty() || pw == null || pw.isEmpty()) {
            request.setAttribute("errorMessage", "ユーザーIDとパスワードは必須です。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        IdPwDAO iDao = new IdPwDAO();
        if (!iDao.isLoginOK(new IdPw(usersId, pw))) {
            request.setAttribute("errorMessage", "ユーザーIDまたはパスワードに誤りがあります。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

    
        UserDAO userDao = new UserDAO();
        List<User> userList = userDao.findByUserId(usersId);


        if (userList.size() == 1) {
            User user = userList.get(0);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if (user.getTypeId() == 1) {
                response.sendRedirect(request.getContextPath() + "/AdminServlet");
            } else {
                response.sendRedirect(request.getContextPath() + "/LoginProfileServlet");
            }
        } else if (userList.size() > 1) {
            HttpSession session = request.getSession();
            session.setAttribute("users_id", usersId);

            request.setAttribute("userList", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/select_account.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "該当するアカウントが見つかりません。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
