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

		if (args.length != 1) {
			System.out
					.println("Usage: give the name of a single dictionary file as the sole argument");
		}
		Trie trie = new Trie();
		String s;
		BufferedReader br = null;
		FileReader in = null;

		int count = 0;
		double start = System.nanoTime();
		try {
			File file = new File(args[0]);
			in = new FileReader(file);
			br = new BufferedReader(in);
			System.out.print("Loading file " + args[0] + "...");
			while ((s = br.readLine()) != null) {
				trie.insert(s, trie.root);
				count++;
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		System.out.print("Done.\n");
		double elapsed = (System.nanoTime() - start);
		System.out.print(count + " words loaded in " + elapsed / 1000000000
				+ " seconds\n");
		BufferedReader buffy = new BufferedReader(new InputStreamReader(
				System.in));
		System.out
				.println("Type a pattern to find matches in the dictionary. Type '/q' to exit the program.");
		while (true) {
			System.out.print("prefix-matcher$ ");
			System.out.flush();
			String p = buffy.readLine();
			if (p.equals("/q")) {
				System.exit(0);
			} else {

				start = System.nanoTime();
//				System.out.println("You entered " + p);
				ArrayList<String> list = trie.search(p);
				if (list != null) {
					for (String m : list) {
						System.out.println(m);
					}
				} else {
					System.out.println("No matches found for pattern "+p);
				}
				elapsed = (System.nanoTime() - start);
				System.out.print("Trie lookup complete.\n" + list.size()
						+ " words found.\n" + elapsed / 1000
						+ " microseconds\n");
			}

		}
	}
}
