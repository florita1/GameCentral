package ModelWar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MakePlayersHands {
	
	private String playerName;
	private List<String> player1;
	private List<String> player2;
	private HashMap<String, List<String>> playerMap;
	
	public MakePlayersHands(){
		
	}
	
	public MakePlayersHands(String setPlayerName) {
		playerMap = new HashMap<String, List<String>>();
		playerName = setPlayerName;
		player1 = new ArrayList<String>();
		player2 = new ArrayList<String>();
		fillMap();
	}
	
	public void fillMap() {
		List<String> cards = new MakeDeck().getDeckCards();
		for(String card : cards) {
			if(cards.indexOf(card) <= 25) {
				player1.add(card);
			} else {
				player2.add(card);
			}
		}
		
		playerMap.put(playerName, player1);
		playerMap.put("Player 2", player2);
	}

	public HashMap<String, List<String>> getPlayerMap() {
		return playerMap;
	}
	

}
