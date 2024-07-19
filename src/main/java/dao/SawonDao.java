package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.SawonVo;

public class SawonDao {

	//Mybatis 객체 선언
	SqlSessionFactory factory;
	
	// single-ton pattern : 객체 한 개만 생성하여 이용하자
	static SawonDao single = null;

	public static SawonDao getInstance() {

		// 객체 없을 시 생성해라
		if (single == null)
			single = new SawonDao();
		return single;
	}

	// private : 외부에서 객체 생성 하지 마라
	private SawonDao() {
		// MyBatis 객체 정보 얻어오기 ->  MyBatis 사용하기 위해서
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	// 전체 조회
	public List<SawonVo> selectList() {

		List<SawonVo> list = null;
		
		// 1. SqlSession 얻어오기(mybatis 수행객체)
		SqlSession sqlSession = factory.openSession();
		
		// 2. 작업수행					 namespace, mapper_id
		list = sqlSession.selectList("sawon.sawon_list");	// select한 결과를 ArrayList로 포장해줘
		
		// 3. 닫기 -> 공식. 무조건 닫아줘야 함
		sqlSession.close();

		return list;

	} // end : selectList

	// 부서별 조회 : selectListFromDeptno
	public List<SawonVo> selectListFromDeptno(int deptno) {
		List<SawonVo> list = null;
		
		// 1. SqlSession 얻어오기(mybatis 수행객체)
		SqlSession sqlSession = factory.openSession();
		
		// 2. 작업수행					 namespace, mapper_id		parameter
		list = sqlSession.selectList("sawon.sawon_list_deptno", deptno);	// select한 결과를 ArrayList로 포장해줘
		
		// 3. 닫기 -> 공식. 무조건 닫아줘야 함
		sqlSession.close();

		return list;
	}

	// 부서별 조회 : selectListFromSajob
		public List<SawonVo> selectListFromSajob(String sajob) {
			List<SawonVo> list = null;
			
			// 1. SqlSession 얻어오기(mybatis 수행객체)
			SqlSession sqlSession = factory.openSession();
			
			// 2. 작업수행					 namespace, mapper_id		parameter
			list = sqlSession.selectList("sawon.sawon_list_sajob", sajob);	// select한 결과를 ArrayList로 포장해줘
			
			// 3. 닫기 -> 공식. 무조건 닫아줘야 함
			sqlSession.close();

			return list;
		}

		public List<SawonVo> selectList(Map<String, Object> map) {
			List<SawonVo> list = null;
			
			// 1. SqlSession 얻어오기(mybatis 수행객체)
			SqlSession sqlSession = factory.openSession();
			
			// 2. 작업수행					 namespace, mapper_id		parameter
			list = sqlSession.selectList("sawon.sawon_list_condition", map);	// map 안에 deptno의 값, sajob
			
			// 3. 닫기 -> 공식. 무조건 닫아줘야 함
			sqlSession.close();

			return list;
		}
}