package org.vp.pis.domain;

/**
 * Class that holds the Link details for payment request
 *
 */
public class Links {
	/**
	 * Holds the Self of Links
	 */
	private String Self;
	/**
	 * Holds the First of Links
	 */
	private String First;
	/**
	 * Holds the Previous of Links
	 */
	private String Prev;
	/**
	 * Holds the Next of Links
	 */
	private String Next;
	/**
	 * Holds the Last of Links
	 */
	private String Last;

	/**
	 * @return the self
	 */
	public String getSelf() {
		return Self;
	}

	/**
	 * @param self
	 *            the self to set
	 */
	public void setSelf(String self) {
		Self = self;
	}

	/**
	 * @return the first
	 */
	public String getFirst() {
		return First;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(String first) {
		First = first;
	}

	/**
	 * @return the prev
	 */
	public String getPrev() {
		return Prev;
	}

	/**
	 * @param prev
	 *            the prev to set
	 */
	public void setPrev(String prev) {
		Prev = prev;
	}

	/**
	 * @return the next
	 */
	public String getNext() {
		return Next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(String next) {
		Next = next;
	}

	/**
	 * @return the last
	 */
	public String getLast() {
		return Last;
	}

	/**
	 * @param last
	 *            the last to set
	 */
	public void setLast(String last) {
		Last = last;
	}

}
