package algostudy;

import java.util.*;
import java.io.*;

public class Solution {

    static int[] arr;
    static int[] temp;
    static boolean[] visited;
    static int n;
    static int answer;
    static List<Node> sale;


    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());

        for (int t = 0; t < T; t++) {

            answer = Integer.MAX_VALUE;
            n = Integer.parseInt(bf.readLine());
            sale = new ArrayList<>();
            arr = new int[n];
            temp = new int[n];
            visited = new boolean[n];
            st = new StringTokenizer(bf.readLine());

            int stx = Integer.parseInt(st.nextToken());
            int sty = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            sale.add(new Node(stx, sty));
            sale.add(new Node(ex, ey));

            for (int i = 0; i < n; i++) {
                arr[i] = i;
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                sale.add(new Node(x, y));
            }


            func(0);

            System.out.println("#" + (t+1)+ " " + answer);
        }
    }

    private static void func(int cur) {
        if (cur == n) {
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = arr[temp[i]];

            }
            int dist = makeDistance(result);
            answer = Math.min(answer, dist);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[cur] = i;
                func(cur + 1);
                visited[i] = false;
            }
        }
    }

    private static int makeDistance(int[] result) {

        int total = 0;
        int stx = sale.get(0).x;
        int sty = sale.get(0).y;
        int endX = sale.get(1).x;
        int endY = sale.get(1).y;

        for (int i = 0; i < n; i++) {
            int nx = sale.get(result[i]+2).x;
            int ny = sale.get(result[i]+2).y;

            total += Math.abs(stx - nx) + Math.abs(sty - ny);
            stx = nx;
            sty = ny;
        }

        total += Math.abs(stx - endX) + Math.abs(sty - endY);

        return total;
    }

    static class Node{

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
