import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class TrieNode {
		Map<Character,TrieNode> childNodes = new HashMap<>();
		boolean isLastChar;
	}
	static class Trie{
		TrieNode rootNode = new TrieNode();
		
		void insert(String word) {
			
			TrieNode thisNode = rootNode;
			
			for(int i = 0 ; i<word.length(); i++) {
				char character = word.charAt(i);
				if(thisNode.childNodes.get(character) == null) {
					thisNode.childNodes.put(character, new TrieNode());
				}
				thisNode = thisNode.childNodes.get(character);
			}
			thisNode.isLastChar = true;
		}
		boolean contains(String word) {
			TrieNode thisNode = this.rootNode;
			
			for(int i = 0; i<word.length(); i++) {
				char character = word.charAt(i);
				TrieNode node = thisNode.childNodes.get(character);
				
				if(thisNode.isLastChar == true) return false;
				
				thisNode = node;
			}
			if(thisNode.childNodes.size()!= 0) return false;
			
			return true;
		}
	}
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		 
		for(int i = 0; i< testCase ; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			String arr[] = new String[n];
			Trie trie = new Trie();
			for(int j = 0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				arr[j] = st.nextToken();
				trie.insert(arr[j]);
			}
			boolean flag = true;
			for(int j = 0; j<n; j++) {
				if(!trie.contains(arr[j])) {
					flag = false;
					break;
				}
			}
			if(!flag) {
				System.out.println("NO");
			}else {
				System.out.println("YES");
			}
		}

	}

}
