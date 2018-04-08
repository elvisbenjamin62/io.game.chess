package io.game.chess;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import io.game.chess.piece.*;



public class Board implements Serializable {
	
	public static final int WHITE=1;
	public static final int BLACK=2;


	
	private Set <Piece> whitePieces=new HashSet<Piece>();
	private Set <Piece> blackPieces=new HashSet<Piece>();
	int turn;
	
	public  enum Player {WHITE,BLACK };
	
	public Board() {
		blackPieces.addAll(Bishop.defaultBishop(BLACK));
		blackPieces.addAll(King.defaultKing(BLACK));
		blackPieces.addAll(Queen.defaultQueen(BLACK));
		blackPieces.addAll(Knight.defaultKnight(BLACK));
		blackPieces.addAll(Rook.defaultRook(BLACK));
		blackPieces.addAll(Pawn.defaultPawn(BLACK));

		whitePieces.addAll(Bishop.defaultBishop(WHITE));
		whitePieces.addAll(King.defaultKing(WHITE));
		whitePieces.addAll(Queen.defaultQueen(WHITE));
		whitePieces.addAll(Knight.defaultKnight(WHITE));
		whitePieces.addAll(Rook.defaultRook(WHITE));
		whitePieces.addAll(Pawn.defaultPawn(WHITE));

	}

	public String toString() {
		Set<Piece> myTotalBoard =new HashSet<Piece>();
		if (whitePieces!=null && whitePieces.size()>0)
			myTotalBoard.addAll(whitePieces);
		if (blackPieces!=null && blackPieces.size()>0)
			myTotalBoard.addAll(blackPieces);
		System.out.println( "=======================");
		System.out.println( "Turn : " + this.turn);


		System.out.print( "\t");
		for(int i=0;i<8;i++)
			System.out.print( i+ "\t");
		System.out.println();

		for(int i=0;i<8;i++)
		{
			System.out.print( i + "\t");

			for(int j=0;j<8;j++)
			{
				Position myTempPosition=new Position(j,i);
				
				Piece myPiece=myTotalBoard.stream().filter(t -> t.getPosition().equals(myTempPosition)).findFirst().orElse(null);
				if (myPiece!= null)
					System.out.print(myPiece + "\t");
				else
					System.out.print( "x\t");
					
			}
			System.out.println( );
		}
		
		return "=======================";
	}
	


	public Set<Piece> getMyBlackPieces() {
		return whitePieces;
	}

	public void setMyBlackPieces(Set<Piece> myBlackPieces) {
		Set<Piece> myTempPieces=new HashSet<Piece>();
		for(Piece piece:myBlackPieces)
		{
			switch(piece.getType())
			{
				case "Kg":
					myTempPieces.add(new King(piece));
					break;
				case "Kt":
					myTempPieces.add(new Knight(piece));
					break;
				case "B":
					myTempPieces.add(new Bishop(piece));
					break;
				case "P":
					myTempPieces.add(new Pawn(piece));
					break;
				case "Q":
					myTempPieces.add(new Queen(piece));
					break;
				case "R":
					myTempPieces.add(new Rook(piece));
					break;
			}
		}

		this.whitePieces = myTempPieces;
		
	}

	public Set<Piece> getMyWhitePieces() {
		
		
		return blackPieces;
	}

	public void setMyWhitePieces(Set<Piece> myWhitePieces) {
		
		Set<Piece> myTempPieces=new HashSet<Piece>();
		for(Piece piece:myWhitePieces)
		{
			switch(piece.getType())
			{
				case "Kg":
					myTempPieces.add(new King(piece));
					break;
				case "Kt":
					myTempPieces.add(new Knight(piece));
					break;
				case "B":
					myTempPieces.add(new Bishop(piece));
					break;
				case "P":
					myTempPieces.add(new Pawn(piece));
					break;
				case "Q":
					myTempPieces.add(new Queen(piece));
					break;
				case "R":
					myTempPieces.add(new Rook(piece));
					break;
			}
		}
			
		
		this.blackPieces = myTempPieces;
		
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void getNextMove() {
		
	}
	
	public Piece getPieceAt(Position position)
	{
		
		Set<Piece> myTotalPieces=new HashSet<Piece>(); 
		myTotalPieces.addAll(this.getMyBlackPieces());
		myTotalPieces.addAll(this.getMyWhitePieces());		
		
		Piece tempPiece=myTotalPieces.stream().filter(t -> t.getPosition().equals(position)).findFirst().orElse(null);
		
		return tempPiece;
		
	}
	
	public int isOccupied(Position position)
	{
		
		Set<Piece> myTotalPieces=new HashSet<Piece>(); 
		myTotalPieces.addAll(this.getMyBlackPieces());
		myTotalPieces.addAll(this.getMyWhitePieces());		
		
		Piece tempPiece=myTotalPieces.stream().filter(t -> t.getPosition().equals(position)).findFirst().orElse(null);
		if (tempPiece==null)
			return 0;
		else
			return tempPiece.getColor();
		
	}
	
	public static Board clone(Board board)
	{
		  Object obj = null;
	        try {
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();
	            ObjectOutputStream out = new ObjectOutputStream(bos);
	            out.writeObject(board);
	            out.flush();
	            out.close();

	            ObjectInputStream in = new ObjectInputStream(
	                new ByteArrayInputStream(bos.toByteArray()));
	            obj = in.readObject();
	        }
	        catch(IOException e) {
	            e.printStackTrace();
	        }
	        catch(ClassNotFoundException cnfe) {
	            cnfe.printStackTrace();
	        }
	        return (Board)obj;		
	}
	
	public int calculateValue() {
		int value=0;
		Set<Piece> myTotalPieces=new HashSet<Piece>(); 
		myTotalPieces.addAll(this.getMyBlackPieces());
		myTotalPieces.addAll(this.getMyWhitePieces());
		
		
		for(Piece tempPiece:myTotalPieces)
		{
			int factor=(tempPiece.getColor()==WHITE?1:-1);
			switch(tempPiece.getType())
			{
				case "Kg":
					value+= 900 * factor;
					break;
				case "Kt":
					value+= 30 * factor;
					break;
				case "B":
					value+= 30 * factor;
					break;
				case "P":
					value+= 10 * factor;
					break;
				case "Q":
					value+= 90 * factor;
					break;
				case "R":
					value+= 50 * factor;
					break;
			}
		}
		
		return value;
	}
}

