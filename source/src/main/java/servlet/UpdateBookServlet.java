package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dto.Book;

@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateBookServlet() {
        super();
    }

    // 編集画面表示（GET）
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // ログインさせる処理
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        // bookId を取得して対象の本データを取得
        int id = Integer.parseInt(request.getParameter("bookId"));
        BookDAO dao = new BookDAO();
        Book book = dao.selectById(id);

        // JSP に渡す
        request.setAttribute("book", book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/teacherUpdate.jsp");
        dispatcher.forward(request, response);
    }

    // 編集更新処理（POST）
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 文字コード設定
        request.setCharacterEncoding("UTF-8");

        // ログインチェック
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        // フォームから値取得
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String gets = request.getParameter("gets");
        int page = Integer.parseInt(request.getParameter("page"));
        int genreId = Integer.parseInt(request.getParameter("genreId"));
        String cover = request.getParameter("cover");

        // DTOへ格納
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGets(gets);
        book.setPage(page);
        book.setGenre_id(genreId);
        book.setCover(cover);

        // 更新処理実行
        BookDAO dao = new BookDAO();
        boolean result = dao.update(book);

        // 遷移先
        if (result) {
            response.sendRedirect(request.getContextPath() + "/BookListServlet");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
