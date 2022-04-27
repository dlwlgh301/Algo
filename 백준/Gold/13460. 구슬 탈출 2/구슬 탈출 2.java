import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class posit {
		int x, y;

		public posit(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int M, N;
	static char map[][];
	static boolean visit[][];
	static int answer = Integer.MAX_VALUE;
	static posit red, blue, goal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'R') {
					red = new posit(i, j);
				} else if (map[i][j] == 'B') {
					blue = new posit(i, j);
				} else if (map[i][j] == 'O') {
					goal = new posit(i, j);
				}
			}
		}
		solv(1, red, blue);
		if(answer!=Integer.MAX_VALUE) {
			System.out.println(answer);
		}else {
			System.out.println(-1);
		}
	}

	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	private static void solv(int depth, posit r, posit b) {
		if (depth > 10) return;
		if (depth > answer) return;
		
		for (int i = 0; i < 4; i++) {
			
			int rx = r.x, ry = r.y;
			int bx = b.x, by = b.y;
			while (true) { // 빨간공
				if (map[rx][ry] != '#' && map[rx][ry] != 'O') {
					rx += dx[i];
					ry += dy[i];
				} else {
					if (map[rx][ry] == '#') {
						rx -= dx[i];
						ry -= dy[i];
					}
					break;
				}
			}
			while (true) {// 파란공
				if (map[bx][by] != '#' && map[bx][by] != 'O') {
					bx += dx[i];
					by += dy[i];
				} else {
					if (map[bx][by] == '#') {
						bx -= dx[i];
						by -= dy[i];
					}
					break;
				}
			}
			if(rx==bx&&ry==by) {
				if(map[rx][ry]!='O') {
					int rcost = Math.abs(r.x-rx)+ Math.abs(r.y-ry);
					int bcost = Math.abs(b.x-bx)+Math.abs(b.y-by);
					
					if(rcost>bcost) {
						rx-=dx[i];
						ry-=dy[i];
					}else {
						bx-=dx[i];
						by-=dy[i];
					}
				}else if(map[rx][ry]=='O') {
					continue;
				}
			}
			if(map[bx][by]=='O') {
				continue;
			}
			if(map[rx][ry]=='O') {
				answer = Math.min(answer, depth);
				return;
			}
			posit rt = new posit(rx,ry);
			posit bt = new posit(bx,by);
			solv(depth+1,rt,bt);
		}
	}

}
