package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProgressDAO;
import dto.Progress;
import dto.Result;
/**
 * Servlet implementation class ProgressServlet
 */
@WebServlet("/ProgressServlet")
public class ProgressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/webapp/LoginServlet");
//			return;
//		}
//		User user = (User) session.getAttribute("user");
		
		
        String view = "/WEB-INF/jsp/teacherProgress.jsp";
        
 //       if(user != null) {
//        	switch (user.getTypeId()) {
//        	case 1:
 //       		view = "/WEB-INF/jsp/teacherProgress.jsp";
 //       	case 2:
 //       		view = "/WEB-INF/jsp/parentProgress.jsp";
 //       	case 3:
  //      		view = "/WEB-INF/jsp/studentProgress.jsp";
 //       		break;
 //       	default: 
 //       		view = "/WEB-INF/jsp/teacherProgress.jsp";
 //       	}
 //       }
        
        request.setCharacterEncoding("UTF-8");
		
        String monthStr = request.getParameter("month");
        int month = 0;

        if (monthStr != null && !monthStr.isEmpty()) {
            try {
                month = Integer.parseInt(monthStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
		int user_id = 1;
		
		ProgressDAO proDao = new ProgressDAO();
		List<Progress> progressList = proDao.select(user_id, month);
		
//        List<Integer> labels = new ArrayList<>();
//        List<Integer> readData = new ArrayList<>();
//       List<Integer> targetData = new ArrayList<>();
		
//		for(Progress pro : progressList) {
//			labels.add(pro.getDay());
//			targetData.add(pro.getTarget_page());
//			readData.add(pro.getRead_page());
//		}
		
//		Map<String, Object> chartData = new HashMap<>();
//        chartData.put("labels", labels);
//        chartData.put("readData", readData);
 //       chartData.put("targetData", targetData);

        String jsonData = new Gson().toJson(progressList);

        request.setAttribute("jsonData", jsonData);

		// 検索結果をセッションスコープに格納する
//		List<Progress> progress = (List<Progress>)progressList;
		request.setAttribute("selectMonth", month);
//		request.setAttribute("progressList", progressList);

        
        request.getRequestDispatcher(view).forward(request, response);
//        	request.getRequestDispatcher("/WEB-INF/jsp/studentProgress.jsp").forward(request, response);
//        }
//        else if("parents".equals(userTypes)) {
//        	request.getRequestDispatcher("/WEB-INF/jsp/parentProgress.jsp").forward(request, response);
//        }
//        else if("teacher".equals(userTypes)) {
//        	request.getRequestDispatcher("/WEB-INF/jsp/teacherProgress.jsp").forward(request, response);
 //       }
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/webapp/LoginServlet");
//			return;
//		}
		request.setCharacterEncoding("UTF-8");
		
		String submit = request.getParameter("submit");
		
		if (submit.equals("送信")) {
			
			request.setAttribute("result", new Result("プリント完了！", "印刷が完了しました。", "/b4/ProgressServlet"));
			
			}
		
		doGet(request,response);  
	}

}
