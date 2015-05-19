package ModelPoker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CheckRanks {

	private LinkedHashMap<String, String[]> playerCards;
	private HashMap<String, Integer> ranks;
	private TreeMap<Integer, String> sortedRanks;
	private String winner = null;
	private int highestRank;
	private List<String> tiedWinners;

	public String getWinner() {
		return winner;
	}

	// Constructor for class
	public CheckRanks(HashMap<String, Integer> setRanks,
			LinkedHashMap<String, String[]> setPlayerCards) {
		playerCards = setPlayerCards;
		ranks = setRanks;
		
		// Create TreeMap which is sorted on the players hand rank (key), values are players names
		sortedRanks = new TreeMap<Integer, String>();
		// FOR loop through players names from the unsorted HashMap
		for (String player : ranks.keySet()) {
			// Fills TreeMap with rank of the players hand
			sortedRanks.put(ranks.get(player), player);
		}

		// Create list for players whose hand ranks are tied for the win
		tiedWinners = new ArrayList<String>();
		// FOR loop to find players whose rank matches the last entry in the TreeMap
			//	Last entry in TreeMap is the highest hand rank
		highestRank = 0;
		for (Entry<String, Integer> rank : ranks.entrySet()) {
			if (rank.getValue() == sortedRanks.lastKey()) {
				highestRank += 1;
				tiedWinners.add(rank.getKey());
			}
		}

		// Check if there is one winner or multiple winners
		if (highestRank == 1) {
			checkOneWinner();
		} else {
			checkMultiWinner();
		}

	}

	// Method to return the winner in a one winner scenario
	public String checkOneWinner() {
		this.winner = sortedRanks.get(sortedRanks.lastKey());
		//System.out.println(winner + " Wins!");
		return winner;
	}

	// Method to return the winner in a multiple winner scenario
	public String checkMultiWinner() {
		// Pass multiple winners to HighestHand class with the tied rank value
		HighestHand win = new HighestHand(tiedWinners, sortedRanks.lastKey(), playerCards);
		winner = win.getWinner();
		return winner;
	}

}
