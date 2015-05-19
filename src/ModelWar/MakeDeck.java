package ModelWar;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MakeDeck {

	private  List<String> deckCards;
	
	public MakeDeck() {
		generateDeckCards();
	}

	private void generateDeckCards() {
		deckCards = new ArrayList<String>();
		String[] cards = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J",
				"Q", "K", "A" };
		String[] suits = { "S", "D", "C", "H" };

		String newCard = "";
		for (String suit : suits) {
			for (String card : cards) {
				newCard = card + suit;
				this.deckCards.add(newCard);
			}
		}
	}

	public List<String> getDeckCards() {
		Collections.shuffle(deckCards);
		return deckCards;
	}

}
