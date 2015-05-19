<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>War</title>
</head>
<body>
	<h2>War Game</h2>
	<%@ page import="ModelWar.MakePlayersHands" %>
	<%@ page import="ModelWar.CompareCards" %>
	<%@ page import="ModelWar.GetWinner" %>
	<%@ page import="ModelWar.HandleDeck" %>
	<%@ page import="java.util.HashMap" %>
	<%@ page import="java.util.List" %>

	<%-- <jsp:useBean id="playersHands" scope="session" class="ModelWar.MakePlayersHands" /> --%>
	<% String playerName = "player"; %>
	<% MakePlayersHands playerHands = new MakePlayersHands(playerName); %>
	<% HashMap<String, List<String>> playerCards = playerHands.getPlayerMap(); %>
	
	<script>
		function getWinner() {
			<% String winner = new GetWinner(playerCards, playerName).getWinner(); %>
			<% boolean war =  new GetWinner(playerCards, playerName).getWar(); %>
			document.getElementById("winner").innerHTML = '<%=winner%> wins!';
			<% new HandleDeck(winner, playerCards, war, playerName); %>
			document.getElementById("pcard1").innerHTML = "AH";
			document.getElementById("pdeck").innerHTML = "26";
			document.getElementById("dcard1").innerHTML = "2D";
			document.getElementById("ddeck").innerHTML = "26";
			/* document.cards.reload();*/
		}
	</script>
	<p id="winner"></p>
	
		<table id="displayCards">
			<TR>
				<TD>Your cards:</TD>
				<TD class="cards" id="pcard1"> </TD>
				<TD id="pdeck">Your deck:</TD>
			</TR>

			<TR>
				<TD>Player 2 cards:</TD>
				<TD class="cards" id="dcard1"> </TD>
				<TD id="ddeck">Player 2 deck:</TD>
			</TR>
		</table>
		<button onclick="getWinner()">Deal Card</button> 
	<br>
</body>
</html>