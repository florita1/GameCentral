package ModelBlackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ModelBlackjack.MakeDeck;

public class MakeHands {
	private String playerName;
	private List<String> player1;
	private List<String> dealer;
	private HashMap<String, List<String>> playerMap;
	private List<String> cards;

	public MakeHands(String setPlayerName) {
		playerMap = new HashMap<String, List<String>>();
		playerName = setPlayerName;
		player1 = new ArrayList<String>();
		dealer = new ArrayList<String>();
		cards = new MakeDeck().getDeckCards();
		fillMap();
	}
	
	public void fillMap() {
		for( int x = 0; x <= 3; x++) {
			String card = cards.get(x);
			if(x <= 1) {
				player1.add(card);
			} else {
				dealer.add(card);
			}
			cards.remove(card);
		}

		playerMap.put(playerName, player1);
		playerMap.put("Dealer", dealer);
	}
	
	public void addCard() {
		String card = cards.get(0);
		player1.add(card);
		cards.remove(card);
		playerMap.put(playerName, player1);
	}

	public HashMap<String, List<String>> getPlayerMap() {
		return playerMap;
	}
	
	public List<String> getCards() {
		return cards;
	}
}
