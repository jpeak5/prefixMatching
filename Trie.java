import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Trie implements StringStorage {

	Node root = null;

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

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("apple");
		trie.insert("ape");
		trie.insert("a");
		trie.insert("abs");
		System.out.print(trie.preorder(trie.root, new StringBuffer()));

	}

	// preorder walk
	public StringBuffer preorder(Node node, StringBuffer sb) {

		if (node.children.size() > 0) {
			for (Node n : node.children) {
				sb.append(n.letter);
				preorder(n, sb);
			}
		} else {
			sb.append(node.letter);
			sb.append(new String("-|>"));
		}
		return sb;
	}

	@Override
	public void insert(String s) {
		char[] cha = s.toCharArray();
		Node node = root;
		for (char c : cha) {
			boolean found = false;
			int i = 0;
			if (node.children.size() >0) {
				while (i < node.children.size() - 1 && node.children.get(i).letter < c) {
					if (node.children.get(i).letter == c) {
						found = true;
						break;
					} else {
						i++;
					}
				}
				if (found == true) {
					node = node.children.get(i);
				} else {
					node.children.add(i, new Node(c, node));
					node = node.children.get(i);
				}
			}else{
				node.children.add(new Node(c, node));
				node = node.children.get(0);
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
