package skullking;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

// firstNumber 설정  - 게임마다 - 처음 숫자 나왔을때 제한시키는 comparator_card.setFirstNumberCard() 사용해야함.
// 컴퓨터 플레이어가 어떻게 카드를 내는가
// 점수 계산 및 종료(종료 언제 하는지 확인)

class predictWinTextField extends JTextField{
	private boolean locked; //OK to predict?
	private int[] predict;
	private int round;
	private Random r;
	public predictWinTextField(int numOfPlayer){
		locked=false;
		predict=new int[numOfPlayer];
		round=1;
		r=new Random();
	}
	public void setRound(int rnd) {
		round=rnd;
		System.out.println("new round : "+rnd);
		locked=false;
	}
	public void setPrediction(Player[] p) {
		for(int i=0;i<p.length;i++) {
			if(i!=0) {
				predict[i]=r.nextInt(round+1);
			}
			p[i].setPredictWin(predict[i]);
			System.out.println(p[i].getPlayerName()+"는 "+p[i].getPredictWin()+"승 예상");
		}
	}
	public void setPredictionValue(int val, int i) {
		predict[i]=val;
	}
	public int getRound() {
		return round;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLock(boolean TF) {
		locked=TF;
	}
}

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
	public void setFree(int idx) {
		isAlreadyPicked[idx]=false;
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
		
		/*JButton b=new JButton(new ImageIcon("black8.jpg"));
		b.setSize(100,150);
		b.setLocation(0,0);
		gameScreen.add(b);*/
		
		JLabel roundNumber=new JLabel("1round");		//label to represent round number
		roundNumber.setFont(roundNumber.getFont().deriveFont(20.0f)); //font size 20
		roundNumber.setSize(300,300); 	//label size
		roundNumber.setLocation(Main.CENTER, -100);//location
		gameScreen.add(roundNumber);//look frame
		
		JLabel statusLabel=new JLabel("Your Turn : ");
		statusLabel.setFont(statusLabel.getFont().deriveFont(25.0f));
		statusLabel.setSize(600,300);
		statusLabel.setLocation(Main.CENTER-200,180);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
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
		
		JLabel last_game_winner=new JLabel("Last Game Winner");
		last_game_winner.setFont(last_game_winner.getFont().deriveFont(15.0f));
		last_game_winner.setSize(300,300);
		last_game_winner.setLocation(Main.CENTER+200,-20);
		gameScreen.add(last_game_winner);
		
		JLabel what_was_winner_card=new JLabel("Last Winning Card: ");
		what_was_winner_card.setFont(what_was_winner_card.getFont().deriveFont(15.0f));
		what_was_winner_card.setSize(300,300);
		what_was_winner_card.setLocation(Main.CENTER+200,20);
		gameScreen.add(what_was_winner_card);
		
		JLabel winStatus=new JLabel("real win/prediction : ");
		winStatus.setFont(winStatus.getFont().deriveFont(15.0f));
		winStatus.setSize(300,300);
		winStatus.setLocation(Main.CENTER-100,225);
		winStatus.setHorizontalAlignment(JLabel.CENTER);
		gameScreen.add(winStatus);
		
		
		//"total win "+ p[0].getWin() +"/ predicted win"+p[0].getPredictWin()
		
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
		
		CardDeck deck=new CardDeck();
		
		for(int i=0;i<10;i++) {
			cslot[i]=new CardSlot(i,round.getRound(), deck.getCard());	//make random card in cardslot
			gameScreen.add(cslot[i]);	//you can see that slot
		}
		
		predictWinTextField predField=new predictWinTextField(Main.NUM_OF_PLAYER);
		predField.setSize(150,40); //size
		predField.setLocation(300,600); //location
		predField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) { //예상승수 입력하는 이벤트
				try {
					int inputData=Integer.parseInt(predField.getText());
					System.out.println(inputData+"입력했음 player가");
					if(inputData>predField.getRound())
						return;
					predField.setPredictionValue(inputData,0);
					//player 잘 안들어가는데?
					predField.setPrediction(p);
					predField.setLock(true); //언제 false?
					statusLabel.setText("You predicted "+p[0].getPredictWin()+"win");
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		gameScreen.add(predField);
		
		JTextField jt=new JTextField(10);	//text field to write int
		jt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//if string entered
				//이 클래스 안에 넣기
				try {
					if(predField.isLocked()==false) {
						System.out.println("You didn't predict win");
						return;
					}
					int lastGameWinner=comparator_card.getWinner(); //이전게임 승자 ?항상 0?
					//System.out.println("저번판 이긴 사람은 "+lastGameWinner); 
					int val=Integer.parseInt(jt.getText());	//change into int
					System.out.println("==================="+val+"번 카드 선택함================="); 
					
					/*for(int computer=1; computer<lastGameWinner; computer++) { //나 다음 컴퓨터가 카드 냄
						comparator_card.updateCard(new CardSlot(0,0,deck.getCard()),computer); 
					}*/
					int rnd=round.getRound(); //라운드 체크
					
					if(val>=rnd) { 
						System.out.println("not a valid card : round="+rnd+", cardnum="+val);
						return;
					}
					
					boolean tfval=false;
					for(int k=0;k<10;k++) {
						boolean isOK=cslot[k].checkValidity(comparator_card);
						System.out.println(k+"번째 카드낼 수 있는지 : "+isOK);
						if(isOK) {
							tfval=true;
							break;
						}
					}
					if(!tfval) {
						if(cslot[val].isVisible())
							cslot[val].setCanPlay(true);
						System.out.println("모든 카드 가능");
					}
					System.out.println(comparator_card.getCardInfo()+"는 현재  comp에 놓인 카드의 카드정보");
					
					if(cslot[val].getCanPick(comparator_card) && cslot[val].isVisible()) {			//if the card is pickable
						//지금 사라진 카드도 선택하면 되게 됨.
						//check level
						//comparator_card.updateCard(cslot[val]); //need to update comparator when more powerful card occurs.(resolve it!)

						for(int i=0;i<10;i++) {
							System.out.println(i+"번째는..........."+cslot[i].isVisible());
						}
						
						System.out.println("컴퓨터 카드 냄");
						comparator_card.updateCard(cslot[val],0); //input for player 0 : user
						my_play_card.updateCard(cslot[val]);
						System.out.println("현재 comparator에 놓인 카드는 "+comparator_card.getCardInfo());
						
						for(int computer=1; computer<4;/*computer%NUM_OF_PLAYER<lastGameWinner;*/ computer++) {
							System.out.println("컴퓨터 "+computer+"의 차례");
							while(true) {
								int comCardNum=deck.getCard();
								System.out.println(comCardNum+"번 뽑음@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
								CardSlot c=new CardSlot(0,0,comCardNum);
								c.setLocation(100,100);
								c.setSize(50,75);
								c.setVisible(true);
								gameScreen.add(c);
								System.out.println(c.getCardInfo()+"새로운 카드의 정보");
								if(c.checkValidity(comparator_card)) {
									System.out.println("player "+computer+"가 그 카드 냄");
									comparator_card.updateCard(c,computer);
									break;
								} else {
									deck.setFree(c.getCardIndex());
								}
							}
						}
						statusLabel.setText("You picked "+cslot[val].getCardInfo()+
						". Won this Game? : "+comparator_card.getWinOrLose(0)); //change text
						
						for(int i=0;i<NUM_OF_PLAYER; i++) {
							boolean winOrLose=comparator_card.getWinOrLose(i);
							System.out.println("player "+i+" win? : "+winOrLose);
							System.out.println("predict : "+p[i].getPredictWin()+"real win : "+p[i].getWin());
							p[i].setWin(winOrLose);
						}
						
						cslot[val].setCanPlay(false);
						cslot[val].setVisible(false); //disable to pick slot's card
						round.endTurn();
						
						int r_win=p[0].getWin();
						int p_win=p[0].getPredictWin();
						winStatus.setText("real win/prediction : "+r_win+"win(s) / "+p_win+"win(s) | Prediction Hit : "+(r_win==p_win));
						
						if(round.isRoundEnd()) {
							System.out.println(round.getRound()+"round종료");
							//종료시 이벤트 처리..................점수계산 안함!!!!
							rnd=round.getRound(); ////////////////////////////round 수정함
							lastGameWinner=comparator_card.getWinner();
							System.out.println("이번판의 승자는 "+lastGameWinner);
							for(int i=0;i<NUM_OF_PLAYER; i++) {
								int score=p[i].calScore(rnd);
								p[i].updateScore(score);
								System.out.println("player"+p[i].getPlayerName()+"의 현재까지 점수는 "+p[i].getScore());
								// 화면에 띄워주기
							}
							if(round.getRound()==10) { 
								for(Player pp:p) {
									System.out.println(pp.getPlayerName()+"의 최종 점수는!!!!! "+pp.getScore());
								}
								gameScreen.setVisible(false); //점수 보여주고 나중에 꺼지기
								System.exit(0); //끝!@
							}
							what_was_winner_card.setText("Last Winning Card: "+comparator_card.getCardInfo());
							System.out.println(rnd+"라운드 새로 시작");
							deck.resetCardDeck();
							for(int i=0;i<10;i++) {	//카드세팅
								cslot[i].setVisible(false);
								//System.out.println("============================================================");
								cslot[i].setCardSlot(i, rnd+1, deck.getCard()); //새 라운드에서 카드 받음
								cslot[i].setVisible(true);
								//System.out.println("카드세팅완료!");
							}

							/*for(int computer=1; computer<NUM_OF_PLAYER;computer++) { //예측
								//카드 중복처리 안되어있음. 여기는 예측
								int computerWinPrediction=r.nextInt(rnd+1);
								p[computer].setPredictWin(computerWinPrediction); //예측
								System.out.println(p[computer].getPlayerName()+"는 "+p[computer].getPredictWin()+"승 예상");
								//playerNum : 이전게임승자 ~ 플레이어 전까지
							}*/
							
							for(int computer=lastGameWinner; computer<NUM_OF_PLAYER; computer++) {
								//여기는 카드 내는것. 사람이 내고나서 다른 컴퓨터들도 카드를 내야 함.
								while(true) {
									CardSlot newCard=new CardSlot(0,0,deck.getCard());
									if(newCard.checkValidity(comparator_card)) {
										comparator_card.updateCard(new CardSlot(0,0,deck.getCard()),computer);
										break;
									} else {
										deck.setFree(newCard.getCardIndex());
									}
								}
								System.out.println("computer picked!");
							}
							last_game_winner.setText("Last Game Winner : "+lastGameWinner);
							round.setRound(rnd+1);
							roundNumber.setText((1+rnd)+"round");
							predField.setRound(rnd+1);
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
		
		/*JTextField predictWin=new JTextField(10);
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
		});*/
		//gameScreen.add(predictWin);
		gameScreen.setVisible(true); //look frame
		
	}

}
