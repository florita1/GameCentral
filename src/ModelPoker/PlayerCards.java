package ModelPoker;

import java.io.IOException;
import java.util.LinkedHashMap;

public class PlayerCards {

	private int numOfPlayers;
	private String playerName;
	private String[] playerNames;
	private String[] cards;
	private LinkedHashMap<String, String[]> playerMap;

	// Constructor for class
	public PlayerCards(String[] setCards, int setNumOfPlayers,
			String setPlayerName) throws IOException {
		numOfPlayers = setNumOfPlayers;
		playerName = setPlayerName;
		// Create string for players names to be used in HashMap key
		playerNames = new String[] { playerName, "Player 2", "Player 3",
				"Player 4", "Player 5" };
		cards = setCards;
		makePlayers();

	}

	// Method to create player HashMap with empty values
	public void makePlayers() {
		playerMap = new LinkedHashMap<String, String[]>();
		for (int x = 0; x <= numOfPlayers - 1; x++) {
			playerMap.put(playerNames[x], new String[5]);
		}

	}
	
	// Method to fill players hand from card deck
	public LinkedHashMap<String, String[]> getData() {
		// Temporary string array to hold players cards
		String[] playerHand = new String[5];
		// Variable to keep track of number of cards in players hand
		int cardCount = 0;
		// Variable to keep track of indices in card deck
		int startVal = 0;
		// Outer FOR loop for players in game
		for (String player : playerMap.keySet()) {
			// Inner FOR loop for cards started at the starting value and executing 5 times
			for (int x = startVal; x <= startVal + 4; x++) {
				// Assigns card in x element of cards to the players hand
				playerHand[cardCount] = cards[x];
				// Increments the card count for the players hand
				cardCount += 1;
				// IF condition to execute when 5 cards have been assigned to a player
				if (cardCount > 4) {
					// Fills player HashMap value with the prepared player hand
					playerMap.put(player, playerHand);
					// Reassigns the start value to not lose place in card deck
					startVal = x + 1;
					// Reassigns the player hand card count to zero
					cardCount = 0;
					// Empties string array for next player
					playerHand = new String[5];
					// Breaks out of inner FOR loop and goes to next player in outer FOR loop
					break;
				}
			}
		}

		return playerMap;

	}
}
