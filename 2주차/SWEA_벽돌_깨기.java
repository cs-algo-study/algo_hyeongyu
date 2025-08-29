package algostudy;

import java.util.*;
import java.io.*;


public class Solution {

    static int[] temp;
    static int[][] board;
    static int N;
    static int W;
    static int H;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {

            StringTokenizer st;
            result = Integer.MAX_VALUE;
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            temp = new int[N];
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            board = new int[H][W];
            visited = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            func(0);
            System.out.println("#"+(t+1) + " " + result);
        }
    }

    private static void func(int cur) {
        if (cur == N) {
            int[] idx = new int[N];

            for (int i = 0; i < N; i++) {
                idx[i] = temp[i];
            }
            int[][] copy = exploreWall(idx);

            int cnt = 0;

            for (int row = 0; row < H; row++) {
                for (int col = 0; col < W; col++) {
                    if (copy[row][col] > 0) {
                        cnt++;
                    }
                }
            }
            result = Math.min(cnt, result);

            return;
        }

        for (int i = 0; i < W; i++) {
            temp[cur] = i;
            func(cur + 1);
        }
    }

    private static int[][] stackWall(int[][] currentBoard) {
        int[][] newBoard = new int[H][W];

        for (int col = 0; col < W; col++) {

            int bottomRowIdx = H - 1;

            for (int row = H - 1; row >= 0; row--) {

                if (currentBoard[row][col] > 0) {
                    newBoard[bottomRowIdx][col] = currentBoard[row][col];
                    bottomRowIdx--;
                }
            }
        }
        return newBoard;
    }

    private static int[][] exploreWall(int[] idx) {

        int[][] copiedBoard = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copiedBoard[i][j] = board[i][j];
            }
        }


        for (int i = 0; i < idx.length; i++) {
            int curY = idx[i];
            for (int j = 0; j < H; j++) {
                int curX = j;

                if (copiedBoard[curX][curY] == 0) {
                    continue;
                }
                else if (copiedBoard[curX][curY] >= 1) {

                    Deque<Node> que = new LinkedList<>();

                    que.offer(new Node(curX, curY, copiedBoard[curX][curY]));

                    while (!que.isEmpty()) {
                        Node cur = que.poll();
                        copiedBoard[cur.x][cur.y] = 0;

                        int range = cur.range;
                        for (int k = 0; k < 4; k++) {

                            for (int step = 1; step < range; step++) {
                                int nx = cur.x + dx[k] * step;
                                int ny = cur.y + dy[k] * step;

                                if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
                                    continue;
                                }

                                if (copiedBoard[nx][ny] > 0) {
                                    que.offer(new Node(nx, ny, copiedBoard[nx][ny]));
                                    copiedBoard[nx][ny] = 0;
                                }
                            }
                        }
                    }
                    break;
                }

            }
            copiedBoard = stackWall(copiedBoard);
        }
        return copiedBoard;
    }


    static class Node {

        int x;
        int y;
        int range;

        public Node(int x, int y, int range) {
            this.x = x;
            this.y = y;
            this.range = range;
        }
    }
}
