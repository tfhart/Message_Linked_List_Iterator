package src;

///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            P2
// Files:            DblListnode.java, DisplayEditor.java, 
// 									 EmptyLoopException.java, MessageLoop.java, 
//									 MessageLoopIterator.java; 
//									 UnrecognizedCharacterException.java
// Semester:         CS302 Spring 2014
//
//Author:           Thomas Hart
//Email:            tfhart@wisc.edu
//CS Login:         thart
//Lecturer's Name:  Skrentny
//
//                STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
//Credits:          (list anyone who helped you write your program)
////////////////////////////80 columns wide //////////////////////////////////

import java.util.*;
import java.io.*;

public class DisplayEditor {
	/**
	 * Main class that calls upon other classes. Reads in files and messages, 
	 * and promts the user with options on what they would like to do next.
	 * Choices they have include displaying the dot matrixes, going to the next
	 * one, going to the previous ones, etc.
	 */
	
	public static void main (String [] args) throws EmptyLoopException{
		/**
		 * The main method, creates a scanner for the input file and text file.
		 * Then prompts the user with choices of what they would like to do next.
		 */
		Scanner in = new Scanner(System.in);
		Scanner text;
		boolean quit = false;
		MessageLoop<ArrayList<String>> loop = new MessageLoop<ArrayList<String>>();
		boolean useFile = args.length == 2;
		//if we have two valid command line arguments...
		if (useFile) {
			File inFile = new File(args[0]);
			File textFile = new File(args[1]);
			if (!inFile.exists() || !inFile.canRead()) {
				System.err.println("Problem with input file!");
				System.exit(1);
			}
			if (!textFile.exists() || !textFile.canRead()){
				System.err.println("Problem with input file!");
				System.exit(1);
			}
			try {
				in = new Scanner(inFile);
				text = new Scanner(textFile);
			} catch (FileNotFoundException e) {
				System.err.println("Problem with input file!");
				System.exit(1);
			}
		}
		//if we don't have two valid.... options are:
		else{
			in = new Scanner(System.in);
			//if we aren't given any
			if(args.length == 0){
				System.out.println("Enter the dot-matrix alphabets file:");
				File textFile = new File(in.next());
				if(!textFile.exists() || !textFile.canRead()){
					System.err.println("Problem with input file!");
					System.exit(1);
				}
				try{
					text = new Scanner(textFile);
				} catch(FileNotFoundException e){
					System.err.println("Problem with input file!");
					System.exit(1);
				}
			}
			//if we are given one or more than two... 
			else{
				System.err.println("invalid command-line arguments");
				System.exit(1);
			}
		}

		//dealing with user commands... 
		while(!quit){
			System.out.println("enter command (? for help)");
			String userChoice = in.next();

			if(userChoice.equals("?"))
			{
				System.out.println("s (save)     l (load)        d (display)" +
						"n (next)     p (previous)    j (jump)" +
						"d (delete)   a (add after)   i (insert before)" +
						"(context)    r (replace)     q (quit)");
			}

			else if(userChoice.equals("s")){
				save(loop,in);		
			}

			else if(userChoice.equals("l")){
				load(loop, in);
			}
			else if(userChoice.equals("d")){
				display(loop);
			}
			else if(userChoice.equals("n")){
				next(loop);
			}
			else if(userChoice.equals("p")){
				back(loop);
			}
			else if(userChoice.equals("j")){
				jump(loop, in);
			}
			else if(userChoice.equals("x")){
				remove(loop);
			}
			else if(userChoice.equals("a")){
				try {
					addAllAfter(loop, in);
				}
				catch (UnrecognizedCharacterException e){
					System.out.println("An unrecognized character has been entered.");
				}
			}
			else if(userChoice.equals("i")){
				try {
					insertAllBefore(loop, in);
				}
				catch (UnrecognizedCharacterException e){
					System.out.println("An unrecognized character has been entered.");
				}
			}
			else if(userChoice.equals("c")){
				displayCurrent(loop);
			}
			else if(userChoice.equals("r")){
				try {
					replace(loop, in);
				}
				catch (UnrecognizedCharacterException e){
					System.out.println("An unrecognized character has been entered.");
				}
			}
			else if(userChoice.equals("q")){
				quit = true;
			}
			else 
			{
				System.out.println("invalid command");
			}
		}
	}

