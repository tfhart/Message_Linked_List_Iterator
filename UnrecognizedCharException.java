///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             UnrecognizedCharacterException.java
// Semester:         CS302 Spring 2014
//
// Author:           Thomas Hart
// Email:            tfhart@wisc.edu
// CS Login:         thart
// Lecturer's Name:  Skrentny
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////
package src;

public class UnrecognizedCharacterException  extends Exception {
	/**
	 * Throws an exception if there is an unrecognized character. 
	 * The class extends the exception class.
	 */
	public UnrecognizedCharacterException() {
		/**
		 * Displays this message if it's an unrecognized char.
		 */
		System.out.println("An unrecognized character has been entered.");
	}
}
