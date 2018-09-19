/**
 * 
 */
package com.sb.hackerearth.ted.service;

import java.util.Set;

import com.sb.hackerearth.ted.pojo.ExcelData;
import com.sb.hackerearth.ted.pojo.Page;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
public interface TedService {

	Set<String> getUniqueTags();

	void readCsvFile();

	Set<ExcelData> getTedsByTag(String tag);

	Page getTedsByTagByPagination(String tag, Long page, Long size, boolean sortByDate, boolean sortByViews);

	Page tedSearch(String name, String speakerOccupation, String event, String title, Long index, Long size, boolean sortByDate,
			boolean sortByViews);

}
