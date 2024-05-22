package pm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pm.vo.EmpVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Servlet implementation class Ex7_EmpAll
 */
public class Ex7_EmpAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex7_EmpAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// myBatis를 활용하기 위해
		// myBatis.jar, mysql-connector가 필요함.
		
		// config.xml 생성
		
		// 환경설정파일을 연결하는 스트림 생성
		Reader r = Resources.getResourceAsReader("pm/config/config.xml");
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
		
		// 원하는 sql문을 호출하기 위해
		
		SqlSession ss = factory.openSession();
		
		List<EmpVO> list = ss.selectList("emp.all");
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<ol>");
		for(int i=0; i<list.size(); i++) {
			EmpVO evo = list.get(i);
			sb.append("<li>");
			sb.append(evo.getEmpno());
			sb.append(" | ");
			sb.append(evo.getEname());
			sb.append(" | ");
			sb.append(evo.getJob());
			sb.append("</li>");
		}
		sb.append("</ol>");
		
		//응답 시 한글처리
		response.setContentType("text/html; charset=utf-8");
		
		//응답을 위해 스트림 준비
		PrintWriter out = response.getWriter();
		
		//응답 시작
		out.println("<h1>사원목록</h1>");
		out.print(sb.toString());
		out.close();
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
