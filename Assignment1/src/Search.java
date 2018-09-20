import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Search {

	Problem problem;

	public Search(Problem problem){
		this.problem = problem;
	}

	//Tree-search methods
	public String BreadthFirstTreeSearch() {
		return TreeSearch(new FrontierFIFO());
	}

	public String DepthFirstTreeSearch() {
		return TreeSearch(new FrontierLIFO());
	}

	public String UniformCostTreeSearch() {
		return TreeSearch(new FrontierPriorityQueue(new ComparatorG()));
	}

	public String GreedyBestFirstTreeSearch() {
		return TreeSearch(new FrontierPriorityQueue(new ComparatorH(problem)));
	}

	// If the heuristics function is admissible, A* using tree search is optimal
	// An admissible heuristic never iverestimates the cost of getting to the goal, i.e. It is optimistic
	public String AstarTreeSearch() {
		return TreeSearch(new FrontierPriorityQueue(new ComparatorF(problem)));
	}

	//Graph-search methods
	public String BreadthFirstGraphSearch() {
		return GraphSearch(new FrontierFIFO());
	}

	public String DepthFirstGraphSearch() {
		return GraphSearch(new FrontierLIFO());
	}

	public String UniformCostGraphSearch() {
		return GraphSearch(new FrontierPriorityQueue(new ComparatorG()));
	}

	public String GreedyBestFirstGraphSearch() {
		return GraphSearch(new FrontierPriorityQueue(new ComparatorH(problem)));
	}

	// A* Graph Search can return sub-optimal solutions because graph search can discard the optimal paths
	// to a repeated state if the optimal path is not the first one generated.
	public String AstarGraphSearch() {
		return GraphSearch(new FrontierPriorityQueue(new ComparatorF(problem)));
	}


	//Iterative deepening tree-search
	public String IterativeDeepeningTreeSearch() {
		Map<String,Double> cumulativeSolutions = new HashMap<String,Double>();
		double minCost = Double.MAX_VALUE;
		String minSolution = "";
		for( int limit = 0; ; limit++) { //iterate forever until we see a repeated path
			Node solution_node = TreeSearchDepthLimited(new FrontierLIFO(), limit);
			if( solution_node != null ) {
				if(cumulativeSolutions.containsKey(Solution(solution_node))) {
					//The tree search will add unique paths until it reaches depth 48, crazy
					//System.out.println("REPEATED PATH, BREAKING AT LIMIT: " + limit);
					break;
				}
				else {
					if (solution_node.path_cost < minCost) {
						minCost = solution_node.path_cost;
						minSolution = Solution(solution_node);
					}
					cumulativeSolutions.put(Solution(solution_node), solution_node.path_cost);
				}
			}
		}
		return minSolution;
	}

	/*
	* An implementation of Iterative Deepening Search for a Graph - Henri De Boever 2018/09/17
	*/
	public String IterativeDeepeningGraphSearch() {

		Map<String,Double> cumulativeSolutions = new HashMap<String,Double>();
		double minCost = Double.MAX_VALUE;
		String optimalSolution = "";
		//ArrayList<String> previouslySeenSolutions = new ArrayList<String>();
		//ArrayList<Double> previouslySeenSolutionCosts = new ArrayList<Double>();
		//int iterator = 0;
		//int minIndex = 0;
		int iteration = 0;
		while(true){
			Node solution_node = GraphSearchDepthLimited(new FrontierLIFO(), iteration);
			// Store the result into a HashMap
			if(solution_node != null){
				// Iterate while the solutions being generated are unique
				if(cumulativeSolutions.containsKey(Solution(solution_node))){
					break;
				}else{
					if(solution_node.path_cost < minCost){
						minCost = solution_node.path_cost;
						optimalSolution = Solution(solution_node);
						//System.out.println(optimalSolution);
					}
					cumulativeSolutions.put(Solution(solution_node), solution_node.path_cost);
				}
			// 	System.out.println(Solution(solution_node));
			// 	// Insert all solution paths that lead to the goal state and keep track of previously seen solutions.
			// 	if(!previouslySeenSolutions.contains(Solution(solution_node))){
			// 		previouslySeenSolutions.add(Solution(solution_node));
			// 		previouslySeenSolutionCosts.add(solution_node.path_cost);
			// 		minIndex = previouslySeenSolutionCosts.indexOf(Collections.min(previouslySeenSolutionCosts));
			// 	// Stop searching for solutions once the cost optimal one is found
			// }else if(solution_node.path_cost < 1536.0){
			// 		System.out.println("Solution found at " + iterator + " expansions.");
			// 		break;
			// 	}
			// 	iterator ++;
			}
			iteration ++;
			//System.out.println(iteration);
		}
		// Return the cost optimal solution
		//return previouslySeenSolutions.get(minIndex);
		return optimalSolution;
	}


	//For statistics purposes
	int count; //count expansions
	List<Node> node_list; //store all nodes ever generated
	Node initialNode; //initial node based on initial state

	private String TreeSearch(Frontier frontier) {
		count = 0;
		node_list = new ArrayList<Node>();

		initialNode = MakeNode(problem.initialState);
		node_list.add( initialNode );

		frontier.insert( initialNode );
		while(true) {
			if(frontier.isEmpty()) {
				return null;
			}

			Node node = frontier.remove();

			if(problem.goal_test(node.state) )
				return Solution(node);

			frontier.insertAll(Expand(node,problem));
			count++;
		}
	}

	private String GraphSearch(Frontier frontier) {

		count = 0;
		node_list = new ArrayList<Node>();
		initialNode = MakeNode(problem.initialState);
		node_list.add(initialNode);
		Set<Object> explored = new HashSet<Object>(); //empty set
		frontier.insert( initialNode );
		while(true) {

			if(frontier.isEmpty()){
				return null;
			}

			Node node = frontier.remove();
			if(!explored.contains(node.state) ) {
				explored.add(node.state);
				frontier.insertAll(Expand(node, problem));
			}

			// Following code is just for visualization of explored nodes at each step
			System.out.println("\nCurrent Set of Explored Nodes:");
			System.out.print("{ ");
			for (Object obj : explored){
				System.out.print(obj + " ");
			}
			System.out.print("}");
			System.out.println();

			// If the state of the current node is the problem's goal state, return the solution
			if( problem.goal_test(node.state) ){
				return Solution(node);
			}
			count++;
		}
	}

	private Node TreeSearchDepthLimited(Frontier frontier, int limit) {
		//TODO ALEX
		//Initialize frontier to initial state of problem
		count = 0;
		node_list = new ArrayList<Node>();
		initialNode = MakeNode(problem.initialState);
		node_list.add( initialNode );
		frontier.insert( initialNode );

		//Loop do
		while(true) {
			//If frontier is empty return failure
			if(frontier.isEmpty()) {
				return null;
			}

			//Remove n from the frontier (pops from lifo)
			Node node = frontier.remove();

			//If n contains the goal state then return the corresponding solution
			if(problem.goal_test(node.state) ) {
				return node;
			}
			else if( count < limit ) {
				frontier.insertAll(Expand(node,problem));
				count++;
			}
			else {
				return null;
			}
		}
	}

	private Node GraphSearchDepthLimited(Frontier frontier, int limit) {

		// Initialize the expansion count to 0 before starting
		count = 0;
		// Initialize the frontier using the initial state of the problem
		initialNode = MakeNode(problem.initialState);
		node_list = new ArrayList<Node>();
		node_list.add(initialNode);
		// Initialize an empty set to contain explored nodes
		Set<Object> explored = new HashSet<Object>(); //empty set
		// Insert the start point into the frontier
		frontier.insert(initialNode);

		while(true){

			// If the frontier is empty, return failure
			if(frontier.isEmpty())
				return null;
			else{

				// remove a node from the frontier
				Node node = frontier.remove();

				// If the state of the node is not explored and if the depth is less than the limit
				if(!explored.contains(node.state) && count < limit) {
					explored.add(node.state);
					frontier.insertAll(Expand(node, problem));
					// Increment the number of expansions
					count ++;
				}

				// Print testing lines: At each loop print the contents of the visited nodes list
				// System.out.println("\nPrinting the node list:");
				// Iterator iter = frontier.iterator();
				// while(iter.hasNext()){
				// 	System.out.print(iter.next() + " ");
				// }
				// System.out.println();

				// If the state of the current node is the problem's goal state, return the solution
				if( problem.goal_test(node.state) ){
					//System.out.println(Solution(node));
					return node;
				}
			}
		}
	}

	private Node MakeNode(Object state) {
		Node node = new Node();
		node.state = state;
		node.parent_node = null;
		node.path_cost = 0;
		node.depth = 0;
		return node;
	}

	private Set<Node> Expand(Node node, Problem problem) {

		node.order = count;
		Set<Node> successors = new HashSet<Node>(); //empty set
		Set<Object> successor_states = problem.getSuccessors(node.state);

		for(Object result : successor_states) {
			Node s = new Node();
			s.state = result;
			s.parent_node = node;
			s.path_cost = node.path_cost + problem.step_cost(node.state, result);
			s.depth = node.depth + 1;
			successors.add(s);
			node_list.add(s);
		}
		return successors;
	}

	// This function creates a string to print the solution.
	private String Solution(Node node) {

		String solution_str = "\nOptimal Route:\n(cost = " + node.path_cost + ", frontier expansions = " + count + ")    ";
		Deque<Object> solution = new ArrayDeque<Object>();
		do {
			solution.push(node.state);
			node = node.parent_node;
		} while(node != null);

		while(!solution.isEmpty())
			if(solution.size() == 1){
				solution_str += solution.pop();
			}else{
				solution_str += solution.pop() + " ---> ";
			}
		return solution_str;
	}
}