	private static void save(MessageLoop<ArrayList<String>> loop, Scanner in)
	{
		/**
		 * Saves the message loop.
		 */
		ArrayList<String> pastFilenames = new ArrayList<String>();
		if(loop.size() == 0){
			System.out.println("no messages to save");
		}
		else{
			String filename = in.next();
			for(int i = 0; i < pastFilenames.size(); i++){
				if(pastFilenames.get(i).equals(filename)){
					System.out.println("warning: file already exists, will be overwritten");
					pastFilenames.remove(i);
				}
			}
			MessageLoopIterator<ArrayList<String>> itr = loop.iterator();
			ArrayList<String> start = loop.getCurrent();
			try{
				PrintWriter out = new PrintWriter(filename);
				if(loop.size() == 1){
					out.print("**********");
					out.print(loop.getCurrent());
					out.print("**********");
					out.close();
				}
				else if(loop.size() == 2){
					out.print("**********");
					out.print(loop.getCurrent());
					out.print("**********");
					loop.forward();
					out.print(loop.getCurrent());
				}
				else{
					while(itr.hasNext() && itr.next()!= start){
						out.print(loop.getCurrent());
						loop.forward();
						if(loop.getCurrent() != start) {
							out.print("**********");
						}
					}
				}
			}catch(FileNotFoundException e){
				System.out.println("unable to save");
			}
			pastFilenames.add(filename);
		}
	}

	private static void display(MessageLoop<ArrayList<String>> loop) throws 
	EmptyLoopException{
		/**
		 * Displays the message loop.
		 */
		ArrayList<String> start = loop.getCurrent();
		if (loop.size() == 0) {
			throw new EmptyLoopException();
		}
		else { 
			ArrayList<String> currentLetter = loop.getCurrent();
			MessageLoopIterator<ArrayList<String>> itr = loop.iterator();
			while (itr.hasNext() && itr.next()!= start) {
				for(int i = 0; i< currentLetter.size(); i++){
					System.out.println(currentLetter.get(i));
				}
				loop.forward();
			}
		}
	}

	private static void load(MessageLoop<ArrayList<String>> loop, Scanner in) {
		/**
		 * Loads the message loop.
		 */
		File inputFile = new File(in.next());
		try {
			Scanner fileScanner = new Scanner(inputFile);
			if(!inputFile.exists() || !inputFile.canRead()) {
				System.err.println("unable to load");
			}
			else{
				while(fileScanner.hasNext())
				{
					ArrayList<String> tmpChar = new ArrayList<String>();
					if(!tmpChar.equals("**********")) {

						for(int i = 0; i<7; i++){
							String tmpString = fileScanner.next();
							tmpChar.add(i, tmpString);
						}
						loop.addAfter(tmpChar);
					}
				}
			}
		}
		catch  (FileNotFoundException e){
			System.out.println("unable to load");
		}
	}

	private static void next(MessageLoop<ArrayList<String>> loop)  throws EmptyLoopException
	{
		/**
		 * Traverses forward to the next message in the arrayList
		 */
		if (loop.size() == 0) {
			throw new EmptyLoopException();
		}
		else {
			ArrayList<String> currentLetter = loop.getCurrent();
			loop.forward();
			for(int i = 0; i< currentLetter.size(); i++){
				System.out.println(currentLetter.get(i));
			}
		}	
	}

	private static void back(MessageLoop<ArrayList<String>> loop) throws EmptyLoopException
	{
		/**
		 * Traverses backwards to the next message in the arrayList
		 */
		if (loop.size() == 0) {
			throw new EmptyLoopException();
		}
		else {
			ArrayList<String> currentLetter = loop.getCurrent();
			loop.back();
			for(int i = 0; i< currentLetter.size(); i++){
				System.out.println(currentLetter.get(i));
			}
		}	
	}

