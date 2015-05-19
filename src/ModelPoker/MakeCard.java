package ModelPoker;

import java.awt.FlowLayout;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MakeCard {

	public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		
		JFrame test = new JFrame("Card Test" );
		test.setLayout(new FlowLayout());
		// Convert from Unicode to UTF-8
	    String string1 ="\u1F0C4";
	    
	    //byte[] utf8 = string1.getBytes("UTF-8");

	    // Convert from UTF-8 to Unicode
	    //String string2 = null;
	    //string2 = new String(utf8, "UTF-8");
	    
	    JPanel panel = new JPanel();
	    //String unicode = "\uD83C\uDCA0";
	    String s = null;
	    try {
	    	s = new String(string1.getBytes(), "UTF-8");
	   
	    } catch (UnsupportedEncodingException e1) {
	    	e1.printStackTrace();
	    	
	    }

	    JLabel label = new JLabel(s);
	    
	    //JLabel card = new JLabel(string2);
	    //card.setSize(10,20);
	    panel.add(label);
	    test.add(panel);

	    test.setBounds(400, 100, 200, 200);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

}
