import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

		for (int i = 0; i < args.length; i++) {
			Trie trie = new Trie();
			String s;
			BufferedReader br = null;
			FileReader in = null;

			int count = 0;

			
			try {
				File file = new File(args[i]);
				in = new FileReader(file);
				br = new BufferedReader(in);
				System.out.println("Loading file "+args[i]+"...");
				while ((s = br.readLine()) != null) {
					trie.insert(s, trie.root);
					count++;
				}
			} finally {
				if (in != null) {
					in.close();
				}
			}
			BufferedReader buffy = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("Done");
			System.out
					.println("Type a pattern to find matches in the dictionary. Type '/q' to exit the program.");
			while (true) {
				System.out.print("prefix-matcher$ ");
				System.out.flush();
				String p = buffy.readLine();
				if (p.equals("/q")) {
					System.exit(0);
				} else {
					
					double start = System.nanoTime();
					System.out.println("You entered " + p);
					ArrayList<String> list = trie.search(p);
					if (list != null) {
						for (String m : list) {
							System.out.println(m);
						}
					} else {
						System.out.println("***patern not found");
					}
					double elapsed = (System.nanoTime() - start);
					System.out.print("Trie lookup complete.\n"+list.size()+" words found.\n"+elapsed/1000+" microseconds\n");
				}
			}
			// System.out.print(trie.preorder(trie.root, new StringBuffer()));
			// System.out.println("elapsed time is " + elapsed / 1000
			// + " microseconds for insertion and sorting of " + count
			// + " words from " + args[i] + "\n");
		}
		System.out.println("done");

		while (true) {
			// System.out.print("$prefixMatcher->");//print a prompt
			// wait for input
			// when input comes,
			// start the timer
			// call a.search(p), and save the returned List<String> for later
			// output
			// call t.search(p), and save the returned List<String> for later
			// output
			// output the search results
			// stop the timer
		}
	}

}
