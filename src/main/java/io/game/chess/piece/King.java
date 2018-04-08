package io.game.chess.piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.game.chess.Board;
import io.game.chess.Position;

public class King extends Piece implements Serializable{

	@Override
	public List<Position> getAllowableMove(Board board) {
		List<Position> allowableMoves= new ArrayList<Position>();
		
		allowableMoves.add(new Position(this.getPosition().getxPos()-1,this.getPosition().getyPos()-1));
		allowableMoves.add(new Position(this.getPosition().getxPos()-1,this.getPosition().getyPos()));
		allowableMoves.add(new Position(this.getPosition().getxPos()-1,this.getPosition().getyPos()+1));
		
		allowableMoves.add(new Position(this.getPosition().getxPos(),this.getPosition().getyPos()-1));
		allowableMoves.add(new Position(this.getPosition().getxPos(),this.getPosition().getyPos()+1));
		
		allowableMoves.add(new Position(this.getPosition().getxPos()+1,this.getPosition().getyPos()-1));
		allowableMoves.add(new Position(this.getPosition().getxPos()+1,this.getPosition().getyPos()));
		allowableMoves.add(new Position(this.getPosition().getxPos()+1,this.getPosition().getyPos()+1));

		
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
	
	
	public King(Position position,int color) {
		this.position=position;		
		this.color=color;
		this.type="Kg";
	}
	
	public static List<King>  defaultKing(int color)
	{
		List<King> myKings=new ArrayList<King>();

		if (color == 1)
			myKings.add(new King(new Position(3,0),color));
		else
			myKings.add(new King(new Position(3,7),color));

		return myKings;
	}
	
	@Override
	public String toString() {
		return super.toString()+"Kg";
	}
	
	public King(Piece piece) {
		this.position=piece.position;
		this.color=piece.color;
		this.type=piece.type;
	}
	
}
