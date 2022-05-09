import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {// 상자넣기 LIS문제

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		int result[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int index = 0;
		result[0] = arr[0];
		for(int i = 1; i<N; i++) {
			if(result[index]<arr[i]) {
				result[++index]=arr[i];
			}else {
				int search = BinarySearch(result,index,arr[i]);
				result[search] = arr[i];
			}
		}
		System.out.println(index+1);
	}

	private static int BinarySearch(int[] result, int end, int n) {
		int start = 0;
		while(start<end) {
			int mid = (start+end)/2;
			if(result[mid]>=n) {
				end = mid;
			}else {
				start = mid+1;
			}
		}
		return end;
	}

}
