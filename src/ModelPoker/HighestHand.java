package ModelPoker;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class HighestHand {

	private int rank;
	private String winner;
	private List<String> winners;
	private LinkedHashMap<String, String[]> playerCards;
	private LinkedHashMap<String, Integer> cardValues = null;

	// Constructor for class
	public HighestHand(List<String> setWinners, int setRank,
			LinkedHashMap<String, String[]> setPlayerCards) {
		setCardValues();
		winners = setWinners;
		playerCards = setPlayerCards;
		rank = setRank;
		chooseWinner();
	}
	
	public String getWinner() {
		return winner;
	}

	// Method to get the highest card in hand if there is no winning rank
	public int getHighCard(String[] testHand) {
		int cardVal = 0;
		// Outer FOR loop for players hand
		for (String x : testHand) {
			// Inner FOR loop for card value HashMap
			for (String c : cardValues.keySet()) {
				// IF condition to check if the card value matches the HashMap key
				if (x.substring(0, 1).equals(c)) {
					// If condition to check if the value is higher than the previous highest value card
					if (cardVal <= cardValues.get(c)) {
						// Reassign highest value card for hand
						cardVal = cardValues.get(c);
					}
				}
			}
		}
		// System.out.println(cardVal);
		return cardVal;
	}

	// Method to get value for the hand if there is a winning rank
	public int getHighHand(String[] testHand) {
		int handVal = 0;
		// FOR loop for players hand
		for (String x : testHand) {
			// Accumulates sum for card values from HashMap
			handVal += cardValues.get(x.substring(0, 1));
		}
		// System.out.println(handVal);
		return handVal;
	}

	// Method to determine winner
	public String chooseWinner() {
		// New TreeMap with highest card or hand value as key (last key is winner)
		TreeMap<Integer, String> winnerMap = new TreeMap<Integer, String>();

		// IF condition for winners whose hands do not have a winning strategy
		if (rank == 0) {
			// Outer FOR loop for winners to check highest value card
			for (String player : winners) {
				// Inner FOR loop to get players hand
				for (Entry<String, String[]> map : playerCards.entrySet())
					// IF condition to get the players hand from playerCards HashMap
					if (map.getKey().equals(player)) {
						// Fill TreeMap with the highest card value (key) and player name (value)
						winnerMap.put(getHighCard(map.getValue()), player);
					}
			}
		// Result condition for winners whose hands do have a winning strategy
		} else {
			// Outer FOR loop for winners to check hand value
			for (String player : winners) {
				// Inner FOR loop to get players hand
				for (Entry<String, String[]> map : playerCards.entrySet())
					// IF condition to get the players hand from playerCards HashMap
					if (map.getKey().equals(player)) {
						// Fill TreeMap with the hand value (key) and player name (value)
						winnerMap.put(getHighHand(map.getValue()), player);
					}
			}
		}
		
		//System.out.println(winnerMap.get(winnerMap.lastKey()) + " wins!");
		// Assign winner by pulling the last key and the value for that key
		winner = winnerMap.get(winnerMap.lastKey());
		return winner;
	}

	public LinkedHashMap<String, Integer> getCardValues() {
		return cardValues;
	}

	// Method to create HashMap with a value for the card
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
