 package islands.backend;

 import java.util.ArrayList;
 import java.util.List;

 public class PlayGame {
    public int blackScore,whiteScore,size;
    public static final int WHITE = 1;
    public static final int BLACK = 0;
    public static final int GREY = 2;


    public UnionFind uf;


    int[][] board;



     /**
      *
      * Constructs an empty game board  with given sizexsize and intializes the scores for black and white
      * @param sz the square size of the board
      */
    PlayGame(int sz){

        blackScore=0;
        whiteScore=0;

        size=sz;
        board = new int[size][size];

        uf = new UnionFind(size);


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = PlayGame.GREY;
            }
        }

    }

     /**
      * Can a play be made at position row, col
      * @param row the row in question
      * @param col the col in question
      * @return true if row, col is empty, false o.w.
      * @throws IllegalArgumentException for invalid row and col
      */
    public boolean checkPlay(int row, int col) {
        if (row <0 || col <0 || row >= size || col >= size) {
            throw new IllegalArgumentException();
        }


        if (board[row][col] == PlayGame.GREY) {
            return true;
        } else {
            return false;
        }
    }


     /**
      * play a piece and report if the game is over (true) false, otherwise
      * @param row the row where a piece is played
      * @param col the col where a piece is played
      * @param clr -1 for WHITE and 1 for BLACK
      * @return true if the game is over and false otherwise
      */
    public boolean placePiece(int row, int col, boolean clr) {
        boolean endGame = false;
        if (clr) {
            board[row][col] = PlayGame.WHITE;
            whiteScore++;
        } else {
            board[row][col] = PlayGame.BLACK;
            blackScore++;
        }

        List<int[]> neighbours = getAdjacentIndices(row, col);
        for (int[] neighbour : neighbours) {
            int rowN = neighbour[0];
            int colN = neighbour[1];

            if (board[row][col] == board[rowN][colN]){
                if (uf.union(new int[]{row, col}, neighbour)) {
                    if (board[row][col] == WHITE) {
                        whiteScore--;
                    } else if (board[row][col] == BLACK) {
                        blackScore--;
                    }
                }
            ;
        }
    }

        if (clr){
            endGame =  uf.hasTopToBottomPath(new int[] {row, col});
        }
        else{
            endGame = uf.hasLeftToRightPath(new int[] {row, col});
        }

        return endGame;

    }


     /***
      * @param row
      * @param col
      * @return returns a list of the positions adjacent to a row and column
      */
     public List<int[]> getAdjacentIndices(int row, int col) {

         int[][] position = {  {row-1,col},  {row,col+1},   {row-1,col-1}, {row+1,col},  {row,col-1},{row+1,col+1} };
         List<int[]> finalPositions = new ArrayList<>();

         for (int[]a:position){
             if(a[0]>=0 & a[0]< size & a[1]>=0 & a[1]<size){
                 finalPositions.add(a);
             }

         }
         return finalPositions;

     }














}
