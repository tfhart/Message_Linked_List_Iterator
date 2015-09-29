///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  DisplayEditor.java
// File:             MessageLoop.java
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

import java.util.Iterator;
public class MessageLoop<E> implements LoopADT<E>, Iterable<E> {
	/**
	 * Creates a new Message Loop implementing the loop ADT interface.
	 */
	
	private DblListnode<E> head;
	private int numItems;
	private DblListnode<E> curr;
	
	public MessageLoop(){
		/**
		 * Creates new message loop
		 */
		numItems = 0;
		head = null;
		curr = head;
	}
	
	public void addBefore(E item){
		/**
		 * Adds node before current
		 */
		DblListnode<E> newNode = new DblListnode<E>(item);
		if(numItems == 0){
			head.setNext(newNode);
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
		}
		else{
			newNode.setNext(curr);
			curr.getPrev().setNext(newNode);
			newNode.setPrev(curr.getPrev());
			curr.setPrev(newNode);
		}
		numItems++;
	}

	public void addAfter(E item){
		/**
		 * Adds node after current
		 */
		DblListnode<E> newNode = new DblListnode<E>(item);
		if(numItems == 0){
			head.setNext(newNode);
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
		}
		else{
			newNode.setNext(curr.getNext());
			curr.getNext().setPrev(newNode);
			newNode.setPrev(curr);
			curr.setNext(newNode);
		}
		numItems++;
	}
	
	public E getCurrent(){
		/**
		 * returns current
		 */
		return curr.getData();
	}
	
	public E removeCurrent(){
		/**
		 * removes current
		 */
		curr.getNext().setPrev(curr.getPrev());
		curr.getPrev().setNext(curr.getNext());
		return curr.getData();
	}
	
	public void forward(){
		/**
		 * moves current forward 1
		 */
		curr = curr.getNext();
	}
	
	public void back(){
		/**
		 * moves current back 1
		 */
		curr = curr.getPrev();
	}
	
	public int size(){
		/**
		 * returns the number of items
		 */
		return numItems;
	}
	
	public MessageLoopIterator<E> iterator(){
		/**
		 * returns a new iterator starting at the head
		 */
		return new MessageLoopIterator<E>(head);
	}
}
