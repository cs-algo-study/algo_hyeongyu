package algostudy;

import java.util.*;
import java.io.*;


public class Solution {

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static Node[][] board;
    static int[][] more;
    static int N;
    static int M;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {
            List<Node> group = new ArrayList<>();
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(bf.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int mi = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                group.add(new Node(x, y, mi, dir, mi));
            }

            for (int i = 0; i < M; i++) {
                Queue<Node> que = new LinkedList<>(group);
                group.clear();
                board = new Node[N][N];

                while (!que.isEmpty()) {
                    Node cur = que.poll();
                    int nDir = cur.dir;
                    int nMi = cur.mi;
                    int dir = cur.dir;
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    if (nx <= 0 || nx >= N - 1 || ny <= 0 || ny >= N - 1) {
                        if (cur.dir == 1) {
                            nDir = 2;
                        } else if (cur.dir == 2) {
                            nDir = 1;
                        } else if (cur.dir == 3) {
                            nDir = 4;
                        } else if (cur.dir == 4) {
                            nDir = 3;
                        }
                        nMi = cur.mi / 2;

                        if (nMi == 0) {
                            continue;
                        }
                    }

                    if (board[nx][ny] == null) {
                        board[nx][ny] = new Node(nx, ny, nMi, nDir, cur.mi);
                    } else {
                        Node ex = board[nx][ny];
                        ex.mi += nMi;

                        if (cur.mi > ex.maxMi) {
                            ex.dir = nDir;
                            ex.maxMi = cur.mi;
                        }
                    }
                }

                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (board[r][c] != null) {
                            group.add(board[r][c]);
                        }
                    }
                }
            }

            int total = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != null) {
                        total += board[i][j].mi;
                    }
                }
            }
            System.out.println("#" + (t+1) + " " + total);
        }

    }

    static class Node {

        int x;
        int y;
        int mi;
        int dir;
        int maxMi;

        public Node(int x, int y, int mi, int dir, int maxMi) {
            this.x = x;
            this.y = y;
            this.mi = mi;
            this.dir = dir;
            this.maxMi = maxMi;
        }


    }


}
