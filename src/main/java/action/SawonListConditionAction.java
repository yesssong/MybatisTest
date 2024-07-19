package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@WebServlet("/sawon/list_condition.do")
public class SawonListConditionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// /sawon/list_condition.do
		// /sawon/list_condition.do?deptno=0&sajob=all
		// /sawon/list_condition.do?deptno=0&sajob=부장
		// /sawon/list_condition.do?deptno=10&sajob=사원
		
		// 수신인코딩
		request.setCharacterEncoding("utf-8");
		
		// deptno 받기
		int deptno = 0;
		
		try {
			deptno=Integer.parseInt(request.getParameter("deptno"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// sajob 받기
		String sajob = request.getParameter("sajob");
		
		if(sajob==null) sajob="all";
		
		// sasex 받기
		String sasex = request.getParameter("sasex");
		
		if(sasex==null) sasex="all";
		
		// hire_year_10 받기 (입사년대)
		int hire_year_10 = 0;
		
		try {
			hire_year_10 = Integer.parseInt(request.getParameter("hire_year_10"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		
		// 검색할 조건을 전달할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 부서가 전체가 아니면
		if(deptno!=0) {
			map.put("deptno", deptno);
		}
		// 직급이 전체가 아니면
		if(!sajob.equals("all")) {
			map.put("sajob", sajob);
		}
		// 성별이 전체가 아니면
		if(!sasex.equals("all")) {
			map.put("sasex", sasex);	// map에 성별을 넣어라?
		}
		// 입사년대가 전체가 아니면
		if(hire_year_10!=0) {
			map.put("hire_year_10", hire_year_10);    // 검색 조건에 추가해줘 -> map
		}
		
		
		// 사원목록 데이터 가져오기						
		List<SawonVo> list = SawonDao.getInstance().selectList(map); 
		
		//request binding
		request.setAttribute("list", list);

		// Dispatcher 형식으로 호출
		String forward_page = "sawon_list.jsp";		// sawon_list.jsp 만들기
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}