package com.konane.java;

import java.util.ArrayList;

public class ThreadedKonane extends Board implements Runnable{

	Board board;
	int index;
	
	ArrayList<Board> boardlist;
	ArrayList<Board> resultlist;
	public void cleanTurn() {
		this.resultlist.clear();
		this.boardlist.clear();
		this.index=0;
		this.board.bval=MIN-1;
	}
	public void coordinate(int color) {
		//ensures lowest bval possible in program
		this.board.playerColor=color;
		 
		//reset index for other functions and uses   

		for (int i=0; i <8; i++) {
			Board toAdd = this.board;
			this.boardlist.add(i,toAdd);	
		}
	}
	
	
	//synchronized method picks up a board...  
	//increments index. returns the board and the operation proceeds.

	
	
	
	public void comparison() {
		Board result = resultlist.get(0);
		for (int i=0;i<7;i++) {
			if(result.bval<this.resultlist.get(i+1).bval) {
				result=this.resultlist.get(i+1);
			}
		}
		this.board= result;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		Board selected;
		int id = this.index;
		synchronized(this) {
			selected = boardlist.get(id);
			this.index++;
		}
		selected.threadKonane(id, MIN, MAX, this.playerColor);
		synchronized(this) {
			resultlist.add(selected);
		}
		
	}
	

}
/**     for(int n = 0;n<8; n++) {
Thread th=new Thread();
th.start();
this.threadList.add(th);
}
this.prevBoard.bval=(MIN-1);
for(int n=0;n<8;n++) {
//stores the board
this.prevBoard=this.board;

//changes the board
this.threadList.get(n).run();

//compares the board...
this.board=comparison(this.prevBoard,this.board);
}
for(int n =0 ;n<8;n++) {
this.threadList.get(n).join();
}*/