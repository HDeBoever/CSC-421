/*
 * For any game, the stateT structure records the current state
 * of the game.  For tic-tac-toe, the main component of the
 * state record is the board, which is an array of characters
 * using 'X' for the first player, 'O' for the second, and ' '
 * for empty squares.  Although the board array is logically
 * a two-dimensional structure, it is stored as a linear array
 * so that its indices match the numbers used by the human
 * player to refer to the squares, as follows:
 *
 *        1 | 2 | 3
 *       ---+---+---
 *        4 | 5 | 6
 *       ---+---+---
 *        7 | 8 | 9
 *
 * Note that element 0 is not used, which requires allocation
 * of an extra element.
 *
 * In addition to the board array, the code stores a player
 * value to indicate whose turn it is.
 */


public class StateNim extends State {

    //public char board[] = new char [(3 * 3) + 1];
    public int board[] = new int [3]; //human coins, ai coins, table coins
    public static int AI = 1;
    public static int HUMAN = 0;
    public static int TABLE_COINS = 2;

    public StateNim() {
        board[HUMAN] = 0;
        board[AI] = 0;
        board[TABLE_COINS] = 13;

        player = 1;
    }

    public StateNim(StateNim state) {

        this.board[HUMAN] = state.board[HUMAN];
        this.board[AI] = state.board[AI];
        this.board[TABLE_COINS] = state.board[TABLE_COINS];

        player = state.player;
    }

    public String getTableStateVisual(int tableCoins) {
        String ret = "";
        switch(tableCoins) {
            case 13: ret = "_____NIM_____\n|           |\n|  O O O O  |\n| O O O O O |\n|  O O O O  |\n|___________|\n";
            break;
            case 12: ret = "_____NIM_____\n|           |\n|  O O O O  |\n| O O   O O |\n|  O O O O  |\n|___________|\n";
            break;
            case 11: ret = "_____NIM_____\n|           |\n|  O O O O  |\n|  O O O    |\n|  O O O O  |\n|___________|\n";
            break;
            case 10: ret = "_____NIM_____\n|           |\n|  O O O O  |\n| O       O |\n|  O O O O  |\n|___________|\n";
            break;
            case 9: ret =  "_____NIM_____\n|           |\n|    O O    |\n| O O O O O |\n|    O O    |\n|___________|\n";
            break;
            case 8: ret =  "_____NIM_____\n|           |\n|    O O    |\n| O O   O O |\n|    O O    |\n|___________|\n";
            break;
            case 7: ret =  "_____NIM_____\n|           |\n|    O O    |\n|   O O O   |\n|    O O    |\n|___________|\n";
            break;
            case 6: ret =  "_____NIM_____\n|           |\n|    O O    |\n|    O O    |\n|    O O    |\n|___________|\n";
            break;
            case 5: ret =  "_____NIM_____\n|           |\n|     O     |\n|   O O O   |\n|     O     |\n|___________|\n";
            break;
            case 4: ret =  "_____NIM_____\n|           |\n|    O O    |\n|           |\n|    O O    |\n|___________|\n";
            break;
            case 3: ret =  "_____NIM_____\n|           |\n|     O     |\n|     O     |\n|     O     |\n|___________|\n";
            break;
            case 2: ret =  "_____NIM_____\n|           |\n|           |\n|    O O    |\n|           |\n|___________|\n";
            break;
            case 1: ret =  "_____NIM_____\n|           |\n|           |\n|     O     |\n|           |\n|___________|\n";
            break;
            case 0: ret =  "_____NIM_____\n|           |\n|           |\n|           |\n|           |\n|___________|\n";
            break;
        }
        return ret + " Remaining on table: " + tableCoins;
    }


    public String toString() {

    	String ret = "";

        ret += getTableStateVisual(this.board[TABLE_COINS]);
        ret += "\n AI Coins: " + this.board[AI];
        ret += "\n Human Coins: " + this.board[HUMAN];

    	return ret;
    }
}
