package skullking;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

class Game{
	int round;
}

public class Main extends JFrame{
	static JFrame gameScreen;			//main screen where game played.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gameScreen=new JFrame(); //my frame
		gameScreen.setLayout(null); //not use layout manager
		gameScreen.setSize(800, 800); //frame size
		gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close option
		gameScreen.setVisible(true); //look frame
		
		JLabel roundNumber=new JLabel("roundNumber");		//label to represent round number
		roundNumber.setFont(roundNumber.getFont().deriveFont(20.0f)); //font size 20
		roundNumber.setSize(300,300); 	//label size
		roundNumber.setLocation(700,100);//location
		gameScreen.add(roundNumber);//look frame
		
		
		CardSlot[] cslot=new CardSlot[10]; //ten cards
		Comparator comparator_card=new Comparator(); //slot for most strong card. //make comparator = inheritance of cardslot?
		//comparator_card.setLocation(500,200);
		gameScreen.add(comparator_card);	//now you can see comparator_card in screen
		Random r=new Random();
		for(int i=0;i<10;i++) {
			cslot[i]=new CardSlot(i,5, r.nextInt(CardSlot.NUM_OF_CARDS));	//make random card in cardslot
			gameScreen.add(cslot[i]);	//you can see that slot
		}
		
		JTextField jt=new JTextField(10);	//text field to write int
		jt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//if string entered
				try {
					int val=Integer.parseInt(jt.getText());	//change into int
					System.out.println(val);
					if(cslot[val].getCanPick(comparator_card)) {			//if the card is pickable
						comparator_card.updateCard(cslot[val]); //need to update comparator when more powerful card occurs.(resolve it!)
						cslot[val].setVisible(false); //disable to pick slot's card
					}
				} catch(Exception ex){}
			}
		});
		jt.setSize(300,40); //textfield size
		jt.setLocation(800,700); //textfield location
		gameScreen.add(jt);		//add it
		
	}

}
