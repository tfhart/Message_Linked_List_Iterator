///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             MessageLoopIterator.java
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
import java.util.*;
public class MessageLoopIterator<E> implements Iterator<E> {
	/**
	 * Creates an iterator for the message loop implementing the Iterator
	 * interface.
	 */
	
	private DblListnode<E> curr;
	
	MessageLoopIterator(DblListnode<E> h){
		/**
		 * Sets curr to the head
		 */
		curr = h;
	}
	
	public boolean hasNext() {
		/**
		 * Checks to see if their's a next pos
		 */
		return curr!=null;
	}
	
	public E next() {
		/**
		 * Checks next
		 */
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		E data = curr.getData();
		curr = curr.getNext();
		return data;
	}
	
	public void remove(){
		/**
		 * Removes current node.
		 */
		throw new UnsupportedOperationException();
	}
}
