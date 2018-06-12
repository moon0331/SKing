package skullking;

import javax.swing.*;
import java.awt.event.*;
/*
 * escape : 0~4
 * gold : 5~
 */

public class CardSlot extends JButton {
	int slotIndex;			// index of cardslot (0~9)
	int cardIndex;			// present card number (0~65)
	int cardNum;			// important when normal card appeared (1~13)
	boolean canPick;		// can pick card?
	boolean canPlay;		// can play card?
	ImageIcon canPickImg;	// image for pickable card
	ImageIcon dontPickImg;	// image for non-pickable card
	
	static final int ESCAPE=1; 		//index 0~4 (5)
	static final int GOLD=2;		//index 5~17 (13)
	static final int RED=3;			//index 18~30 (13)
	static final int BLUE=4;		//index 31~43 (13)
	static final int BLACK=5;		//index 44~56 (13)
	static final int MERMAID=6;		//index 57~58 (2)
	static final int PIRATE=7;		//index 59~63 (5)
	static final int SCARY_MARRY=8;	//index 64
	static final int SKULL_KING=9;	//index 65
	
	static final int NUM_OF_CARDS=66;
	
	static String[] cardLevelString= 
				{"zero", "escape", 
				"gold", "red", "blue", "black", 
				"mermaid","pirate",
				"scarymarry", "skullking"
			};
	
	public CardSlot() { //for comparator. not need to change variables.
		System.out.println("생성자 for comparator");
		slotIndex=-1;
		cardIndex=-1;
		canPick=false;
		this.setSize(100,150); //card size
		this.setLocation(Main.CENTER,150); //location
	}
	
	
	public CardSlot(int idx, int round, int cardidx) {
		slotIndex=idx;
		cardIndex=cardidx;
		//setRolloverIcon(new ImageIcon("13.jpg")); //마우스 갖다댔을때 when rollover mouse
		System.out.println(cardidx+"picked");
		if(cardidx>=5 && cardidx<=56)
			cardNum=(cardidx-5)%13+1;	//set card number when normal card
		else
			cardNum=0;
		this.setSize(100,150);		//card size
		this.setLocation(400+80*idx,400); //need to check
		if(idx<round) { // if it is available to pick in this round (round5 : only can pick card[0]~card[4])
			//can pick
			canPick=true;
			this.setIconImage(canPick); //set icon for card picture
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("card number " + cardIndex 
							+ " in slotIndex "+ slotIndex +" picked.."); //when clicked, print result
				}
			});
		}else {
			canPick=false;
			this.setIconImage(canPick); //set icon for XXXXX
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("do not touch card "+slotIndex); //when clicked print result
				}
			});
		}
	}
	
	public void setCardSlot(int idx, int round, int cardidx) { //new setting
		slotIndex=idx;
		cardIndex=cardidx;
		//setRolloverIcon(new ImageIcon("13.jpg")); //마우스 갖다댔을때 when rollover mouse
		System.out.println(cardidx+"picked");
		if(cardidx>=5 && cardidx<=56)
			cardNum=(cardidx-5)%13+1;	//set card number when normal card
		else
			cardNum=0;
		if(idx<round) { // if it is available to pick in this round (round5 : only can pick card[0]~card[4])
			//can pick
			canPick=true;
			this.setIconImage(canPick); //set icon for card picture
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("card number " + cardIndex 
							+ " in slotIndex "+ slotIndex +" picked.."); //when clicked, print result
				}
			});
		}else {
			canPick=false;
			System.out.println("this card is not allowed : "+idx+"th card");
			this.setIconImage(canPick); //set icon for XXXXX
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("do not touch card "+slotIndex); //when clicked print result
				}
			});
		}
	}
	
	public String getCardInfo() {
		if(cardNum==0)
			return CardSlot.cardLevelString[getLevel()];
		else
			return CardSlot.cardLevelString[getLevel()]+cardNum;
	}

	public void setIconImage(boolean canPick) {	//set icon
		if(canPick) {
			String str=cardLevelString[getLevel()]+".jpg";
			canPickImg=new ImageIcon(str);
			System.out.println(str);
			setIcon(canPickImg);	//proper icon
		}
		else {
			dontPickImg=new ImageIcon("dontPick.jpg");	//XXXX
			setIcon(dontPickImg);
		}
	}
	
	public int getLevel() {
		if(cardIndex>=-1 && cardIndex<=4) //-1 for comparator
			return ESCAPE;
		else if(cardIndex<=17)
			return GOLD;
		else if(cardIndex<=30)
			return RED;
		else if(cardIndex<=43)
			return BLUE;
		else if(cardIndex<=56)
			return BLACK;
		else if(cardIndex<=58)
			return MERMAID;
		else if(cardIndex<=63)
			return PIRATE;
		else if(cardIndex==64)
			return SCARY_MARRY;
		else
			return SKULL_KING;
	}
	
	public int getcardIndex() {
		if(canPick)
			return cardIndex;
		else
			return 0;
	}
	
	public boolean checkValidity(Comparator C) {
		System.out.println("check validity");
		System.out.println("comparator level : "+C.getLevel()+CardSlot.cardLevelString[C.getLevel()]);
		System.out.println(C.getFirstNumberCard());
		int level=1;
		if(C.getFirstNumberCard()!=null) {
			level=C.getFirstNumberCard().getLevel();
		}
		System.out.println(level+"현재 지배하는 레벨");
		if(level>=CardSlot.GOLD && level<=CardSlot.BLACK) {//if first filed num card color is same as mine, it can be played
			if(level==this.getLevel()) {
				canPlay=true;
			}
			else {
				canPlay=false;
			}
		}
		else{ //if first filed is not num card, u can play anyone.
			canPlay=true;
		}
		//System.out.println("낼수 있나? : "+canPlay);
		return canPlay;
	}
	
	public boolean getCanPick(Comparator compCardNum) {	// return whether this cardslot can be picked
		return canPick && checkValidity(compCardNum);
	}
	
	public ImageIcon getCanPickImg() {	//return canPickImg, which is card image
		return canPickImg;
	}
	
	public void setCanPlay(boolean tf) {
		canPlay=tf;
	}
	
	public void updateCard(CardSlot cslot) {	//update card image (used in comparator ; need to move?)
		System.out.println("update card!");
		ImageIcon icon=cslot.getCanPickImg();
		setIcon(icon);
	}
}
