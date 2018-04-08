package io.game.chess;

import java.io.Serializable;

public class Position implements Serializable {
	private int xPos;
	private int yPos;
	public int getxPos() {
		return xPos;
	}
	public Position() {
		
			
		}
	public Position(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public boolean equals(Position testPosition) {
	    if(testPosition!=null && testPosition.xPos==this.xPos && testPosition.yPos==this.yPos) 
	    {
	        return true;
	    } 
	    else 
	    {
	        return false;
	    }
	}
	
	public static boolean isValid(Position position) {
		if(position.getxPos()>=0 && position.getxPos()<=7 && position.getyPos()>=0 && position.getyPos()<=7)
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "x:" + this.xPos + "| y:" + this.yPos;
	}
	
}
