package com.sar.findingnumber;

public class Score {
	
	private int countS,countE;
	private long currentScore;
	Score(){
		currentScore=0;
	}
	
	void setCurrentScore(long score){
		currentScore+=score;
	}
	
	long getCurrentScore(){
		return currentScore;
	}
	long currentS(int countS,int countE){
		int d= countE-countS;
		if(d<=1)
			currentScore+=100;
		else if(d==2)
			currentScore+=80;
		else if(d>=3 && d<=5)
			currentScore+=50;
		else if(d>=6 && d<=10)
			currentScore+=20;
		else currentScore+=10;
		return currentScore;
	}
	
	long totalScore(int lengTime, int chance, int level){
		//int total;
		long total= currentScore;
		total+=(150-level+1)*10;
		if(chance>0) total+=chance*10;
		if(lengTime>0) total+=lengTime/500*10;
		return total;
	}
}
