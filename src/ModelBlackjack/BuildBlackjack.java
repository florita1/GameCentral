package ModelBlackjack;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BuildBlackjack {
	
	private JFrame f;
	private GridBagConstraints comp;
	private MakeHands cards;
	private String playerName;
	private JLabel playerCards;
	private JPanel playerPanel;
	private CountHands playerInfo;
	private CheckDealer dealerInfo;
	private JPanel dealerPanel;

	public static void main(String[] a) {
		BuildBlackjack bb = new BuildBlackjack();
		bb.createFrame();
	}

	public void createFrame() {
		// Create frame for poker game
		f = new JFrame("Poker Game");
		f.setLayout(new GridBagLayout());
		comp = new GridBagConstraints();
		
		JLabel getname = new JLabel("What is your players name? ", SwingConstants.CENTER);
		comp = setComp("HORIZONTAL",0.5, 1, 0, 0);
		f.add(getname, comp);

		final JTextField name = new JTextField(10);
		comp = setComp("HORIZONTAL",0.5, 1, 1, 0);
		comp.ipadx = 15;
		f.add(name, comp);

		final JButton submit = new JButton("Submit");
		comp = setComp("HORIZONTAL",0.0, 1, 2, 0);
		f.add(submit, comp);

		// Action listener to accept the player name and continue
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerName = name.getText();
				makeControls();
				submit.setEnabled(false);
				name.setEditable(false);
				cards = new MakeHands(playerName);
				displayCards(cards.getPlayerMap());
			}
		});

		f.setBounds(400, 100, 500, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void displayCards(HashMap<String,List<String>> pCards) {
		playerPanel = new JPanel();
		comp = setComp("RELATIVE",0.5, 2, 0, 1);
		comp.ipadx = 100;
		f.add(playerPanel, comp);
		playerCards = new JLabel(playerName+ ":");
		playerPanel.add(playerCards);
		for(String card : pCards.get(playerName)) {
			JLabel c = new JLabel(card);
			playerPanel.add(c);
		}
		
		f.setVisible(true);
	}
	
	public void displayDealer() {
		dealerPanel = new JPanel();
		comp = setComp("RELATIVE",0.5, 1, 2, 1);
		
		JLabel dealerCards = new JLabel("Dealer:");
		dealerPanel.add(dealerCards);
		for(String card : cards.getPlayerMap().get("Dealer")) {
			JLabel c = new JLabel(card);
			dealerPanel.add(c);
		}
		f.add(dealerPanel, comp);
		f.setVisible(true);
		
	}

	
	public void makeControls() {
		JButton stay = new JButton("Stay");
		comp = setComp("RELATIVE",0.5, 1, 0, 2);
		f.add(stay, comp);
		JButton hit = new JButton("Hit");
		comp = setComp("RELATIVE",0.5, 1, 1, 2);
		f.add(hit, comp);
		
		f.setVisible(true);
		hit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.addCard();
				displayCards(cards.getPlayerMap());	
				playerInfo = new CountHands(cards.getPlayerMap());
			}
		});
		
		stay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dealerInfo = new CheckDealer(cards);
				displayDealer();
				showWinner();
			}
		});
		
	}

	protected void showWinner() {
		int dealerCount = dealerInfo.getDealerCount();
		int playerCount = playerInfo.countHand(playerName);
		
		final JPanel gameResult = new JPanel(new GridLayout(2,2));
		comp = setComp("HORIZONTAL",0.5, 3, 0, 4);
		f.add(gameResult, comp);
		
		JLabel playerHand = new JLabel("Player Count: " + playerCount);
		gameResult.add(playerHand);
		
		JLabel dealerHand = new JLabel("Dealer Count: " + dealerCount);
		gameResult.add(dealerHand);
		
		ChooseWinner wins = new ChooseWinner(playerInfo, dealerInfo, playerCount);
		
		JLabel playerwins = new JLabel("Player wins: " + wins.getPlayerWins());
		gameResult.add(playerwins);
		
		JLabel dealerwins = new JLabel("Dealer wins: " + wins.getDealerWins());
		gameResult.add(dealerwins);
		
		JButton playAgain = new JButton("Play Again");
		comp = setComp("RELATIVE",0.5, 3, 0, 5);
		f.add(playAgain, comp);

		// Action listener to accept the player name and continue
		playAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeControls();
				f.remove(dealerPanel);
				f.remove(gameResult);
				cards = new MakeHands(playerName);
				displayCards(cards.getPlayerMap());
				
				f.setVisible(true);
			}
		});
		
		f.setVisible(true);
	}

	public GridBagConstraints setComp(String conStr, double weight, int gridW, int gridX, int gridY) {
		GridBagConstraints c = new GridBagConstraints();
		if(conStr.equals("LINE_START")) {
			c.fill = GridBagConstraints.LINE_START;
		} else if(conStr.equals("RELATIVE")) {
			c.fill = GridBagConstraints.RELATIVE;
		} else {
			c.fill = GridBagConstraints.HORIZONTAL;
		}
		c.weightx = weight;
		c.gridwidth = gridW;
		c.gridx = gridX;
		c.gridy = gridY;
		return c;
	}

}
