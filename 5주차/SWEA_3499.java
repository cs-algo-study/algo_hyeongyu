package algostudy;

import java.util.*;
import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {

            int N = Integer.parseInt(bf.readLine());

            int[] arr = new int[N];
            List<String> input = new ArrayList<>();
            List<Integer> turn = new ArrayList<>();
            Queue<Integer> q1 = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();

            int mid = N / 2;

            if (N % 2 != 0) {
                mid++;
            }

            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {

                input.add(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                arr[i] = i;
            }

            for (int i = 0; i < mid; i++) {
                q1.add(arr[i]);
            }

            for (int i = mid; i < N; i++) {
                q2.add(arr[i]);
            }
            List<String> res = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0) {
                    res.add(input.get(q1.poll()));
                } else {
                    res.add(input.get(q2.poll()));
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String re : res) {
                sb.append(re).append(" ");
            }
            System.out.println("#" + (t + 1) + " " + sb.toString());

        }

    }
}
