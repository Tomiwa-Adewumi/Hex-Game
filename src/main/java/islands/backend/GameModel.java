package islands.backend;

/**
 * Class to model the play of the game
 *
 */
public class GameModel {

    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    public PlayGame game ;

    /**
     * Construct a game with given sizexsize and an empty game board
     * @param sz the square size of the board
     */
    public GameModel(int sz) {
          game = new PlayGame(sz);
    }

    /**
     * Can a play be made at position row, col
     * @param row the row in question
     * @param col the col in question
     * @return true if row, col is empty, false o.w.
     * @throws IllegalArgumentException for invalid row and col
     */
    public boolean canPlay(int row, int col) {
        return game.checkPlay(row, col);
    }

    /**
     * play a piece and report if the game is over (true) false, otherwise
     * @param row the row where a piece is played
     * @param col the col where a piece is played
     * @param clr -1 for WHITE and 1 for BLACK
     * @return true if the game is over and false otherwise
     * @throws IllegalArgumentException for invalid row and col
     */
    public boolean makePlay(int row, int col, boolean clr) {
        if (row <0 || col <0 || row >= game.size || col >= game.size) {
            throw new IllegalArgumentException();
        }
       return game.placePiece(row, col, clr);
    }

    /**
     * Return the score for white
     * @return white score
     */
    public int whiteScore() {
        return game.whiteScore;
    }

    /**
     * return the score for black
     * @return black score
     */
    public int blackScore() {
        return game.blackScore;
    }
}
