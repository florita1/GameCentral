package ModelPoker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneratePlayers {

	private int playerNum;
	private List<String> deckCards;

	// Constructor for class
	public GeneratePlayers(int setPlayerNum) throws IOException {
		playerNum = setPlayerNum;
		generateDeckCards();
		makePlayerFile();
	}

	// Writes file with cards for the specified number of players
	public void makePlayerFile() throws IOException {
		File file = new File("poker.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		String line = "";
		// Shuffle cards before they are written to the file
		Collections.shuffle(deckCards);
		// Calculate the number of cards needed for the game
		int cardsInGame = 5 * playerNum;
		// FOR loop to add the card to a string, line
		for (int x = 0; x <= cardsInGame - 1; x++) {
			line += deckCards.get(x) + " ";
		}
		// Write string to file for cards
		writer.write(line);
		writer.close();
	}

	// Method to generate cards for the deck
	private void generateDeckCards() {
		deckCards = new ArrayList<String>();
		// String array with all of the card values
		String[] cards = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J",
				"Q", "K", "A" };
		// String array with all suits
		String[] suits = { "S", "D", "C", "H" };

		// FOR loop to create card string and to add it the the card deck list
		String newCard = "";
		for (String suit : suits) {
			for (String card : cards) {
				newCard = card + suit;
				this.deckCards.add(newCard);
			}
		}
	}

}
