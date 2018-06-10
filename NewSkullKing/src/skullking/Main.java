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
		gameScreen=new JFrame();
		gameScreen.setLayout(null);
		gameScreen.setSize(800, 800);
		gameScreen.setVisible(true);
		
		
		CardSlot[] cslot=new CardSlot[10];
		CardSlot myCard=new CardSlot(0,0,-1);
		Random r=new Random();
		for(int i=0;i<10;i++) {
			cslot[i]=new CardSlot(i,5, r.nextInt(CardSlot.NUM_OF_CARDS));
			gameScreen.add(cslot[i]);
		}
		
		JTextField jt=new JTextField(10);
		jt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int val=Integer.parseInt(jt.getText());
					System.out.println(val);
					if(cslot[val].getCanPick())
						cslot[val].setVisible(false);
				} catch(Exception ex){}
			}
		});
		jt.setSize(300,40);
		jt.setLocation(500,200);
		gameScreen.add(jt);
		
	}

}
