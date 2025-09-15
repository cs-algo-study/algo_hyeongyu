import java.util.*;
import java.io.*;


public class Main {

    static boolean[] visited;
    static int N;
    static long ans;
    static List<Node> snake;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        for (int t = 0; t < T; t++) {

            N = Integer.parseInt(bf.readLine());
            ans = Long.MAX_VALUE;
            StringTokenizer st;

            snake = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                snake.add(new Node(a, b));
            }
            visited = new boolean[N];

            func(0, 0);
            System.out.println("#" + (t + 1) + " " + ans);
        }
    }

    private static void func(int cur, int st) {
        if (cur == N / 2) {
            long x = 0;
            long y = 0;

            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    x += snake.get(i).x;
                    y += snake.get(i).y;
                } else {
                    x -= snake.get(i).x;
                    y -= snake.get(i).y;
                }
            }

            ans = Math.min(ans, Math.abs(x * x + y * y));

            return;
        }

        for (int i = st; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                func(cur + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static class Node {

        long x;
        long y;

        public Node(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

}


