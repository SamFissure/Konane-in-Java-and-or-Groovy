package com.konane.java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//System.nanoTime() (in spite of issues)
//Use something else to monitor time taken by entire game, nano is bad at long time expanses
public class Konane extends ThreadedKonane{
	
	/*Order of Game Play*/
	/*Game is lost when there are no longer any valid moves.*/
			/* 1. determine who plays Black and is thus first.
			 * 2. choose to remove 2 pieces if not first (another variant has the removal of one piece by each player).
			 *
			 *     **GAME LOOP**    *
			 * 3. wait for input or make input
			 * 4. upon AI move:

			 * run legalmove on each position in array when is turn (and board is accurate.)
			 * N,S,E,W determines legalmove direction.
			 *
			 * the following bounds due to no move possible:
			 * if i<2 (row) don't run N
			 * if i>5 don't run S,
			 *
			 * if j<2 (column) don't run W,
			 * if j>5 don't run E.
			 *
			 * so the first square in a column doesn't evaluate the Northern most move and the last square will not
			 * evaluate the Southernmost... etc
			 * ******************* RECURSIVE CALLS  ***************************
			 * 5. if legalmove returns true, form node (recursive call with new board) with move and repeat process board to obtain number.
			 * it is a DFS.
			 * 6. the calls eventually reach depth d and the SEF is run.  It is notable that the alpha and beta cutoffs will reduce the number of boards evaluated
			 * WITHOUT any data loss.
			 * (this is a less intuitive action of the recursive function, no data is lost, but branches that the AI will NEVER select are not explored further.
			 * essentially, a node which comes out too high or too low relative to the node examined will not be selected)
			 *
			 **************	State Evaluation function (SEF)*********************
			 *
			 * 7. The SEF evaluates partly by counting legal moves for player in that state.
			 * Additionally, it counts pieces and estimates based on expected piece balance (each move must remove one piece, if more pieces are missing a
			 * double move has occurred.  The SEF assumes that this is bad for the player who had this happen to it.)
			 *
			 * The SEF value is passed up to the top of the tree with varied modifications, minimizing levels take the lowest value to pass, maximizing
			 * will take up the largest values
			 *
			 * Dir can = 0 for N (up), 1 for E(right), 2 for S (down), 3 for W (left)
			 */


	/**REMEMBER TO USE THE DIAGNOSTIC PRINTOUTS FOR FUTURE CHANGES**/


	/**FUNCTIONAL WITH 16 DEPTH, EASE OF TESTING AT 14 OR 12 DEPTH, GAMEPLAY IS DIFFERENT DEPENDING ON DEPTH**/

	/**color is AI, humanColor is opponent**/

