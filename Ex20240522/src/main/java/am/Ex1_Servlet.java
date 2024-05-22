package am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Ex1_Servlet
 */
public class Ex1_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ex1_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답시 한글
		response.setContentType("text/html; charset=utf-8");
		
		// 응답(response)을 하기 위해 스트림(stream) 준비
		PrintWriter out = response.getWriter(); // out은 외부와 연결됨
		
		// 응답시작
		out.println("<h1>내 Servlet</h2>");
		out.println("<h2></h2>");
		
		
		//스트림 종료(닫기)
		out.close(); //  외부와 연결된 out이기에 종료를 해주어야 처리속도가 느려지지 않는다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
