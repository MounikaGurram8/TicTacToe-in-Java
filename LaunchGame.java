package TicTacToe32;
import java.util.*;
class TicTacToe {
	static char[][] board;
	public TicTacToe() {
		board=new char[3][3];
		initBoard();
	}
	void initBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]= ' ';
			}
		}
	}
	static void dispBoard() {
		System.out.println("-------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j] +  " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	static void placeMark(int row, int col, char mark) {
		if(row>=0 && row<=2 && col>=0 && row<=2) {
			board[row][col]=mark;
		}
		else {
			System.out.println("Invalid Input");
		}
	}
	static boolean checkColWin(char mark) {
		for(int j=0;j<=2;j++) {
			if(board[0][j]==mark && board[1][j]==mark && board[2][j]==mark) {
				return true;
			}
		}
		return false;
	}
	static boolean checkRowWin(char mark) {
		for(int i=0;i<=2;i++) {
			if(board[i][0]==mark && board[i][1]==mark && board[i][2]==mark) {
				return true;
			}
		}
		return false;
	}
	static boolean checkDiagWin(char mark) {
		for(int i=0;i<=2;i++) {
			if((board[0][0]==mark && board[1][1]==mark && board[2][2]== mark) || (board[0][2]==mark && board[1][1]==mark && board[2][0]==mark) ) {
				return true;
			}
		}
		return false;
	}
	static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
}
abstract class Player {
	String name;
	char mark;
	abstract void makeMove();
	boolean isValidMove(int row, int col) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(TicTacToe.board[row][col]==' ') {
				return true;
			}
		}
		return false;
	}		
}
class HumanPlayer extends Player {
	HumanPlayer(String name, char mark) {
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner sc= new Scanner(System.in);
		int row;
		int col;
		do{
			System.out.print("Enter the row and col: ");
			row=sc.nextInt();
			col=sc .nextInt();
		}
		while(!isValidMove(row,col));
		TicTacToe.placeMark(row, col, mark);	
	}
}
class AIPlayer extends Player{
	AIPlayer(String name, char mark) {
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		
		int row;
		int col;
		do{
			Random r = new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
		}
		while(!isValidMove(row,col));
		TicTacToe.placeMark(row, col, mark);	
	}
}
public class LaunchGame {
	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		HumanPlayer p1 = new HumanPlayer("Bob", 'X');
		AIPlayer p2 = new AIPlayer("Tai", 'O');
		Player cp;
		cp=p1;
		while(true) {
			System.out.println(cp.name + " turn");
			cp.makeMove();
			TicTacToe.dispBoard();
			if(TicTacToe.checkColWin(cp.mark) || TicTacToe.checkRowWin(cp.mark) || TicTacToe.checkDiagWin(cp.mark)) 
			{
				System.out.println(cp.name+ " won the game");
				break;
			}
			else if(TicTacToe.checkDraw()) {
				System.out.println("Game is a draw");
				break;
			}
			else {
				if(cp==p1) {
					cp=p2;
				}
				else {
					cp=p1;
				}
			}
		}
	}

}
