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

import dao.UserDAO;
import dto.User;

@WebServlet("/SelectAccountServlet")
public class SelectAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // doGet でユーザーリストを取得し、JSPへ渡す
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
    	HttpSession session = request.getSession(false);
    	String usersIdStr = request.getParameter("users_id");

    	if ((usersIdStr == null || usersIdStr.isEmpty()) && session != null && session.getAttribute("users_id") != null) {
    	    usersIdStr = session.getAttribute("users_id").toString();
    	}

        if (usersIdStr == null || usersIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "users_idが指定されていません。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/select_account.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int usersId = 0;
        try {
            usersId = Integer.parseInt(usersIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "users_idは数値で指定してください。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/select_account.jsp");
            dispatcher.forward(request, response);
            return;
        }

        UserDAO userDao = new UserDAO();
        // users_idで複数ユーザーを取得
        List<User> userList = userDao.findByUsersId(usersId);

        if (userList == null || userList.isEmpty()) {
            request.setAttribute("errorMessage", "該当するアカウントが見つかりません。");
        } else {
            request.setAttribute("userList", userList);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/select_account.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォームから送信された選択されたユーザーIDを取得
        String selectedIdStr = request.getParameter("selectedUserId");

        // ユーザーIDが送られていない場合のエラーハンドリング
        if (selectedIdStr == null || selectedIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "アカウントが選択されていません。");
            // ユーザー一覧表示のためにdoGetを呼び出す
            doGet(request, response);
            return;
        }

        int selectedId = 0;
        try {
            selectedId = Integer.parseInt(selectedIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "不正なアカウントIDが指定されました。");
            doGet(request, response);
            return;
        }

        UserDAO userDao = new UserDAO();
        User selectedUser = userDao.findByPrimaryKey(selectedId);

        // ユーザーが見つからない場合のエラー処理
        if (selectedUser == null) {
            request.setAttribute("errorMessage", "該当ユーザーが見つかりません。");
            doGet(request, response);
            return;
        }

        // セッションにユーザー情報をセット
        HttpSession session = request.getSession();
        session.setAttribute("user", selectedUser);
        
        String view = "/WEB-INF/jsp/studentHome.jsp";
        
        // ユーザー種別に応じて遷移先を変える
        int typeId = selectedUser.getTypeId();
        if (typeId == 2) {
        	view = "/WEB-INF/jsp/parentHome.jsp";
        } else if (typeId == 3) {
        	view = "/WEB-INF/jsp/studentHome.jsp";
        } else {
            request.setAttribute("errorMessage", "不明なユーザー種別です。");
            doGet(request, response);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

}
