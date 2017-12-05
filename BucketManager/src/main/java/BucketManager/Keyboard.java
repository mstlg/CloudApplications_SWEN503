package BucketManager;

/*
 * CompSci 101 - Keyboard Class
 * ============================
 * Version 4th March, 2011
 */

import java.util.*;
import java.io.*;

//This class creates a scanner and reads the user input and returns a string of that input
public class Keyboard {

	private static Scanner in = new Scanner(System.in);
	private static boolean redirected = false;

	public static String readInput() {

		try {
			if (!redirected) {
				redirected = System.in.available() != 0;
			}
		} catch (IOException e) {
			System.err.println("An error has occurred in the Keyboard constructor.");
			e.printStackTrace();
			System.exit(-1);
		}

		try {
			String input = in.nextLine();
			if (redirected) {
				System.out.println(input);
			}
			return input;
		} catch (NoSuchElementException e) {
			return null; // End of file
		} catch (IllegalStateException e) {
			System.err.println("An error has occurred in the Keyboard.readInput() method.");
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
}