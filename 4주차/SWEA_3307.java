package algostudy;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {

            int N = Integer.parseInt(bf.readLine());

            int[] arr = new int[N];
            int[] dp = new int[N];

            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.fill(dp, 1);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] > arr[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            int maxValue= 0;
            for (int d : dp) {
                if (d > maxValue) {
                    maxValue = d;
                }
            }

            System.out.println("#" + (t+1) + " " + maxValue);
        }
    }
}