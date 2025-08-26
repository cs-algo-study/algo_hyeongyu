package algostudy;

import java.util.*;
import java.io.*;


public class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(bf.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(bf.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph.get(u).add(v);
                graph.get(v).add(u);
            }


            boolean[] visited = new boolean[N + 1];
            int res = 0;
            for (int i = 1; i <=N; i++) {
                Queue<Integer> que = new LinkedList<>();
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                que.offer(i);

                while (!que.isEmpty()) {
                    int cur = que.poll();

                    for (int nxt : graph.get(cur)) {
                        if (visited[nxt]) {
                            continue;
                        }
                        visited[nxt] = true;
                        que.offer(nxt);

                    }
                }
                res++;

            }
            System.out.println("#" + t +" "+res);

        }


    }
}
