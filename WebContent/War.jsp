<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>War</title>
<link rel="stylesheet" href="GameCentral.css">
</head>
<body>
	<h2>War Game</h2>
	<a id="login" href="/GameCentral/Login.html"> Login </a>
	<nav>
	<table id="mainMenu">
		<TR>
			<TD><a href="/GameCentral/GameCentral.html"> Home </a></TD>
			<TD><a href="/GameCentral/Poker.html"> Poker </a></TD>
			<TD><a href="/GameCentral/Blackjack.html"> BlackJack </a></TD>
			<TD><a href="/GameCentral/War.html"> War </a></TD>
		</TR>
	</table>
	</nav>
	
	<%@ page import="ModelWar.MakePlayersHands" %>
	<%@ page import="ModelWar.CompareCards" %>
	<%@ page import="ModelWar.GetWinner" %>
	<%@ page import="ModelWar.HandleDeck" %>
	<%@ page import="java.util.HashMap" %>
	<%@ page import="java.util.List" %>

	<%-- <jsp:useBean id="playersHands" scope="session" class="ModelWar.MakePlayersHands" /> --%>
	<% String playerName = request.getParameter("playerName");%>
	<%-- <% String playerName = "player"; %> --%>
	<% MakePlayersHands playerHands = new MakePlayersHands(playerName); %>
	<% HashMap<String, List<String>> playerCards = playerHands.getPlayerMap(); %>
	
	<script type="text/javascript">
		function getWinner() {
			<% String winner = new GetWinner(playerCards, playerName).getWinner(); %>
			<% boolean war =  new GetWinner(playerCards, playerName).getWar(); %>
			document.getElementById("winner").innerHTML = '<%=winner%> wins!';
			<% new HandleDeck(winner, playerCards, war, playerName); %>
			document.getElementById("pcard1").innerHTML = '<%out.print(playerCards.get(playerName).get(0));%>';
			document.getElementById("pdeck").innerHTML = 'Your deck: <%out.print(playerCards.get(playerName).size());%>';
			document.getElementById("dcard1").innerHTML = '<%out.print(playerCards.get("Player 2").get(0));%>';
			document.getElementById("ddeck").innerHTML = 'Your deck: <%out.print(playerCards.get("Player 2").size());%>';
			/* document.cards.reload();*/
		}
	</script>
	<p id="winner"></p>
	
		<table id="displayCards">
			<TR>
				<TD>Your cards:</TD>
				<TD class="cards" id="pcard1"> </TD>
				<TD id="pdeck"> </TD>
			</TR>

			<TR>
				<TD>Player 2 cards:</TD>
				<TD class="cards" id="dcard1"> </TD>
				<TD id="ddeck"> </TD>
			</TR>
		</table>
		<button onclick="getWinner()">Deal Card</button> 
		<input type="button" id="exit" value="Quit Game"/>
	
	<br>
</body>
</html>