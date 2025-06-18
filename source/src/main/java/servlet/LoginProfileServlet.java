package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IdPwDAO;

@WebServlet("/LoginProfileServlet")
public class LoginProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET: ログイン後最初に呼ばれる
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        String loginId = (String) session.getAttribute("loginId");

        IdPwDAO dao = new IdPwDAO();
        int typeId = dao.getUserTypeById(loginId);

        if (typeId == 1) {
            // 管理者 → そのままプロフィール画面へ
            request.setAttribute("loginId", loginId);
            request.setAttribute("profile", "管理者");
            request.getRequestDispatcher("/WEB-INF/jsp/loginProfile.jsp").forward(request, response);
        } else {
            // 保護者または生徒 → 選択画面へ
            session.setAttribute("typeId", typeId); // 必要なら保持
            request.getRequestDispatcher("/WEB-INF/jsp/selectProfile.jsp").forward(request, response);
        }
    }

    // POST: 選択画面からの結果受け取り
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginId") == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        String loginId = (String) session.getAttribute("loginId");
        String selectedProfile = request.getParameter("profileType");

        String profile = "不明";
        if ("2".equals(selectedProfile)) {
            profile = "保護者";
        } else if ("3".equals(selectedProfile)) {
            profile = "生徒";
        }

        request.setAttribute("loginId", loginId);
        request.setAttribute("profile", profile);
        request.getRequestDispatcher("/WEB-INF/jsp/loginProfile.jsp").forward(request, response);
    }
}

