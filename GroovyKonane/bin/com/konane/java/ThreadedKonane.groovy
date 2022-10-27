trait ThreadedKonane{
    //need only be implemented for threaded variation
    void threadKonane(int i, int alpha, int beta, int depth, int levelColor) {
		int opColor = levelColor%2;
		opColor++;
		int currentmove [] = new int[4];
		int val = MIN;
		int temp = MIN;
		this.bval = MIN;
		
		for (int j = calibrate(i, levelColor); j < 8; j = j + 2)
		{
			if (this.objBoard[i][j] > 0)
			{
				for (int Dir = 0; Dir < 4; Dir++) {
					if (legal_move(currentmove, i, j, Dir)) {
						/*****MOVE PIECE*****/
						makeMove(currentmove, levelColor);
						//calls for minimization using the opposing color
						//needs some work.

						//calling serial variation
						val = Math.max(val, ABMin(alpha, beta, 1, depth, opColor));


						unmakeMove(currentmove, levelColor, opColor);

	                    /**AT ROOT OF RECURSIVE TREE, IS CURRENT MOVE BEST?**/
						/**IF CURRENT MOVE BEST, CHANGE MOVE**/
	                    //This normally happens at depth zero
	                    //This IS "depth zero" as far as the code
	                    //is concerned, hence this can happen here.
	                    //could remove from other code.
						alpha = Math.max(alpha, val);
	                        if (temp < val) {
	                            this.bval=val;
	                            //the best move is the highest scoring move selected
	                            this.bestmove[0] = currentmove[0];
	                            this.bestmove[1] = currentmove[1];
	                            this.bestmove[2] = currentmove[2];
	                            this.bestmove[3] = currentmove[3];
	                        }
					}
				}
			}
		}
	return;
	}

    //below used in threaded only shold be relocated later
	Board comparison(Board board1, Board board2, Board board3, Board board4, Board board5, Board board6, Board board7, Board board8) {
	    if (board1.bval<board2.bval)
	    {
	        //System.out.println(board1.bval);
	       board1=board2;
	    }
	    if (board1.bval<board3.bval)
	    {
	        //System.out.println(board1.bval);
	       board1=board3;
	    }
	    if (board1.bval<board4.bval)
	    {
	       //System.out.println(board1.bval);
	       board1=board4;
	    }
	    if (board1.bval<board5.bval)
	    {
	        //System.out.println(board1.bval);
	       board1=board5;
	    }
	    if (board1.bval<board6.bval)
	    {
	       //System.out.println(board1.bval);
	       board1=board6;
	    }
	    if (board1.bval<board7.bval)
	    {   //System.out.println(board1.bval);
	       board1=board7;
	    }
	    if (board1.bval<board8.bval)
	    {
	        //System.out.println(board1.bval);
	       board1=board8;
	     
	    }
		return board1;
	}
}