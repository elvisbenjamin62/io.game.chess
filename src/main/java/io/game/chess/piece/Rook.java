package io.game.chess.piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.game.chess.Board;
import io.game.chess.Position;

public class Rook extends Piece implements Serializable {

	@Override
	public List<Position> getAllowableMove(Board board) {
		List<Position> allowableMoves= new ArrayList<Position>();
		Set<Piece> myTotalPieces=new HashSet<Piece>(); 
		myTotalPieces.addAll(board.getMyBlackPieces());
		myTotalPieces.addAll(board.getMyWhitePieces());

		
		// Search of Available positions up
		for(int i=this.position.getyPos(); i>0;i--)
		{
			Position tempPosition=new Position(this.getPosition().getxPos(),i-1);
			Piece tempPiece=myTotalPieces.stream().filter(t -> t.getPosition().equals(tempPosition)).findFirst().orElse(null);
			if(tempPiece==null)
			{
				allowableMoves.add(tempPosition);
			}
			else if(tempPiece!=null && tempPiece.getColor()!=board.getTurn())
			{
				allowableMoves.add(tempPosition);
				break;

			}
			else if(tempPiece!=null && tempPiece.getColor()==board.getTurn())
			{
				break;
			}
		}
		
		// Search of Available positions down
		for(int i=this.position.getyPos(); i<7;i++)
		{
			Position tempPosition=new Position(this.getPosition().getxPos(),i+1);
			Piece tempPiece=myTotalPieces.stream().filter(t -> t.getPosition().equals(tempPosition)).findFirst().orElse(null);
			if(tempPiece==null)
			{
				allowableMoves.add(tempPosition);
			}
			else if(tempPiece!=null && tempPiece.getColor()!=board.getTurn())
			{
				allowableMoves.add(tempPosition);
				break;

			}
			else if(tempPiece!=null && tempPiece.getColor()==board.getTurn())
			{
				break;
			}
		}
		
		
		// Search of Available positions left
				for(int i=this.position.getxPos(); i>0;i--)
				{
					Position tempPosition=new Position(i-1,this.getPosition().getyPos());
					Piece tempPiece=myTotalPieces.stream().filter(t -> t.getPosition().equals(tempPosition)).findFirst().orElse(null);
					if(tempPiece==null)
					{
						allowableMoves.add(tempPosition);
					}
					else if(tempPiece!=null && tempPiece.getColor()!=board.getTurn())
					{
						allowableMoves.add(tempPosition);
						break;

					}
					else if(tempPiece!=null && tempPiece.getColor()==board.getTurn())
					{
						break;
					}
				}
				
				// Search of Available positions right
				for(int i=this.position.getxPos(); i<7;i++)
				{
					Position tempPosition=new Position(i+1,this.getPosition().getyPos());
					Piece tempPiece=myTotalPieces.stream().filter(t -> t.getPosition().equals(tempPosition)).findFirst().orElse(null);
					if(tempPiece==null)
					{
						allowableMoves.add(tempPosition);
					}
					else if(tempPiece!=null && tempPiece.getColor()!=board.getTurn())
					{
						allowableMoves.add(tempPosition);
						break;

					}
					else if(tempPiece!=null && tempPiece.getColor()==board.getTurn())
					{
						break;
					}
				}
		return allowableMoves;
	}

	public Rook(Position position,int color) {
		this.position=position;		
		this.color=color;
		this.type="R";
	}
	
	public static List<Rook>  defaultRook(int color)
	{
		List<Rook> myRooks=new ArrayList<Rook>();
		int y;
		if (color == 1)
			y=0;
		else
			y=7;

			myRooks.add(new Rook(new Position(0,y),color));
			myRooks.add(new Rook(new Position(7,y),color));
			
		return myRooks;
	}
	
	@Override
	public String toString() {
		return super.toString()+"R";
	}
	
	public Rook(Piece piece) {
		this.position=piece.position;
		this.color=piece.color;
		this.type=piece.type;
	}
	
}
