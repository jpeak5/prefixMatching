import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class TrieArray {

	Node root = null;
	int insertionCount = 0;

	public TrieArray() {
		this.root = new Node();
	}

	class Node {
		int key; 		// 4 bytes
		char letter; 	// 2 bytes
		Node parent;	// 4 bytes (reference field)
		Node[] children = new Node[27];// 12 bytes for the array

		public Node() {

		}

		public Node(Node leaf) {
			this.parent = leaf;

			this.key = -1;
		}

		public Node(char letter, Node parent) {
			this.parent = parent;
			this.letter = letter;
			this.key = (int) this.letter;
		}
	}

	// preorder walk
	public StringBuffer preorder(Node node, StringBuffer sb) {
		if (node.children.size() > 0) {
			for (Node n : node.children) {
				preorder(n, sb);
			}
		} else {
			StringBuffer word = new StringBuffer();
			while (node.parent != null) {
				word.append(node.letter);
				node = node.parent;
			}
			sb.append(word.reverse() + "\n");
		}
		return sb;
	}

	public void insert(String s, Node node) {
		char c = s.charAt(0);
		ArrayList<Node> children = node.children; // convenience var

		boolean found = false;
		int i = 0;
		// first, see if the current node
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
			} else {
				Node leaf = new Node(node);
				node.children.add(0, leaf);
			}
		} else {// create a new node
			Node n = new Node(c, node);
			children.add(i, n);
			// System.out.println("insert("+n.letter+") at n.children["+i+"] for node.letter = \'"+node.letter+"\'");
			if (s.length() > 1) {
				insert(newStr, n);
			} else {
				Node leaf = new Node(n);
				n.children.add(0, leaf);
			}
		}

	}

	public ArrayList<String> searchTraversal(Node node, String p,
			ArrayList<String> list) {
		if (node.children.size() > 0) {
			for (Node n : node.children) {
				searchTraversal(n, p, list);
			}
		} else {
			StringBuffer word = new StringBuffer();
			while (node.parent != null) {
				word.append(node.letter);
				node = node.parent;
			}
			list.add(word.reverse().toString());
		}
		return list;
	}

	
	public ArrayList<String> search(String p) {
		// TODO Auto-generated method stub
		Node node = root;
		while (p.length() > 0) {
			char c = p.charAt(0);
			for (Node n : node.children) {
				if (n.letter == c) {
					p = p.substring(1);
					node = n;
				}
			}if(node == root){
				return null;
			}
		}

		ArrayList<String> list = searchTraversal(node, p, new ArrayList<String>());
		return list;
	}

}
