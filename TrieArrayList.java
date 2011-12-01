import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class TrieArrayList {
	
	long size;
	Node root = null;
	int insertionCount = 0;

	public TrieArrayList() {
		this.root = new Node();
	}

	class Node implements Comparable<Node> {
		@Override
		public int compareTo(Node node) {
			// TODO Auto-generated method stub
			if (this.letter > node.letter) {
				return 1;
			} else if (this.letter < node.letter) {
				return -1;
			} else {
				return 0;
			}
		}

		int terminator; // 4 bytes
		char letter; // 2 bytes
		Node parent; // 4 bytes (reference field)
		ArrayList<Node> children = new ArrayList<Node>();// 8+4 bytes
		
		public Node(){}
		
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
	
	public long nodeSize(Node node){
		long size =10;//sum of static fields
		size+=node.children.size()*4;//4bytes per node reference
		return size;
	}
	
	public void insert(String s, Node node) {
		char c = s.charAt(0);
		ArrayList<Node> children = node.children; // convenience var
		Node test = new Node(c);
		boolean found = false;

		int i = Collections.binarySearch(children, test);
		if (i >= 0) {
			found = true;
		} else {
			i = Math.abs(i + 1);
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

	public ArrayList<String> getFingers(Node node, ArrayList<String> list) {
		if (node.children.size() > 0) {
			for (Node n : node.children) {
				getFingers(n, list);
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

	
	public ArrayList<Node> getSubNodes(Node node, ArrayList<Node>nodes){
		//using a preorder traversal to visit each node
		if(node == null){
			return null;
		}else{
			if(node.terminator!=-1){
			nodes.add(node);
			}
//			System.out.println(">>"+node.letter);
		}
		for(Node n : node.children){
			getSubNodes(n, nodes);
		}
		return nodes;
	}
	
	public ArrayList<Node> calculateStorage(String p){
		Node node = getWristNode(p);
		if(node!=null){
			ArrayList<Node> nodes = getSubNodes(node, new ArrayList<Node>());
			
			while(node.parent!=null){
				node=node.parent;
				nodes.add(0,node);
			}
			
			return nodes;
		}
		return null;
	}
	
	public long calculateStorage(){
		long size = 0;
		ArrayList <Node> al = getSubNodes(root, new ArrayList<Node>());
		for(Node n : al){
			size+=nodeSize(n);
		}
		return size;
	}
	
	public Node getWristNode(String p) {
		Node node = root;
		while (p.length() > 0) {
			char c = p.charAt(0);
			Node target = new Node(c);
			int search = Collections.binarySearch(node.children, target);
			if (search >= 0) {
				p = p.substring(1);
				node = node.children.get(search);
			}else{
				return null;
			}
		}
		return node;
	}

	public ArrayList<String> search(String p) {

		Node node = getWristNode(p);
		if (node == null) {
			return null;
		} else {
			ArrayList<String> list = getFingers(node,
					new ArrayList<String>());
			return list;
		}
	}

}
