package ModelPoker;

import java.util.LinkedHashMap;

public class PokerHand {

	private LinkedHashMap<String, Integer> cardsinHand;
	private LinkedHashMap<String, Integer> suitsinHand;
	private int rank;
	private boolean flush = false;
	private boolean strait = false;
	private int pairCount = 0;
	
	// Constructor for class
	public PokerHand(PlayersHand startHand) {
		setCardsinHand(startHand.checkCards());
		setSuitsinHand(startHand.checkSuits());
	}

	// Method that checks all winning strategies and returns a rank
	public int getRank() {
		checkPairs();
		checkOnePair();
		checkTwoPair();
		checkThreeOfAKind();
		checkStraits();
		checkFlush();
		checkFullHouse();
		checkFourOfAKind();
		checkStraightFlush();
		checkRoyalFlush();
		return rank;
	}

	private void checkPairs() {
		// FOR loop to check if a card appears twice in a hand
		for (int pair : cardsinHand.values()) {
			if (pair == 2) {
				pairCount += 1;
			}
		}
	}

	private void checkOnePair() {
		if (pairCount == 1)
			rank = 1;
	}

	private void checkTwoPair() {
		if (pairCount == 2)
			rank = 2;
	}

	private void checkThreeOfAKind() {
		if (cardsinHand.containsValue(3))
			rank = 3;
	}

	private void checkStraits() {
		String[] cards = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J",
				"Q", "K", "A" };
		// IF condition to check if a card appears once
		if (cardsinHand.containsValue(1)) {
			// FOR loop iterating through card values
			for (int v = 0; v <= 13; v++) {
				// IF conditions to check if consecutive values are one as well before card value 9
				if (cardsinHand.get(cards[v]).equals(1) && v <= 10) {
					if (cardsinHand.get(cards[v + 1]).equals(1)) {
						if (cardsinHand.get(cards[v + 2]).equals(1)) {
							if (cardsinHand.get(cards[v + 3]).equals(1)) {
								if (cardsinHand.get(cards[v + 4]).equals(1)) {
									strait = true;
								}
							}
						}
					}

				}
			}
		}

		if (strait) {
			rank = 4;
		}

	}

	private void checkFlush() {
		if (suitsinHand.containsValue(5)) {
			flush = true;
			rank = 5;
		}
	}

	private void checkFullHouse() {
		if (cardsinHand.containsValue(2) && cardsinHand.containsValue(3))
			rank = 6;
	}

	private void checkFourOfAKind() {
		if (cardsinHand.containsValue(4))
			rank = 7;
	}

	private void checkStraightFlush() {
		if (strait && flush) {
			rank = 8;
		}
	}

	private void checkRoyalFlush() {
		String[] faces = { "A", "T", "J", "Q", "K" };
		int royalCount = 0;
		for (String face : faces) {
			if (cardsinHand.get(face) == 1) {
				royalCount += 1;
			}
		}

		if (royalCount == 5 && flush) {
			// Setting rank for Royal Flush if a suit appears 5 times in
			// checkSuit (flush is true)
			rank = 9;
		}
	}

	public LinkedHashMap<String, Integer> getCardsinHand() {
		return cardsinHand;
	}

	public void setCardsinHand(LinkedHashMap<String, Integer> cardsinHand) {
		this.cardsinHand = cardsinHand;
	}

	public LinkedHashMap<String, Integer> getSuitsinHand() {
		return suitsinHand;
	}

	public void setSuitsinHand(LinkedHashMap<String, Integer> suitsinHand) {
		this.suitsinHand = suitsinHand;
	}

}
