package servlet;


import java.io.IOException;
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
		request.getRequestDispatcher("/WEB-INF/jsp/collection.jsp").forward(request, response);
	
		
	//リクエストパラメーターからステータス名を取得
	String statusName = request.getParameter("statusName");
	
	//DAOを使ってデータを取得
    CollectionDAO dao = new CollectionDAO();
    List<dto.Collection> collectionList = null;
	try {
		collectionList = dao.selectByUserId(1);
		
		
	} catch (ClassNotFoundException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
	}
	
	  System.out.println("件数: " + collectionList.size());
      for (Collection c : collectionList) {
          System.out.println("ステータス名: " + c.getStatusName());
      }
      
    request.setAttribute("collectionList", collectionList);
    
    try {
    	if (statusName != null && !statusName.isEmpty()) {
    	collectionList = dao.selectByStatusName(statusName, 0);
    	}
    }catch(ClassNotFoundException e) {
    e.printStackTrace();
    }
   
	
    // JSPにデータを渡す
    request.setAttribute("collectionList", collectionList);
    request.setAttribute("statusName", statusName); // 表示用に渡しておく

    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/collection.jsp");
    dispatcher.forward(request, response);
	}


/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected record doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request response);
	}

