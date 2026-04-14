import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long max = 0;
        for (int t : times) {
            max = Math.max(t, max);
        }
        
        long left = 1;
        long right = n * max;
        
        while (left <= right) {
            long mid = (left + right) / 2; //시간
            
            //mid 시간 동안 심사할 수 있는 인원 수
            long cnt = 0;
            for (int t : times) {
                cnt += mid / t;
            }
            
            if (cnt < n) { //시간 늘려야함
                left = mid + 1;
            } else { //줄여야함
                right = mid - 1;
            }
        }
        
        return left;
    }
}