import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class  Main {

	static int map[][];
	static int N, sx,sy;

	static int wx[][] = { 
			{ 0, -1, 1, -1, 1, -2, 2, -1, 1 }, // 동
			{ 2, 1, 1, 0, 0, 0, 0, -1, -1, }, // 남
			{ 0, 1, -1, 1, -1, 2, -2, 1, -1 }, // 서
			{ -2, -1, -1, 0, 0, 0, 0, 1, 1 } // 북
	};
	static int wy[][] = { 
			{ -2, -1, -1, 0, 0, 0, 0, 1, 1 }, // 동
			{ 0, -1, 1, -1, 1, -2, 2, -1, 1 }, // 남
			{ 2, 1, 1, 0, 0, 0, 0, -1, -1 }, // 서
			{ 0, 1, -1, 1, -1, 2, -2, 1, -1 }, // 북
	};
	static int ratio[] = { 5, 10, 10, 7, 7, 2, 2, 1, 1 };
	
	static int dx[] = {0, 1, 0,-1};
	static int dy[] = {-1, 0, 1, 0,};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		sx = N/2; 
		sy = N/2;
		
		int dir = 0;
		int ans = 0;
		int conor = 0;
		int size = 1;
		root : while(true) {
			for(int i = 0; i<size; i++) {
				if(sx ==0 && sy ==0) break root;
				sx += dx[dir];
				sy += dy[dir];
				int sand = map[sx][sy];
				map[sx][sy] = 0;
				int tsand = 0;
				for(int wind = 0; wind < 9; wind++) {
					int tx = sx+wx[dir][wind];
					int ty = sy+wy[dir][wind];
					int spread =  (sand * ratio[wind])/100;
					if(tx<0||tx>=N||ty<0||ty>=N) {
						ans+=spread;
					}else {
						map[tx][ty]+=spread;
					}
					tsand += spread;
				}
				int ax = sx+dx[dir];
				int ay = sy+dy[dir];
				if(ax<0||ax>=N||ay<0||ay>=N){
					ans+= sand-tsand;
				}else {
					map[ax][ay] += sand-tsand;
				}
			}
			
			
			dir = (dir+1)%4;
			conor++;
			if(conor==2) {
				conor = 0;
				size++;
			}
			
		}
		System.out.println(ans);
	}

}
