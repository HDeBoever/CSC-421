import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		//TODO ALEX

		String result = null;
		while(true) {
			result = TreeSearchDepthLimited(new FrontierLIFO(), 8);

			if( result != null ) {
				//System.out.println("Got a result");
				break;
			}
			//System.out.println("In the iterative deepening tree search:");
			if( result != null && result.equals("Bucharest") ) {
				//System.out.println("RESULT: " + result.toString());
				//System.out.println("Done");
				return result;
			}
			else {
				//System.out.println("TreeSearchDepthLimited returned null, returning null back to Main\n");
				//return null;
			}
		}
		return "";
	}

	/*
	* An implementation of Iterative Deepening Search for a Graph - Henri De Boever 2018/09/17
	*/
	public String IterativeDeepeningGraphSearch() {

		ArrayList<String> previouslySeenSolutions = new ArrayList<String>();
		ArrayList<Double> previouslySeenSolutionCosts = new ArrayList<Double>();
		int iterator = 0;
		int minIndex = 0;
		while(true){
			Node solution_node = GraphSearchDepthLimited(new FrontierLIFO(), 6);
			// Store the result into a list
			if(solution_node != null){
				// Insert all solution paths that lead to the goal state and keep track of previously seen solutions.
				if(!previouslySeenSolutions.contains(Solution(solution_node))){
					previouslySeenSolutions.add(Solution(solution_node));
					previouslySeenSolutionCosts.add(solution_node.path_cost);
					minIndex = previouslySeenSolutionCosts.indexOf(Collections.min(previouslySeenSolutionCosts));
				// Stop searching for solutions once the cost optimal one is found
				}else if(solution_node.path_cost == 536.0){
					System.out.println("Solution found at " + iterator + " expansions.");
					break;
				}
				iterator ++;
			}
		}
		// Return the cost optimal solution
		return previouslySeenSolutions.get(minIndex);
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
				//count++;
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

	private String TreeSearchDepthLimited(Frontier frontier, int limit) {
		//System.out.println("TreeSearchDepthLimited called");
		//TODO ALEX
		//Initialize frontier to initial state of problem
		count = 0;
		node_list = new ArrayList<Node>();
		initialNode = MakeNode(problem.initialState);
		node_list.add( initialNode );
		frontier.insert( initialNode );

		//Loop do
		while(true) {
			//System.out.println("Iterating in TreeSearchDepthLimited. Count: " + count);
			//If frontier is empty return failure
			if(frontier.isEmpty()) {
				//System.out.println("Frontier empty, TreeSearchDepthLimited() returning null");
				return null;
			}

			//Remove n from the frontier (pops from lifo)
			Node node = frontier.remove();
			//System.out.println("Popping next node from frontier, node.path_cost: " + node.path_cost + " node.state: " + node.state);

			//If n contains the goal state then return the corresponding solution
			if(problem.goal_test(node.state) ) {
				//System.out.println("Found a solution");
				return Solution(node);
			}
			//System.out.println("Expanding the frontier (ie adding to stack)");
			frontier.insertAll(Expand(node,problem));
			count++;

			if( count >= limit ) {
			//	System.out.println("Count was: " + count + "Reached limit: " + limit);
				return null;
			}
		}
	}

	/*Henri is working on the method below*/
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
				if(!explored.contains(node.state) && count <= limit) {
					explored.add(node.state);
					frontier.insertAll(Expand(node, problem));
				}

				// Print testing lines: At each loop print the contents of the visited nodes list
				// System.out.println("\nPrinting the node list:");
				// for (Object obj : explored){
				// 	System.out.print(obj + " ");
				// }
				// System.out.println();

				// If the state of the current node is the problem's goal state, return the solution
				if( problem.goal_test(node.state) ){
					//System.out.println(Solution(node));
					return node;
				}

				// Increment the number of expansions
				count++;
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
