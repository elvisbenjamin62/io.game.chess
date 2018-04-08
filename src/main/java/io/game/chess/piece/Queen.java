package io.game.chess.piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.game.chess.Board;
import io.game.chess.Position;

public class Queen extends Piece implements Serializable {

	@Override
	public List<Position> getAllowableMove(Board board) {
		List<Position> allowableMoves= new ArrayList<Position>();
		Set<Piece> myTotalPieces=new HashSet<Piece>(); 
		myTotalPieces.addAll(board.getMyBlackPieces());
		myTotalPieces.addAll(board.getMyWhitePieces());

		
		// Start of Rook Moves---------------------------

		
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
				
				// End of Rook Moves---------------------------

				
				// Start of Bishop Moves---------------------------


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
				// End of Bishop Moves---------------------------
				
			
			return allowableMoves;
	}

	public Queen(Position position,int color) {
		this.position=position;		
		this.color=color;
		this.type="Q";

	}
	
	public static List<Queen>  defaultQueen(int color)
	{
		List<Queen> myQueens=new ArrayList<Queen>();

		if (color == 1)
			myQueens.add(new Queen(new Position(4,0),color));
		else
			myQueens.add(new Queen(new Position(4,7),color));

		return myQueens;
	}
	
	@Override
	public String toString() {
		return super.toString()+"Q";
	}
	
	public Queen(Piece piece) {
		this.position=piece.position;
		this.color=piece.color;
		this.type=piece.type;
	}
	
	
}
