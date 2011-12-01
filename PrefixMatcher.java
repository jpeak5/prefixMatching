import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class PrefixMatcher {

	/**
	 * @param args
	 *            this program takes a file as command line parameter <br/>
	 *            This file should contain a number of strings within which we
	 *            will search for prefixes<br/>
	 */
	public static void main(String[] args) throws IOException {

		if (args.length != 1) {
			System.out.println("Usage: give the name of a single dictionary file as the sole argument");
			System.exit(-1);
		}
		
		String s;
		BufferedReader br = null;
		FileReader in = null;
		
		
		TrieArrayList trieArrayList = new TrieArrayList();


		try {
			int count = 0;
			double start = System.nanoTime();
			File file = new File(args[0]);
			in = new FileReader(file);
			br = new BufferedReader(in);
			System.out.print("Loading file " + args[0] + "...");
			
			while ((s = br.readLine()) != null) {
				trieArrayList.insert(s, trieArrayList.root);
				count++;
			}
			
			double elapsed = (System.nanoTime() - start);
			System.out.printf("%,d  words loaded into Trie in %,f microseconds\n", count, elapsed / 1000);
			
			trieArrayList.preorder(trieArrayList.root, new StringBuffer());
			System.out.println("Trie storage of "+count+" words consumes "+trieArrayList.calculateStorage()+"bytes");
			// test for insertion correctness by calling preorder on root
			// System.out.println(trieArrayList.preorder(trieArrayList.root, new
			// StringBuffer()));
		} finally {
			if (in != null) {
				in.close();
			}
		}
		//now read in the array storage
		ArrayStorage as = new ArrayStorage();
		

		
		try {
			
			File file = new File(args[0]);
			in = new FileReader(file);
			br = new BufferedReader(in);
			System.out.print("Loading file " + args[0] + "...");

			int asCount = 0;
			double asStart = System.nanoTime();
			while ((s = br.readLine()) != null) {
				as.add(s);
				asCount++;
			}
			double asElapsed = System.nanoTime() - asStart;
			System.out.printf("%,d  words loaded into Trie in %,f microseconds\n", asCount, asElapsed / 1000);
			
			//now sort the array storage structure
			asStart = System.nanoTime();
			as.sort();
			asElapsed = System.nanoTime() - asStart;
			System.out.printf("ArrayStorage: %,d words sorted in %,f micro-seconds\n", asCount, asElapsed / 1000);
			
//			 test for insertion correctness by calling preorder on root
//			 System.out.println(trieArrayList.preorder(trieArrayList.root, new
//			 StringBuffer()));
		}finally {
			if (in != null) {
				in.close();
			}
		}
		
		
		BufferedReader buffy = new BufferedReader(new InputStreamReader(
				System.in));
		System.out
				.println("Type a pattern to find matches in this dictionary. Type '/q' to exit the program.");
		while (true) {
			System.out.flush();
			System.out.print("prefix-matcher$ ");
			System.out.flush();
			String p = buffy.readLine();
			if (p.equals("/q")) {
				System.exit(0);
			} else {

				double start = System.nanoTime();
				ArrayList<String> list = trieArrayList.search(p);
				double elapsed = (System.nanoTime() - start);
				
				double asStart = System.nanoTime();
				LinkedList <String> results = as.search(p);
				double asElapsed = System.nanoTime() - start;
				if (list != null) {
					for (String m : list) {
						System.out.println(m);
					}
					int wordsFound = list != null ? list.size() : 0;
					System.out
							.println("-----------------\nTrie lookup complete.\n"
									+ wordsFound
									+ " words found.\nin "
									+ elapsed / 1000 + " microseconds\n");
					ArrayList<TrieArrayList.Node> nodes = trieArrayList
							.calculateStorage(p);
//					System.out
//							.println((p.length() + nodes.size() - 1)
//									+ " nodes used for storage (including the root node)");
//					int i = 0;
//					while (i < p.length() - 1) {
//						System.out.print(p.charAt(i) + " ");
//						i++;
//					}
					System.out.print(nodes.size()+" nodes required to store the result set (root node is counted but not output) \n{");
//					int i;
//					for(i=0;i<nodes.size()-1;i++) {
//						char l = nodes.get(i).letter;
//						System.out.print(l);
//						System.out.print(", ");
//						if(i%10 ==0){
//							System.out.print("\n");
//						}
//					}
//					System.out.print(nodes.get(i).letter+"\n}\n");
//					 System.out.println("results stored in "+(nodes.size()-1 + p.length()-1)+" nodes");
				} else {
					System.out.println("No matches found for pattern " + p);
				}
				System.out.println("ArrayStorage Results:");
				if(results !=null){
				for(String str : results){
					System.out.println(str);
				}
				System.out.printf("%,d results found in %,f microseconds\n", results.size(), asElapsed/1000);
				}
				
			}
//			System.out.flush();
		}
	}
}
