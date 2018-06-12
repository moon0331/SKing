package skullking;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

class Game{
	int round;
}

public class Main extends JFrame{
	JFrame gameScreen;			//main screen where game played.
	Player[] p;
	
	static final int CENTER=750;
	
	public void gameRound(int round) {
		for(int i=0; i<round;i++) {
			
		}
	}
	
	public void calResult() {
		System.out.println("END the game");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame gameScreen=new JFrame(); //my frame
		gameScreen.setLayout(null); //not use layout manager
		gameScreen.setSize(Main.CENTER*2,800); //frame size
		gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close option
		
		JLabel roundNumber=new JLabel("roundNumber");		//label to represent round number
		roundNumber.setFont(roundNumber.getFont().deriveFont(20.0f)); //font size 20
		roundNumber.setSize(300,300); 	//label size
		roundNumber.setLocation(Main.CENTER+150, 50);//location
		gameScreen.add(roundNumber);//look frame
		
		JLabel statusLabel=new JLabel("SKULLKING");
		statusLabel.setFont(statusLabel.getFont().deriveFont(25.0f));
		statusLabel.setSize(600,300);
		statusLabel.setLocation(Main.CENTER-200,200);
		gameScreen.add(statusLabel);
		
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
				//이 클래스 안에 넣기
				try {
					int val=Integer.parseInt(jt.getText());	//change into int
					System.out.println(val); 
					
					if(cslot[val].getCanPick(comparator_card)) {			//if the card is pickable
						//check level
						//comparator_card.updateCard(cslot[val]); //need to update comparator when more powerful card occurs.(resolve it!)
						comparator_card.updateCard(cslot[val],0); //input for player 0 : user
						statusLabel.setText(cslot[val].getCardInfo()+" picked. "
						+"You may "+comparator_card.getWinOrLose(0)); //change text
						cslot[val].setVisible(false); //disable to pick slot's card
						//calculate result and end turn.
						//round++;
						//if(round==11) calResult();
						/*if(remainCard==0) {
							for(int i=0;i<10;i++) {
								cslot[i].setVisible(false);
								cslot[i].setCardSlot(i, 6, r.nextInt(CardSlot.NUM_OF_CARDS));
								cslot[i].setVisible(true);
							}
						}*/ //카드 재배치
					}
				} catch(Exception ex){
					//승수예측처리
				}
			}
		});
		jt.setSize(300,40); //textfield size
		jt.setLocation(Main.CENTER-150,600); //textfield location
		gameScreen.add(jt);		//add it
		
		Player[] p=new Player[4]; //4 members to play.
		for(int i=0;i<4;i++) {
			p[i]=new Player(i, !(i!=0)); //zero for real player, otherwise computer
		}
		
		JTextField predictWin=new JTextField(10);
		predictWin.setSize(150,40); //size
		predictWin.setLocation(300,600); //location
		predictWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int inputWin=0;
				try {
					inputWin=Integer.parseInt(predictWin.getText());
				} catch(Exception e) {
					inputWin=0;
				} finally {
					p[0].setPredictWin(inputWin);
					for(int i=1;i<p.length;i++) {
						p[i].setPredictWin(r.nextInt(10)); //10 -> number of round
					}
					for(int i=0;i<p.length;i++) {
						System.out.println(p[i].getPredictWin()+"wins predicted by player"+i);
					}
				}
				//p.set
			}
		});
		gameScreen.add(predictWin);
		gameScreen.setVisible(true); //look frame
		
	}

}
