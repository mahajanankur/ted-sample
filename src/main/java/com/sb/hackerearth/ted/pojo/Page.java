/**
 * 
 */
package com.sb.hackerearth.ted.pojo;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
public class Page {

	private Object content;

	private Long currentIndex;

	private Long lastIndex;

	private Long size;

	/**
	 * @return the currentIndex
	 */
	public Long getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * @param currentIndex
	 *            the currentIndex to set
	 */
	public void setCurrentIndex(Long currentIndex) {
		this.currentIndex = currentIndex;
	}

	/**
	 * @return the lastIndex
	 */
	public Long getLastIndex() {
		return lastIndex;
	}

	/**
	 * @param lastIndex
	 *            the lastIndex to set
	 */
	public void setLastIndex(Long lastIndex) {
		this.lastIndex = lastIndex;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * @return the content
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Object content) {
		this.content = content;
	}

}
