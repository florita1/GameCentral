package ModelPoker;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

// This class pull all code together to get a poker winner
public class PlayPokerGame {
	
	private LinkedHashMap<String, String[]> playerCards;
	private String winner;

	public LinkedHashMap<String, String[]> getPlayerCards() {
		return playerCards;
	}

	public String getWinner() {
		return winner;
	}

	public PlayPokerGame(int setPlayerNum, String setPlayerName) {

		try {
			PlayerCards cards = null;
			PlayersHand tempHand = null;
			PokerHand tempWin = null;
			new GeneratePlayers(setPlayerNum);
			FileOperations data = new FileOperations("poker.txt");
			cards = new PlayerCards(data.openFile(), setPlayerNum,
					setPlayerName);

			HashMap<String, Integer> ranks = new HashMap<String, Integer>();
			playerCards = cards.getData();
			for (String player : playerCards.keySet()) {

				tempHand = new PlayersHand(playerCards.get(player));
				tempWin = new PokerHand(tempHand);
				ranks.put(player, tempWin.getRank());
			}

			//System.out.println(ranks.values());
			CheckRanks winners = new CheckRanks(ranks, playerCards);
			 this.winner = winners.getWinner();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
