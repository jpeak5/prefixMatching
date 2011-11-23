import java.util.List;
public interface StringStorage {
	
	void insert(String s, Trie.Node node);
	List <String> search(String p);
}
