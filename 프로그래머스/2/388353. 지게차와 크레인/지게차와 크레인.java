import java.util.*;
class Solution {
    static final char EMPTY = '-';
    static int n;
    static int m;
    static char[][] containers;
    static boolean[][] visit;
    public int solution(String[] storage, String[] requests) {
        n = storage.length; //행
        m = storage[0].length(); //열
        
        //containers로 변환
        containers = new char[n+2][m+2];
        for (int i = 0; i < n+2; i++) {
             Arrays.fill(containers[i], EMPTY); 
        }
        
        int x = 1;
        for (int i = 0; i < n; i++) {
            int y = 1;
            for (int j = 0; j < m; j++) {
                containers[x][y++] = storage[i].charAt(j);
            }
            x++;
        }
        
        for (String req : requests) {
            if (req.length() == 2) { //크레인
                crane(req.charAt(0));
            } else { //지게차
                visit = new boolean[n+2][m+2];
                forklift(req.charAt(0));
            }
        }
        
        //정답 세기
        int answer = 0;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (containers[i][j] != EMPTY) {
                    answer++;
                }
            }
        }       
        return answer;
    }
    
    private void crane(char alpha) { //모든 알파벳 제거
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (containers[i][j] == alpha) {
                    containers[i][j] = EMPTY;
                }
            }
        }
    }
    
    private void forklift(char alpha) { //접근 가능한 알파벳 제거
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        DFS(0, 0, alpha, dx, dy);
    }  
    
    private void DFS(int x, int y, char alpha, int[] dx, int[] dy) { 
        visit[x][y] = true;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= n+2 || ny < 0 || ny >= m+2 || visit[nx][ny]) {
                continue;
            }
            
            if (containers[nx][ny] == EMPTY) { //EMPTY일 경우 전진
                DFS(nx, ny, alpha, dx, dy);
            }
            
            if (containers[nx][ny] == alpha) { //제거해야하는 알파벳일 경우
                visit[nx][ny] = true;
                containers[nx][ny] = EMPTY;
            }
        }
    }
}