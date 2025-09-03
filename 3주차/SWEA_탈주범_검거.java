package algostudy;

import java.util.*;
import java.io.*;

public class Solution {

    static int[][][] d = {
        {{0}},
        {{1, 0}, {0, 1}, {-1, 0}, {0, -1}},
        {{1, 0}, {-1, 0}},
        {{0, 1}, {0, -1}},
        {{-1, 0}, {0, 1}},
        {{1, 0}, {0, 1}},
        {{1, 0}, {0, -1}},
        {{-1, 0}, {0, -1}}
    };

    static int N;
    static int M;
    static int R;
    static int C;
    static int L;
    static int[][] board;
    static int[][] dist;
    static boolean[][] connect;


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st;
            st = new StringTokenizer(bf.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            board = new int[N][M];
            dist = new int[N][M];

            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], -1);
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < M; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dist[R][C] = 1;

            Queue<Node> que = new LinkedList<>();
            que.offer(new Node(R, C));

            while (!que.isEmpty()) {
                Node cur = que.poll();

                int num = board[cur.x][cur.y];

                if (dist[cur.x][cur.y] == L) {
                    continue;
                }
                for (int i = 0; i < d[num].length; i++) {
                    int dx = d[num][i][0];
                    int dy = d[num][i][1];
                    int nx = cur.x + d[num][i][0];
                    int ny = cur.y + d[num][i][1];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                        continue;
                    }

                    if (dist[nx][ny] != -1 || board[nx][ny] == 0) {
                        continue;
                    }

                    int nextNode = board[nx][ny];
                    boolean flag = false;

                    for (int j = 0; j < d[nextNode].length; j++) {
                        int bx = d[nextNode][j][0];
                        int by = d[nextNode][j][1];

                        if (bx == -dx && by == -dy) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        que.offer(new Node(nx, ny));
                        dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    }
                }


            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (dist[i][j] != -1) {
                        cnt++;
                    }
                }
            }
            System.out.println("#" + (t + 1) + " " + cnt);

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
