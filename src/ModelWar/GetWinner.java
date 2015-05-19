package ModelWar;

import java.util.HashMap;
import java.util.List;

public class GetWinner {
	boolean war = false;
	private String winner;

	public GetWinner(HashMap<String, List<String>> playerCards, String playerName ) {
		CompareCards winners = new CompareCards(playerCards,playerName);
		if(winners.isWar()) {
			war = true;
			winners.warScenario();
			winner = winners.getWinner();
		} else {
			war = false;
			winner = winners.getWinner();
		}
	}

	public String getWinner() {
		return winner;
	}
	
	public boolean getWar() {
		return war;
	}
}
