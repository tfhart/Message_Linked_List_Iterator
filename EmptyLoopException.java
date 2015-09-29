///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             EmptyLoopException.java
// Semester:         CS302 Spring 2014
//
// Author:           Thomas Hart
// Email:            tfhart@wisc.edu
// CS Login:         thart
// Lecturer's Name:  Skrentny
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////
package src;

public class EmptyLoopException extends Exception {
	/**
	 * Throws an empty loop exception if no empty loop exists.
	 */
	
	public EmptyLoopException() {
		/**
		 * Prints out the first error message if their are no messages
		 */
		System.out.println("no messages");
	}
	public EmptyLoopException(String s){
		/**
		 * Prints out an error message if there are no messages to save
		 */
		System.out.println("no messages to save");
	}
}
