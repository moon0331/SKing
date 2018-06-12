package skullking;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

// firstNumber 설정  - 게임마다 - 처음 숫자 나왔을때 제한시키는 comparator_card.setFirstNumberCard() 사용해야함.
// 컴퓨터 플레이어가 어떻게 카드를 내는가
// 점수 계산 및 종료(종료 언제 하는지 확인)

class GameRound{
	int round;
	int remained;
	public GameRound(int val) {
		setRound(val);
	}
	public void setRound(int r) {
		round=r;
		remained=r;
	}
	public int getRound() {
		return round;
	}
	public boolean isRoundEnd() {
		return remained==0;
	}
	public void endTurn() {
		remained-=1;
	}
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
		
		GameRound round=new GameRound(1);
		
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
			cslot[i]=new CardSlot(i,round.getRound(), r.nextInt(CardSlot.NUM_OF_CARDS));	//make random card in cardslot
			gameScreen.add(cslot[i]);	//you can see that slot
		}
		
		JTextField jt=new JTextField(10);	//text field to write int
		jt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//if string entered
				//이 클래스 안에 넣기
				try {
					int val=Integer.parseInt(jt.getText());	//change into int
					System.out.println(val+"선택함"); 
					
					boolean tfval=false;
					for(int k=0;k<10;k++) {
						if(cslot[k].checkValidity(comparator_card)) {
							tfval=true;
							break;
						}
					}
					if(!tfval) {
						cslot[val].setCanPlay(true);
					}
					
					if(cslot[val].getCanPick(comparator_card)) {			//if the card is pickable
						//check level
						//comparator_card.updateCard(cslot[val]); //need to update comparator when more powerful card occurs.(resolve it!)
						comparator_card.updateCard(cslot[val],0); //input for player 0 : user
						statusLabel.setText(cslot[val].getCardInfo()+" picked. "
						+"You may "+comparator_card.getWinOrLose(0)); //change text
						cslot[val].setVisible(false); //disable to pick slot's card
						round.endTurn();
						//calculate result and end turn.
						//round++;
						//if(round==11) calResult();
						if(round.isRoundEnd()) {
							//종료시 이벤트 처리..................점수계산
							int rnd=round.getRound();
							for(int i=0;i<10;i++) {
								cslot[i].setVisible(false);
								System.out.println(rnd+"라운드 새로 시작");
								cslot[i].setCardSlot(i, rnd+1, r.nextInt(CardSlot.NUM_OF_CARDS)); //새 라운드에서 카드 받음
								cslot[i].setVisible(true);
								//comparator 초기화해야함....................
							}
							round.setRound(rnd+1);
						}
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
