package algostudy;

import java.io.*;
import java.util.*;

public class Main {

    static final int[] dx = {0, 0, 1, 0, -1};
    static final int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) throws IOException{

        Main process = new Main();
        process.run();
    }


    private void run() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st;

            st = new StringTokenizer(bf.readLine());
            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            int[] moveA = new int[M];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }
            int[] moveB = new int[M];
            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            int[] bcX = new int[A];
            int[] bcY = new int[A];
            int[] bcC = new int[A];
            int[] bcP = new int[A];

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(bf.readLine());
                bcX[i] = Integer.parseInt(st.nextToken());
                bcY[i] = Integer.parseInt(st.nextToken());
                bcC[i] = Integer.parseInt(st.nextToken());
                bcP[i] = Integer.parseInt(st.nextToken());
            }

            boolean[][][] cover = new boolean[11][11][A];
            for (int y = 1; y <= 10; y++) {
                for (int x = 1; x <= 10; x++) {
                    for (int i = 0; i < A; i++) {
                        if (calculateDistance(x, y, bcX[i], bcY[i]) <= bcC[i]) {
                            cover[y][x][i] = true;
                        }
                    }
                }
            }

            int ax = 1, ay = 1;
            int bx = 10, by = 10;

            int total = 0;

            for (int t = 0; t <= M; t++) {
                List<Integer> aAvail = new ArrayList<>();
                List<Integer> bAvail = new ArrayList<>();
                for (int i = 0; i < A; i++) {
                    if (cover[ay][ax][i]) {
                        aAvail.add(i);
                    }
                    if (cover[by][bx][i]) {
                        bAvail.add(i);
                    }
                }

                int best = 0;

                for (int a : aAvail) {
                    if (bcP[a] > best) {
                        best = bcP[a];
                    }
                }

                for (int b : bAvail) {
                    if (bcP[b] > best) {
                        best = bcP[b];
                    }
                }

                for (int a : aAvail) {
                    for (int b : bAvail) {
                        int gain;
                        if (a == b) {
                            gain = bcP[a];
                        } else {
                            gain = bcP[a] + bcP[b];
                        }
                        if (gain > best) {
                            best = gain;
                        }
                    }
                }

                total += best;

                if (t < M) {
                    int da = moveA[t];
                    int db = moveB[t];
                    ax += dx[da];
                    ay += dy[da];
                    bx += dx[db];
                    by += dy[db];
                }
            }

            System.out.print("#" + tc + " " + total);
            System.out.println();

        }

    }


    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
