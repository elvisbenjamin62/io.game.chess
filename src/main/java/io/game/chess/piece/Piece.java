package io.game.chess.piece;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.game.chess.Board;
import io.game.chess.Position;

public  class Piece implements Serializable{
	protected String type;
	protected int color; //  1 - white and 2 - black
	protected Position position;
	protected Boolean state;
	
	public List<Position> getAllowableMove(Board board){
		return null;
		
	};
	
	@Override
	public String toString() {
		if(color==1)
			return "w";
		else if (color==2)
			return "b";
		return null;
	}
	
	public Position getPosition() {
		return position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public static Set<Piece> getPieces(Set <Piece> allPieces,String type)
	{
		Set<Piece> myPieces=new HashSet<Piece>();
		for(Piece piece:allPieces)
		{
			if (piece.type.equals(type))
				myPieces.add(piece);
		}
		return myPieces;
		
	}

}
