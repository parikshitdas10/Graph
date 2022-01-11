import java.util.*;

public class GraphQ5 {
    private LinkedList<Integer> adj[];

    public GraphQ5(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int src, int dst) {
        adj[src].add(dst);
        adj[dst].add(src);// since undirected so making edge from both sides
    }

    public boolean checkCycle(int src) {
        Queue<Integer> q = new LinkedList<Integer>();
        int[] flag = new int[adj.length];
        for (int i = 0; i < adj.length; i++) {
            flag[i] = -1;// marking all nodes unvisited
        }
        q.add(src);
        flag[src] = 0;// 0 means it is in the queue
        while (!q.isEmpty()) {
            int curr = q.poll();
            flag[curr] = 1;// visited
            for (int neighbour : adj[curr]) {
                if (flag[neighbour] == 0) {
                    return true;
                } else if (flag[neighbour] == -1) {
                    q.add(neighbour);
                    flag[neighbour] = 0;
                }
            }

        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices and edges: ");
        int v = sc.nextInt();
        int e = sc.nextInt();
        GraphQ5 graph = new GraphQ5(v);
        System.out.println("Enter " + e + " edges");
        int src, dst;
        for (int i = 0; i < e; i++) {
            src = sc.nextInt();
            dst = sc.nextInt();
            graph.addEdge(src, dst);
        }
        System.out.println("Cycle detected: " + graph.checkCycle(0));

    }

}
// Algorithm:
// Step 1: add source to queue and flag it 0(added to queue)
// Step 2: pop the element and flag it 1(which means traversed)
// Step 3: explore this popped element's unvisited neighbours
// Step 4: continue until queue is empty
// Step 5: return true if element with flag 0 is found

// Stopping condition:
// when you find an element with flag 0 while exploring neighbours, it means
// cycle is there.
// flag 0 means it is already in queue and in bfs if it is already in queue,
// it has to be some other element's neighbour too, hence there is a cycle
