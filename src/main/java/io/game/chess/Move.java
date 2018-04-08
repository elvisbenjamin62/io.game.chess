package io.game.chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.game.chess.piece.*;

public class Move {
	private Position from;
	private Position to;
	public Move() {
	}
	
	public Move(Position from,Position to) {
		this.from=from;
		this.to=to;
	}
	
	public Position getFrom() {
		return from;
	}


	public void setFrom(Position from) {
		this.from = from;
	}


	public Position getTo() {
		return to;
	}


	public void setTo(Position to) {
		this.to = to;
	}


	public static Board makeMove(Board board,Piece from,Position to)
	{
		Board myTempBoard=Board.clone(board);
		Piece myPieceToMove=myTempBoard.getPieceAt(from.getPosition());

		//Validate destination position and remove destination if existing
		if(myTempBoard.isOccupied(to)!=0)	
		{
			if(myTempBoard.getTurn()==Board.WHITE)
			{
				myTempBoard.getMyBlackPieces().remove(myTempBoard.getPieceAt(to));
			}
			else if(myTempBoard.getTurn()==Board.BLACK) 
			{
				myTempBoard.getMyWhitePieces().remove(myTempBoard.getPieceAt(to));
			}
		}
		myPieceToMove.setPosition(new Position(to.getxPos(),to.getyPos()));
		myTempBoard.setTurn(board.getTurn()==Board.WHITE?Board.BLACK:Board.WHITE);
		return myTempBoard;
	}
	
	public static Move getOptimalMove(Board board)
	{
		Set <Piece> piecesThatCanMove;
		List<Position> myPossiblePosition=new ArrayList<Position>();
		Move myMove=null;
		int value;
		
		if (board.getTurn()==Board.WHITE)
		{
			value = -999999;
			piecesThatCanMove=board.getMyWhitePieces();
		}
		else
		{
			value = 999999;
			piecesThatCanMove=board.getMyBlackPieces();
		}
		
				
		for(Piece myTempPiece: piecesThatCanMove)
		{
			myPossiblePosition.addAll(myTempPiece.getAllowableMove(board));
			for(Position target:myPossiblePosition)
			{
				int tempValue;
				Board tempBoard= Move.makeMove(board,myTempPiece,target);
				tempValue=calculateAlphaBeta(tempBoard, 1, -9999999,999999);
				if(board.getTurn()==Board.WHITE && tempValue>=value) {
					myMove=new Move(myTempPiece.getPosition(),target);//set move
				}
				else if (board.getTurn()==Board.BLACK && tempValue<=value)
				{
					myMove=new Move(myTempPiece.getPosition(),target);//set move				
				}
			}
		}

		return myMove;
		
	}
	
	public static int calculateAlphaBeta(Board board,int depth,int alpha,int beta) {
		
		int v;
		if(depth==0)
			return board.calculateValue();
		if(board.turn==Board.WHITE)
		{
			v=-10000000;
			for(Piece myTempPiece: board.getMyWhitePieces())
			{
				List<Position> myPossiblePosition=new ArrayList<Position>();
				myPossiblePosition.addAll(myTempPiece.getAllowableMove(board));
				for(Position target:myPossiblePosition)
				{
					Board tempBoard= Move.makeMove(board,myTempPiece,target);
					v= Math.max(v, calculateAlphaBeta(tempBoard, (depth-1), alpha, beta));
					alpha=Math.max(alpha,v);
					if (beta<=alpha)
						break;
				}
				
			}
			return v;
			
		}
		else
		{
			v=10000000;
			
			for(Piece myTempPiece: board.getMyBlackPieces())
			{
				
				List<Position> myPossiblePosition=new ArrayList<Position>();
				myPossiblePosition.addAll(myTempPiece.getAllowableMove(board));
				
				for(Position target:myPossiblePosition)
				{
					
					
					
					Board tempBoard= Move.makeMove(board,myTempPiece,target);
					//System.out.println(tempBoard);

					v= Math.min(v, calculateAlphaBeta(tempBoard, (depth-1), alpha, beta));
					beta=Math.min(beta,v);
					if (beta<=alpha)
						break;
				}
				
			}
			return v;		
		}		
	}
	
}
