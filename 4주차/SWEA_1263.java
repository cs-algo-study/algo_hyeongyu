import java.util.*;
import java.io.*;

class Solution {

    public static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(bf.readLine());

            int N = Integer.parseInt(st.nextToken());

            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());

                    if (num == 1) {
                        dist[i][j] = 1;
                    } else if (i == j) {
                        dist[i][j] = 0;
                    } else {
                        dist[i][j] = INF;
                    }
                }
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (dist[i][k] != INF && dist[k][j] != INF) {
                            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        }
                    }
                }
            }

            int res = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                int total = 0;
                for (int j = 0; j < N; j++) {
                    total += dist[i][j];
                }
                res = Math.min(total, res);
            }

            System.out.println("#" + (t + 1) + " " + res);
        }
    }
}