	/***MUST HAVE THESE GLOBALS, COULD PERHAPS EDIT INTO BOARD?***/
	static int AIcolor;
	static int humanColor;
	/******************DUE TO FOLLOWING OFFICIAL RULES, COLOR HAS BEEN MERGED WITH FIRST**********************/
	static void write_csv(ArrayList<Long> input) throws IOException{
		FileWriter csv = null;
		try {
			csv = new FileWriter("parallel-moves-14-1.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			int turn = 0;
	    // Make a CSV file with move record for stat tracking
	    // Each column of data is represented by the pair <column name, column data>

	    csv.write("Turn, Time \n");
	    for(Long time : input){
	    	turn++;
	    	csv.write(turn + "," + time +"\n"); 
	    }
	    // Close the file
	    csv.close();
	    }



	public static void main(String[] args) throws IOException, InterruptedException{


		
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		/**TODO:: TOO MUCH IN MAIN!! REFACTOR!!**/
			@SuppressWarnings("unused")
			int level=0;
			int turn=0;
			int i,j,k,m;
			@SuppressWarnings("unused")
			int alpha, beta, state;
			long turnStart, turnTime;
			
			boolean AIturn, legalHumMove, first;
			AIturn=false;
			Thread th1 = new Thread();
			Thread th2 = new Thread();
			Thread th3 = new Thread();
			Thread th4 = new Thread();
			Thread th5 = new Thread();
			Thread th6 = new Thread();
			Thread th7 = new Thread();
			Thread th8 = new Thread();
			
			ThreadedKonane evalBoard = new ThreadedKonane(); 
			
			
			
			//for recording time
		    ArrayList<Long> times = new ArrayList<Long>();
		    

		    //who is first, whose turn is it, is it zero player,is the human player making a legal move?

			//objects
			/**odd numbered depths end on min nodes, even on max. This doesn't work. This is to check for that**/
			System.out.println("depth = " + depth +". (This should be an even number)\n");
			evalBoard.zpgame=evalBoard.setItem("zero");
			//evalBoard.zpgame=true;
			//Who is black, goes first?
			//AIturn=true;
			AIcolor=1;
			humanColor=2;
			first=evalBoard.setItem("color");
			if(first) {
				AIturn=true;
				AIcolor=2;
				humanColor=1;
			}
			
			/**SETS BOARD UP**/
		    evalBoard.manualOverride();
		    //Starts the clock.

		    long gameStart = System.nanoTime();
			while (true) {
		            //if all moves lose.

		        if(evalBoard.bval<-500){
		            System.out.println("Game over, White loses");
		            break;
		                }
				if (AIturn) {
		            /**This should be refactored into a function.
		            *This initializes every board object to the same state, starts the clock on the move and
		            *makes the **/
					
					



					turnStart = System.nanoTime();
					evalBoard.coordinate(2);
					
					evalBoard.comparison();
					evalBoard.cleanTurn();
		            //Initiates threads, calls them on the rows of the board
		            //Alpha and Beta NOT set to Min and Max at first call.  This has improved efficiency and reduced error.
		            //They ARE set to MAX and MIN later.
					//th1( &board::threadKonane, &board1, 0, 1,2,depth,AIcolor);

					//Joins threads to main program after program run (some efficiencies to realize here).
					
					//makes a comparison between the 8 boards and selects the ideal board from them
		           
					//ThreadedKonane
					
					//board = evalBoard.comparison(board1, board2, board3, board4, board5, board6, board7, board8);
		            
					//displays prior board
					evalBoard.display();
					//if no valid moves
		            if(evalBoard.bval<-500){
		                System.out.println("Game Over, Black Loses");
		                break;
		                }

		            //Uses the "bestmove" array to make the move chosen by algorithim
		            evalBoard.makeMove(evalBoard.bestmove, AIcolor);
		            //displays the move in start i, start j, destination i, destination j, format.
		            evalBoard.displayMove();
		            //displays the board
					evalBoard.display();

					//Passes turn
					AIturn = false;
					//increments turn counter
					turn++;
					//stops clock and counts value
					turnTime = (System.nanoTime()-turnStart);
		            //gives amount of time taken
		            System.out.println("\n"+turnTime+"\n");
		            //records time and turn to array
		            times.add(turnTime);
		            }
		            /** if PLAYING AGAINST ITSELF OR A SIMILAR AI W A DIFFERENT SEF**/
		            //same as above
		            if((evalBoard.zpgame) && (AIturn==false)){
		            	
		            	
						turnStart = System.nanoTime();
						evalBoard.coordinate(1);
						
						evalBoard.comparison();
						evalBoard.cleanTurn();
						//Initiates threads, calls them on the rows of the board
			            //Alpha and Beta NOT set to Min and Max at first call.  This has improved efficiency and reduced error.
			            //They ARE set to MAX and MIN later.
						//th1( &board::threadKonane, &board1, 0, 1,2,depth,AIcolor);

						//Joins threads to main program after program run (some efficiencies to realize here).
						
						//makes a comparison between the 8 boards and selects the ideal board from them
			            
						
						
						//board = evalBoard.comparison(board1, board2, board3, board4, board5, board6, board7, board8);
			            
						//displays prior board
		                evalBoard.display();
		                //if no valid moves
		                if(evalBoard.bval<-500){
		                System.out.println("Game over, White loses");
		                break;
		                }
		                evalBoard.makeMove(evalBoard.bestmove, humanColor);
		                evalBoard.displayMove();

		                evalBoard.display();


		                AIturn = true;
		                turn++;
		                //record timing.
		                turnTime = (System.nanoTime()-turnStart);
		                //System.out.println("\n"+turnTime+"\n");
		                times.add(turnTime);
		                }
		            /** if PLAYING A HUMAN**/
				if((! evalBoard.zpgame) && (AIturn==false)) {
		            do{
		                System.out.println("\nenter piece to move column, 1-8: ");
		                //source column
		                j=read.nextInt();
		                //Accommodate indexing
		                j--;
		                //enters player move into array
		                evalBoard.bestmove[1]=j;
		                System.out.println("\nenter piece to move row, 1-8: ");
		                i=read.nextInt();
		                i--;
		                //source row
		                evalBoard.bestmove[0]=i;
		                System.out.println("\nenter piece destination column, 1-8: ");
		                m =read.nextInt();
		                m--;
		                //destination column
		                evalBoard.bestmove[3]=m;
		                System.out.println("\nenter piece destination row, 1-8: ");
		                k =read.nextInt();
		                k--;
		                //destination row
		                evalBoard.bestmove[2]=k;
		                legalHumMove=evalBoard.guardRails();
		            }while(! legalHumMove);
		            //is this move legal?


					evalBoard.makeMove(evalBoard.bestmove,humanColor);
					evalBoard.displayMove();
					evalBoard.display();
					turn++;
					AIturn=true;
					}
		        System.out.println(  "\nassuming correct board and continue\n");
			}
			
			long gameEnd = (System.nanoTime()-gameStart);
			float totalSeconds = gameEnd/1000000000;
			System.out.println("finished in "+turn+" turns");
		    System.out.println("It took me " + totalSeconds + " seconds.\n");
		    for(int z=0;z<turn;z++){
		    	System.out.println("turn " +z+1+ " took "+times.get(z)+" nanoseconds \n");
		    	}
		    write_csv(times);
		}










}
