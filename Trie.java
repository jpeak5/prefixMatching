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
		ArrayList <Node> children = new ArrayList<Node>();

		public Node() {

		}

		public Node(char letter, Node parent) {
			this.parent = parent;
			this.letter = letter;
			this.key = (int) this.letter;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		String[] words = { "abs", "bucket", "buck", "apple", "ape", "al",
				"charlie" };

		for (String s : words) {
			trie.insert(s, trie.root);
			System.out.print(trie.preorder(trie.root, new StringBuffer()));
			System.out.println("\n------------");

		}

	}

	// preorder walk
	public StringBuffer preorder(Node node, StringBuffer sb) {

		if (node.children.size() > 0) {
			for (Node n : node.children) {
				sb.append(n.letter);
				preorder(n, sb);
			}
		} else {
			// sb.append(node.letter);
			sb.append(new String("-|>"));
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
		if (found == true) {// continue to try the insertion on the found
			// node
			if (s.length() > 1) {
				String newS = s.substring(1);
				insert(newS, node.children.get(i));
			}
		} else {// create a new node
			Node n = new Node(cti, node);
			node.children.add(i,n);
			if (s.length() > 1) {
				String newS = s.substring(1);
				insert(newS, n);
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
