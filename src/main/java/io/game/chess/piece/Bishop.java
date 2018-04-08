package io.game.chess.piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.game.chess.Board;
import io.game.chess.Position;

public class Bishop extends Piece implements Serializable {

	@Override
	public List<Position> getAllowableMove(Board board) {
		List<Position> allowableMoves= new ArrayList<Position>();
		Set<Piece> myTotalPieces=new HashSet<Piece>(); 
		myTotalPieces.addAll(board.getMyBlackPieces());
		myTotalPieces.addAll(board.getMyWhitePieces());
		 
		Boolean NE=true;
		Boolean NW=true;
		Boolean SE=true;
		Boolean SW=true;

		for(int i=1;i<=7;i++)
		{
			Position positionNE=new Position(this.position.getxPos()+i,this.position.getyPos()-i);
			Position positionSE=new Position(this.position.getxPos()+i,this.position.getyPos()+i);
			Position positionNW=new Position(this.position.getxPos()-i,this.position.getyPos()-i);
			Position positionSW=new Position(this.position.getxPos()-i,this.position.getyPos()+i);

			if(NE)
			{
				if (!Position.isValid(positionNE))
					NE=false;
				else
				{	
					int positionStatus=board.isOccupied(positionNE);
					if (positionStatus==0)
						allowableMoves.add(positionNE);
					else if (positionStatus!=board.getTurn())
					{
							allowableMoves.add(positionNE);
							NE=false;
					}
					else if (positionStatus==board.getTurn())
						NE=false;
				}
			}
			if(SE)
			{
				if (!Position.isValid(positionSE))
					SE=false;
				else
				{	
					int positionStatus=board.isOccupied(positionSE);
					if (positionStatus==0)
						allowableMoves.add(positionSE);
					else if (positionStatus!=board.getTurn())
					{
							allowableMoves.add(positionSE);
							SE=false;
					}
					else if (positionStatus==board.getTurn())
						SE=false;
				}
			}
			if(NW )
			{
				if (!Position.isValid(positionNW))
					NW=false;
				else
				{	
					int positionStatus=board.isOccupied(positionNW);
					if (positionStatus==0)
						allowableMoves.add(positionNW);
					else if (positionStatus!=board.getTurn())
					{
							allowableMoves.add(positionNW);
							NW=false;
					}
					else if (positionStatus==board.getTurn())
						NW=false;
				}
			}
			if(SW)
			{

				if (!Position.isValid(positionSW))
					SW=false;
				else
				{	
					int positionStatus=board.isOccupied(positionSW);
					if (positionStatus==0)
						allowableMoves.add(positionSW);
					else if (positionStatus!=board.getTurn())
					{
							allowableMoves.add(positionSW);
							SW=false;
					}
					else if (positionStatus==board.getTurn())
						SW=false;
				}
			}
		}

		return allowableMoves;
	}
	
	public Bishop(Piece piece) {
		this.position=piece.position;
		this.color=piece.color;
		this.type=piece.type;
	}
	public Bishop(Position position,int color) {
		this.position=position;		
		this.color=color;
		this.type="B";
	}
	
	public static List<Bishop>  defaultBishop(int color)
	{
		List<Bishop> myBishops=new ArrayList<Bishop>();
		int y;
		if (color == 1)
			y=0;
		else
			y=7;

			myBishops.add(new Bishop(new Position(2,y),color));
			myBishops.add(new Bishop(new Position(5,y),color));
			
		return myBishops;
	}
	
	@Override
	public String toString() {
		return super.toString()+"B";
	}
}
