package ModelPoker;

import java.util.HashMap;

public class MakeWallets {
	
	private HashMap<String,Integer> startWallets;

	// Constructor for class
	public MakeWallets(int playNum, int ante, String playName){
		// String array for all possible players
		String[] names = {playName,"Player 2","Player 3","Player 4","Player 5"};
		startWallets = new HashMap<String,Integer>();
		// FOR loop that fill HashMap with a wallet value three times the ante
		for(int x = 0; x <= playNum-1; x++) {
			startWallets.put(names[x], ante*3);
		}
	}

	public HashMap<String,Integer> getStartWallets() {
		return startWallets;
	}

}
