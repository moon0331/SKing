package skullking;

import javax.swing.JPanel;

public class Player extends JPanel{
	static String[] nameString= {"AAA","BBB","CCC","DDD","FFF"};
	String name;
	boolean isComputer;
	int predictWin;
	int numOfWin;
	int idx;
	int score;
	public Player(int i, boolean isComputer) {
		name=nameString[i];
		idx=i;
		name=nameString[i]; //exception
		this.isComputer=isComputer;
	}
	
	public String getPlayerName() {
		return name;
	}
	
	public void setPredictWin(int win) {
		predictWin=win;
	}
	
	public int getPredictWin() {
		return predictWin;
	}
	
	public int setWin() {
		numOfWin++;
		return numOfWin;
	}
	
	public int calScore(int round) {
		int val=0;
		if(predictWin==0) {
			if(numOfWin==0) val=10*round;
			else val=(-10)*round;
		} else if(predictWin==numOfWin) val=20*predictWin;
		else val=Math.abs(predictWin-numOfWin)*(-10);
		return val;
	}
	
	public void updateScore(int round) {
		int s=calScore(round);
		System.out.println(name+" score +"+s);
		score+=s;
	}
	
	public int getScore() {
		return score;
	}
}