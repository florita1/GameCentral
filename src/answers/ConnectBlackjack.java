package answers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModelBlackjack.CheckDealer;
import ModelBlackjack.CountHands;
import ModelBlackjack.MakeHands;

/**
 * Servlet implementation class ConnectBlackjack
 */
@WebServlet("/ConnectBlackjack")
public class ConnectBlackjack extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean authenticate = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectBlackjack() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0" + "transitional//en\">\n";
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		checkCookies(request);
		if(authenticate) {
			String title = "Playing Blackjack Game";
			String playerName = request.getParameter("playerName");
			MakeHands cards = new MakeHands(playerName);
			HashMap<String,List<String>> playerCards = cards.getPlayerMap();
			CountHands playerInfo = new CountHands(playerCards);
			CheckDealer dealerInfo = new CheckDealer(cards);
			out.println(docType+"<html><head><title>"+title+"</title> <link rel='stylesheet' href='GameCentral.css'></head> "
					+ "<body><h2> Blackjack Game </h2><nav><table id='mainMenu'><TR>"
					+ "<TD> <a href='/GameCentral/GameCentral.html'> Home </a> </TD>"
					+ "<TD> <a href='/GameCentral/Poker.html'> Poker </a> </TD>"
					+ "<TD> <a href='/GameCentral/Blackjack.html'> BlackJack </a> </TD>"
					+ "<TD> <a href ='/GameCentral/War.html'>War</a></TD></TR></table></nav>"
					+ "<form class='Blackjack' action='ConnectBlackjack' method='POST'>"
					+ "Player Name: <input name='playerName' type='text' value='"+playerName+"'> "
					+ "<input type='submit'> <br><br>"
					+ "<table id='displayCards'>"
					+ "<TR>"
					+ "<TD> Your cards: </TD>"
					+ "<TD class='cards' id='pcard1'>"+playerCards.get(playerName).get(0)+"</TD>"
					+ "<TD class='cards' id='pcard2'>"+playerCards.get(playerName).get(1)+"</TD>"
					+ "<TD> Hand Count: "+playerInfo.countHand(playerName)+"</TD>"
					+ "<TD> Your Score: 0</TD>"
					+ "</TR><TR>"
					+ "<TD> Player 2 cards: </TD>"
					+ "<TD class='cards' id='dcard1'>"+playerCards.get("Dealer").get(0)+"</TD>"
					+ "<TD class='cards' id='dcard2'>"+playerCards.get("Dealer").get(1)+"</TD>"
					+ "<TD> Dealer Count: "+dealerInfo.getDealerCount()+"</TD>"
					+ "<TD> Dealer Score: 0</TD>"
					+ "</TR></table>"
					+ "<input type='button' value='Stay'>"
					+ "<input type='button' value='Hit'> <br>"
					+ "<input type='button' value='Play Again'>"
					+ "<input type='button' id='exit' value='Quit Game'> <br>"
					+ "</form></body></html>");
		} else {
			out.println(docType+"<html><head><title>Login Error</title> <link rel='stylesheet' href='GameCentral.css'></head>"
					+ "<body><h2> Login Error </h2> <br>"
					+ "You must be logged in to play this game. <br>"
					+ "Please log in <a href='/GameCentral/Login.html'>here</a>"
					+ "</body></html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void checkCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("authenticated")) {
					authenticate = true;
					break;
				} 
			}
		}else {
			authenticate = false;
		}
	}

}
