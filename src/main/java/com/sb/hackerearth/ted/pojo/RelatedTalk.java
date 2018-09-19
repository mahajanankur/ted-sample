/**
 * 
 */
package com.sb.hackerearth.ted.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedTalk {

	private String id;

	private String duration;

	private String title;

	private String hero;

	private String speaker;

	private String slug;

	@JsonProperty("viewed_count")
	private String viewedCount;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
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
	 * @return the hero
	 */
	public String getHero() {
		return hero;
	}

	/**
	 * @param hero
	 *            the hero to set
	 */
	public void setHero(String hero) {
		this.hero = hero;
	}

	/**
	 * @return the speaker
	 */
	public String getSpeaker() {
		return speaker;
	}

	/**
	 * @param speaker
	 *            the speaker to set
	 */
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	/**
	 * @return the slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug
	 *            the slug to set
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * @return the viewedCount
	 */
	public String getViewedCount() {
		return viewedCount;
	}

	/**
	 * @param viewedCount
	 *            the viewedCount to set
	 */
	public void setViewedCount(String viewedCount) {
		this.viewedCount = viewedCount;
	}

}
