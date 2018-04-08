package io.game.chess.piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.game.chess.Board;
import io.game.chess.Position;

public class Knight extends Piece implements Serializable {

	@Override
	public List<Position> getAllowableMove(Board board) {
		List<Position> allowableMoves= new ArrayList<Position>();
		
		allowableMoves.add(new Position(this.getPosition().getxPos()-2,this.getPosition().getyPos()+1));
		allowableMoves.add(new Position(this.getPosition().getxPos()-2,this.getPosition().getyPos()-1));
		allowableMoves.add(new Position(this.getPosition().getxPos()+2,this.getPosition().getyPos()+1));
		allowableMoves.add(new Position(this.getPosition().getxPos()+2,this.getPosition().getyPos()-1));
		allowableMoves.add(new Position(this.getPosition().getxPos()-1,this.getPosition().getyPos()+2));
		allowableMoves.add(new Position(this.getPosition().getxPos()+1,this.getPosition().getyPos()+2));
		allowableMoves.add(new Position(this.getPosition().getxPos()-1,this.getPosition().getyPos()-2));
		allowableMoves.add(new Position(this.getPosition().getxPos()+1,this.getPosition().getyPos()-2));
		
		//allowableMoves.removeAll(allowableMoves.stream().filter(t -> t.getxPos()>=0 && t.getyPos()>=0 && t.getxPos()<=7 && t.getyPos()<=7).findFirst().orElse(null));
		List<Position> wrongMoves =new ArrayList<Position>();
		allowableMoves.stream().filter(t -> t.getxPos()<0 || t.getyPos()<0 || t.getxPos()>7 || t.getyPos()>7).forEach(wrongMoves::add);;
		for(Position tempPosition:allowableMoves)
		{
			if(board.getTurn()==1)
			{
				if(board.getMyWhitePieces().stream().filter(t->t.getPosition().equals(tempPosition)).findFirst().orElse(null)!=null)
				{
					wrongMoves.add(tempPosition);
				}
				
			}
			else
			{
				if(board.getMyBlackPieces().stream().filter(t->t.getPosition().equals(tempPosition)).findFirst().orElse(null)!=null)
				{
					wrongMoves.add(tempPosition);
				}
			}
		}

		allowableMoves.removeAll(wrongMoves);
		return allowableMoves;
	}
	
	public Knight(Position position,int color) {
		this.position=position;		
		this.color=color;
		this.type="Kt";

	}
	
	public static List<Knight>  defaultKnight(int color)
	{
		List<Knight> myKnights=new ArrayList<Knight>();
		int y;
		if (color == 1)
			y=0;
		else
			y=7;

			myKnights.add(new Knight(new Position(1,y),color));
			myKnights.add(new Knight(new Position(6,y),color));
			
		return myKnights;
	}
	
	@Override
	public String toString() {
		return super.toString()+"Kt";
	}
	
	public Knight(Piece piece) {
		this.position=piece.position;
		this.color=piece.color;
		this.type=piece.type;
	}
	

}
