package algostudy;

import java.util.*;
import java.io.*;

public class Solution {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] dp = new int[K + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int V = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                for (int j = K; j >= V; j--) {
                    dp[j] = Math.max(dp[j], dp[j - V] + C);
                }
            }

            System.out.println("#" + (t + 1) + " " + dp[K]);
        }
    }
}