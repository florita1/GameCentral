package ModelWar;

import java.util.HashMap;
import java.util.List;

public class HandleDeck {
	
	//private HashMap<String,List<String>> playerCards;
	private String  winner;
	private String playerName;
	private boolean war = false;
	
	public HandleDeck(String setWinner, HashMap<String,List<String>> setCards, boolean isWar, String setName) {
		//playerCards = setCards;
		winner = setWinner;
		war = isWar;
		playerName = setName;
		String p1Card = setCards.get(playerName).get(0);
		String p2Card = setCards.get("Player 2").get(0);
		if(war) {
			for(int y = 0; y <= 3; y++) {
				String tP1 = setCards.get(playerName).get(0 + y);
				String tP2 = setCards.get("Player 2").get(0 + y);
				setCards.get(playerName).remove(tP1);
				setCards.get("Player 2").remove(tP2);
				setCards.get(winner).add(tP1);
				setCards.get(winner).add(tP2);	
			}
		} else {
			setCards.get(playerName).remove(p1Card);
			setCards.get("Player 2").remove(p2Card);
			setCards.get(winner).add(p1Card);
			setCards.get(winner).add(p2Card);
		}
		System.out.println(setCards.get(playerName).size());
		System.out.println(setCards.get("Player 2").size());
	}

}
