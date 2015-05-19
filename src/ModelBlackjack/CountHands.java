package ModelBlackjack;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class CountHands {
	
	private HashMap<String,List<String>> playerHands;
	private LinkedHashMap<String, Integer> cardValues;
	private boolean bust = false;
	
	public CountHands(HashMap<String,List<String>> setPlayerHands) {
		playerHands = setPlayerHands;
		setCardValues();
	}
	
	public int countHand(String pName) {
		List<String> hand = playerHands.get(pName);
		int handVal = 0;
		for (String x : hand) {
			handVal += cardValues.get(x.substring(0, 1));
			if(handVal > 21) {
				if(x.substring(0, 1).equals("A")){
					handVal -= 10;
				} else {
					bust = true;
				}
			}
		}
		//System.out.println(handVal);
		return handVal;
	}
	
	public boolean isBust() {
		return bust;
	}

	public void setCardValues() {
		this.cardValues = new LinkedHashMap<String, Integer>();
		String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "T"};
		String[] royalty = {"J", "Q", "K"};
		int val = 1;
		for (String card : cards) {
			this.cardValues.put(card, val += 1);
		}
		
		for(String royal : royalty) {
			this.cardValues.put(royal, 10);
		}
		
		this.cardValues.put("A", 11);
	}

}
