package com.jb.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class PasswordGenerator {

	private String pathname = "/Applications/Password Generator/res/names.txt";
	//private String debugTestName = "res/names.txt";
	private ArrayList<String> names;
	private Scanner readNames;
	private static boolean longWords = false;
	private String password;
	private int[] digitsWanted;
	private Random random;
	private int firstWord, secondWord;
	private String firstString, secondString, formattedFirst, formattedSecond;

	public PasswordGenerator() {
		
		loadNames();
		random = new Random();
		digitsWanted = new int[2];

	}

	public void loadNames() {

		// Load Names
		names = new ArrayList<String>();
		try {
			readNames = new Scanner(new File(pathname));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error loading word text file! Try and reinstall application!", "Error Loading Text File", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
		while (readNames.hasNextLine()) {
			String nameRead = readNames.nextLine();
			names.add(nameRead);
		}

	}

	public void generateNewPassword() {
		
		// Get Strings		
		firstWord = random.nextInt(names.size());
		secondWord = random.nextInt(names.size());
		
		// Get Numbers
		for (int i = 0; i < digitsWanted.length; i++) {
			digitsWanted[i] = random.nextInt(9);
		}
		
		// Get Strings
		getWords();
		
		// Set Password
		password = formattedFirst + formattedSecond + digitsWanted[0] + digitsWanted[1];
		
	}
	
	private void getWords() {
		
		if (longWords) {
			firstString = names.get(firstWord);
			secondString = names.get(secondWord);
			
			// Format Strings
			formattedFirst = firstString.substring(0, 1).toUpperCase() + firstString.substring(1);
			formattedSecond = secondString.substring(0, 1).toUpperCase() + secondString.substring(1);
			
			
		} else {
			firstString = names.get(firstWord);
			secondString = names.get(secondWord);
			
			if(firstString.length() >= 8 || secondString.length() >= 8) {
				firstWord = random.nextInt(names.size());
				secondWord = random.nextInt(names.size());
				getWords();
			}
			
			// Format Strings
			formattedFirst = firstString.substring(0, 1).toUpperCase() + firstString.substring(1);
			formattedSecond = secondString.substring(0, 1).toUpperCase() + secondString.substring(1);
			
			
		}
	}

	public static void setLongWords(boolean longWords) {
		PasswordGenerator.longWords = longWords;
	}
	
	public String getPassword() {
		return password;
	}
	


}
