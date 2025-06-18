package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginProfileServlet")
public class LoginProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // POSTメソッドでプロフィール選択後の処理
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
            profile = "親";
        } else if ("3".equals(selectedProfile)) {
            profile = "子供";
        }

        // 役割情報をセッションに保存
        session.setAttribute("profile", profile);

        // 結果画面に遷移
        request.setAttribute("loginId", loginId);
        request.setAttribute("profile", profile);
        request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
    }
}

