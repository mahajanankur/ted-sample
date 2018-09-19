/**
 * 
 */
package com.sb.hackerearth.ted.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sb.hackerearth.ted.pojo.ExcelData;
import com.sb.hackerearth.ted.pojo.Page;
import com.sb.hackerearth.ted.service.TedService;
import com.sb.hackerearth.ted.utils.DateCompartor;
import com.sb.hackerearth.ted.utils.MultiSequenceComparator;
import com.sb.hackerearth.ted.utils.ViewsCompartor;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
@Service("tedService")
public class TedServiceImpl implements TedService {

	@Value("${csv.file.location}")
	private String CSV_FILE_PATH;

	public static List<ExcelData> excelData;

	@Override
	public Set<String> getUniqueTags() {
		Set<String> uniqueTags = new HashSet<>();
		for (ExcelData data : excelData) {
			List<String> tags = data.getTags();
			for (String tag : tags) {
				uniqueTags.add(tag.trim());
			}
		}
		return uniqueTags;
	}

	@Override
	public void readCsvFile() {
		// ['description', 'event', 'main_speaker', 'name', 'published_date',
		// 'ratings', 'related_talks', 'speaker_occupation', 'tags', 'title',
		// 'url', 'views']
		excelData = new ArrayList<ExcelData>();
		try (Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(CSV_FILE_PATH), "utf-8"));
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT
								.withHeader("description", "event", "main_speaker", "name", "published_date", "ratings",
										"related_talks", "speaker_occupation", "tags", "title", "url", "views")
								.withIgnoreHeaderCase().withTrim());) {
			long count = 0;
			for (CSVRecord csvRecord : csvParser) {
				if (count == 0) {
					count++;
					continue;
				}
				ExcelData data = new ExcelData();
				// Accessing values by the names assigned to each column
				String description = csvRecord.get("description");
				String event = csvRecord.get("event");
				String mainSpeaker = csvRecord.get("main_speaker");
				String name = csvRecord.get("name");
				String publishedDate = csvRecord.get("published_date");
				// String ratings = csvRecord.get("ratings");
				// String relatedTalks = csvRecord.get("related_talks");
				String speakerOccupation = csvRecord.get("speaker_occupation");
				String tags = csvRecord.get("tags");
				String title = csvRecord.get("title");
				String url = csvRecord.get("url");
				String views = csvRecord.get("views");

				// System.out.println("Record No - " +
				// csvRecord.getRecordNumber());
				data.setDescription(description);
				data.setEvent(event);
				data.setMainSpeaker(mainSpeaker);
				data.setName(name);
				data.setPublishedDate(Long.parseLong(publishedDate));
				data.setSpeakerOccupation(speakerOccupation);
				String[] tagSplit = tags.replace("[", "").replace("]", "").replace("'", "").trim().split(",");
				List<String> tagList = new ArrayList<>();
				for (String tag : tagSplit) {
					tagList.add(tag.trim());
				}
				data.setTags(tagList);
				data.setTitle(title);
				data.setUrl(url);
				data.setViews(Long.parseLong(views));
				excelData.add(data);
				count++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<ExcelData> getTedsByTag(String tag) {
		Set<ExcelData> tedsByTag = new HashSet<>();
		for (ExcelData data : excelData) {
			List<String> tags = data.getTags();
			if (tags.contains(tag)) {
				tedsByTag.add(data);
			}
		}
		return tedsByTag;
	}

	@Override
	public Page getTedsByTagByPagination(String tag, Long index, Long size, boolean sortByDate, boolean sortByViews) {
		Page pageObject = new Page();
		List<ExcelData> tedsByTag = new ArrayList<>();
		int tempIndex = 0;
		for (int i = index == 0 ? 0 : index.intValue(); tedsByTag.size() < size; i++) {
			List<String> tags = excelData.get(i).getTags();
			if (tags.contains(tag)) {
				tedsByTag.add(excelData.get(i));
			}
			tempIndex = i;
		}
		if (sortByViews || sortByDate) {
			tedsByTag = sortBasedOnParams(tedsByTag, sortByDate, sortByViews);
		}
		pageObject.setCurrentIndex(Long.valueOf(tempIndex));
		pageObject.setContent(tedsByTag);
		pageObject.setLastIndex(index);
		pageObject.setSize(size);
		return pageObject;
	}

	@Override
	public Page tedSearch(String name, String speakerOccupation, String event, String title, Long index, Long size,
			boolean sortByDate, boolean sortByViews) {
		Page pageObject = new Page();
		List<ExcelData> teds = new ArrayList<>();
		int tempIndex = 0;
		if (null == name && null == speakerOccupation && null == event && null == title) {

		}
		else if (null == name && null == speakerOccupation && null == event && null != title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sTitle = excelData.get(i).getTitle();
				if (title.equals(sTitle)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null == name && null == speakerOccupation && null != event && null == title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sEvent = excelData.get(i).getEvent();
				if (event.equals(sEvent)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null == name && null == speakerOccupation && null != event && null != title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sEvent = excelData.get(i).getEvent();
				String sTitle = excelData.get(i).getTitle();
				if (event.equals(sEvent) && title.equals(sTitle)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null == name && null != speakerOccupation && null == event && null == title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sSpeakerOccupation = excelData.get(i).getSpeakerOccupation();
				if (speakerOccupation.equals(sSpeakerOccupation)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null == name && null != speakerOccupation && null == event && null != title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sSpeakerOccupation = excelData.get(i).getSpeakerOccupation();
				String sTitle = excelData.get(i).getTitle();
				if (speakerOccupation.equals(sSpeakerOccupation) && title.equals(sTitle)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null == name && null != speakerOccupation && null != event && null == title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sSpeakerOccupation = excelData.get(i).getSpeakerOccupation();
				String sEvent = excelData.get(i).getEvent();
				if (speakerOccupation.equals(sSpeakerOccupation) && event.equals(sEvent)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null == name && null != speakerOccupation && null != event && null != title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sSpeakerOccupation = excelData.get(i).getSpeakerOccupation();
				String sEvent = excelData.get(i).getEvent();
				String sTitle = excelData.get(i).getTitle();
				if (speakerOccupation.equals(sSpeakerOccupation) && event.equals(sEvent) && title.equals(sTitle)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null != name && null == speakerOccupation && null == event && null == title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sName = excelData.get(i).getName();
				if (name.equals(sName)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null != name && null == speakerOccupation && null == event && null != title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sName = excelData.get(i).getName();
				String sTitle = excelData.get(i).getTitle();
				if (name.equals(sName) && title.equals(sTitle)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null != name && null == speakerOccupation && null != event && null == title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sName = excelData.get(i).getName();
				String sEvent = excelData.get(i).getEvent();
				if (name.equals(sName) && event.equals(sEvent)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null != name && null == speakerOccupation && null != event && null != title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sName = excelData.get(i).getName();
				String sEvent = excelData.get(i).getEvent();
				String sTitle = excelData.get(i).getTitle();
				if (name.equals(sName) && event.equals(sEvent) && title.equals(sTitle)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}

		else if (null != name && null != speakerOccupation && null == event && null == title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sName = excelData.get(i).getName();
				String sSpeakerOccupation = excelData.get(i).getSpeakerOccupation();
				if (name.equals(sName) && speakerOccupation.equals(sSpeakerOccupation)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}
		else if (null != name && null != speakerOccupation && null == event && null != title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sName = excelData.get(i).getName();
				String sSpeakerOccupation = excelData.get(i).getSpeakerOccupation();
				String sTitle = excelData.get(i).getTitle();
				if (name.equals(sName) && speakerOccupation.equals(sSpeakerOccupation) && title.equals(sTitle)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}
		else if (null != name && null != speakerOccupation && null != event && null == title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sName = excelData.get(i).getName();
				String sSpeakerOccupation = excelData.get(i).getSpeakerOccupation();
				String sEvent = excelData.get(i).getEvent();
				if (name.equals(sName) && speakerOccupation.equals(sSpeakerOccupation) && event.equals(sEvent)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}
		else if (null != name && null != speakerOccupation && null != event && null != title) {
			for (int i = index == 0 ? 0 : index.intValue(); teds.size() < size; i++) {
				String sName = excelData.get(i).getName();
				String sSpeakerOccupation = excelData.get(i).getSpeakerOccupation();
				String sEvent = excelData.get(i).getEvent();
				String sTitle = excelData.get(i).getTitle();
				if (name.equals(sName) && speakerOccupation.equals(sSpeakerOccupation) && event.equals(sEvent)
						&& title.equals(sTitle)) {
					teds.add(excelData.get(i));
				}
				tempIndex = i;
			}
		}
		if (sortByViews || sortByDate) {
			teds = sortBasedOnParams(teds, sortByDate, sortByViews);
		}
		pageObject.setCurrentIndex(Long.valueOf(tempIndex));
		pageObject.setContent(teds);
		pageObject.setLastIndex(index);
		pageObject.setSize(size);
		return pageObject;
	}

	private List<ExcelData> sortBasedOnParams(List<ExcelData> list, boolean sortByDate, boolean sortByViews) {
		if (sortByDate) {
			Collections.sort(list, new DateCompartor());
		}
		else if (sortByViews) {
			Collections.sort(list, new ViewsCompartor());
		}
		else if (sortByViews && sortByDate) {
			Collections.sort(list, new MultiSequenceComparator(new DateCompartor(), new ViewsCompartor()));
		}
		return list;
	}

}
