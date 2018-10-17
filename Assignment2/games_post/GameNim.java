import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game {

    public static int AI_COINS_IDX = 1;
    public static int HUMAN_COINS_IDX = 0;
    public static int TABLE_COINS = 2;

	//char marks[] = {'O', 'X'}; //'O' for computer, 'X' for human

//    int winningLines[][] = {
//        { 1, 2, 3 },
//        { 4, 5, 6 },
//        { 7, 8, 9 },
//        { 1, 4, 7 },
//        { 2, 5, 8 },
//        { 3, 6, 9 },
//        { 1, 5, 9 },
//        { 3, 5, 7 }
//    };

    int WinningScore = 10;
    int LosingScore = -10;
    int BetterScore = 10;
    int NeutralScore = 0;

    public GameNim() {
    	currentState = new StateNim();
    }

    //TODO solve this. The problem is that this win state
    //returns true WHEN THE BOARD BECOMES ZERO on your move
    //that is, if the computer takes the last coin, it wins because
    //the board becomes zero coins. Instead, you LOSE if the board becomes
    //zero on your move...instead, you should win if the PARENT state, the move
    //just previous to yours the board became zero.
    public boolean isWinState(State state)
    {
        StateNim tstate = (StateNim) state;
        //player who did the last move
        int previous_player = (state.player==0 ? 1 : 0);
        if (tstate.board[TABLE_COINS] == 0) return true;

        return false;


//        char mark = marks[previous_player];
//
//        for (int i = 0; i < winningLines.length; i++) {
//
//            if (mark == tstate.board[winningLines[i][0]]
//             && mark == tstate.board[winningLines[i][1]]
//             && mark == tstate.board[winningLines[i][2]]) {
//
//            	return true;
//            }
//        }
//        return false;
    }

    public boolean isStuckState(State state) {

        if (isWinState(state))
            return false;

        return false;
//        StateNim tstate = (StateNim) state;
//
//        for (int i=1; i<=9; i++)
//            if ( tstate.board[i] == ' ' )
//                return false;
//
//        return true;
    }


	public Set<State> getSuccessors(State state)
    {
		if( isWinState(state) )// || isStuckState(state))
			return null;

		Set<State> successors = new HashSet<State>();
        StateNim tstate = (StateNim) state;

        StateNim successor_state;

        //At any state, a player can 1, 2, or 3 coins from the table
        if ( tstate.board[TABLE_COINS] >= 1 ) {
            successor_state = new StateNim(tstate);
            successor_state.board[TABLE_COINS] -= 1;
            successor_state.board[ (state.player == 0 ? AI_COINS_IDX : HUMAN_COINS_IDX) ] += 1;
            successor_state.player = (state.player==0 ? 1 : 0);
            successors.add(successor_state);
        }

        if ( tstate.board[TABLE_COINS] >= 2 ) {
            successor_state = new StateNim(tstate);
            successor_state.board[TABLE_COINS] -= 2;
            successor_state.board[ (state.player==0 ? AI_COINS_IDX : HUMAN_COINS_IDX) ] += 2;
            successor_state.player = (state.player==0 ? 1 : 0);
            successors.add(successor_state);
        }

        if ( tstate.board[TABLE_COINS] >= 3 ) {
            successor_state = new StateNim(tstate);
            successor_state.board[TABLE_COINS] -= 3;
            successor_state.board[ (state.player==0 ? AI_COINS_IDX : HUMAN_COINS_IDX) ] += 3;
            successor_state.player = (state.player==0 ? 1 : 0);
            successors.add(successor_state);
        }


        /*
        char mark = 'O';
        if (tstate.player == 1) //human
            mark = 'X';

        for (int i = 1; i <= 9; i++) {
            if (tstate.board[i] == ' ') {
                successor_state = new StateNim(tstate);
                successor_state.board[i] = mark;
                successor_state.player = (state.player==0 ? 1 : 0);

                successors.add(successor_state);
            }
        }*/

        return successors;
    }

    public double eval(State state)
    {
        StateNim tstate = (StateNim) state;

        //player who made last move
        int previous_player = (state.player==0 ? 1 : 0);

        if(isWinState(state)) {
            if (previous_player == 1) //computer wins
                return WinningScore;
            else //human wins
                return LosingScore;
        }
    	else if( previous_player == HUMAN_COINS_IDX && tstate.board[TABLE_COINS] == 5 ) return 2*WinningScore;
    	else return NeutralScore;

        //return NeutralScore;
    }


    public static void main(String[] args) throws Exception {

		//TODO continue to implement Nim
		//TODO Refactor tic tac toe code to work with Nim rules
		//TODO Work on get successors for nim moves

        Game game = new GameNim();
        Search search = new Search(game);
        int depth = 8;

        System.out.println(game.currentState);
        //needed to get human's move
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

        	StateNim 	nextState = null;

            switch ( game.currentState.player ) {
              case 1: //Human

            	  //get human's move
                  System.out.print("\n\nEnter the number of coins to take from the table> ");
                  int pos = Integer.parseInt( in.readLine() );

                  nextState = new StateNim((StateNim)game.currentState);
                  nextState.player = 1;
                  nextState.board[TABLE_COINS] -= pos;
                  nextState.board[HUMAN_COINS_IDX] += pos;
                  System.out.println("State after Human move: \n" + nextState);
                  break;

              case 0: //Computer

            	  nextState = (StateNim)search.bestSuccessorState(depth);
            	  nextState.player = 0;
            	  System.out.println("State after Computer move: \n" + nextState);
                  break;
            }

            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player==0 ? 1 : 0);

            //Who wins?
            if ( game.isWinState(game.currentState) ) {

            	if (game.currentState.player == 0) //i.e. last move was by the computer
            		System.out.println("Computer wins!");
            	else
            		System.out.println("You win!");

            	break;
            }
        }
    }
}