	private static void jump(MessageLoop<ArrayList<String>> loop, Scanner in) 
			throws EmptyLoopException{
		/**
		 * Jumps forward or backward the number of messages entered by the user. 
		 */
		if (loop.size() == 0) {
			throw new EmptyLoopException();
		}
		else {
			int numberOfJumps = in.nextInt();
			ArrayList<String> currentLetter = loop.getCurrent();
			if (numberOfJumps < 0)
			{
				for (int i = numberOfJumps; i < 0; i++)
				{
					loop.back();
				}
				for(int i = 0; i< currentLetter.size(); i++){
					System.out.println(currentLetter.get(i));
				}
			}
			else if (numberOfJumps > 0)
			{
				for (int i = numberOfJumps; i > 0; i--)
				{
					loop.forward();
				}
				for(int i = 0; i< currentLetter.size(); i++){
					System.out.println(currentLetter.get(i));
				}
			}
			else if (numberOfJumps == 0)
			{
				loop.getCurrent();
			}
			for(int i = 0; i< currentLetter.size(); i++){
				System.out.println(currentLetter.get(i));
			}
		}
	}

	private static void remove(MessageLoop<ArrayList<String>> loop) throws 
	EmptyLoopException {
		/**
		 * Removes the current message from the message loop.
		 */
		ArrayList<String> currentLetter = loop.getCurrent();
		if (loop.size() == 0) {
			throw new EmptyLoopException();
		}
		else {
			loop.removeCurrent();
			if (loop.size() == 0){
				throw new EmptyLoopException();
			}
			else {
				loop.forward();
				for(int i = 0; i< currentLetter.size(); i++){
					System.out.println(currentLetter.get(i));
				}
			}
		}
	}

	private static void addAllAfter(MessageLoop<ArrayList<String>> loop, 
			Scanner in) 
					throws UnrecognizedCharacterException{
		/**
		 * Adds (if possible) all the messages in the array list. One at a time
		 * after the previous message.
		 */
		if (loop.size() == 0) {
			while (in.hasNext()) {
				String tmpString = in.next();
				for(int i =0; i<tmpString.length(); i++){
					String currentChar = tmpString.substring(i,i+1);
					DotMatrix DotMatrix = new DotMatrix();
					if (DotMatrix.isValidCharacter((String) currentChar));
					{
						DotMatrix.loadAlphabets(currentChar);
						ArrayList<String> currentNode = new ArrayList<String>
						(DotMatrix.getDotMatrix(currentChar));
						loop.addAfter(currentNode);
					}
				}
			}
		}
	}

	private static void insertAllBefore(MessageLoop<ArrayList<String>> loop, 
			Scanner in) 
					throws UnrecognizedCharacterException{
		/**
		 * Adds (if possible) all the messages in the array list. One at a time
		 * before the previous message.
		 */
		if (loop.size() == 0) {
			while (in.hasNext()) {
				String tmpString = in.next();
				for(int i =0; i<tmpString.length(); i++){
					String currentChar = tmpString.substring(i,i+1);
					DotMatrix DotMatrix = new DotMatrix();
					if (DotMatrix.isValidCharacter((String) currentChar));
					{
						DotMatrix.loadAlphabets(currentChar);
						ArrayList<String> currentNode = new ArrayList<String>
						(DotMatrix.getDotMatrix(currentChar));
						loop.addBefore(currentNode);
					}
				}
			}
		}
	}

	private static void displayCurrent(MessageLoop<ArrayList<String>> loop) throws 
	EmptyLoopException {
		/**
		 * Displays the current message.
		 */
		ArrayList<String> currentLetter = loop.getCurrent();
		if (loop.size() == 0) {
			throw new EmptyLoopException();
		}
		else {
			for(int i = 0; i< currentLetter.size(); i++){
				System.out.println(currentLetter.get(i));
			}
		}
	}

	private static void replace(MessageLoop<ArrayList<String>> loop, Scanner in)
			throws UnrecognizedCharacterException, EmptyLoopException {
		/**
		 * Replaces the current message with the new one entered if it is a 
		 * valid character.
		 */
		DotMatrix DotMatrix = new DotMatrix();
		if (loop.size() == 0) {
			throw new EmptyLoopException();
		}
		else {
			String replacedChar = in.next();
			if (DotMatrix.isValidCharacter(replacedChar))
			{
				DotMatrix.loadAlphabets(replacedChar);
				ArrayList<String> currentDotMatrix = new ArrayList<String>(DotMatrix.getDotMatrix(replacedChar));
				loop.addAfter(currentDotMatrix);
				loop.removeCurrent();
				System.out.println(currentDotMatrix);
			}
			else {
				throw new UnrecognizedCharacterException();
			}
		}
	}

}

