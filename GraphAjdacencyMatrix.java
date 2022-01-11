public class GraphAjdacencyMatrix {
    int vertex;
    int matrix[][];

    public GraphAjdacencyMatrix(int vertex) {
        this.vertex = vertex;
        matrix = new int[vertex][vertex];
    }

    public void addEdge(int source, int destination) {
        // add edge
        matrix[source][destination] = 1;

        // add back edge for undirected graph
        matrix[destination][source] = 1;
    }

    public void dfs(int start, boolean visited[]) {

        // Print the current node
        System.out.println(start + " ");

        // Set current node as visited
        visited[start] = true;

        // For every node of the graph
        for (int i = 0; i < vertex; i++) {

            // If some node is adjacent to the current node
            // and it has not already been visited
            if (matrix[start][i] == 1 && (!visited[i])) {
                dfs(i, visited);
            }
        }
    }

    public void printGraph() {
        System.out.println("Graph: (Adjacency Matrix)");
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < vertex; i++) {
            System.out.print("Vertex " + i + " is connected to:");
            for (int j = 0; j < vertex; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphAjdacencyMatrix graph = new GraphAjdacencyMatrix(4);
        boolean[] visited = new boolean[4];
        // graph.addEdge(0, 1);
        // graph.addEdge(0, 4);
        // graph.addEdge(1, 2);
        // graph.addEdge(1, 3);
        // graph.addEdge(1, 4);
        // graph.addEdge(2, 3);
        // graph.addEdge(3, 4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        graph.printGraph();
        graph.dfs(0, visited);
    }
}