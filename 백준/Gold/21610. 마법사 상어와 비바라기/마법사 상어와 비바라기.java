import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, d, s;
	static int map[][];
	static boolean visit[][];
	static Queue<cloud> q;

	static class cloud {
		int x;
		int y;

		public cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		q = new LinkedList<>();

		q.add(new cloud(N - 1, 0));
		q.add(new cloud(N - 1, 1));
		q.add(new cloud(N - 2, 0));
		q.add(new cloud(N - 2, 1));
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			move();
			rain();
			create();
		}
		int sum = 0;
		
		for(int i =0 ; i<N; i++) {
			for(int j = 0; j<N; j++) {
				sum+=map[i][j];
			}
		}
		System.out.println(sum);
	}

	private static void create() {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j]>=2) {
					if(visit[i][j]) {
						visit[i][j] = false;
						continue;
					}
					map[i][j] = map[i][j] - 2;
					q.add(new cloud(i,j));
				}
			}
		}
	}

	private static void rain() {
		int xx[] = { -1, -1, 1, 1 };
		int yy[] = { -1, 1, -1, 1 };

		int size = q.size();
		for (int i = 0; i < size; i++) {
			cloud temp = q.poll();
			
			for(int dir = 0; dir<4; dir++) {
				int tx = temp.x+xx[dir];
				int ty = temp.y+yy[dir];
				
				if(tx<0||ty<0||tx>=N||ty>=N) continue;
				if(map[tx][ty]>0) {
					map[temp.x][temp.y]+=1;
				}
			}
		}
	}

	static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	private static void move() {
		int size = q.size();

		for (int i = 0; i < size; i++) {
			cloud temp = q.poll();
			int tx = (temp.x + dx[d] * s) % N;
			int ty = (temp.y + dy[d] * s) % N;
			
			if(tx<0) tx = tx + N;
			if(ty<0) ty = ty + N;
			map[tx][ty] += 1;
			visit[tx][ty] = true;
			q.add(new cloud(tx, ty));
		}
	}

}
