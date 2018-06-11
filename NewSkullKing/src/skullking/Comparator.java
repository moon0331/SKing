package skullking;

public class Comparator extends CardSlot {
	private int winner; 
	private CardSlot strongest_card;

	public Comparator() {
		super();  
	}
	
	public void updateCard(CardSlot present_card, int player_num) { //update card picture, but alert strongest card and its owner
		if(strongest_card==null)
		{
			strongest_card=present_card; 
			winner=player_num;
		} 
		else {
			
			if(strongest_card.getLevel()==1) {
				strongest_card=present_card; 
				winner=player_num;
			}
			
			else if(strongest_card.getLevel()==2) {
				
				if(present_card.getLevel()==2) {
					if(present_card.getcardIndex()>strongest_card.getcardIndex()) {
						strongest_card=present_card; 
						winner=player_num;
					}
								
				}
				
				else {
					if(present_card.getLevel()>4) {
						strongest_card=present_card; 
						winner=player_num;
					}
						
				}
						
			} 
			
			else if (strongest_card.getLevel() == 3) {

				if (present_card.getLevel() == 3) {
					if (present_card.getcardIndex() > strongest_card.getcardIndex()) {
						strongest_card=present_card; 
						winner=player_num;
					}
						
				}

				else {
					if (present_card.getLevel() > 4) {
						strongest_card=present_card; 
						winner=player_num;
					}
						
				}

			}

			else if (strongest_card.getLevel() == 4) {

				if (present_card.getLevel() == 4) {
					if (present_card.getcardIndex() > strongest_card.getcardIndex()) {
						strongest_card=present_card; 
						winner=player_num;
					}
						
				}

				else {
					if (present_card.getLevel() > 4) {
						strongest_card=present_card; 
						winner=player_num;
					}
						
				}

			}	
			
			else if (strongest_card.getLevel() == 5) {

				if (present_card.getLevel() == 5) {
					if (present_card.getcardIndex() > strongest_card.getcardIndex()) {
						strongest_card=present_card; 
						winner=player_num;
					}
					
				}

				else {
					if (present_card.getLevel() > 5) {
						strongest_card=present_card; 
						winner=player_num;
					}
						
				}

			} 
			//
			
			else if (strongest_card.getLevel() == 6) { 
				if(present_card.getLevel()>6) {
					strongest_card=present_card; 
					winner=player_num;
				}
					 
			} 
			
			else if (strongest_card.getLevel() == 7) { 
				if(present_card.getLevel()==9) {
					strongest_card=present_card; 
					winner=player_num;
				}
					
			} 
			
			else if (strongest_card.getLevel() == 8) { 
				if(present_card.getLevel()==9) {
					strongest_card=present_card; 
					winner=player_num;
				}
					
			} 
			
			else if (strongest_card.getLevel() == 9) { 
				if(present_card.getLevel()==6) {
					strongest_card=present_card; 
					winner=player_num;
				}
					
			}	
		}
	}

	public int getWinner() {
		return winner;
	}
}
