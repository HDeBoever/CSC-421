import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*ProblemMap.java
* Implements abstract class Problem
*/
public class ProblemMap extends Problem {
	Map<String, Map<String, Double>> map;
	Map<String, Double> sld;

	public Object goalState;

		/*Takes a text file input
		* Creates a hashmap with Key = fromCity, Value=<toCity, Cost>
		* Edges are bi-directional (each fromCity is also a toCity)
		*/
	public ProblemMap(String mapfilename) throws Exception {
		map = new HashMap<String, Map<String, Double>>();
		//read map from file of source-destination-cost triples (tab separated)
			BufferedReader reader = new BufferedReader( new FileReader (mapfilename));
				String line;
				while( ( line = reader.readLine() ) != null ) {
					String[] strA = line.split("\t");

					String 	from_city = strA[0],
							to_city   = strA[1];
					Double 	cost 	  = Double.parseDouble(strA[2]);

					if(!map.containsKey(from_city))
						map.put(from_city, new HashMap<String, Double>());
					map.get(from_city).put(to_city,cost);

					//putting the reverse edge as well
					if(!map.containsKey(to_city))
						map.put(to_city, new HashMap<String, Double>());
					map.get(to_city).put(from_city,cost);
				}
				reader.close();
	}

	public ProblemMap(String mapfilename, String heuristicfilename) throws Exception {
		this(mapfilename);

		sld = new HashMap<String, Double>();
			BufferedReader reader = new BufferedReader( new FileReader (heuristicfilename));
				String line;
				while( ( line = reader.readLine() ) != null ) {
						String[] strA = line.split("\t");
						String 	city = strA[0];
						double 	h 	 = Double.parseDouble(strA[1]);

						sld.put(city, h);
				}
				reader.close();
	}

		/*Checks if the current state is a goal state*/
	boolean goal_test(Object state) {
		return state.equals(goalState);
	}

		/*Returns a Set (iterable non-duplicate list) of state objects
		* which are successor states to the current STATE (the city)
		* A successor state might be any connected city. This is determined
		* by calling map.get(state) which will obtain all those VALUE pairs
		* (to/from cities and cost) connected to the current state city
		* and then map.get(state).keySet() returns a Set view of all of
		* those connected cities.
		*/
	Set<Object> getSuccessors(Object state) {
		Set<Object> result = new HashSet<Object>();
		for(Object successor_state : map.get(state).keySet())
			result.add(successor_state);
		return result;
	}

		/*Gets the cost between two cities
		* Gets the keyvalue pair of the keyvalue pair
		* which is the cost
		*/
	double step_cost(Object fromState, Object toState) {
		return map.get(fromState).get(toState);
	}

		/*Gets the heuristic version of the map, sld.
		* not sure what its used for yet
		*/
	public double h(Object state) {
		return sld.get(state);
	}

		/*Creates a new ProblemMap object
		* sets the initial state, then the goal state
		* creates a Search() object with parameter
		*/
	public static void main(String[] args) throws Exception {

		ProblemMap problem = new ProblemMap("../romania.txt","../romaniaSLD.txt");
		ProblemMap problem1 = new ProblemMap("../romania.txt");

		problem.initialState = "Arad";
		problem.goalState = "Bucharest";

		problem1.initialState = "Arad";
		problem1.goalState = "Bucharest";

		Search search  = new Search(problem);
		// Search search1  = new Search(problem1);

		//Henri
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

		System.out.println("\n\nUniform Cost Tree Search:----------------------------------------\n");
		System.out.println( search.UniformCostTreeSearch() + "\n");
		System.out.println("\n\nUniform Cost Graph Search:----------------------------------------\n");
		System.out.println( search.UniformCostGraphSearch() + "\n");

		System.out.println("\n\nGreedy Best Tree Search:----------------------------------------\n");
		System.out.println(search.GreedyBestFirstTreeSearch());
		System.out.println("\n\nGreedy Best Graph Search:----------------------------------------\n");
		System.out.println(search.GreedyBestFirstGraphSearch());

		// A* implementations only work when the straight-line-distance file for Romania is read into the constructor
		// for the ProblemMap Object instance.
		System.out.println("\n\nA-Star Tree Search:----------------------------------------\n");
		System.out.println(search.BreadthFirstGraphSearch());
		System.out.println("\n\nA-Star Graph Search:----------------------------------------\n");
		System.out.println(search.AstarGraphSearch());
		//
		// System.out.println("BreadthFirstTreeSearch:\t\t" + search1.BreadthFirstTreeSearch());
		// System.out.println("BreadthFirstGraphSearch:\t" + search1.BreadthFirstGraphSearch());
	}
}
