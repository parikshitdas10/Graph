
import java.util.*;

public class GraphQ7 {
    int N;
    int matrix[][];// grid
    boolean vis[][];// visited array
    int dis[][];// distance array

    class Pair {// for storing pair of coordinates
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public GraphQ7(int N) {
        this.N = N;
        matrix = new int[N][N];
    }

    public boolean isValid(int corrx, int corry) {// checks if the move is going out of bounds
        if (corrx > N || corry > N)
            return false;
        if (corrx < 1 || corry < 1)
            return false;

        return true;
    }

    public void bfs(int srcx, int srcy, int targetx, int targety) {
        int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
        int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };// possible move combination of knight
        Queue<Pair> q = new LinkedList<>();
        vis = new boolean[N + 1][N + 1];
        dis = new int[N + 1][N + 1];
        q.add(new Pair(srcx, srcy));
        dis[srcx][srcy] = 1;
        while (!q.isEmpty()) {
            int currx = q.element().x;
            int curry = q.element().y;
            q.poll();
            vis[currx][curry] = true;
            for (int i = 0; i < 8; i++) {

                if (isValid(currx + dx[i], curry + dy[i]) && !vis[currx + dx[i]][curry + dy[i]]) {
                    int newx = currx + dx[i];
                    int newy = curry + dy[i];
                    vis[newx][newy] = true;
                    q.add(new Pair(newx, newy));
                    dis[newx][newy] = dis[currx][curry] + 1;
                    if (newx == targetx && newy == targety) {
                        return;
                    }
                }

            }

        }

    }

    public void printDistance(int targetx, int targety) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(dis[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("Min distance of target is " + dis[targetx][targety] + " moves");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of rows for square 2D grid");
        int N = sc.nextInt();

        GraphQ7 graph = new GraphQ7(N);// follow 1 indexing
        System.out.println("Enter Starting position co-ordinates");
        int srcx = sc.nextInt();
        int srcy = sc.nextInt();
        System.out.println("Enter ending position co-ordinates");
        int targetx = sc.nextInt();
        int targety = sc.nextInt();
        graph.bfs(srcx, srcy, targetx, targety);
        graph.printDistance(targetx, targety);

    }
}
