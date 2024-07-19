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
@WebServlet("/sawon/list.do")
public class SawonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /sawon/list.do
		// /sawon/list.do?deptno=0
		// /sawon/list.do?deptno=10
		int deptno = 0;
		try {
			deptno=Integer.parseInt(request.getParameter("deptno"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 데이터 가져오기
		List<SawonVo> list = null; 
		
		if(deptno==0) {		// 전체 조회
			list = SawonDao.getInstance().selectList();
		} else {			// 부서별 조회
			list = SawonDao.getInstance().selectListFromDeptno(deptno);
		}
		
		//request binding
		request.setAttribute("list", list);

		// Dispatcher 형식으로 호출
		String forward_page = "sawon_list.jsp";		// sawon_list.jsp 만들기
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}