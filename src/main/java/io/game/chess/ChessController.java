package io.game.chess;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChessController {

	@RequestMapping(method = RequestMethod.GET,value="/getBoard")
	public Board getBoard() {
		Board myBoard=new Board();
		return myBoard;	
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/getMove")
	public Move validateBoard(@RequestBody Board body) {
		return Move.getOptimalMove(body);
	}
}
