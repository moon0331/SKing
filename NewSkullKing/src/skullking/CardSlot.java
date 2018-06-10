package skullking;

import javax.swing.*;
import java.awt.event.*;
/*
 * escape : 0~4
 * gold : 5~
 */

public class CardSlot extends JButton {
	int slotIndex;			// index of cardslot (0~9)
	int cardIndex;			// present card number
	int cardNum;			// important when normal card appeared
	boolean canPick;		// can pick card?
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
	
	
	public CardSlot(int idx, int round, int cardidx) {
		slotIndex=idx;
		cardIndex=cardidx;
		if(cardIndex>=GOLD && cardIndex<=BLACK)
			cardNum=(cardidx-5)%13;
		else
			cardNum=-1;
		this.setSize(100,150);
		this.setLocation(600+80*idx,600); //need to check
		if(idx<round) {
			//can pick
			canPick=true;
			this.setIconImage(canPick);
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("card number " + cardIndex 
							+ " in slotIndex "+ slotIndex +"picked..");
				}
			});
		}else {
			canPick=false;
			this.setIconImage(canPick);
			this.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("do not touch card "+slotIndex);
				}
			});
		}
	}

	public void setIconImage(boolean canPick) {
		if(canPick) {
			String str=cardLevelString[getLevel()]+".jpg";
			canPickImg=new ImageIcon(str);
			System.out.println(str);
			setIcon(canPickImg);
		}
		else {
			dontPickImg=new ImageIcon("dontPick.jpg");
			setIcon(dontPickImg);
		}
	}
	
	public int getLevel() {
		if(cardIndex>=0 && cardIndex<=4)
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
	
	public boolean getCanPick() {
		return canPick;
	}
}
