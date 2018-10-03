import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class ProblemJugs extends Problem{
    static final int jug1 = 0; //12
	static final int jug2 = 1; //8
	static final int jug3 = 2; //3
    static final int jug1_CAP = 12; //12
    static final int jug2_CAP = 8; //8
    static final int jug3_CAP = 3; //3
    static final int EMPTY = 0; //3

	boolean goal_test(Object state) {
        StateJugs jug_state = (StateJugs) state;
        if (jug_state.jugArray[jug1]==1 || jug_state.jugArray[jug2]==1 || jug_state.jugArray[jug3]==1) {
            return true;
        }
        else{
            return false;
        }
	}

    Set<Object> getSuccessors(Object state) {
        Set<Object> set = new HashSet<Object>();

		StateJugs jug_state = (StateJugs) state;

        //Let's create without any constraint, then remove the illegal ones
        StateJugs ss1;
        StateJugs ss2;
        StateJugs ss3;
        StateJugs ss4;
        StateJugs ss5;
        StateJugs ss6;
        StateJugs ss7;
        StateJugs ss8;
        StateJugs ss9;
        StateJugs ss10;
        StateJugs ss11;
        StateJugs ss12;

        int jug1_remaining_capacity;
        int jug2_remaining_capacity;
        int jug3_remaining_capacity;
        int jug1_contents;
        int jug2_contents;
        int jug3_contents;

        //Pour 2 into 1
        ss1 = new StateJugs(jug_state);
        jug1_remaining_capacity = jug1_CAP - ss1.jugArray[jug1];
        jug2_contents = ss1.jugArray[jug2];
        ss1.jugArray[jug2] -= jug1_remaining_capacity;
        ss1.jugArray[jug1] += jug2_contents;
        resolveCapacity(ss1);
        set.add(ss1);

        //Pour 3 into 1
        ss2 = new StateJugs(jug_state);
        jug1_remaining_capacity = jug1_CAP - ss2.jugArray[jug1];
        jug3_contents = ss2.jugArray[jug3];
        ss2.jugArray[jug3] -= jug1_remaining_capacity;
        ss2.jugArray[jug1] += jug3_contents;
        resolveCapacity(ss2);
        set.add(ss2);

        //Pour 1 into 2
        ss3 = new StateJugs(jug_state);
        jug2_remaining_capacity = jug2_CAP - ss3.jugArray[jug2];
        jug1_contents = ss3.jugArray[jug1];
        ss3.jugArray[jug1] -= jug2_remaining_capacity;
        ss3.jugArray[jug2] += jug1_contents;
        resolveCapacity(ss3);
        set.add(ss3);

        // //Pour 3 into 2
        ss4 = new StateJugs(jug_state);
        jug2_remaining_capacity = jug2_CAP - ss4.jugArray[jug2];
        jug3_contents = ss4.jugArray[jug3];
        ss4.jugArray[jug3] -= jug2_remaining_capacity;
        ss4.jugArray[jug2] += jug3_contents;
        resolveCapacity(ss4);
        set.add(ss4);

        //Pour 1 into 3
        ss5 = new StateJugs(jug_state);
        jug3_remaining_capacity = jug3_CAP - ss5.jugArray[jug3];
        jug1_contents = ss5.jugArray[jug1];
        ss5.jugArray[jug1] -= jug3_remaining_capacity;
        ss5.jugArray[jug3] += jug1_contents;
        resolveCapacity(ss5);
        set.add(ss5);

        // //Pour 2 into 3
        ss6 = new StateJugs(jug_state);
        jug3_remaining_capacity = jug3_CAP - ss6.jugArray[jug3];
        jug2_contents = ss6.jugArray[jug2];
        ss6.jugArray[jug2] -= jug3_remaining_capacity;
        ss6.jugArray[jug3] += jug2_contents;
        resolveCapacity(ss6);
        set.add(ss6);

        //Empty 1
        ss7 = new StateJugs(jug_state);
        ss7.jugArray[jug1] = EMPTY;
        set.add(ss7);

        //Empty 2
        ss8 = new StateJugs(jug_state);
        ss8.jugArray[jug2] = EMPTY;
        set.add(ss8);

        //Empty 3
        ss9 = new StateJugs(jug_state);
        ss9.jugArray[jug3] = EMPTY;
        set.add(ss9);

        //Fill 1
        ss10 = new StateJugs(jug_state);
        ss10.jugArray[jug1] = jug1_CAP;
        set.add(ss10);

        //Fill 2
        ss11 = new StateJugs(jug_state);
        ss11.jugArray[jug2] = jug2_CAP;
        set.add(ss11);

        //Fill 3
        ss12 = new StateJugs(jug_state);
        ss12.jugArray[jug3] = jug3_CAP;
        set.add(ss12);

        return set;
    }

    private void resolveCapacity( StateJugs state ) {
        if( state.jugArray[jug1] > jug1_CAP ) state.jugArray[jug1] = jug1_CAP;
        if( state.jugArray[jug2] > jug2_CAP ) state.jugArray[jug2] = jug2_CAP;
        if( state.jugArray[jug3] > jug3_CAP ) state.jugArray[jug3] = jug3_CAP;
        if( state.jugArray[jug1] < EMPTY ) state.jugArray[jug1] = EMPTY;
        if( state.jugArray[jug2] < EMPTY ) state.jugArray[jug2] = EMPTY;
        if( state.jugArray[jug3] < EMPTY ) state.jugArray[jug3] = EMPTY;
    }

	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { return 0; }

	public static void main(String[] args) throws Exception{
			ProblemJugs problem = new ProblemJugs();
			int[] jugArray = {0,0,0};
			problem.initialState = new StateJugs(jugArray);
            System.out.println( problem.getSuccessors( problem.initialState ).toString() );
			Search search  = new Search(problem);
			System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
			System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
	}
}
