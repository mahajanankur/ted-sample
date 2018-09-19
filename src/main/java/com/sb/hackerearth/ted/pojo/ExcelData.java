/**
 * 
 */
package com.sb.hackerearth.ted.pojo;

import java.util.List;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
public class ExcelData {

	private String description;

	private String event;

	private String mainSpeaker;

	private String name;

	private long publishedDate;

	// private List<Rating> ratings;
	//
	// private List<RelatedTalk> relatedTalks;

	private String speakerOccupation;

	private List<String> tags;

	private String title;

	private String url;

	private long views;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * @return the mainSpeaker
	 */
	public String getMainSpeaker() {
		return mainSpeaker;
	}

	/**
	 * @param mainSpeaker
	 *            the mainSpeaker to set
	 */
	public void setMainSpeaker(String mainSpeaker) {
		this.mainSpeaker = mainSpeaker;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the publishedDate
	 */
	public long getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate
	 *            the publishedDate to set
	 */
	public void setPublishedDate(long publishedDate) {
		this.publishedDate = publishedDate;
	}
	/*
		*//**
			 * @return the ratings
			 */
	/*
	 * public List<Rating> getRatings() {
	 * return ratings;
	 * }
	 *//**
		 * @param ratings
		 *            the ratings to set
		 */
	/*
	 * public void setRatings(List<Rating> ratings) {
	 * this.ratings = ratings;
	 * }
	 *//**
		 * @return the relatedTalks
		 */
	/*
	 * public List<RelatedTalk> getRelatedTalks() {
	 * return relatedTalks;
	 * }
	 *//**
		 * @param relatedTalks
		 *            the relatedTalks to set
		 *//*
		 * public void setRelatedTalks(List<RelatedTalk> relatedTalks) {
		 * this.relatedTalks = relatedTalks;
		 * }
		 */

	/**
	 * @return the speakerOccupation
	 */
	public String getSpeakerOccupation() {
		return speakerOccupation;
	}

	/**
	 * @param speakerOccupation
	 *            the speakerOccupation to set
	 */
	public void setSpeakerOccupation(String speakerOccupation) {
		this.speakerOccupation = speakerOccupation;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the views
	 */
	public long getViews() {
		return views;
	}

	/**
	 * @param views
	 *            the views to set
	 */
	public void setViews(long views) {
		this.views = views;
	}

}
