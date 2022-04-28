import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;

	static class fire {
		int x;
		int y;
		int m; // 질량
		int s; // 속도
		int d; // 방향

		public fire(int x, int y, int m, int s, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static ArrayList<fire> map[][];
	static ArrayList<fire> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayList[N][N];
		
		for(int i =0 ; i<N; i++) {
			for(int j = 0; j<N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			list.add(new fire(x,y,m,s,d));
		}
		
		for(int i = 0; i<K; i++) {
			move();
			check();
		}
		
		int ans = 0;
		for(fire result : list) {
			ans += result.m;
		}
		System.out.println(ans);
	}
	private static void check() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				int size = map[i][j].size();
				if(size == 1 ) map[i][j].clear();
				if(size >= 2 ) {
					int m_sum = 0;
					int s_sum = 0;
					int odd = 0;
					int even = 0;
					for(fire f : map[i][j]) {
						m_sum += f.m;
						s_sum += f.s;
						if(f.d%2==1) {
							odd++;
						}else {
							even++;
						}
						
						list.remove(f);
					}
					int rm = m_sum / 5;
					int rs = s_sum / size;
					map[i][j].clear();
					if(rm ==0) continue;
					if((odd == 0 && even>0 )|| ( odd>0 && even==0 )) {
						list.add(new fire(i,j,rm,rs,0));
						list.add(new fire(i,j,rm,rs,2));
						list.add(new fire(i,j,rm,rs,4));
						list.add(new fire(i,j,rm,rs,6));
					}else {
						list.add(new fire(i,j,rm,rs,1));
						list.add(new fire(i,j,rm,rs,3));
						list.add(new fire(i,j,rm,rs,5));
						list.add(new fire(i,j,rm,rs,7));
					}
					
				}
			}
		}
		
	}
	private static void move() {
		for(fire temp : list) {
			int tx = (temp.x + dx[temp.d]*temp.s) % N;
			int ty = (temp.y + dy[temp.d]*temp.s) % N;
			
			if(tx<0) tx += N;
			if(ty<0) ty += N;
			
			temp.x = tx;
			temp.y = ty;
			
			map[tx][ty].add(temp); 
		}
		
	}

}
