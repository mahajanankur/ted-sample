/**
 * 
 */
package com.sb.hackerearth.ted.utils;

import java.util.Comparator;

import com.sb.hackerearth.ted.pojo.ExcelData;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
public class ViewsCompartor implements Comparator<ExcelData> {

	@Override
	public int compare(ExcelData o1, ExcelData o2) {
		if (o1.getViews() == o2.getViews()) {
			return 0;
		}
		else if (o1.getViews() > o2.getViews()) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
