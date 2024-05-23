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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Servlet implementation class Ex8_Search
 */
public class Ex8_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex8_Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		Reader r = Resources.getResourceAsReader("pm/config/config.xml");
		
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		r.close();
		
		SqlSession ss = factory.openSession();
		
		
		
		
		String keyword = request.getParameter("keyword");
		String search = request.getParameter("search");

		
		Map<String, String> e_map = new HashMap<>();
		
		e_map.put(keyword, search);
		
		List<EmpVO> e_list = ss.selectList("emp.search", e_map);
		
		
		PrintWriter out = response.getWriter();
		
		StringBuffer sb = new StringBuffer("<h2>["+keyword+":'"+search+"']검색결과</h2>");
		
		sb.append("<ol>");
//		for(int i=0; i<e_list.size(); i++) {
//			EmpVO evo = e_list.get(i);
		for(EmpVO evo : e_list) { //개선된 loop || for(자료형 변수명 : 배열)
			sb.append("<li>");
			sb.append(evo.getEmpno());
			sb.append(" | ");
			sb.append(evo.getEname());
			sb.append(" | ");
			sb.append(evo.getJob());
			sb.append("</li>");
		}
		sb.append("</ol>");
		
		
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
