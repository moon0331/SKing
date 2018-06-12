package skullking;

public class Comparator extends CardSlot {
	private int winner; 
	private CardSlot strongest_card;

	public Comparator() {
		super();  
		strongest_card=null;
		System.out.println(this.getLevel()+"레벨");
	}
	
	public void setData(CardSlot present_card, int player_num) {
		strongest_card=present_card;
		winner=player_num;
		super.updateCard(present_card);
	}
	
	public String getWinOrLose(int playerNum) {
		if(winner==playerNum) return "True";
		else return "False";
		
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

		System.out.println(strongest_card+"ㅁㄴㄹㅇ");
		if(strongest_card==null){
			System.out.println("strong card is none");
			strongest_card=present_card; 
			winner=player_num;
			setData(present_card,player_num);
		} else {
			int level=strongest_card.getLevel();
			if(level==CardSlot.ESCAPE) {
				setData(present_card,player_num);
			}
			else if(level>=CardSlot.GOLD && level<CardSlot.BLACK){
				if(present_card.getLevel()>=CardSlot.BLACK) {//if the strong card is normal number card, the strong card will be changed when the input is over black_card(includes black card)
					setData(present_card,player_num);
				}
				else if(level==present_card.getLevel()) {//else, if strong card color is same as present_card color, compare the index of cards and set.
					if(present_card.getcardIndex()>strongest_card.getcardIndex()) {
						setData(present_card,player_num);
					}
				}
			}
			else if(level==CardSlot.BLACK) {
				if(present_card.getLevel()>level || present_card.getcardIndex()>strongest_card.getcardIndex()) {
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
		System.out.println(strongest_card.getCardInfo()+winner+"wins");
	}

	public int getWinner() {
		return winner;
	}
}
