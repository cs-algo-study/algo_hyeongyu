package algostudy;

import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int start;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    static int d;
    static int value;


    public static void main(String[] args) throws IOException {
        Solution process = new Solution();
        for (int i = 1; i <= 10; i++) {
            process.run();
            System.out.println("#" + i + " "+ value);
        }
    }

    private void run() throws IOException{
        d = 0;
        value = 0;
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        visited = new boolean[101];

        graph = new ArrayList<>(101);
        for (int i = 0; i <=100; i++) {
            graph.add(new ArrayList<>());
        }
        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N/2; i++) {
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph.get(parent).add(child);
        }

        bfs();

    }

    private void bfs() {
        Queue<Node> que = new LinkedList<>();

        que.offer(new Node(start,0));
        visited[start] = true;
        while (!que.isEmpty()) {
            Node cur = que.poll();

            if (cur.depth > d) {
                d = cur.depth;
                value = cur.x;
            }else if (cur.depth == d){
                value = Math.max(value,cur.x);
            }
            for (int nxt : graph.get(cur.x)) {

                if(!visited[nxt]) {
                    visited[nxt] = true;
                    que.offer(new Node(nxt,cur.depth + 1));
                }
            }
        }

    }


    static class Node{

        int x;
        int depth;

        public Node(int x, int depth) {
            this.x = x;
            this.depth = depth;
        }
    }

}
