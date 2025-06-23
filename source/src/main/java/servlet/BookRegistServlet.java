package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.BookDAO;
import dto.Book;
import dto.User;

@WebServlet("/BookRegistServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,  // 一時ファイル化の閾値（1MB）
    maxFileSize = 1024 * 1024 * 5,    // アップロード1ファイル上限（5MB）
    maxRequestSize = 1024 * 1024 * 10 // リクエスト全体上限（10MB）
)
public class BookRegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
     // ログインさせる処理
    	HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        
        // セッションからログインユーザーのIDを取得
        Integer userId = user.getId();

        // フォームの入力値を取得
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String gets = request.getParameter("gets");
        String pageStr = request.getParameter("page");
        String genreIdStr = request.getParameter("genre_id");

        int page = 0;
        int genreId = 0;

        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "ページ数は数値で入力してください。");
            request.getRequestDispatcher("/WEB-INF/jsp/teacherRegist.jsp").forward(request, response);
            return;
        }

        try {
            genreId = Integer.parseInt(genreIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "ジャンルが不正です。");
            request.getRequestDispatcher("/WEB-INF/jsp/teacherRegist.jsp").forward(request, response);
            return;
        }

        // ファイル処理
        Part part = request.getPart("cover");
        String originalFileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
        String ext = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex >= 0) {
            ext = originalFileName.substring(dotIndex).toLowerCase();
        }

        // 保存先フォルダ（/img）
        String savePath = getServletContext().getRealPath("/img");
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // coverX の連番ファイル名決定
        int maxNumber = 0;
        for (String fileName : dir.list()) {
            if (fileName.startsWith("cover") && fileName.endsWith(ext)) {
                try {
                    String num = fileName.substring(5, fileName.lastIndexOf("."));
                    int n = Integer.parseInt(num);
                    maxNumber = Math.max(maxNumber, n);
                } catch (NumberFormatException ignored) {}
            }
        }

        int nextNumber = maxNumber + 1;
        String newFileName = "cover" + nextNumber + ext;
        File saveFile = new File(dir, newFileName);
        part.write(saveFile.getAbsolutePath());
        
        System.out.println("=== ファイル保存ログ ===");
        System.out.println("保存ファイル名: " + newFileName);
        System.out.println("保存先パス: " + saveFile.getAbsolutePath());
        System.out.println("ファイル存在する？: " + saveFile.exists());


        // DTO にデータをセット
        Book book = new Book(0, title, author, publisher, gets, page, genreId, newFileName, null, null);
        book.setUser_id(userId);

        // 登録処理
        BookDAO dao = new BookDAO();
        boolean success = dao.insert(book);

        if (success) {
            request.setAttribute("message", "登録成功！");
            request.setAttribute("coverFileName", newFileName);
        } else {
            request.setAttribute("message", "登録失敗");
        }

        // JSPへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/teacherRegist.jsp");
        dispatcher.forward(request, response);
    }
}
