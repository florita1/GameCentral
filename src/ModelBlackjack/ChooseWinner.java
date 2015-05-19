package ModelBlackjack;

public class ChooseWinner {
	
	private int playerWins;
	private int dealerWins;
	
	public int getPlayerWins() {
		return playerWins;
	}

	public int getDealerWins() {
		return dealerWins;
	}

	public ChooseWinner( CountHands playerInfo, CheckDealer dealerInfo, int playCount) {
		if(playerInfo.isBust() && dealerInfo.isBust()) {
			//System.out.println("Tied");
		}else if(playerInfo.isBust()) {
			dealerWins += 1;
		} else if(dealerInfo.isBust()) {
			playerWins += 1;
		} else {
			int playdiff = 21 - playCount;
			int dealdiff = 21 - dealerInfo.getDealerCount();
			if( playdiff < dealdiff) {
				playerWins += 1;
			} else {
				dealerWins += 1;
			}
		}
	}

}
