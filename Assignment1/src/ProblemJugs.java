import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.lang.Math;

public class ProblemJugs extends Problem{
    static final int jug1 = 0; //12
	static final int jug2 = 1; //8
	static final int jug3 = 2; //3
    static final int jug1_CAP = 12; //12
    static final int jug2_CAP = 8; //8
    static final int jug3_CAP = 3; //3
    static final int EMPTY = 0; //3
    Map<String, Map<String, Double>> map;

    public ProblemJugs() {
        //System.out.println("init");
        map = new HashMap<String, Map<String, Double>>();
        //System.out.println("Map size " + map.size());
    }
    //
    //     //map = new HashMap<String, Map<String, Double>>();
    //     //read map from file of source-destination-cost triples (tab separated)
    //         BufferedReader reader = new BufferedReader( new FileReader (mapfilename));
    //             String line;
    //             while( ( line = reader.readLine() ) != null ) {
    //                 String[] strA = line.split("\t");
    //
    //                 String 	from_city = strA[0],
    //                         to_city   = strA[1];
    //                 Double 	cost 	  = Double.parseDouble(strA[2]);
    //
    //                 if(!map.containsKey(from_city))
    //                     map.put(from_city, new HashMap<String, Double>());
    //                 map.get(from_city).put(to_city,cost);
    //
    //                 //putting the reverse edge as well
    //                 if(!map.containsKey(to_city))
    //                     map.put(to_city, new HashMap<String, Double>());
    //                 map.get(to_city).put(from_city,cost);
    //             }
    //             reader.close();
    // }

	boolean goal_test(Object state) {
        StateJugs jug_state = (StateJugs) state;
        if (jug_state.jugArray[jug1]==1 || jug_state.jugArray[jug2]==1 || jug_state.jugArray[jug3]==1) {
            return true;
        }
        else{
            return false;
        }
	}

    //Will need to create key-value pairs of form < String, Map<String,Double> >
    //Basically < FromState, Map<ToState, PouringCost> >
    //StateForms: Action^Levels -> Action^Levels

    //ie FromState = "Poured2Into1_0_3_3" ToState = "Emptied2_0_0_3"
    //ie etc... ALTERNATELY: FromState = 12_3_0, ToState = 7_8_0, Cost = 5
    Set<Object> getSuccessors(Object state) {
        Set<Object> set = new HashSet<Object>();

		StateJugs jug_state = (StateJugs) state;
        Double cost;
        String toState;
        String fromState = jug_state.jugArray[jug1] + "_" + jug_state.jugArray[jug2] + "_" + jug_state.jugArray[jug3];

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

        // //Pour 1 into 2
        ss3 = new StateJugs(jug_state);
        jug2_remaining_capacity = jug2_CAP - ss3.jugArray[jug2];
        jug1_contents = ss3.jugArray[jug1];
        ss3.jugArray[jug1] -= jug2_remaining_capacity;
        ss3.jugArray[jug2] += jug1_contents;
        resolveCapacity(ss3);
        set.add(ss3);


        //Pour 3 into 2
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

        // //Fill 3
        ss12 = new StateJugs(jug_state);
        ss12.jugArray[jug3] = jug3_CAP;
        set.add(ss12);

        return set;
    }

    // private Double computeCost( int jug1_remaining_capacity, int jug2_contents ) {
    //     int costCalc = jug2_contents - jug1_remaining_capacity;
    //     Double cost;
    //     if( costCalc < 0 ) cost = Double.valueOf(jug2_contents);
    //     else cost = Double.valueOf( jug1_remaining_capacity );
    //     return cost;
    // }
    //
    // private void addToMap( String fromState, String toState, Double cost ) {
    //     if(!map.containsKey(fromState)) {
    //         System.out.println("Adding to map fromState, " + fromState + ", because map didn't contain it");
    //         map.put(fromState, new HashMap<String, Double>());
    //     }
    //     if( ! ( (map.get(fromState)).containsKey(toState) ) && !fromState.equals(toState) ) {
    //         System.out.println("Adding SUCCESOR: " + toState + " to PARENT: " + fromState);
    //         map.get(fromState).put(toState,cost);
    //     }
    //     else {
    //         System.out.println("fromState, " + fromState + " already had toState: " + toState + " as a successor. Not adding.");
    //     }
    // }

    private void resolveCapacity( StateJugs state ) {
        if( state.jugArray[jug1] > jug1_CAP ) state.jugArray[jug1] = jug1_CAP;
        if( state.jugArray[jug2] > jug2_CAP ) state.jugArray[jug2] = jug2_CAP;
        if( state.jugArray[jug3] > jug3_CAP ) state.jugArray[jug3] = jug3_CAP;
        if( state.jugArray[jug1] < EMPTY ) state.jugArray[jug1] = EMPTY;
        if( state.jugArray[jug2] < EMPTY ) state.jugArray[jug2] = EMPTY;
        if( state.jugArray[jug3] < EMPTY ) state.jugArray[jug3] = EMPTY;
    }

	double step_cost(Object fromState, Object toState) {
        StateJugs tempFromState = (StateJugs) fromState;
        StateJugs tempToState = (StateJugs) toState;

        int j1 = Math.abs( tempFromState.jugArray[jug1] - tempToState.jugArray[jug1] );
        int j2 = Math.abs( tempFromState.jugArray[jug2] - tempToState.jugArray[jug2] );
        int j3 = Math.abs( tempFromState.jugArray[jug3] - tempToState.jugArray[jug3] );

        if( j1 != 0 ) {
            //System.out.println( "cost from " + (tempFromState) + " to " + (tempToState) + ": " + j1 );
            return j1;
        }
        else if( j2 != 0 ) {
            //System.out.println( "cost from " + (tempFromState) + " to " + (tempToState) + ": " + j2 );
            return j2;
        }
        else if( j3 != 0 ) {
            //System.out.println( "cost from " + (tempFromState) + " to " + (tempToState) + ": " + j3 );
            return j3;
        }
        return 0;

        //return map.get(fromState).get(toState);
    }

	public double h(Object state) { return 0; }

	public static void main(String[] args) throws Exception{
			ProblemJugs problem = new ProblemJugs();
			int[] jugArray = {0,0,0};
			problem.initialState = new StateJugs(jugArray);
			Search search  = new Search(problem);
            System.out.println("\n\nGraph Search Depth Limited:----------------------------------------\n");
            System.out.println(search.IterativeDeepeningGraphSearch() + "\n");

            // Alex
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
            // System.out.println("\n\nUniform Cost Tree Search:----------------------------------------\n");
            // System.out.println( search.UniformCostTreeSearch() + "\n");
            // System.out.println("\n\nUniform Cost Graph Search:----------------------------------------\n");
	}
}
