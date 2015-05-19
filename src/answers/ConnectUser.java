package answers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConnectUser
 */
@WebServlet("/ConnectUser")
public class ConnectUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0"
				+ "transitional//en\">\n";
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String title = "Game Central Login";

		writeFile(request);
				
		Cookie authenticate = new Cookie("authenticated", "true");
		authenticate.setMaxAge(60*2);
		response.addCookie(authenticate);
		
		out.println(docType
				+ "<html><head><title>"
				+ title
				+ "</title> <link rel='stylesheet' href='GameCentral.css'></head> "
				+ "<body><h1> Login Success </h1><br>"
				+ "<h3> Thank you for loging in!<br>"
				+ "Please choose a game to play <a href='/GameCentral/GameCentral.html'> here</a> </h3>"
				+ "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void writeFile(HttpServletRequest request) throws IOException {
		File file = new File("/Users/floritarodriguez/Desktop/ClaimAcademy/GameCentral/WebContent/Register.xml");
		String name = request.getParameter("userName");
		String pass = request.getParameter("passWord");
		String email = request.getParameter("email");
		FileWriter writer = new FileWriter(file);
		//file.dom.getTagName("userNAme").innerhtml = name;
		writer.write(name + "\n");
		writer.write(pass + "\n");
		writer.write(email);
		writer.close();


	}

}
