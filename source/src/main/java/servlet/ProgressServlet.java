package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import dao.ParentDAO;
import dao.ProgressDAO;
import dto.Progress;
import dto.Result;
import dto.User;
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
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		User user = (User) session.getAttribute("user");
		
		
        String view = "/WEB-INF/jsp/teacherProgress.jsp";
        
       
       if(user != null) {
    	   if(user.getTypeId() == 1) {
        	   view = "/WEB-INF/jsp/teacherProgress.jsp";
        	   String users_id = user.getUsers_id(); 
               
               ParentDAO parDAO = new ParentDAO();
               List<User> userList = parDAO.selectUser(users_id);
               
               ArrayList<Integer> u = new ArrayList<Integer>();
               
               for(User stu : userList) {
          			u.add(stu.getId());
          		}
               
               Integer user_id = u.get(0);
               
               request.setCharacterEncoding("UTF-8");

       		
       		ProgressDAO proDao = new ProgressDAO();
       		List<Progress> progressList = proDao.select(user_id);
       		
               Gson gson = new GsonBuilder()
       			    .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
       			        @Override
       			        public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
       			            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
       			        }
       			    })
       			    .create();
               String jsonProgress = gson.toJson(progressList);

       		// 検索結果をセッションスコープに格納する
//       		List<Progress> progress = (List<Progress>)progressList;
       		
       		request.setAttribute("jsonProgress", jsonProgress);
//       		request.setAttribute("progressList", progressList);

               
               request.getRequestDispatcher(view).forward(request, response);
           }
           else if(user.getTypeId() == 2) {
        	   view = "/WEB-INF/jsp/parentProgress.jsp";
        	   String users_id = user.getUsers_id(); 
               
               ParentDAO parDAO = new ParentDAO();
               List<User> userList = parDAO.selectUser(users_id);
               
               ArrayList<Integer> u = new ArrayList<Integer>();
               
               for(User stu : userList) {
          			u.add(stu.getId());
          		}
               
               Integer user_id = u.get(0);
               
               request.setCharacterEncoding("UTF-8");

       		
       		ProgressDAO proDao = new ProgressDAO();
       		List<Progress> progressList = proDao.select(user_id);
       		


               Gson gson = new GsonBuilder()
       			    .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
       			        @Override
       			        public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
       			            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
       			        }
       			    })
       			    .create();
               String jsonProgress = gson.toJson(progressList);

       		// 検索結果をセッションスコープに格納する
//       		List<Progress> progress = (List<Progress>)progressList;
       		
       		request.setAttribute("jsonProgress", jsonProgress);
//       		request.setAttribute("progressList", progressList);

               
               request.getRequestDispatcher(view).forward(request, response);
           }
           else if(user.getTypeId() == 3) {
        	   view = "/WEB-INF/jsp/studentProgress.jsp";
        	   int user_id = user.getId();
        	   
        	   request.setCharacterEncoding("UTF-8");

       		
       		ProgressDAO proDao = new ProgressDAO();
       		List<Progress> progressList = proDao.select(user_id);
       		


               Gson gson = new GsonBuilder()
       			    .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
       			        @Override
       			        public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
       			            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
       			        }
       			    })
       			    .create();
               String jsonProgress = gson.toJson(progressList);
       		// 検索結果をセッションスコープに格納する
//       		List<Progress> progress = (List<Progress>)progressList;
       		
       		request.setAttribute("jsonProgress", jsonProgress);
//       		request.setAttribute("progressList", progressList);

               
               request.getRequestDispatcher(view).forward(request, response);
           }
           else {
        	   view = "/WEB-INF/jsp/teacherProgress.jsp";
        	   String users_id = user.getUsers_id(); 
               
               ParentDAO parDAO = new ParentDAO();
               List<User> userList = parDAO.selectUser(users_id);
               
               ArrayList<Integer> u = new ArrayList<Integer>();
               
               for(User stu : userList) {
          			u.add(stu.getId());
          		}
               
               Integer user_id = u.get(0);
               
               request.setCharacterEncoding("UTF-8");

       		
       		ProgressDAO proDao = new ProgressDAO();
       		List<Progress> progressList = proDao.select(user_id);
       		
               Gson gson = new GsonBuilder()
       			    .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
       			        @Override
       			        public JsonElement serialize(LocalDateTime src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
       			            return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
       			        }
       			    })
       			    .create();
               String jsonProgress = gson.toJson(progressList);

       		// 検索結果をセッションスコープに格納する
//       		List<Progress> progress = (List<Progress>)progressList;
       		
       		request.setAttribute("jsonProgress", jsonProgress);
//       		request.setAttribute("progressList", progressList);

               
               request.getRequestDispatcher(view).forward(request, response);
           }
       }
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		User user = (User) session.getAttribute("user");
		
		String view = "/WEB-INF/jsp/teacherProgress.jsp";
        
	       if(user != null) {
	        	switch (user.getTypeId()) {
	        	case 1:
	        		view = "/WEB-INF/jsp/teacherProgress.jsp";
	        	case 2:
	        		view = "/WEB-INF/jsp/parentProgress.jsp";
	        	case 3:
	        		view = "/WEB-INF/jsp/studentProgress.jsp";
	        		break;
	        	default: 
	        		view = "/WEB-INF/jsp/teacherProgress.jsp";
	        	}
	       }
		
		request.setCharacterEncoding("UTF-8");
		
		String submit = request.getParameter("submit");
		
		if (submit.equals("送信")) {
			
			request.setAttribute("result", new Result("送信完了！", "送信が完了しました。", "/b4/ProgressServlet"));
			
			}
		
		request.getRequestDispatcher(view).forward(request, response);
	}

}
