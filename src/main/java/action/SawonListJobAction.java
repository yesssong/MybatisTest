package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SawonDao;
import vo.SawonVo;

/**
 * Servlet implementation class SawonListAction
 */
@WebServlet("/sawon/list_job.do")
public class SawonListJobAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /sawon/list_job.do			=> null
		// /sawon/list_job.do?sajob=	=> ""
		// /sawon/list_job.do?sajob=all
		// /sawon/list_job.do?sajob=부장
		
		// 수신인코딩
		request.setCharacterEncoding("utf-8");
		
		String sajob = request.getParameter("sajob");
		
		if(sajob==null || sajob.isEmpty()) {		// sajob.isEmpty() = sajob.equals("")  => 같은 표현(값이 비어있냐)
			sajob = "all";
		}
		// 데이터 가져오기
		List<SawonVo> list = null; 
		
		if(sajob.equals("all")) {		// 전체 조회
			list = SawonDao.getInstance().selectList();
		} else {				// 직급별 조회
			list = SawonDao.getInstance().selectListFromSajob(sajob);
		}
		
		//request binding
		request.setAttribute("list", list);

		// Dispatcher 형식으로 호출
		String forward_page = "sawon_list.jsp";		// sawon_list.jsp 만들기
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}