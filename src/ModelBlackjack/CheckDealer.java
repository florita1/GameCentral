package ModelBlackjack;

import java.util.HashMap;
import java.util.List;

public class CheckDealer {
	
	private int dealerCount;
	private boolean bust = false;

	public int getDealerCount() {
		return dealerCount;
	}

	public CheckDealer(MakeHands cards) {
		HashMap<String,List<String>> playerMap = cards.getPlayerMap();
		dealerCount = new CountHands(playerMap).countHand("Dealer");
		//System.out.println("DealerCount: " + dealerCount);
		
		List<String> cardDeck = cards.getCards();
		List<String> dealerCards = playerMap.get("Dealer");
		if(dealerCount <= 17) {
			String card = cardDeck.get(0);
			dealerCards.add(card);
			cardDeck.remove(card);
			playerMap.put("Dealer", dealerCards);
			dealerCount = new CountHands(playerMap).countHand("Dealer");
			bust = new CountHands(playerMap).isBust();
			//System.out.println("new DealerCount: " + dealerCount);
		}
	}

	public boolean isBust() {
		return bust;
	}

}
