
import java.util.*;


enum PieceType {   // X or O
    X,
    O;
}

class PlayingPiece{
    
    PieceType type;
    PlayingPiece(PieceType type){
        this.type = type;
    }
}
    
class PlayingPieceX extends PlayingPiece {
    
    PlayingPieceX(){
        super(PieceType.X);
    }
}

class PlayingPieceO extends PlayingPiece {
    
    PlayingPieceO(){
       super(PieceType.O);
    }
}

class Player {
    
    String name;
    PlayingPiece piece;
    
    Player(String name,PlayingPiece piece){
        this.name  = name;
        this.piece = piece;
    }
}

class Board{
      
      int size;
      PlayingPiece[][] board;
      
      Board(int size){
        this.size = size;
        board = new PlayingPiece[size][size];
      }
      
      public boolean addPiece(int row,int col,PlayingPiece playingpiece){
          
          if(board[row][col]  != null) return false;
          board[row][col] = playingpiece;
              
          return true;
      }
      
      public void printBoard(){
           
           for(int i=0;i<size;++i){
               for(int j=0;j<size;++j){
                   
                   if( board[i][j] ==  null )
                   System.out.print( " | "  + "  " + " | " );
                   else
                   System.out.print( " | "  +  board[i][j].type + " | " );
               }
               
               System.out.println();
           }
      }
      
      public boolean checkWinner(int row,int col,PlayingPiece piece){
             
             int count=0;
             boolean rowMatch,colMatch,diagonal,anti_diagonal;
             
             rowMatch = colMatch = diagonal = anti_diagonal = true;
             
             // check Row
             for(int j=0;j<3;++j) {  
                 if(  board[row][j]==null  ||  board[row][j].type != piece.type ){
                    rowMatch = false;
                 }
             }
             
             // check Column
             count=0;
             for(int i=0;i<3;++i) {  
                 if(  board[i][col] == null ||  board[i][col].type != piece.type ){
                     colMatch = false;
                 }
             }
             
 
             // check diagonal
             int i=0;
             int j=0;
             
             while( i <= 2 && j <= 2){
                 
                 if( board[i][j]==null || 
                     board[i][j].type != piece.type )
                  diagonal = false;
                  
                  i++; j++;
             }
             
             i=0; j=2;
             while( i <= 2  && j >= 0){
                 
                 if( board[i][j]==null || board[i][col].type != piece.type )
                  anti_diagonal = false;
                  
                  i++; j--;
             }
             
             return ( rowMatch || 
                      colMatch || 
                      diagonal || 
                      anti_diagonal  
                    );
      }
}

class TicTacToeGame{ // Game
    
    Deque<Player> players;
    Board board;
    
    TicTacToeGame(){
       // initalizing the game
       
       players = new LinkedList<>();
       PlayingPiece  pieceX = new PlayingPieceX();
       PlayingPiece  pieceO = new PlayingPieceO();
       Player player1 = new Player("player1",pieceX);
       Player player2 = new Player("player2",pieceO);
       
       players.add(player1);
       players.add(player2);
          
       board = new Board(3);
       
    }
    
    void startGame(){
        
        int moves = 9;
        
        while (  moves > 0 ){
            
            Player player = players.peekFirst();
            board.printBoard();
              
            System.out.println( player.name + ": turn Enter row or Column");
            Scanner inputScanner = new Scanner(System.in);
            int inputRow = inputScanner.nextInt();
            int inputColumn = inputScanner.nextInt();
            
            if(    inputRow    < 0 || inputRow    >= 3
                || inputColumn < 0 || inputColumn >= 3  
                || board.board[inputRow][inputColumn] != null 
                ){
                System.out.println("Invalid Cell please enter again");
                System.out.println();
                continue;
                }
            else{
                board.board[inputRow][inputColumn] = player.piece;
                Player removedPlayer = players.pollFirst();
                players.add(removedPlayer);
            }
            
            if( board.checkWinner(inputRow,inputColumn,player.piece) ){
                System.out.println( player.name + " is the winner");
                System.out.println();
                break;
            }
            
            moves--;
            System.out.println();
        }
        
    }
    
}


public class Main{

	public static void main(String[] args) {
	    
	    TicTacToeGame game = new TicTacToeGame();
	    game.startGame();
	    
// 		System.out.println("Hello World");
	}
}
