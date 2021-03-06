package skullking;

public class Comparator extends CardSlot {
	private int winner; 
	private CardSlot strongest_card;
	private CardSlot firstNumber;

	public Comparator(int x) {
		super(x);  
		winner=1;
		strongest_card=null;
		firstNumber=null;
		System.out.println(this.getLevel()+"레벨");
	}
	
	public void newRoundComparator() {
		strongest_card=null;
		firstNumber=null;
	}
	
	public CardSlot getFirstNumberCard() {
		return firstNumber;
	}
	
	public void setFirstNumberCard(CardSlot c) {
		firstNumber=c;
		setVisible(false);
		super.updateCard(c);
		setVisible(true);
	}
	
	public void setData(CardSlot present_card, int player_num) {
		System.out.println("새로운 이미지로 update : "+present_card.getCardInfo());
		strongest_card=present_card;
		System.out.println("그래서 strongest is "+strongest_card.getCardInfo());
		winner=player_num; 
		//System.out.println("카드 번호는?"+strongest_card.cardNum);
		super.updateCard(strongest_card);
	}
	
	public boolean getWinOrLose(int playerNum) {
		System.out.println("winner : "+winner+"/ 플레이어 : ㅁㅁㅁㅁㅁㅁㅁㅁㅁ"+playerNum);
		if(winner==playerNum) return true;
		else return false;
		
	}
	
	public void forceUpdateCard() {
		System.out.println("강제 업데이트");
		updateCard(strongest_card);
	}
	
	public void FupdateCard(CardSlot present_card) {
		if(firstNumber==null) {
			super.updateCard(present_card);
		}
	}
	
	public void updateCard(CardSlot present_card, int player_num) { //update card picture, but alert strongest card and its owner
		/*
		 * 	static final int ESCAPE=1; 		//index 0~4 (5)
			static final int GOLD=2;		//index 5~17 (13)
			static final int RED=3;			//index 18~30 (13)
			static final int BLUE=4;		//index 31~43 (13)
			static final int BLACK=5;		//index 44~56 (13)
			static final int MERMAID=6;		//index 57~58 (2)
			static final int PIRATE=7;		//index 59~63 (5)
			static final int SCARY_MARRY=8;	//index 64
			static final int SKULL_KING=9;	//index 65
		 * */

		System.out.println("updateCard for playerNum "+player_num);
		System.out.println("player"+player_num+"이 "+present_card.getCardInfo()+"를 냄!");
		if(strongest_card==null){
			System.out.println("strong card is none, 따라서 이긴다!");
			strongest_card=present_card; 
			winner=player_num;
			setData(present_card,player_num);  
		
		} else {
			System.out.println(strongest_card.getCardInfo()+"에 대항하여 "+player_num+"이 "+present_card.getCardInfo()+"를 냈다! 과연...."); //카드정보 뽑는 메서드?
			int level=strongest_card.getLevel();
			if(level==CardSlot.ESCAPE) {
				setData(present_card,player_num);
			}
			else if(level>=CardSlot.GOLD && level<CardSlot.BLACK){
				if(present_card.getLevel()>=CardSlot.BLACK) {//if the strong card is normal number card, the strong card will be changed when the input is over black_card(includes black card)
					setData(present_card,player_num);
				}
				else if(level==present_card.getLevel()) {//else, if strong card color is same as present_card color, compare the index of cards and set.
					if(present_card.getCardIndex()>strongest_card.getCardIndex()) {
						setData(present_card,player_num);
					}
				}
			}
			else if(level==CardSlot.BLACK) {
				if(present_card.getLevel()>level) {
					setData(present_card,player_num);
				}
				else if(level==present_card.getLevel() && present_card.getCardIndex()>strongest_card.getCardIndex()) {
					setData(present_card,player_num);
				}
			}
			else if(level==CardSlot.MERMAID) {
				if(present_card.getLevel()>level) {
					setData(present_card,player_num);
				}
			}
			else if(level==CardSlot.PIRATE || level==CardSlot.SCARY_MARRY) {
				if(present_card.getLevel()==SKULL_KING) {
					setData(present_card,player_num);
				}
			}
			else {
				if(present_card.getLevel()==CardSlot.MERMAID) { //MERMAID==6
					setData(present_card,player_num);
				}
			}

		}
		System.out.println(strongest_card.getCardInfo()+"를 낸 "+winner+" wins");
		//super.updateCard(strongest_card);
		//super.setIcon(strongest_card.getIcon());
	}

	public int getWinner() {
		return winner;
	}
}
