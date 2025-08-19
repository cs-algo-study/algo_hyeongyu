import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        Deque<String> lru = new LinkedList<>();

        if(cacheSize == 0){
            return 5 * cities.length;
        }


        for(String city : cities){

            String c = city.toLowerCase();

            if(lru.contains(c)){
                answer += 1;
                lru.remove(c);
                lru.offerLast(c);
            }

            else{

                answer += 5;
                if(lru.size() >= cacheSize){
                    lru.pollFirst();
                }
                lru.offerLast(c);
            }
        }
        return answer;
    }
}