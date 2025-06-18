package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IdPwDAO;
import dto.IdPw;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    // ログイン画面表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    // ログイン処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        IdPw idpw = new IdPw(id, pw);
        IdPwDAO dao = new IdPwDAO();

        boolean isLogin = dao.login(idpw);

        if (isLogin) {
            // 認証成功時はセッションにIDを保存
            HttpSession session = request.getSession();
            session.setAttribute("loginId", id);
            response.sendRedirect("LoginProfileServlet"); // プロフィール取得画面へリダイレクト
        } else {
            // 認証失敗はログイン画面へ戻す
            request.setAttribute("errorMessage", "IDまたはパスワードが正しくありません。");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}

