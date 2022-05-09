import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N, work[][];
	static boolean visit[];
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		work = new int[2][N];
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			int T = sc.nextInt();
			int P = sc.nextInt();
			work[0][i] = T;
			work[1][i] = P;
		}
		solv(0,0);
		System.out.println(answer);
	}

	private static void solv(int depth,int money) {

		if (depth >= N) {
			answer = Math.max(money, answer);
			return;
		}
		visit[depth] = true;
		if (depth + work[0][depth] <= N) {
			solv(depth + work[0][depth],money+work[1][depth]);
		}
		visit[depth] = false;
		solv(depth + 1,money);
	}

}
