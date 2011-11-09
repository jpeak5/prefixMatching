import java.util.List;

public class Array implements StringStorage {
	
	//keeps track of the size of the array versus its capacity
	//this will be useful when we begin binary searching
	int size = 0;
	
	String[] array;

	@Override
	public void insert(String s) {
		// TODO Auto-generated method stub
		this.append(s);
	}

	@Override
	public List<String> search(String p) {
		// find all the strings whose prefixes match parameter p and return as a List
		return null;
	}
	
	public void sort(){
		//sort me
	}
	
	private void append(String s){
		//array.push(s);
	}
}
