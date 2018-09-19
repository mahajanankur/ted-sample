/**
 * 
 */
package com.sb.hackerearth.ted.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.sb.hackerearth.ted.pojo.ExcelData;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
public class MultiSequenceComparator implements Comparator<ExcelData> {

	private List<Comparator<ExcelData>> compList;

	public MultiSequenceComparator(Comparator<ExcelData>... comparator) {
		this.compList = Arrays.asList(comparator);
	}

	@Override
	public int compare(ExcelData o1, ExcelData o2) {

		for (Comparator<ExcelData> com : compList) {
			int compare = com.compare(o1, o2);
			if (compare != 0) {
				return compare;
			}
		}
		return 0;
	}

}
