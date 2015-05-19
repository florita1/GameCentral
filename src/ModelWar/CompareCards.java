package ModelWar;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class CompareCards {
	
	private LinkedHashMap<String, Integer> cardValues;
	private String p1;
	private String p2;
	private String winner;
	private String playerName;
	private HashMap<String,List<String>> playerCards;
	private boolean war = false;

	public CompareCards(HashMap<String,List<String>> setPlayerCards, String setName) {
		playerCards = setPlayerCards;
		playerName = setName;
		setCardValues();
		findWinner(0);
	}
	
	public boolean isWar() {
		return war;
	}
	
	public String getWinner() {
		return winner;
	}

	public void findWinner(int index) {
		p1 = playerCards.get(playerName).get(index);
		p2 = playerCards.get("Player 2").get(index);
		System.out.println("Player 1: "+p1+" Player 2: "+p2);
		int p1Card = highestCard(p1);
		int p2Card = highestCard(p2);
		if(p1Card == p2Card) {
			war = true;
			//System.out.println("War");
		} else if(p1Card > p2Card) {
			war = false;
			winner = playerName;
			
		} else {
			war = false;
			winner = "Player 2";
			
		}
		
	}
	
	public void warScenario() {
		if(playerCards.get(playerName).size() >= 4 && playerCards.get("Player 2").size() >= 4) {
			int setIndex = 4;
			findWinner(setIndex);
		} else {
			System.out.println("One player does not have enough cards");
		}
		
	}

	public int highestCard(String player){
		int cardVal = 0;
		for (String c : cardValues.keySet()) {
			if (player.substring(0,1).equals(c)) {
					cardVal = cardValues.get(c);
			}
		}
		
		return cardVal;
	}	
	
	public void setCardValues() {
		this.cardValues = new LinkedHashMap<String, Integer>();
		String[] cards = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J",
				"Q", "K", "A" };
		int val = 0;
		for (String card : cards) {
			this.cardValues.put(card, val += 1);
		}
	}
	
}
