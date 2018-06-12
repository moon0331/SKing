package skullking;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Scanner;

// firstNumber 설정  - 게임마다 - 처음 숫자 나왔을때 제한시키는 comparator_card.setFirstNumberCard() 사용해야함.
// 컴퓨터 플레이어가 어떻게 카드를 내는가
// 점수 계산 및 종료(종료 언제 하는지 확인)

class CardDeck{
	boolean[] isAlreadyPicked;
	Random r=new Random();
	public CardDeck(){
		isAlreadyPicked=new boolean[CardSlot.NUM_OF_CARDS];
		for(int i=0;i<CardSlot.NUM_OF_CARDS; i++) {
			isAlreadyPicked[i]=false;
		}
	}
	public int getCard() {
		while(true) {
			int val=r.nextInt(CardSlot.NUM_OF_CARDS);
			if(isAlreadyPicked[val]==false) {
				isAlreadyPicked[val]=true;
				return val;
			}
		}
	}
	public void resetCardDeck() {
		for(int i=0;i<CardSlot.NUM_OF_CARDS; i++) {
			isAlreadyPicked[i]=false;
		}
	}
}

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
	public JFrame gameScreen;			//main screen where game played.
	static final int NUM_OF_PLAYER=4;
	
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
	
		Player[] p=new Player[NUM_OF_PLAYER]; //4 members to play.
		for(int i=0;i<4;i++) {
			p[i]=new Player(i, !(i!=0)); //zero for real player, otherwise computer
		}
		
		JFrame gameScreen=new JFrame(); //my frame
		gameScreen.setLayout(null); //not use layout manager
		gameScreen.setSize(Main.CENTER*2,800); //frame size
		gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close option
		
		JLabel roundNumber=new JLabel("1round");		//label to represent round number
		roundNumber.setFont(roundNumber.getFont().deriveFont(20.0f)); //font size 20
		roundNumber.setSize(300,300); 	//label size
		roundNumber.setLocation(Main.CENTER, -100);//location
		gameScreen.add(roundNumber);//look frame
		
		JLabel statusLabel=new JLabel("Your Turn : ");
		statusLabel.setFont(statusLabel.getFont().deriveFont(25.0f));
		statusLabel.setSize(600,300);
		statusLabel.setLocation(Main.CENTER-200,200);
		gameScreen.add(statusLabel);
		
		JLabel strongest_card_here=new JLabel("Strongest Card");
		strongest_card_here.setFont(strongest_card_here.getFont().deriveFont(15.0f));
		strongest_card_here.setSize(300,300);
		strongest_card_here.setLocation(Main.CENTER-75,-20);
		gameScreen.add(strongest_card_here);
		
		JLabel your_card_here=new JLabel("Your Card");
		your_card_here.setFont(your_card_here.getFont().deriveFont(15.0f));
		your_card_here.setSize(300,300);
		your_card_here.setLocation(Main.CENTER+90,-20);
		gameScreen.add(your_card_here);
		
		CardSlot[] cslot=new CardSlot[10]; //ten cards
		
		Comparator comparator_card=new Comparator(-75); //slot for most strong card. //make comparator = inheritance of cardslot?
		Comparator my_play_card=new Comparator(75); //to show what you played.
		
		comparator_card.addActionListener(new ActionListener() { //when click comparator_card, 
			public void actionPerformed(ActionEvent ev) {
				try {
					System.out.println(comparator_card.getCardInfo()+" 낸 사람 : "+comparator_card.getWinner()); //print what card is strongest and who played.
				}catch(Exception e) {
					System.out.println("클릭했는데 정보 안뜸!");
					e.printStackTrace();
				}
			}
		});
		
		//comparator_card.setLocation(500,200);
		gameScreen.add(comparator_card);	//now you can see comparator_card in screen
		gameScreen.add(my_play_card);
		
		Random r=new Random();
		CardDeck deck=new CardDeck();
		
		for(int i=0;i<10;i++) {
			cslot[i]=new CardSlot(i,round.getRound(), deck.getCard());	//make random card in cardslot
			gameScreen.add(cslot[i]);	//you can see that slot
		}
		
		JTextField jt=new JTextField(10);	//text field to write int
		jt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//if string entered
				//이 클래스 안에 넣기
				try {
					int lastGameWinner=comparator_card.getWinner(); //이전게임 승자 ?항상 0?
					//System.out.println("저번판 이긴 사람은 "+lastGameWinner); 
					int val=Integer.parseInt(jt.getText());	//change into int
					System.out.println(val+"선택함"); 
					
					/*for(int computer=1; computer<lastGameWinner; computer++) { //나 다음 컴퓨터가 카드 냄
						comparator_card.updateCard(new CardSlot(0,0,deck.getCard()),computer); 
					}*/
					int rnd=round.getRound();
					
					if(val>=rnd) return;
					
					boolean tfval=false;
					for(int k=0;k<10;k++) {
						System.out.println(k+"번째 카드체크"+cslot[k].checkValidity(comparator_card));
						if(cslot[k].checkValidity(comparator_card)) {
							tfval=true;
							System.out.println("이 카드 낼수 있음");
							break;
						}
					}
					if(!tfval) {
						if(!cslot[val].isVisible())
							cslot[val].setCanPlay(true);
						System.out.println("모든 카드 가능");
					}
					System.out.println("=========================================================");
					System.out.println(comparator_card.getCardInfo()+"현재 comparator카드정보");
					System.out.println("=========================================================");
					if(cslot[val].getCanPick(comparator_card) && cslot[val].isVisible()) {			//if the card is pickable
						//지금 사라진 카드도 선택하면 되게 됨.
						//check level
						//comparator_card.updateCard(cslot[val]); //need to update comparator when more powerful card occurs.(resolve it!)

						for(int i=0;i<10;i++) {
							System.out.println("i번째는..........."+cslot[i].isVisible());
						}
						
						System.out.println("컴퓨터 카드 냄");
						comparator_card.updateCard(cslot[val],0); //input for player 0 : user
						my_play_card.updateCard(cslot[val]);
						System.out.println("현재 comparator에 놓인 카드는 "+comparator_card.getCardInfo());
						
						//for문 현재 안돌아감
						for(int computer=1; computer<4;/*computer%NUM_OF_PLAYER<lastGameWinner;*/ computer++) {
							System.out.println("컴퓨터 "+computer+"의 차례");
							while(true) {
								CardSlot c=new CardSlot(0,0,deck.getCard());
								System.out.println(c.getCardInfo()+"새로운 카드의 정보");
								if(c.checkValidity(comparator_card)) {
									comparator_card.updateCard(c,computer);
									break;
								}
							}
						}
						statusLabel.setText("You picked "+cslot[val].getCardInfo()+
						". Won"
						+ " the Game? : "+comparator_card.getWinOrLose(0)); //change text
						cslot[val].setCanPlay(false);
						cslot[val].setVisible(false); //disable to pick slot's card
						round.endTurn();
						/*ImageIcon lastWinCardIcon=(ImageIcon)comparator_card.getIcon();
						System.out.println("방금 경기 승리한 카드"+lastWinCardIcon.getDescription());*/
						//calculate result and end turn.
						//round++;
						//if(round==11) calResult();
						if(round.isRoundEnd()) {
							//종료시 이벤트 처리..................점수계산
							rnd=round.getRound(); ////////////////////////////round 수정함
							lastGameWinner=comparator_card.getWinner();
							System.out.println("이번판의 승자는 "+lastGameWinner);
							for(int i=0;i<NUM_OF_PLAYER; i++) {
								p[i].calScore(rnd);
								System.out.println("player"+p[i].getPlayerName()+"의 현재까지 점수는 "+p[i].getScore());
								// 화면에 띄워주기
							}
							System.out.println(rnd+"라운드 새로 시작");
							deck.resetCardDeck();
							for(int i=0;i<10;i++) {	//카드세팅
								cslot[i].setVisible(false);
								//System.out.println("============================================================");
								cslot[i].setCardSlot(i, rnd+1, deck.getCard()); //새 라운드에서 카드 받음
								cslot[i].setVisible(true);
								//System.out.println("카드세팅완료!");
							}

							for(int computer=1; computer<NUM_OF_PLAYER;computer++) { //예측
								//카드 중복처리 안되어있음. 여기는 예측
								int computerWinPrediction=r.nextInt(rnd+1);
								p[computer].setPredictWin(computerWinPrediction); //예측
								System.out.println(p[computer].getPlayerName()+"는 "+p[computer].getPredictWin()+"승 예상");
								//playerNum : 이전게임승자 ~ 플레이어 전까지
							}
							
							for(int computer=lastGameWinner; computer<NUM_OF_PLAYER; computer++) {
								//여기는 카드 내는것. 사람이 내고나서 다른 컴퓨터들도 카드를 내야 함.
								comparator_card.updateCard(new CardSlot(0,0,deck.getCard()),computer); 
							}
							round.setRound(rnd+1);
							roundNumber.setText((1+rnd)+"round");
						}
					}
					System.out.println("process done");
				} catch(Exception ex){
					//승수예측처리
					ex.printStackTrace();
				}
			}
		});
		jt.setSize(300,40); //textfield size
		jt.setLocation(Main.CENTER-150,600); //textfield location
		gameScreen.add(jt);		//add it
		
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
