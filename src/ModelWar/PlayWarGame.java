package ModelWar;

import java.util.HashMap;
import java.util.List;

public class PlayWarGame {

	public static void main(String[] args) {
		HashMap<String,List<String>> playerCards = new MakePlayersHands("Florita").getPlayerMap();
		List<String> p1 = playerCards.get("Florita");
		
		//String winner = new CompareCards(p1,p2).getWinner();
		System.out.println(playerCards.keySet());
		System.out.println(p1.size());
		
		for(int count = 0; count <= 5; count++) {
			CompareCards winners = new CompareCards(playerCards,"Florita");
			if(winners.isWar()) {
				System.out.println("war");
				winners.warScenario();
				System.out.println(winners.getWinner());
			} else {
				System.out.println(winners.getWinner());
			}
			//System.out.println("Player 1: "+p1Card+" Player 2: "+p2Card);
		}
		

	}
	
}
