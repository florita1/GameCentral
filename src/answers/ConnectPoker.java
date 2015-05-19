package answers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModelPoker.MakeWallets;
import ModelPoker.PlayPokerGame;

/**
 * Servlet implementation class ConnectPoker
 */
@WebServlet("/ConnectPoker")
public class ConnectPoker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean authenticate = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectPoker() {
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
			String title = "Playing Poker Game";
			String playerName = request.getParameter("playerName");
			int anteAmount = Integer.parseInt(request.getParameter("anteAmount"));
			int numOfPlayers = Integer.parseInt(request.getParameter("players"));
			PlayPokerGame game = new PlayPokerGame(numOfPlayers, playerName);
			HashMap<String,Integer> wallets = new MakeWallets(numOfPlayers, anteAmount, playerName).getStartWallets();


			out.println(docType+"<html><head><title>"+title+"</title> <link rel='stylesheet' href='GameCentral.css'></head> "
					+ "<body><h2> Poker Game </h2><nav><table id='mainMenu'><TR>"
					+ "<TD> <a href='/GameCentral/GameCentral.html'> Home </a> </TD>"
					+ "<TD> <a href='/GameCentral/Poker.html'> Poker </a> </TD>"
					+ "<TD> <a href='/GameCentral/Blackjack.html'> BlackJack </a> </TD>"
					+ "<TD> <a href ='/GameCentral/War.html'>War</a></TD></TR></table></nav>"
					+ "<form class='Poker' action='ConnectPoker' method='POST'>"
					+ "Player Name: <input name='playerName' type='text' value='"+playerName+"'> "
					+ "<input type='submit'> <br><br>"
					+ "<table id='displayCards'>"
					+ "<TR><TD> Your cards: </TD>"
					+ "<TD class='cards' id='pcard1'>"+game.getPlayerCards().get(playerName)[0] +"</TD>"
					+ "<TD class='cards' id='pcard2'>"+game.getPlayerCards().get(playerName)[1] +"</TD>"
					+ "<TD class='cards' id='pcard3'>"+game.getPlayerCards().get(playerName)[2] +"</TD>"
					+ "<TD class='cards' id='pcard4'>"+game.getPlayerCards().get(playerName)[3] +"</TD>"
					+ "<TD class='cards' id='pcard5'>"+game.getPlayerCards().get(playerName)[4] +"</TD>"
					+ "<TD name='wallet' id='pW' > Wallet: $"+wallets.get(playerName) +"</TD>"
					+ "</TR><TR>"
					+ "<TD> Player 2 cards: </TD>"
					+ "<TD class='cards' id='2card1'>"+game.getPlayerCards().get("Player 2")[0] +"</TD>"
					+ "<TD class='cards' id='2card2'>"+game.getPlayerCards().get("Player 2")[1] +"</TD>"
					+ "<TD class='cards' id='2card3'>"+game.getPlayerCards().get("Player 2")[2] +"</TD>"
					+ "<TD class='cards' id='2card4'>"+game.getPlayerCards().get("Player 2")[3] +"</TD>"
					+ "<TD class='cards' id='2card5'>"+game.getPlayerCards().get("Player 2")[4] +"</TD>"
					+ "<TD name='wallet' id='p2W' > Wallet: $"+wallets.get("Player 2") +"</TD>"
					+ "</TR><TR>"
					+ "<TD> Player 3 cards: </TD>"
					+ "<TD class='cards' id='3card1'>"+game.getPlayerCards().get("Player 3")[0] +"</TD>"
					+ "<TD class='cards' id='3card2'>"+game.getPlayerCards().get("Player 3")[1] +"</TD>"
					+ "<TD class='cards' id='3card3'>"+game.getPlayerCards().get("Player 3")[2] +"</TD>"
					+ "<TD class='cards' id='3card4'>"+game.getPlayerCards().get("Player 3")[3] +"</TD>"
					+ "<TD class='cards' id='3card5'>"+game.getPlayerCards().get("Player 3")[4] +"</TD>"
					+ "<TD name='wallet' id='p3W' > Wallet: $"+wallets.get("Player 3") +"</TD>"
					+ "</TR><TR>"
					+ "<TD> Player 4 cards: </TD>"
					+ "<TD class='cards' id='4card1'>"+game.getPlayerCards().get("Player 4")[0] +"</TD>"
					+ "<TD class='cards' id='4card2'>"+game.getPlayerCards().get("Player 4")[1] +"</TD>"
					+ "<TD class='cards' id='4card3'>"+game.getPlayerCards().get("Player 4")[2] +"</TD>"
					+ "<TD class='cards' id='4card4'>"+game.getPlayerCards().get("Player 4")[3] +"</TD>"
					+ "<TD class='cards' id='4card5'>"+game.getPlayerCards().get("Player 4")[4] +"</TD>"
					+ "<TD name='wallet' id='p4W' > Wallet: $"+wallets.get("Player 4") +"</TD>"
					+ "</TR><TR>"
					+ "<TD> Player 5 cards: </TD>"
					+ "<TD class='cards' id='5card1'>"+game.getPlayerCards().get("Player 5")[0] +"</TD>"
					+ "<TD class='cards' id='5card2'>"+game.getPlayerCards().get("Player 5")[1] +"</TD>"
					+ "<TD class='cards' id='5card3'>"+game.getPlayerCards().get("Player 5")[2] +"</TD>"
					+ "<TD class='cards' id='5card4'>"+game.getPlayerCards().get("Player 5")[3] +"</TD>"
					+ "<TD class='cards' id='5card5'>"+game.getPlayerCards().get("Player 5")[4] +"</TD>"
					+ "<TD name='wallet' id='p5W' > Wallet: $"+wallets.get("Player 5") +"</TD>"
					+ "</TR>"
					+ "<input type='submit' value='Deal Again'>"
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
