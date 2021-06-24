package com.konane.java;

import java.util.Scanner;

class Board extends Constants{
	Scanner read = new Scanner(System.in);
	int bval;
	boolean zpgame;
	int playerColor;
	int [][] objBoard = {
			{B,W,B,W,B,W,B,W},
			{W,B,W,B,W,B,W,B},
			{B,W,B,W,B,W,B,W},
			{W,B,W,B,W,B,W,B},
			{B,W,B,W,B,W,B,W},
			{W,B,W,B,W,B,W,B},
			{B,W,B,W,B,W,B,W},
			{W,B,W,B,W,B,W,B}
			};
	int bestmove[]= new int[4];
	//takes move and player color, calibrates to compensate
	//for checkerboard pattern
	
	
	int calibrate(int i, int playerColor) {
		if(playerColor==2) {
			return i%2;
			}
		return ((i+1)%2);
		}
	
	void threadKonane(int i, int alpha, int beta, int levelColor) {
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
						val = Math.max(val, ABMin(alpha, beta, 1, opColor));


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

	
	
	
	
	boolean legal_move(int currentmove[], int startRow, int startColumn, int Dir){
	    boolean state;
	    int destRow = startRow;
		int destColumn = startColumn;
		if (Dir == 0) //N moving "up"
		{
			if (destRow > 1) {
				//moving "up"
	            destRow--;
	            state = isFull(destRow, destColumn);
	            if (state == true) {
					destRow--;
					state = isFull(destRow, destColumn);
				if (state == false) {
	                currentmove[0] = startRow;
	                currentmove[1] = startColumn;
	                currentmove[2] = destRow;
	                currentmove[3] = destColumn;
	                    if(destRow>1){
	                        destRow--;
	                        state = isFull(destRow, destColumn);
	                        if (state == true) {
	                            destRow--;
	                            state = isFull(destRow, destColumn);
	                            if (state == false) {
	                                currentmove[2] = destRow;
	                                currentmove[3] = destColumn;
	                            }

						}
	                    }
	                    //returns true regardless of number of jumps, not sure about array rules in Java
						return true;
	                }
				}
			}
		}
		if (Dir == 1) //S
		{
	        if (destRow < 6) {
	            destRow++;
	            state = isFull(destRow, destColumn);
	            if (state == true) {
					destRow++;
					state = isFull(destRow, destColumn);
				if (state == false) {
	                currentmove[0] = startRow;
	                currentmove[1] = startColumn;
	                currentmove[2] = destRow;
	                currentmove[3] = destColumn;
	                    if(destRow<6){
	                        destRow++;
	                        state = isFull(destRow, destColumn);
	                        if (state == true) {
	                            destRow++;
	                            state = isFull(destRow, destColumn);
	                            if (state == false) {
	                                currentmove[2] = destRow;
	                                currentmove[3] = destColumn;
	                            }

						}
	                    }
						return true;
	                }
				}
			}
		}
		if (Dir == 2)  //W
		{
			if (destColumn > 1) {
	            destColumn--;
	            state = isFull(destRow, destColumn);
	            if (state == true) {
					destColumn--;
					state = isFull(destRow, destColumn);
				if (state == false) {
	                currentmove[0] = startRow;
	                currentmove[1] = startColumn;
	                currentmove[2] = destRow;
	                currentmove[3] = destColumn;
	                    if(destColumn>1){
	                        destColumn--;
	                        state = isFull(destRow, destColumn);
	                        if (state == true) {
	                            destColumn--;
	                            state = isFull(destRow, destColumn);
	                            if (state == false) {
	                                currentmove[2] = destRow;
	                                currentmove[3] = destColumn;
	                            }

						}
	                    }
						return true;
	                }
				}
			}
		}

		if (Dir == 3) //E
		{
			if (destColumn < 6) {
	            destColumn++;
	            state = isFull(destRow, destColumn);
	            if (state == true) {
					destColumn++;
					state = isFull(destRow, destColumn);
				if (state == false) {
	                currentmove[0] = startRow;
	                currentmove[1] = startColumn;
	                currentmove[2] = destRow;
	                currentmove[3] = destColumn;
	                    if(destColumn<6){
	                        destColumn++;
	                        state = isFull(destRow, destColumn);
	                        if (state == true) {
	                            destColumn++;
	                            state = isFull(destRow, destColumn);
	                            if (state == false) {
	                                currentmove[2] = destRow;
	                                currentmove[3] = destColumn;
	                            }

						}
	                    }
						return true;
	                }
				}
			}
		}
		//if no move at all
		return false;
	}
	
	boolean isFull(int i, int j) {

		if (this.objBoard[i][j] == 0){
			return false;
		}
			return true;
	}

	void makeMove(int currentmove[], int playerColor)
	{   
	    int startRow,startColumn,destRow,destColumn;
	    boolean neq;
		startRow =    currentmove[0];
		startColumn = currentmove[1];
		destRow =     currentmove[2];
		destColumn =  currentmove[3];
		
		neq = true;
	    do {
	    this.objBoard[startRow][startColumn] = 0;

	    if (destRow < startRow)
	    {
	        this.objBoard[startRow - 1][startColumn] = 0;
	        this.objBoard[startRow - 2][startColumn] = playerColor;
	        startRow=startRow-2;
	    }
	    if (destRow > startRow)
	    {
	        this.objBoard[startRow + 1][startColumn] = 0;
	        this.objBoard[startRow + 2][startColumn] = playerColor;
	        startRow=startRow+2;
	    }
	    if (destColumn < startColumn)
	    {
	        this.objBoard[startRow][startColumn - 1] = 0;
	        this.objBoard[startRow][startColumn - 2] = playerColor;
	        startColumn=startColumn-2;
	    }
	    if (destColumn > startColumn)
	    {
	        this.objBoard[startRow][startColumn + 1] = 0;
	        this.objBoard[startRow][startColumn + 2] = playerColor;
	        startColumn=startColumn+2;
	    }
	    if((startRow==destRow)&&(destColumn==startColumn)){neq=false;}
	    } while (neq);
	    return;
	}
	
	void unmakeMove(int currentmove[],int playerColor, int opColor) {
	    int startRow,startColumn,destRow,destColumn;
	    boolean neq=true;
		startRow =    currentmove[0];
		startColumn = currentmove[1];
		destRow =     currentmove[2];
		destColumn =  currentmove[3];

		while (neq){
			this.objBoard[destRow][destColumn] = 0;
			if (destRow > startRow)
			{
				this.objBoard[destRow - 1][destColumn] = opColor;
				this.objBoard[destRow - 2][destColumn] = playerColor;
	            destRow=destRow-2;
	            if(destRow==startRow)
	            {
	                break;
	            }
			}
			if (destRow < startRow)
			{
				this.objBoard[destRow + 1][destColumn] = opColor;
				this.objBoard[destRow + 2][destColumn] = playerColor;
				destRow=destRow+2;
	            if(destRow==startRow)
	            {
	            break;
	            }
			}
			if (destColumn > startColumn)
			{
				this.objBoard[destRow][destColumn-1] = opColor;
				this.objBoard[destRow][destColumn-2] = playerColor;
				destColumn=destColumn-2;
	            if(destColumn==startColumn)
	            {
	            break;
	            }
			}
			if (destColumn < startColumn)
			{
				this.objBoard[destRow][destColumn+1] = opColor;
				this.objBoard[destRow][destColumn+2] = playerColor;
				destColumn=destColumn+2;
	            if(destColumn==startColumn)
	            {
	            break;
	            }
			}
	}
	}
	
	int ABMin(int alpha, int beta, int level, int levelColor)
	{
	    int val = MAX;
	    int opColor = levelColor%2;
	    opColor++;
	    /**RESOLVE MATH AFTER SUCCESSFUL TEST**/
	    int currentmove [] = new int[4];

			for (int i = 0; i < 8; i++)
			{
				for (int j = calibrate(i, levelColor); j < 8; j = j + 2)
				{
					if (this.objBoard[i][j] > 0)
					{
						for (int Dir = 0; Dir < 4; Dir++) {
							if (legal_move(currentmove, i, j, Dir)) {
								makeMove(currentmove, levelColor);
								val = Math.min(val, ABMax(alpha, beta, level+1, opColor));
								beta = Math.min(beta, val);
								unmakeMove(currentmove, levelColor, opColor);
								// Alpha Beta Pruning
								if (beta <= alpha) {
								
									return beta;
								}
							}
						}
					}
				}
			}
		return val;
	}
	
	int ABMax(int alpha, int beta, int level, int levelColor)
	{
	int tshoot, temp;
		/* Initial values of
		 * Alpha and Beta
		 * Terminating condition. i.e
		 *leaf node, is reached*/
	    int opColor = levelColor%2;
	    opColor++;
	    /**RESOLVE MATH AFTER SUCCESSFUL TEST**/
	    int currentmove [] = new int[4];

		if (level == depth) {
				tshoot = SEF(levelColor);
				return tshoot;
		}
			int val = MIN;

			for (int i = 0; i < 8; i++)
			{
				for (int j = calibrate(i, levelColor); j < 8; j = j + 2)
				{
					if (this.objBoard[i][j] > 0)
					{
						for (int Dir = 0; Dir < 4; Dir++) {
							if (legal_move(currentmove, i, j, Dir)) {
								/*****MOVE PIECE*****/
								makeMove(currentmove, levelColor);
								//calls for minimization using the opposing color
								temp = val;
								val = Math.max(val, ABMin(alpha, beta, level+1, opColor));
								unmakeMove(currentmove, levelColor, opColor);
								/**AT ROOT OF RECURSIVE TREE, IS CURRENT MOVE BEST?**/
								/**IF CURRENT MOVE BEST, CHANGE MOVE**/
								//Below is for serial variant
								if (level == 0 && temp < val) {
									bestmove[0] = currentmove[0];
									bestmove[1] = currentmove[1];
									bestmove[2] = currentmove[2];
									bestmove[3] = currentmove[3];
								}
								alpha = Math.max(alpha, val);
								//Alpha cutoff
								if (beta <= alpha) {
									return alpha;
								}
							}
						}

					}
				}
			}
			/**Program can predict loss, and will end properly if loss.**/
			if ((level == 0)&&(val == MIN))
			{
				this.bval=LOSE;
				return LOSE;
			}

	    return val;
	}
	
	int SEF(int playerColor) {
		int sumMoves=0;    //sum of total moves for SEF player
		int pb=0;     //Player pieces versus opponent pieces, ("piece balance")

		int opColor = playerColor%2;
		opColor++;
		for (int i = 0; i < 8; i++)
		{
			for (int j = calibrate(i, playerColor); j < 8; j = j + 2)
			{
				if (objBoard[i][j] > 0)
				{
					//pieces AI
				    pb++;
					for (int Dir = 0; Dir < 4; Dir++) {
						if (legal_SEF(i, j, Dir)) {
							//possible moves AI
							sumMoves++;
						}
					}

				}

			}
		}
		for (int i = 0; i < 8; i++)
		{
			for (int j = calibrate(i, opColor); j < 8; j = j + 2)
			{
				if (objBoard[i][j] > 0)
				{
					//pieces player
				    pb--;
					for (int Dir = 0; Dir < 4; Dir++) {
						if (legal_SEF(i, j, Dir)) {
							//AI moves minus possible moves player
							sumMoves--;
						}
					}

				}

			}
		}
		
	    return (sumMoves+pb); //all the moves possible for AI less the total moves possible by the player and the piece balance
	}

	
	boolean legal_SEF(int startRow, int startColumn, int Dir)
	{
		//System.out.print(" ");
	    int N = 0;
	    int S = 1;
	    int W = 2;
	    int E = 3;

		int destRow = startRow;

		int destColumn = startColumn;

		boolean state;

		if (Dir == N) //N
		{
			if (startRow > 1) {
				destRow--;
				state = isFull(destRow, destColumn);
				if (state == true) {
					destRow--;
					state = isFull(destRow, destColumn);
					if (state == false) {
						return true;
					}
				}

			}
		}
		if (Dir == S)
		{
			if (startRow < 6) {
				{
					destRow++;
					state = isFull(destRow, destColumn);
					if (state == true) {
						destRow++;
						state = isFull(destRow, destColumn);
						if (state == false) {
							return true;
						}
					}
					}

			}
		}
		if (Dir == W)
		{
			if (startColumn > 1) {
				destColumn--;
				state = isFull(destRow, destColumn);
				if (state == true) {
					destColumn--;
					state = isFull(destRow, destColumn);
					if (state == false) {
						return true;
					}
				}

			}
		}

		if (Dir == E)
		{
			if(startColumn<6) {
			destColumn++;
			state = isFull(destRow, destColumn);
			if (state == true) {
				destColumn++;
				state = isFull(destRow, destColumn);
				if (state == false) {
					return true;
				}
			}

		}
		}
		return false;
	}


	
	
	//BOTH GAMES
	//Manual Override
	//display
	//set values
	//set color
	//guardRails
	//set zero player game
	//selection

	public boolean setItem(String set) {
		String ans;
		
		if (set == "zero") {
			System.out.println("Will I be playing Myself? \ny/n\n");
		    do{	
		        
		        ans= read.next();
		        if (ans.equalsIgnoreCase("y")){
		        	
		      		return true;
		        }
			    if(ans.equalsIgnoreCase("n")){
				   
				   return false;
		        	
		        }

		    }while (true);
		}
		if (set == "color") {
			System.out.println("\nWhat color should 'I' play as? \n 1 for white and 2 for black:\n(black goes first per Konane rules \n black is X, white is O)\n ");
		    do{	
		        
		        int numAns= read.nextInt();
		        if (numAns == 2){
		      		return true;
		        }
			    if(numAns == 1){
				   return false;
		        	
		        }

		    }while (true);
		}
		return false;

	}

	public void manualOverride() {
		String remadd;
		String ans ="n";
	    display();
	    
		System.out.println( "\n\nX= Black, O = White, Spaces for empty squares\n\n");
		System.out.println( "r = Removal of piece, b = add black, w = add white e = exit \n");
		remadd = read.nextLine();
		System.out.println(remadd);
		while (ans.equalsIgnoreCase("n")) {

			while (true) {
				if (remadd.equalsIgnoreCase("e")){
					break;
				}
				else if (remadd.equalsIgnoreCase("r"))
				{
					System.out.println( "What pieces will be removed? choose row and column for each.  (NO adjacency test)\n");
					selection(0);
				}
				else if (remadd.equalsIgnoreCase("b"))
				{
					System.out.println( "What black pieces will be added? choose row and column for each.  (NO adjacency test)\n");
					selection(B);
				}
				else if (remadd.equalsIgnoreCase("w")) {
					System.out.println( "What white pieces will be added? choose row and column for each.  (NO adjacency test)\n");
					selection(W);
				}
				else{
					System.out.println("\n invalid choice, restarting selection\n");
	                display();
	                System.out.println( "\n\nX= Black, O = White, Spaces for empty squares\n\n");
	                System.out.println( "r = Removal of piece, b = add black, w = add white e = exit \n");
	                remadd = read.nextLine();
				}
	        display();
	        System.out.println( "r = Removal of piece, b = add black, w = add white e = exit \n");
	        remadd = read.nextLine();
			}
	    display();
	    System.out.println( "\n is board correct? any key for yes, n for no \n \n");
		 ans= read.nextLine();
		}
		
	}
	
	void selection(int S) {
		int startRow, startColumn;

		System.out.println( "\n column (A-H = 1-8): ");
		startColumn= read.nextInt();
		System.out.println( "\n row (1-8): ");
		startRow= read.nextInt();
		//accommodate array indicies.
		startRow--;
		startColumn--;
		if (startRow > 7 || startRow < 0 || startColumn>7 || startColumn < 0) {
			System.out.println( "\n invalid choice, restarting selection");
		}

		else {
			objBoard[startRow][startColumn] = S;
		}
		
		return;
	}
	void display() {
		int row = 0;
		System.out.println( "   A B C D E F G H ");
	    System.out.println( "   1 2 3 4 5 6 7 8 ");

		System.out.print( "\n");
		for (int i = 0; i < 8; i++) {
	        System.out.print( ++row +"  ");

			for (int j = 0; j < 8; j++) {


				if (objBoard[i][j] > 1) {
					System.out.print( "X ");
				}
				else if (objBoard[i][j] > 0) {
					System.out.print( "O ");
				}
				else {
					System.out.print( "  ");
				}
			}
			System.out.print( "\n");
		}
	}
	
	
	void displayMove(){
		System.out.println((bestmove[0]+1)+", "+(bestmove[1]+1)+", to "+ (bestmove[2]+1)+", "+(bestmove[3]+1)+", \n 'your' move");
	}
	
	
	boolean guardRails(){
	    int i,j,k,m;
	    i=bestmove[0];
	    j=bestmove[1];
	    k=bestmove[2];
	    m=bestmove[3];
	    //check for horizontal or vertical movement by checking if one value doesn't change
	    if(i==k||j==m){
	        //check for even number of squares traversed, if mod 2 = zero, then move is probably legal, 
	    	//player can still break game w illegal move, but player can flip the board in monopoly as well soooo....
	        i=(Math.abs(i-k))%2;
	        j=(Math.abs(j-m))%2;

	        if (i==0&&j==0)
	        {
	            return true;
	        }

	    }
	   System.out.println("incorrect move");
	    return false;
	}
	
	
}