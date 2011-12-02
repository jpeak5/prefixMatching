import java.util.Arrays;
import java.util.LinkedList;

public class ArrayStorage {
private String[] strs;
private int end;

	public ArrayStorage() {
strs = new String[50];
end=0;
	}

public long calculateStorage() {
long store = 8;
for (int i=0; i<end; i++) {
int strstore = strs[i].length()*2+8;
if(strstore%8 == 0) {
store += strstore;
}
else {
strstore += (8-(strstore%8));
store += strstore;
}
}
return store;
}

	public void add(String s) {
		if (s == null) {
			return;
		}
if(end == strs.length) {
String[] strs2 = new String[(int) (strs.length*1.3)];
for(int i = 0; i<strs.length; i++) {
strs2[i] = strs[i];
}
strs = strs2;
}
strs[end] = s;
end++;
	}

	public void sort() {
String[] strs2 = new String[end];
for(int i = 0; i<end; i++) {
strs2[i] = strs[i];
}
strs = strs2;
Arrays.sort(strs);
	}

	public LinkedList<String> search(String pre) {
		int low = 0;
		int high = end - 1;
		int index = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			String s = strs[mid];
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
			ll.add(strs[index]);
			int flag = 1;
			int index2 = index;
			while (flag == 1) {
				index2--;
				if (index2 < 0) {
					break;
				}
				String s = strs[index2];
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
				if (index2 > end - 1) {
					break;
				}
				String s = strs[index2];
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
System.out.println(as.search("roar"));
	}
}
