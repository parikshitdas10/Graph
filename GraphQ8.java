import java.util.*;

public class GraphQ8 {
    static class Pair {
        int x, y;

        Pair(int x, int y) {// pair class for storing co ordinates
            this.x = x;
            this.y = y;
        }
    }

    static boolean isValid(int x, int y, int[][] image, int currColor) {// checks if traversal is valid
        if (x > image.length - 1 || y > image[0].length - 1) {// checks out of bounds condition
            return false;
        } else if (x < 0 || y < 0) {
            return false;
        } else if (image[x][y] != currColor) {// checks if new pixel is the same type
            return false;
        }
        return true;
    }

    static void floodFill(int[][] image, int sr, int sc, int newColor) {// basic BFS algorithm applied
        boolean[][] visited = new boolean[image.length][image[0].length];// boolean array to mark visited elements
        int[] dx = { -1, 0, 1, 0 };// possible traversals in 4 directions
        int[] dy = { 0, 1, 0, -1 };
        // x is for vertical and y for horizontal traversals
        int currColor = image[sr][sc];// storing the color so that other pixels with same color can be changed
        Queue<Pair> q = new LinkedList<>();// queue for bfs
        q.add(new Pair(sr, sc));
        image[sr][sc] = newColor;
        while (!q.isEmpty()) {
            int currx = q.element().x;
            int curry = q.element().y;
            q.poll();
            visited[currx][curry] = true;
            for (int i = 0; i < 4; i++) {
                if (isValid(currx + dx[i], curry + dy[i], image, currColor) && !visited[currx + dx[i]][curry + dy[i]]) {

                    int newx = currx + dx[i];
                    int newy = curry + dy[i];
                    visited[newx][newy] = true;
                    q.add(new Pair(newx, newy));
                    image[newx][newy] = newColor;

                }
            }
        }
        printImage(image);

    }

    static void printImage(int[][] image) {// to print the Graph
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    static void getImage(int[][] image, Scanner scan) {// to populate the graph
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                image[i][j] = scan.nextInt();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of rows and columns");
        int r = scan.nextInt();
        int c = scan.nextInt();
        int[][] image = new int[r][c];
        System.out.println("Fill image");
        getImage(image, scan);// saturates the graph
        System.out.println("Enter sr and sc");
        int sr = scan.nextInt();// starting co ordinates
        int sc = scan.nextInt();
        System.out.println("enter new color");
        int newColor = scan.nextInt();// new color to be filled
        floodFill(image, sr, sc, newColor);

    }
}
