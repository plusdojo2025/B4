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

import dao.RankingDAO;
import dto.Ranking;
/**
 * Servlet implementation class RankingServlet
 */

@WebServlet("/RankingServlet")
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

		
		// DAOを呼び出してランキングデータを取得
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String rankingType = request.getParameter("rankingType"); // class or genre
		    String schoolClassParam = request.getParameter("school_class");
		    String genreIdParam = request.getParameter("genre_id");
		    String month = request.getParameter("month");
		    
		    RankingDAO dao = new RankingDAO();
		    List<Ranking> RankList = new ArrayList<>();
		    String title = "";
		    if(month == null || month.isEmpty()) {
		    	title = "月が指定されていません";
		    }else if("genre".equals(rankingType)) {
		        if (genreIdParam != null && !genreIdParam.isEmpty()) {
		            int genreId = Integer.parseInt(genreIdParam);
		            RankList = dao.selectByGenre(genreId, month);
		            title = "ジャンル別ランキング(" + month + ")";
		        } else {
		            
		            title = "ジャンルが選択されていません";
		        }
		    } else { // デフォルト：クラス別
		        int schoolClass = 1;
		        if (schoolClassParam != null && !schoolClassParam.isEmpty()) {
		            schoolClass = Integer.parseInt(schoolClassParam);
		        }
		        RankList = dao.selectBySchool_class(schoolClass, month);
		        title = "クラス別ランキング(" + month + ")";
		    }

		    request.setAttribute("RankList", RankList);
		    request.setAttribute("rankingType", rankingType);
		    request.setAttribute("title", title);
		    request.setAttribute("selectedMonth", month);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/teacherRanking.jsp");
		    dispatcher.forward(request, response);

		}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}