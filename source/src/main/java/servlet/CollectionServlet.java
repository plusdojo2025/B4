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
	//String statusName = request.getParameter("statusName");
//	String trophyPhoto =request.getParameter("trophyPhoto");
	//
	
		//useIdを使ってstatuses_logsテーブルから statuses_idを取得する
		//statuses_idを使ってstatusesテーブルからnameを取得する
		
		//useIdを使ってtrophys_logsテーブルから trophy_idを取得する
		//trophy_idを使ってtroplysテーブルからtrophy_photoを取得する
	
	//DAOを使ってデータを取得
    CollectionDAO colldao = new CollectionDAO();
    List<dto.Collection> collectionList;
    
    
	try {
		
		 if (statusName != null && !statusName.isEmpty()) {
	            // ステータス名で検索
	            collectionList = colldao.selectByStatusName(1);
	        } else {
	        	
	            // ユーザーIDで一覧取得（例：userId=1）
	            collectionList = colldao.selectByStatusName(1);
//	            List<Collection> statusList = new ArrayList<>();
	        } 
		 
		 if (trophyPhoto != null && !trophyPhoto.isEmpty()) {
	            // トロフィーで検索
	            collectionList = colldao.selectByTrophyPhoto(1);
	            
	        } else {
	            // ユーザーIDで一覧取得（例：userId=1）    
	         collectionList = colldao.selectByTrophyPhoto(1);
//	           List<Collection> trophyList = new ArrayList<>();
	        }
	
		 
	}catch (ClassNotFoundException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
		collectionList = new ArrayList<>();
	}
	
	if (collectionList != null && !collectionList.isEmpty()) {
	    System.out.println("件数: " + collectionList.size());
	    for (dto.Collection c : collectionList) {
	        System.out.println("ステータス名: " + c.getStatusName());
	    }
	} else {
	    System.out.println("collectionListがnull、または中身が空です");
	}
	
	
	  System.out.println("件数: " + collectionList.size());
      for (dto.Collection c : collectionList) {
          System.out.println("ステータス名: " + c.getStatusName());
      }
      
      System.out.println("件数: " + collectionList.size());
        for (dto.Collection c : collectionList) {          
        System.out.println("トロフィー: " + c.getTrophyPhoto());
      }
      
      // JSPにデータを渡す
    request.setAttribute("collectionList", collectionList);
    request.setAttribute("statusName", statusName);
    request.setAttribute("trophyPhoto", trophyPhoto);
  
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