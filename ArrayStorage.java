import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class ArrayStorage {
	private List<String> l;

	public ArrayStorage() {
		l = new LinkedList<String>();
	}

	public void add(String s) {
		if (s == null) {
			return;
		}
		l.add(s);
	}

	public void sort() {
		Collections.sort(l);
	}

	public LinkedList<String> search(String pre) {
		int low = 0;
		int high = l.size() - 1;
		int index = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			String s = l.get(mid);
			if (s.length() >= pre.length()) {
				String t = "";
				for (int i = 0; i < pre.length(); i++) {
					t = t + s.charAt(i);
				}
				if (t.compareTo(pre) > 0) {
					high = mid - 1;
				} else if (t.compareTo(pre) < 0) {
					low = mid + 1;
				} else {
					index = mid;
					break;
				}
			} else {
				if (s.compareTo(pre) > 0) {
					high = mid - 1;
				} else if (s.compareTo(pre) < 0) {
					low = mid + 1;
				}
			}
		}
		if (index != -1) {
			LinkedList<String> ll = new LinkedList<String>();
			ll.add(l.get(index));
			int flag = 1;
			int index2 = index;
			while (flag == 1) {
				index2--;
				if (index2 < 0) {
					break;
				}
				String s = l.get(index2);
				if (s.length() >= pre.length()) {
					String t = "";
					for (int i = 0; i < pre.length(); i++) {
						t = t + s.charAt(i);
					}
					if (t.equals(pre)) {
						ll.add(s);
						continue;
					} else {
						break;
					}
				} else {
					break;
				}
			}
			flag = 1;
			index2 = index;
			while (flag == 1) {
				index2++;
				if (index2 > l.size() - 1) {
					break;
				}
				String s = l.get(index2);
				if (s.length() >= pre.length()) {
					String t = "";
					for (int i = 0; i < pre.length(); i++) {
						t = t + s.charAt(i);
					}
					if (t.equals(pre)) {
						ll.add(s);
						continue;
					} else {
						break;
					}
				} else {
					break;
				}
			}
			return ll;
		}
		return null;
	}

	public static void main(String[] args) {
		ArrayStorage as = new ArrayStorage();
		as.add("hello");
		as.add("grin");
		as.add("grip");
		as.add("hahaha");
		as.sort();
		System.out.println(as.search("gri"));
		System.out.println(as.search("ha"));
		System.out.println(as.search("hell"));
		System.out.println(as.search("hahaha"));
	}
}
