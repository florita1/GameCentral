package answers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExceptionHandlerPage
 */
@WebServlet("/ExceptionHandlerPage")
public class ExceptionHandlerPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExceptionHandlerPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}
	
	private void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javaz.servlet.error.status_code");
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if(servletName == null) {
			servletName = "Unkown";
		}
		
		if(requestUri == null) {
			requestUri = "Unkown";
		}
		
		response.setContentType("text.html");
		out.write("<html><head><title>Exception page</title></head><body>"
				+ "<h3>Error Details</h3>"
				+ "<strong>Status Code</strong>:"+statusCode+"<br>"
						+ "<strong>Requested URI</strong>:"+requestUri+""
								+ "<br></body></html>");
		
		out.write("Please go <a href='/GameCentral/GameCentral.html'> Home </a>");
	}

}
