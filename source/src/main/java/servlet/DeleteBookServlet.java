package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dto.User;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteBookServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ログインチェック
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        User user = (User) session.getAttribute("user");
        
        // パラメータ取得
        String idStr = request.getParameter("bookId");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);

            // 削除処理
            BookDAO dao = new BookDAO();
            boolean result = dao.delete(id);

            if (result) {
                // 削除成功 → 一覧へ
                response.sendRedirect(request.getContextPath() + "/BookListServlet");
            } else {
                // 削除失敗 → エラー画面へ（※必要であれば変更）
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {
            // パラメータなし → 一覧へ戻す
            response.sendRedirect(request.getContextPath() + "/BookListServlet");
        }
    }
}
