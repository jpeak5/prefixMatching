import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Trie implements StringStorage {

	Node root = null;
	int insertionCount = 0;

	public Trie() {
		this.root = new Node();
	}

	class Node {
		int key;
		char letter;
		Node parent;
		ArrayList<Node> children = new ArrayList<Node>();

		public Node() {

		}

		public Node(char letter, Node parent) {
			this.parent = parent;
			this.letter = letter;
			this.key = (int) this.letter;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		File file = new File(args[0]);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String s;
		double start = System.nanoTime();
		int count = 0;
		while((s=br.readLine())!=null){
			trie.insert(s, trie.root);
			count++;
		}
		double elapsed = (System.nanoTime() - start);
		System.out.print("elpsed time is "+ elapsed+" nanoseconds for insertion and sorting of "+count+" words");
		System.out.print(trie.preorder(trie.root, new StringBuffer()));

	}
	
	// preorder walk
	public StringBuffer preorder(Node node, StringBuffer sb) {
		if (node.children.size() > 0) {
			for (Node n : node.children) {
				preorder(n, sb);
			}
		} else {
			StringBuffer word = new StringBuffer();
			while(node.parent!=null){
				word.append(node.letter);
				node = node.parent;
			}
			sb.append(word.reverse()+"\n");
		}
		return sb;
	}
	

	public void insert(String s, Node node) {
		boolean found = false;
		int i = 0;
		char cti = s.charAt(0);

		while (node.children.size() > i && node.children.get(i).letter <= cti) {
			if (cti == node.children.get(i).letter) {
				found = true;
				break;
			}
			i++;
		}

		String newS = s.substring(1);
		if (found == true) {// continue to try the insertion on the found
			// node
			if (s.length() > 1) {
				insert(newS, node.children.get(i));
			}
		} else {// create a new node
			Node n = new Node(cti, node);
			node.children.add(i, n);
//			System.out.println("insert("+n.letter+") at n.children["+i+"] for node.letter = \'"+node.letter+"\'");
			if (s.length() > 1) {
				insert(newS, n);
			}else{
				newS = ne
			}
		}

	}

	public int find(char c) {
		int i = 0;
		return i;
	}

	@Override
	public List<String> search(String p) {
		// TODO Auto-generated method stub
		return null;
	}

}
