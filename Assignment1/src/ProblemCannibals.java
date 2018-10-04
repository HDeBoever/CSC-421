import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class ProblemCannibals extends Problem{
	static final int cannL = 0;
	static final int missL = 1;
	static final int boatL = 2;
	static final int cannR = 3;
	static final int missR = 4;
	static final int boatR = 5;

	boolean goal_test(Object state) {
		StateCannibals can_state = (StateCannibals) state;
		if (can_state.canArray[cannR]==3 && can_state.canArray[missR]==3 && can_state.canArray[boatR]==1) {
			return true;
		}
		else{
			return false;
		}
	}

	Set<Object> getSuccessors(Object state) {
		Set<Object> set = new HashSet<Object>();

		StateCannibals can_state = (StateCannibals) state;

		//Let's create without any constraint, then remove the illegal ones
		StateCannibals successor_state;
		StateCannibals successor_state1;
		StateCannibals successor_state2;
		StateCannibals successor_state3;
		StateCannibals successor_state4;
		StateCannibals successor_state5;
		StateCannibals successor_state6;
		StateCannibals successor_state7;
		StateCannibals successor_state8;
		StateCannibals successor_state9;

		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		//one cannibal only from left to right
		successor_state = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state.toString(): " + successor_state.toString() + " based on can_state: " + can_state.toString());
		successor_state.canArray[cannL] -= 1;
		successor_state.canArray[cannR] += 1;
		successor_state.canArray[boatL] -= 1;
		successor_state.canArray[boatR] += 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state.toString(): " + successor_state.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state)) set.add(successor_state);

		//one cannibal only from right to left
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state1 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state1.toString(): " + successor_state1.toString() + " based on can_state: " + can_state.toString());
		successor_state1.canArray[cannL] += 1;
		successor_state1.canArray[cannR] -= 1;
		successor_state1.canArray[boatL] += 1;
		successor_state1.canArray[boatR] -= 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state1.toString(): " + successor_state1.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state1)) set.add(successor_state1);

		//two cannibals from left to right
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state2 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state2.toString(): " + successor_state2.toString() + " based on can_state: " + can_state.toString());
		successor_state2.canArray[cannL] -= 2;
		successor_state2.canArray[cannR] += 2;
		successor_state2.canArray[boatL] -= 1;
		successor_state2.canArray[boatR] += 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state2.toString(): " + successor_state2.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state2)) set.add(successor_state2);

		//two cannibals from right to left
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state3 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state3.toString(): " + successor_state3.toString() + " based on can_state: " + can_state.toString());
		successor_state3.canArray[cannL] += 2;
		successor_state3.canArray[cannR] -= 2;
		successor_state3.canArray[boatL] += 1;
		successor_state3.canArray[boatR] -= 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state3.toString(): " + successor_state3.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state3)) set.add(successor_state3);

		//one missionary only from left to right
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state4 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state4.toString(): " + successor_state4.toString() + " based on can_state: " + can_state.toString());
		successor_state4.canArray[missL] -= 1;
		successor_state4.canArray[missR] += 1;
		successor_state4.canArray[boatL] -= 1;
		successor_state4.canArray[boatR] += 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state4.toString(): " + successor_state4.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state4)) set.add(successor_state4);

		//one missionary only from right to left
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state5 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state5.toString(): " + successor_state5.toString() + " based on can_state: " + can_state.toString());
		successor_state5.canArray[missL] += 1;
		successor_state5.canArray[missR] -= 1;
		successor_state5.canArray[boatL] += 1;
		successor_state5.canArray[boatR] -= 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state5.toString(): " + successor_state5.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state5)) set.add(successor_state5);

		//two missionaries from left to right
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state6 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state6.toString(): " + successor_state6.toString() + " based on can_state: " + can_state.toString());
		successor_state6.canArray[missL] -= 2;
		successor_state6.canArray[missR] += 2;
		successor_state6.canArray[boatL] -= 1;
		successor_state6.canArray[boatR] += 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state6.toString(): " + successor_state6.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state6)) set.add(successor_state6);

		//two missionaries from right to left
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state7 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state7.toString(): " + successor_state7.toString() + " based on can_state: " + can_state.toString());
		successor_state7.canArray[missL] += 2;
		successor_state7.canArray[missR] -= 2;
		successor_state7.canArray[boatL] += 1;
		successor_state7.canArray[boatR] -= 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state7.toString(): " + successor_state7.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state7)) set.add(successor_state7);

		//one cannibal and one missionary from left to right
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state8 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state8.toString(): " + successor_state8.toString() + " based on can_state: " + can_state.toString());
		successor_state8.canArray[missL] -= 1;
		successor_state8.canArray[cannL] -= 1;
		successor_state8.canArray[missR] += 1;
		successor_state8.canArray[cannR] += 1;
		successor_state8.canArray[boatL] -= 1;
		successor_state8.canArray[boatR] += 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state8.toString(): " + successor_state8.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state8)) set.add(successor_state8);

		//one cannibal and one missionary from right to left
		//System.out.println("ProblemCannibals.getSuccessors() => Creating possible successor_state...");
		successor_state9 = new StateCannibals(can_state);
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state9.toString(): " + successor_state9.toString() + " based on can_state: " + can_state.toString());
		successor_state9.canArray[missR] -= 1;
		successor_state9.canArray[cannR] -= 1;
		successor_state9.canArray[missL] += 1;
		successor_state9.canArray[cannL] += 1;
		successor_state9.canArray[boatR] -= 1;
		successor_state9.canArray[boatL] += 1;
		//System.out.println("ProblemCannibals.getSuccessors() => successor_state9.toString(): " + successor_state9.toString() + " based on can_state: " + can_state.toString());
		if (isValid(successor_state9)) set.add(successor_state9);

		return set;
	}

		private boolean isValid(StateCannibals state){

			//System.out.println("Validity check----------------------------- [ cannL, missL, boatL, cannR, missR, boatR ]");
			boolean flag = true;
			//System.out.println("Checking if state: " + state.toString() + " is valid state. If so, will print TRUE, else nothing");
			//Checking to see if any element of the array is negative
			for (int i = 0; i < 6; i++){
					if( state.canArray[i] < 0 ) {
						//System.out.println( Arrays.toString(state.canArray) + "@" + i + " negative");
						flag = false; }
			}
			//Checking to see if the numbers of cannibals, missionaries, and boat
			//are more then 3,3,1 respectively
			//TODO
			for( int i = 0; i < 6; i++ ) {
				switch(i) {
					case missR:
						if( state.canArray[missR] > 3 ) {
							//System.out.println( Arrays.toString(state.canArray) + "@" + i + " missR > 3");
							flag = false;
						}
					break;
					case missL:
						if( state.canArray[missL] > 3 ) {
							//System.out.println( Arrays.toString(state.canArray) + "@" + i + " missL > 3");
							flag = false;
						}
					break;
					case cannR:
						if( state.canArray[cannR] > 3 ) {
							//System.out.println( Arrays.toString(state.canArray) + "@" + i + " cannR > 3");
							flag = false;
						}
					break;
					case cannL:
						if( state.canArray[cannL] > 3 ) {
							//System.out.println( Arrays.toString(state.canArray) + "@" + i + " cannL > 3");
							flag = false;
						}
					break;
					case boatR:
						if( state.canArray[boatR] > 1 ) {
							//System.out.println( Arrays.toString(state.canArray) + "@" + i + " boatR > 1");
							flag = false;
						}
					break;
					case boatL:
						if( state.canArray[boatL] > 1 ) {
							//System.out.println( Arrays.toString(state.canArray) + "@" + i + " boatL > 1");
							flag = false;
						}
					break;
				}
			}

			//Now, checking if cannibals out number missionaries
			if( state.canArray[cannR] > state.canArray[missR] && state.canArray[missR] > 0) {
					//System.out.println( Arrays.toString(state.canArray) + ": CannR@" + cannR + " outnumber MissR@" + missR);
					flag = false;
			}
			else if( state.canArray[cannL] > state.canArray[missL] && state.canArray[missL] > 0) {
				//System.out.println( Arrays.toString(state.canArray) + ": CannL@" + cannL + " outnumber missL@" + missL);
				flag = false;
			}

			//System.out.println("State: " + state.toString() + " isValid() => " + flag);
			//System.out.println("END Validity check-----------------------------[ cannL, missL, boatL, cannR, missR, boatR ]\n");
			return flag;
	}

	// Compute the step cost
	double step_cost(Object fromState, Object toState) {
		return 1;
	}

	// Return Heuristic here
	public double h(Object state) {

		// Cast the passed in object to a state cannibals object
		StateCannibals can_state = (StateCannibals) state;
		return can_state.canArray[0] + can_state.canArray[1];
	}

	public static void main(String[] args) throws Exception{

		ProblemCannibals problem = new ProblemCannibals();
		int[] canArray = {3,3,1,0,0,0};
		problem.initialState = new StateCannibals(canArray);

		Search search  = new Search(problem);

		// Uninformed Searches
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("DepthFirstGraphSearch:\t" + search.DepthFirstGraphSearch());
		System.out.println("GraphSearchDepthLimited:\t" + search.IterativeDeepeningGraphSearch());

		// Uninformed graph search functions
		System.out.println("\n\nGraph Search Depth Limited:----------------------------------------\n");
		System.out.println(search.IterativeDeepeningGraphSearch() + "\n");

		System.out.println("\n\nTree Search Depth Limited:----------------------------------------\n");
		System.out.println(search.IterativeDeepeningTreeSearch() + "\n");

		System.out.println("\n\nBreadth First Tree Search:----------------------------------------\n");
		System.out.println(search.BreadthFirstTreeSearch() + "\n");
		System.out.println("\n\nBreadth First Graph Search:----------------------------------------\n");
		System.out.println( search.BreadthFirstGraphSearch() + "\n");

		System.out.println("\n\nDepth First Tree Search:----------------------------------------\n");
		System.out.println(search.DepthFirstTreeSearch() + "\n");
		System.out.println("\n\nDepth First Graph Search:----------------------------------------\n");
		System.out.println( search.DepthFirstGraphSearch() + "\n");

		System.out.println("\n\nUniform Cost Tree Search:----------------------------------------\n");
		System.out.println( search.UniformCostTreeSearch() + "\n");
		System.out.println("\n\nUniform Cost Graph Search:----------------------------------------\n");
		System.out.println( search.UniformCostGraphSearch() + "\n");

		// Informed Searches A-Star and Greedy, called only when the heuristic is finished
		// System.out.println("AstarGraphSearch:\t" + search.AstarGraphSearch());
		// System.out.println("GreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());
		// System.out.println("AstarTreeSearch:\t" + search.AstarTreeSearch());
		// System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
	}
}
