import java.util.ArrayDeque;
import java.util.ArrayList;
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

	public String AstarGraphSearch() {
		return GraphSearch(new FrontierPriorityQueue(new ComparatorF(problem)));
	}


	//Iterative deepening, tree-search and graph-search
	public String IterativeDeepeningTreeSearch() {
		//TODO ALEX
		return null;
	}

	/*Henri is working on the method below*/
	public String IterativeDeepeningGraphSearch() {

		System.out.println("In the iterative deepening graph search:\n");
		ArrayList<String> memory = new ArrayList<String>();
		while(true){
			String result = GraphSearchDepthLimited(new FrontierLIFO(), 8);
			System.out.println("RESULT: " + result);
			// Store the result into a list
			if(!memory.contains(result)){
				System.out.println(result);
				memory.add(result);
			}else{
				System.out.println("Done!");
				System.exit(0);
			}
			// if(result.equals("Bucharest")){
			// 		System.out.println("Done");
			// }
		}
	}

	//For statistics purposes
	int count; //count expansions
	List<Node> node_list; //store all nodes ever generated
	Node initialNode; //initial node based on initial state

	private String TreeSearch(Frontier frontier) {
		System.out.println("TreeSearch called");
		count = 0;
		System.out.println("Node list set to a new ArrayList<Node>()");
		node_list = new ArrayList<Node>();

		System.out.println("Created initialNode from problem.initialState");
		initialNode = MakeNode(problem.initialState);
		System.out.println("Adding initialNode to node_list. InitialNode: " + initialNode.state.toString());
		node_list.add( initialNode );


		System.out.println("Inserting initial node into frontier");
		frontier.insert( initialNode );
		while(true) {
				System.out.println("TreeSeach iteration");

			if(frontier.isEmpty()) {
				System.out.println("Frontier was empty");
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
			if(frontier.isEmpty())
				return null;

			Node node = frontier.remove();
			// If the state of the current node is the problem's goal state, return the solution
			if( problem.goal_test(node.state) )
				return Solution(node);
			if(!explored.contains(node.state) ) {
				explored.add(node.state);
				frontier.insertAll(Expand(node, problem));
				count++;
			}
		}
	}

	private String TreeSearchDepthLimited(Frontier frontier, int limit) {
		//TODO ALEX

		return null;
	}

	/*Henri is working on the method below*/
	private String GraphSearchDepthLimited(Frontier frontier, int limit) {
		System.out.println("GraphSearchDepthLimited is called...");

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

			// At each loop print the contents of the visited nodes list
			// System.out.println("\nPrinting the node list:");
			// for (Node node : node_list){
			// 		System.out.print(node.state + " " + node.path_cost + " " + node.depth + " ");
			// }

			// Print the contents of the Frontier here
			// System.out.println("\nPrinting the Frontier:");
			// System.out.println(frontier);

			// If the frontier is empty, return failure
			if(frontier.isEmpty())
				return null;
			else{

				// remove a node from the frontier
				Node node = frontier.remove();
				System.out.println("NODE STATE: " + node.state);

				// If the state of the current node is the problem's goal state, return the solution
				if( problem.goal_test(node.state) ){
					return Solution(node);
				}
				// If the state of the node is not explored and if the depth is less than the limit
				if(!explored.contains(node.state) && count < limit) {
					explored.add(node.state);
					frontier.insertAll(Expand(node, problem));
					// Increment the number of expansions
					count++;
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

		//Create a string to print solution.
		private String Solution(Node node) {

			String solution_str = "(cost = " + node.path_cost + ", expansions = " + count + ")\t";

			Deque<Object> solution = new ArrayDeque<Object>();
			do {
				solution.push(node.state);
				node = node.parent_node;
			} while(node != null);

			while(!solution.isEmpty())
				solution_str += solution.pop() + " ";

			return solution_str;
		}
}
