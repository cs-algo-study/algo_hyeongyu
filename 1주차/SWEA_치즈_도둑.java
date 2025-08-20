package algostudy;

import java.util.*;
import java.io.*;

public class Solution {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static BufferedReader bf;
    static int standard = 1;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        Solution process = new Solution();
        bf = new BufferedReader(new InputStreamReader(System.in));
        int ts = Integer.parseInt(bf.readLine());

        for (int t = 1; t <= ts; t++) {
            standard = 1;
            process.run();

            System.out.println("#" + t +" " + standard);
        }
    }

    private void run() throws IOException {
        StringTokenizer st;

        int n = Integer.parseInt(bf.readLine());

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        makeBoard(board, n);


    }


    private void makeBoard(int[][] board, int n) {
        for (int k = 0; k <= 100; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == k) {
                        board[i][j] -= k;
                    }
                }
            }

            int cnt = 0;
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0 || visited[i][j]) {
                        continue;
                    }
                    bfs(board, n, i, j);
                    cnt++;
                }
            }
            standard = Math.max(cnt, standard);
        }

    }


    private void bfs(int[][] board, int n, int x, int y) {

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x, y));
        while (!que.isEmpty()) {
            Node cur = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (board[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }

                que.offer(new Node(nx, ny));
                visited[nx][ny] = true;
            }


        }
    }

    static class Node {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}