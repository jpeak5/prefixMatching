import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Trie {

	long size;
	Node root = null;
	int insertionCount = 0;

	public Trie() {
		this.root = new Node();
	}

	class Node implements Comparable<Node> {
		int terminator; // 4 bytes
		char letter; // 2 bytes
		Node parent; // 4 bytes (reference field)
		ArrayList<Node> children = new ArrayList<Node>();// 8+4 bytes

		public Node() {
		}

		public Node(char c) {
			this.letter = c;
		}

		public Node(Node leaf) {
			this.parent = leaf;

			this.terminator = -1;
		}

		public Node(char letter, Node parent) {
			this.parent = parent;
			this.letter = letter;
			this.terminator = (int) this.letter;
		}

		@Override
		public int compareTo(Node node) {
			// 
			if (this.letter > node.letter) {
				return 1;
			} else if (this.letter < node.letter) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	// preorder walk
	public StringBuffer preorder(Node node, StringBuffer sb) {
		if (node.terminator == -1) {
			StringBuffer word = new StringBuffer();
			Node climber = node;
			while (climber.parent != null) {
				word.append(climber.letter);
				climber = climber.parent;
			}
			sb.append(word.reverse() + "\n");
		}
		if (node.children != null) {
			for (Node n : node.children) {
				preorder(n, sb);
			}
		}
		return sb;
	}
	public ArrayList<String> getFingers(Node node, ArrayList<String> list) {
		if(node.terminator == -1){
			StringBuffer word = new StringBuffer();
			Node climber = node;
			while (climber.parent != null) {
				word.append(climber.letter);
				climber = climber.parent;
			}
			list.add(word.reverse().toString());
		}
		if (node.children.size() > 0) {
			for (Node n : node.children) {
				getFingers(n, list);
			}
		} 
		return list;
	}

	public void insert(String s, Node node) {
		char c = s.charAt(0);
		Node test = new Node(c);
		boolean found = false;

		int i = Collections.binarySearch(node.children, test);
		if (i >= 0) {
			found = true;
		} else {
			i = Math.abs(i + 1);
		}

		if (found == true) {// continue to try the insertion on the found
			// node
			if (s.length() > 1) {
				insert(s.substring(1), node.children.get(i));
			} else {
				node.children.get(i).terminator = -1;
			}
		} else {// create a new node
			Node n = new Node(c, node);
			node.children.add(i, n);

			if (s.length() > 1) {
				insert(s.substring(1), n);
			} else {
				n.terminator = -1;
			}
		}

	}

	public ArrayList<String> search(String p) {

		Node node = getSubRoot(p);
		if (node == null) {
			return null;
		} else {
			ArrayList<String> list = getFingers(node, new ArrayList<String>());
			return list;
		}
	}

	public Node getSubRoot(String p) {
		Node node = root;
		while (p.length() > 0) {
			char c = p.charAt(0);
			int i = Collections.binarySearch(node.children, new Node(c));
			if (i >= 0) {
				p = p.substring(1);
				node = node.children.get(i);
			} else {
				return null;
			}
		}
		return node;
	}
	




	public ArrayList<Node> getSubtree(Node node, ArrayList<Node> nodes) {
		// using a preorder traversal to visit each node
		if (node == null) {
			return null;
		} else {
			if (node.terminator != -1) {
				nodes.add(node);
			}
			// System.out.println(">>"+node.letter);
		}
		for (Node n : node.children) {
			getSubtree(n, nodes);
		}
		return nodes;
	}

	public ArrayList<Node> calculateStorage(String p) {
		Node node = getSubRoot(p);
		if (node != null) {
			ArrayList<Node> nodes = getSubtree(node, new ArrayList<Node>());

			while (node.parent != null) {
				node = node.parent;
				nodes.add(0, node);
			}

			return nodes;
		}
		return null;
	}

	public long calculateStorage() {
		long size = 0;
		ArrayList<Node> al = getSubtree(root, new ArrayList<Node>());
		for (Node n : al) {
			size += nodeSize(n);
		}
		return size;
	}

	public long nodeSize(Node node) {
		long size = 10;// sum of static fields
		size += node.children.size() * 4;// 4bytes per node reference
		return size;
	}

}
