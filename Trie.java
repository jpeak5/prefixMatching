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
		
		public Node(Node leaf){
			this.parent = leaf;
			
			this.key=-1;
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
		System.out.print("elpsed time is "+ elapsed+" nanoseconds for insertion and sorting of "+count+" words\n");
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
		char c = s.charAt(0);
		ArrayList <Node> children = node.children; //convenience var

		boolean found = false;
		int i = 0;
		//first, see if the current node
		while (children.size() > i && children.get(i).letter <= c) {
			if (c == children.get(i).letter) {
				found = true;
				break;
			}
			i++;
		}

		String newStr = s.substring(1);
		if (found == true) {// continue to try the insertion on the found
			// node
			if (s.length() > 1) {
				insert(newStr, node.children.get(i));
			}else{
				Node leaf = new Node(node);
				node.children.add(0,leaf);
			}
		} else {// create a new node
			Node n = new Node(c, node);
			children.add(i, n);
//			System.out.println("insert("+n.letter+") at n.children["+i+"] for node.letter = \'"+node.letter+"\'");
			if (s.length() > 1) {
				insert(newStr, n);
			}else{
				Node leaf = new Node(n);
				n.children.add(0,leaf);
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
