package ModelPoker;

import java.util.LinkedHashMap;

public class PlayersHand {
	
	private LinkedHashMap<String, Integer> cardCount;
	private LinkedHashMap<String, Integer> suitCount;
	private String[] hand;
	
	// Constructor for class
	public PlayersHand(String[] startHand) {
		setHand(startHand);
		
		suitCount = new LinkedHashMap<String, Integer>();
		// FOR loop to make HashMap for the suits and the number of times they appear in the hand
		String[] suits = { "S", "D", "C", "H" };
		for (String suit : suits) {
			suitCount.put(suit, 0);
		}

		cardCount = new LinkedHashMap<String, Integer>();
		// FOR loop to make HashMap for the cards and the number of times they appear in the hand
		String[] cards = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J",
				"Q", "K", "A" };
		for (String card : cards) {
			cardCount.put(card, 0);
		}
	}

	// Method to count the number of times a suit is in the hand
	public LinkedHashMap<String, Integer> checkSuits() {
		// Outer FOR loop iterating through cards in hand
		for (String x : hand) {
			// Inner FOR loop iterating through the suit keys
			for (String c : suitCount.keySet()) {
				// IF condition for when the suit of the card matches the key in the HashMap
				if (x.substring(1, 2).equals(c)) {
					// Adds one to the value of the HashMap for the suit
					int newVal = suitCount.get(c) + 1;
					suitCount.put(c, newVal);
				}
			}
		}
		// System.out.println("Suits in Deck: "+suitCount.values());
		return suitCount;
	}

	// Method to count the number of times a card is in the hand
	public LinkedHashMap<String, Integer> checkCards() {
		// Outer FOR loop iterating through cards in hand
		for (String x : hand) {
			// Inner FOR loop iterating through the card keys
			for (String c : cardCount.keySet()) {
				// IF condition for when the card matches the key in the HashMap
				if (x.substring(0, 1).equals(c)) {
					// Adds one to the value of the HashMap for the suit
					int newVal = cardCount.get(c) + 1;
					cardCount.put(c, newVal);
				}
			}
		}

		// System.out.println("Cards in Deck: "+cardCount.values());
		return cardCount;
	}

	public String[] getHand() {
		return hand;
	}

	public void setHand(String[] hand) {
		this.hand = hand;
	}

}
