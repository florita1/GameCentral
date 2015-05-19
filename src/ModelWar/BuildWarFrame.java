package ModelWar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class BuildWarFrame {

	private JFrame f;
	private String playerName;
	private HashMap<String,List<String>> playerCards;
	private JLabel winnerText;
	private JLabel p1Card; 
	private JLabel p2Card;
	private JLabel p1Size; 
	private JLabel p2Size;
	private String winner;
	private boolean war = false;

	public static void main(String[] a) {
		BuildWarFrame wf = new BuildWarFrame();
		wf.createFrame();
	}

	public void createFrame() {
		// Create frame for poker game
		f = new JFrame("Poker Game");
		f.setLayout(new GridLayout(6,1));

		JPanel n = new JPanel();
		JLabel getName = new JLabel("What is your name? ");
		final JTextField name = new JTextField(10);
		JButton set = new JButton("Set");
		n.add(getName);
		n.add(name);
		n.add(set);

		JButton play = new JButton("Deal");	
		JButton exit= new JButton("Quit");
		
		winnerText = new JLabel("", SwingConstants.CENTER);

		f.add(n);
		f.add(play);
		f.add(winnerText);
		f.add(exit);
		
		f.setBounds(450, 200, 500, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		set.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerName = name.getText();
				makePlayerPanel();
				playerCards = new MakePlayersHands(name.getText()).getPlayerMap();
				name.setEditable(false);
			}
		});
		
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCards();
				getWinner();
			}
		});
	}
	
	public void makePlayerPanel() {
		JPanel cards = new JPanel();
		JPanel playerPanel = new JPanel();
		JLabel p1Name = new JLabel(playerName + ":");
		JLabel p2Name = new JLabel("    Player 2:");
		p1Card = new JLabel();
		p2Card = new JLabel();
		p1Size = new JLabel();
		p2Size = new JLabel();
		playerPanel.add(p1Name);
		playerPanel.add(p1Card);
		playerPanel.add(p1Size);
		playerPanel.add(p2Name);
		playerPanel.add(p2Card);
		playerPanel.add(p2Size);
		cards.add(playerPanel);
		f.add(cards);
		//f.setVisible(true);
	}
	
	public void displayCards(){
		p1Card.setText(playerCards.get(playerName).get(0));
		p2Card.setText(playerCards.get("Player 2").get(0));
		p1Size.setText("Deck size: " + playerCards.get(playerName).size());
		p2Size.setText("Deck size: " + playerCards.get("Player 2").size());
		
		f.setVisible(true);
	}
	
	public void getWinner() {
		CompareCards winners = new CompareCards(playerCards,playerName);
		if(winners.isWar()) {
			war = true;
			JLabel warMess = new JLabel("War");
			f.add(warMess);
			f.setVisible(true);
			winners.warScenario();
			winner = winners.getWinner();
		} else {
			war = false;
			winner = winners.getWinner();
		}
		winnerText.setText(winner + " wins!");
		handleDecks();
	}
	
	public void handleDecks(){
		new HandleDeck(winner, playerCards, war, playerName);
		
	}
	
}
