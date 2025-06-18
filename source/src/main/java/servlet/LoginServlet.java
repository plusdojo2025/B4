package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IdPwDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GETメソッドでログインフォームを表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    // POSTメソッドでログイン処理を実行
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        // ログインIDまたはパスワードが空でないか確認
        if (loginId == null || password == null || loginId.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "IDまたはパスワードが空です");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
            return;
        }

        IdPwDAO idPwDao = new IdPwDAO();
        boolean isAuthenticated = idPwDao.authenticate(loginId, password);

        if (isAuthenticated) {
            HttpSession session = request.getSession(true);
            session.setAttribute("loginId", loginId);  // セッションにログインIDをセット

            // ユーザータイプ（管理者、親、子）を取得
            int typeId = idPwDao.getUserTypeById(loginId);

            if (typeId == 1) {
                // 管理者の場合はselectProfile.jspに遷移
                request.getRequestDispatcher("/WEB-INF/jsp/selectProfile.jsp").forward(request, response);
            } else {
                // 親または子の場合はselectProfile.jspに遷移
                request.getRequestDispatcher("/WEB-INF/jsp/selectProfile.jsp").forward(request, response);
            }
        } else {
            // 認証失敗の場合
            request.setAttribute("errorMessage", "IDまたはパスワードが正しくありません。");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}

