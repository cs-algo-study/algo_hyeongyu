package algostudy;

import java.util.*;
import java.io.*;

public class Solution {

    static char[][] board;
    static Set<String> resultSet;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());

        for (int t = 1; t <= T; t++) {

            board = new char[4][4];
            resultSet = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < 4; j++) {
                    board[i][j] = st.nextToken().charAt(0); // 0~9
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    func(i, j, 1, String.valueOf(board[i][j]));
                }
            }

            System.out.println("#" + t + " " + resultSet.size());
        }
    }


    private static void func(int x, int y, int cur, String currentNum) {
        if (cur == 7) {
            resultSet.add(currentNum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                func(nx, ny, cur + 1, currentNum + board[nx][ny]);
            }
        }
    }
}
