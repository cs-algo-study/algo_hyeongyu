import java.util.*;

class Solution {
    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        //twopointer로 접근?
        long total = 0;
        long q1Total = 0;
        long q2Total = 0;

        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();

        for(int i = 0; i<queue1.length ; i++){
            total += queue1[i] + queue2[i];
            q1Total += queue1[i];
            q2Total += queue2[i];
            que1.offer(queue1[i]);
            que2.offer(queue2[i]);
        }

        if(total % 2 != 0){
            return -1;
        }

        int cnt = 0;
        while(true){

            if(cnt > 3*queue1.length){
                return -1;
            }

            if(q1Total > q2Total){
                int first = que1.poll();
                q1Total -= first;
                q2Total += first;
                que2.offer(first);
            }
            else if(q1Total < q2Total){
                int first = que2.poll();
                q2Total -= first;
                q1Total += first;
                que1.offer(first);
            }
            else if(q1Total == q2Total){
                return answer;
            }

            answer++;
            cnt++;
        }
    }


}