package ModelPoker;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BuildFrame2 {

	private JFrame f;
	private JTextField anteAmount;
	private ButtonGroup playerButtons;
	private JTextField name;
	private PlayPokerGame game;
	private JPanel cards;
	private HashMap<String,Integer> wallets;
	private GridBagConstraints comp;
	private JLabel displayWinner;
	private int ante;

	public static void main(String[] a) {
		BuildFrame2 bf = new BuildFrame2();
		bf.createFrame();
	}

	public void createFrame() {
		// Create frame for poker game
		f = new JFrame("Poker Game");
		f.setLayout(new GridBagLayout());
		comp = new GridBagConstraints();

		// Create buttons and labels to play game
		final JLabel myLabel = new JLabel("Would you like to play?", SwingConstants.CENTER);
		comp = setComp("HORIZONTAL",0.5, 1, 0, 0);
		f.add(myLabel, comp);

		final JButton yButton = new JButton("Yes");
		comp = setComp("HORIZONTAL",0.5, 1, 1, 0);
		f.add(yButton, comp);

		final JButton nButton = new JButton("No");
		comp = setComp("HORIZONTAL",0.5, 1, 2, 0);
		f.add(nButton, comp);

		// Set parameters for frame
		f.setBounds(400, 100, 500, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		// Set action listener for yes and no buttons
		// Yes button will continue on with game
		yButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.remove(yButton);
				f.remove(nButton);
				f.remove(myLabel);
				getName();
			}
		});

		// No button will close the window and exit the game
		nButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
			}
		});

		// Create label to display winner after cards are dealt
		displayWinner = new JLabel("", SwingConstants.CENTER);
		comp = setComp("HORIZONTAL",0.5, 3, 0, 6);
		f.add(displayWinner, comp);
	}

	// Method to get player name and game parameters
	public void getName() {
		JLabel getname = new JLabel("What is your players name? ", SwingConstants.CENTER);
		comp = setComp("HORIZONTAL",0.5, 1, 0, 1);
		f.add(getname, comp);

		name = new JTextField(10);
		comp = setComp("HORIZONTAL",0.5, 1, 1, 1);
		comp.ipadx = 15;
		f.add(name, comp);

		final JButton submit = new JButton("Submit");
		comp = setComp("HORIZONTAL",0.0, 1, 2, 1);
		f.add(submit, comp);

		// Action listener to accept the player name and continue
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submit.setEnabled(false);
				name.setEditable(false);
				getPlayers();
			}
		});

		f.setVisible(true);
	}

	// Method to get the number of players for the game
	public void getPlayers() {
		final JLabel getplayers = new JLabel(
				"How many players should we deal cards for? ", SwingConstants.CENTER);
		comp = setComp("HORIZONTAL",0.5, 1, 0, 2);
		f.add(getplayers, comp);

		// Create button group for player radio buttons
		playerButtons = new ButtonGroup();

		final JPanel buttonPanel = new JPanel(new GridLayout(2,2));
		comp = setComp("HORIZONTAL",0.5, 2, 1, 2);
		comp.ipady = 40;
		f.add(buttonPanel, comp);

		// Create radio buttons for players
		JRadioButton players2 = new JRadioButton("2", true);
		players2.setActionCommand(players2.getText());
		buttonPanel.add(players2);

		JRadioButton players3 = new JRadioButton("3");
		players3.setActionCommand(players3.getText());
		buttonPanel.add(players3);

		JRadioButton players4 = new JRadioButton("4");
		players4.setActionCommand(players4.getText());
		buttonPanel.add(players4);

		JRadioButton players5 = new JRadioButton("5");
		players5.setActionCommand(players5.getText());
		buttonPanel.add(players5);

		// Add radio buttons to button group
		playerButtons.add(players2);
		playerButtons.add(players3);
		playerButtons.add(players4);
		playerButtons.add(players5);

		final JButton play = new JButton("Deal");
		comp = setComp("HORIZONTAL",0.5, 3, 0, 4);
		comp.ipady = 20;
		f.add(play, comp);

		// Call method to set ante amount
		getMoney();

		// Add action listener to deal cards for game.
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.remove(buttonPanel);
				f.remove(getplayers);
				play.setText("Deal Again");
				f.setVisible(true);
				submitInfo();
				diplayCards();
				displayWinners();
				anteAmount.setEditable(false);
			}
		});

		f.setVisible(true);

	}

	public void getMoney() {
		JLabel getmoney = new JLabel("Ante: $", SwingConstants.CENTER);
		comp = setComp("HORIZONTAL",0.5, 1, 0, 3);
		f.add(getmoney, comp);

		anteAmount = new JTextField("3");
		anteAmount.setPreferredSize(new Dimension(55,20));
		comp = setComp("LINE_START",0.5, 1, 1, 3);
		comp.ipady = 5;
		comp.ipadx = 15;
		f.add(anteAmount, comp);

		final JButton setAnte = new JButton("Set");
		comp = setComp("LINE_START",0.5, 1, 2, 3);
		f.add(setAnte, comp);

		// Set action listener to create starting wallets for players
		setAnte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAnte.setEnabled(false);
				anteAmount.setEditable(false);
				setWallets();
			}
		});

	}

	// Method to create instance of back end poker game
	public void submitInfo() {
		int n = Integer.parseInt(playerButtons.getSelection().getActionCommand());
		game = new PlayPokerGame(n, name.getText());
		handleBets();
	}

	// Method to create wallets for players with ante amount * 3
	public void setWallets() {
		int n = Integer.parseInt(playerButtons.getSelection().getActionCommand());
		ante = Integer.parseInt(anteAmount.getText());
		String[] names = {name.getText(),"Player 2","Player 3","Player 4","Player 5"};
		wallets = new HashMap<String,Integer>();
		for(int x = 0; x <= n; x++) {
			wallets.put(names[x], ante*3);
		}
	}

	// Method to display players cards and wallets amounts
	public void diplayCards() {
		// Create panel for all players cards
		cards = new JPanel(new GridLayout(3,2));
		// FOR loop going HashMap with players and their cards
		for(Entry<String, String[]> player : game.getPlayerCards().entrySet()) {
			// Creating individual panels for players and their cards
			JPanel playerPanel = new JPanel();
			// Create label for player name
			JLabel n = new JLabel(player.getKey() + ": ");
			// Add player name to player panel
			playerPanel.add(n);
			// FOR loop going through player HashMap value (string array of their cards)
			for(String card : player.getValue()) {
				// Creates a label for the card
				JLabel c = new JLabel(card, SwingConstants.LEFT);
				// Adds cards to player panel
				playerPanel.add(c);
			}

			// Create string for the wallet amount to display (recalculated for winner in handleBets();)
			String walletAmount = Integer.toString(wallets.get(player.getKey()));
			// Create label for wallet amount for player
			JLabel w = new JLabel("Wallet amount: $" + walletAmount);
			// Add wallet label to player panel
			playerPanel.add(w);
			// Add player panel to panel for all players
			cards.add(playerPanel);
		}
		comp = setComp("HORIZONTAL",0.5, 3, 0, 5);
		comp.ipady = 70;
		f.add(cards, comp);

		f.setVisible(true);
	}

	// Method to re-set the text for the winner in the winner panel created at the beginning
	private void displayWinners() {
		String winner = game.getWinner();
		displayWinner.setText(winner + " won!");
		f.setVisible(true);
	}

	// Recalculate winner from newly dealt cards
	public void handleBets() {
		wallets = new CalculateMoney(wallets, ante, game.getWinner()).recalculateMoney();
	}

	// Method to set grid bag parameters for frame objects
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
