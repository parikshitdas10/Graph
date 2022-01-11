import java.util.*;

public class GraphQ4 {
    private LinkedList<Integer> adj[];

    public GraphQ4(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int src, int dst) {
        adj[src].add(dst);// directed graph, so only one edge created.
    }

    public boolean detectCycle(int src) {
        int[] flag = new int[adj.length];// used as visited/ unvisited array
        // flag -1 = unvisited
        // flag 0= visited and in stack
        // flag 1 = visited but popped;
        Stack<Integer> stack = new Stack<>();
        int i;
        for (i = 0; i < adj.length; i++) {
            flag[i] = -1;// marking all elements as unvisited
        }
        int curr = src;
        stack.push(curr);
        flag[curr] = 0;
        boolean goBack;
        while (!stack.isEmpty()) {
            goBack = true;
            for (int neighbour : adj[curr]) {
                if (flag[neighbour] == 0) {
                    return true;
                }
                if (flag[neighbour] == -1) {
                    stack.push(neighbour);
                    flag[neighbour] = 0;
                    curr = neighbour;
                    goBack = false;
                }
            }
            if (goBack) {
                flag[curr] = 1;
                curr = stack.pop();
            }

        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices and edges: ");
        int v = sc.nextInt();
        int e = sc.nextInt();
        GraphQ4 graph = new GraphQ4(v);
        System.out.println("Enter " + e + " edges");
        int src, dst;
        for (int i = 0; i < e; i++) {
            src = sc.nextInt();
            dst = sc.nextInt();
            graph.addEdge(src, dst);
        }
        System.out.println("Cycle detected " + graph.detectCycle(0));
    }
}
// Algorithm:
// Step 1 visit the source node
// Step 2 add it to stack
// Step 3 visit one of it's unvisited neighbour and add it to stack
// Step 4 Repeat process until no where to go
// Step 5 pop the stack(backtrack) and search for unvisited neighbour from prev
// point
// Step 6 terminate process when stack is empty

// Escaping condition:
// if the neighbour is already in the stack, it means there is a cycle, hence
// return true
