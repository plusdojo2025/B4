package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CollectionDAO;
import dto.Collection;

/**
 * Servlet implementation class CollectionServlet
 */
@WebServlet("/CollectionServlet")
public class CollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId=1;
		int rankingId=1;

	//リクエストパラメーターからステータス名を取得
	String statusName = request.getParameter("statusName");
	String trophyphoto =request.getParameter("trophyPhoto");
	
	//DAOを使ってデータを取得
    CollectionDAO dao = new CollectionDAO();
    List<dto.Collection> collectionList = null;
	try {
		
		 if (statusName != null && !statusName.isEmpty()) {
	            // ステータス名で検索（userIdは例で0)
	            collectionList = dao.selectByStatusName(statusName, 0);
	        } else {
	            // ユーザーIDで一覧取得（例：userId=1）
	            collectionList = dao.selectByUserId(1);
	            
	            List<Collection> statusList = new ArrayList<>();
	            List<Collection> trophyList = new ArrayList<>();
	        }         
	}catch (ClassNotFoundException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
		collectionList = new ArrayList<>();
	}
	
	  System.out.println("件数: " + collectionList.size());
      for (Collection c : collectionList) {
          System.out.println("ステータス名: " + c.getStatusName());
      }
      
      System.out.println("件数: " + collectionList.size());
      for (Collection c : collectionList) {
          System.out.println("トロフィー: " + c.getTrophyPhoto());
      }
      
      // JSPにデータを渡す
    request.setAttribute("collectionList", collectionList);
    request.setAttribute("statusName", statusName);
    request.setAttribute("trophyPhoto", trophyphoto);
  
//    try {
//    	if (statusName != null && !statusName.isEmpty()) {
//    	collectionList = dao.selectByStatusName(statusName, 0);
//    	}
//    }catch(ClassNotFoundException e) {
 //   e.printStackTrace();
 //   }
   
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/collection.jsp");
    dispatcher.forward(request, response);
	

/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected record doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request response);
	}

}