package io.game.chess.piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.game.chess.Board;
import io.game.chess.Position;

public class Pawn extends Piece implements Serializable {

	@Override
	public List<Position> getAllowableMove(Board board) {
		
		List<Position> allowableMoves= new ArrayList<Position>();
		Set<Piece> myTotalPieces= new HashSet<Piece>();
		myTotalPieces.addAll(board.getMyBlackPieces());
		myTotalPieces.addAll(board.getMyWhitePieces());
		
		//System.out.println("current position: " + this.position);
		
		if(board.getTurn()==Board.WHITE)
		{
			//White moves Down
			if((this.position.getyPos()+1 )< 7) 
			{
				
				//Straight Forward +1
				Position myTempPosition=new Position(this.position.getxPos(),this.position.getyPos()+1);
				Piece myPiece=myTotalPieces.stream().filter(t -> t.getPosition().equals(myTempPosition)).findFirst().orElse(null);
				if(myPiece==null)
				{
					allowableMoves.add(myTempPosition);

					//Straight Forward +2
					if(this.position.getyPos()==1) 
					{
						Position myTempPosition2=new Position(this.position.getxPos(),this.position.getyPos()+2);
						Piece myPiece2=myTotalPieces.stream().filter(t -> t.getPosition().equals(myTempPosition2)).findFirst().orElse(null);
						if(myPiece2==null)
						{
							allowableMoves.add(myTempPosition2);
						}
					}
				}
				//Cross Take Over
				if(this.position.getxPos()<7)
				{
					Position myTempPosition2=new Position(this.position.getxPos()+1,this.position.getyPos()+1);
					Piece myPiece2=board.getMyBlackPieces().stream().filter(t -> t.getPosition().equals(myTempPosition2)).findFirst().orElse(null);
					if(myPiece2!=null)
					{
						allowableMoves.add(myTempPosition2);
					}
				}
				//Cross Take Over
				if(this.position.getxPos()>0)
				{
					Position myTempPosition2=new Position(this.position.getxPos()-1,this.position.getyPos()+1);
					Piece myPiece2=board.getMyBlackPieces().stream().filter(t -> t.getPosition().equals(myTempPosition2)).findFirst().orElse(null);
					if(myPiece2!=null)
					{
						allowableMoves.add(myTempPosition2);
					}
				}
			}
		}
		else if (board.getTurn()==Board.BLACK)
		{
			//Black Moves Up
			if((this.position.getyPos()-1 )> 0) 
			{
				
				//Straight Forward +1
				Position myTempPosition=new Position(this.position.getxPos(),this.position.getyPos()-1);
				Piece myPiece=myTotalPieces.stream().filter(t -> t.getPosition().equals(myTempPosition)).findFirst().orElse(null);
				if(myPiece==null)
				{
					allowableMoves.add(myTempPosition);

					//Straight Forward +2
					if(this.position.getyPos()==6) 
					{
						Position myTempPosition2=new Position(this.position.getxPos(),this.position.getyPos()-2);
						Piece myPiece2=myTotalPieces.stream().filter(t -> t.getPosition().equals(myTempPosition2)).findFirst().orElse(null);
						if(myPiece2==null)
						{
							allowableMoves.add(myTempPosition2);
						}
					}
				}
				//Cross Take Over
				if(this.position.getxPos()<7)
				{
					Position myTempPosition2=new Position(this.position.getxPos()+1,this.position.getyPos()-1);
					Piece myPiece2=board.getMyWhitePieces().stream().filter(t -> t.getPosition().equals(myTempPosition2)).findFirst().orElse(null);
					if(myPiece2!=null)
					{
						allowableMoves.add(myTempPosition2);
					}
				}
				//Cross Take Over
				if(this.position.getxPos()>0)
				{
					Position myTempPosition2=new Position(this.position.getxPos()-1,this.position.getyPos()-1);
					Piece myPiece2=board.getMyWhitePieces().stream().filter(t -> t.getPosition().equals(myTempPosition2)).findFirst().orElse(null);
					if(myPiece2!=null)
					{
						allowableMoves.add(myTempPosition2);
					}
				}
			}
		}
		return allowableMoves;
	}
	
	public Pawn() {
		
	}
	
	public Pawn(Position position,int color) {
		this.position=position;		
		this.color=color;
		this.type="P";

	}
	
	public static List<Pawn>  defaultPawn(int color)
	{
		List<Pawn> myPawns=new ArrayList<Pawn>();
		int y;
		if (color == 1)
			y=1;
		else
			y=6;
		
		for(int i=0;i<8;i++)
		{
			myPawns.add(new Pawn(new Position(i,y), color));
		}
		return myPawns;
	}
	
	@Override
	public String toString() {
		return super.toString()+"P";
	}
	
	
	public Pawn(Piece piece) {
		this.position=piece.position;
		this.color=piece.color;
		this.type=piece.type;
	}
	
}
