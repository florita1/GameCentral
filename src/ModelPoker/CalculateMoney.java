package ModelPoker;

import java.util.HashMap;
import java.util.Map.Entry;

public class CalculateMoney {
	
	private HashMap<String,Integer> oldWallets;
	private String winner;
	private int ante;
	
	// Constructor for method
	public CalculateMoney(HashMap<String,Integer> setOldWallets, int setAnte, String setWinner) {
		ante = setAnte;
		winner = setWinner;
		oldWallets = setOldWallets;
		
	}
	
	// Method to reset the wallet amount after winner has been determined
	public HashMap<String,Integer> recalculateMoney() {
		int newAmount = 0;
		// New HashMap created to store the new wallet values
		HashMap<String,Integer> newWallets = new HashMap<String,Integer>();
		for(Entry<String,Integer> player : oldWallets.entrySet()) {
			// Adjust wallet for winners to include pot
			if(player.getKey().equals(winner)) {
				newAmount = (player.getValue() + ( oldWallets.keySet().size() * ante));
				newWallets.put(winner,  newAmount);
			// Adjust wallet for losers to take away ante
			} else {
				newAmount = player.getValue() - ante;
				newWallets.put(player.getKey(), newAmount);
			}
		}
		return newWallets;
	}

}
