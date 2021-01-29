package com.ivc.talonsystem.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.Violation;

public class GenerateMapUtil {
	public static Map<AbstractCompany, Long> sortedMap;
	
	public static Map<AbstractCompany, Long> getReverseSortedMap(Map<AbstractCompany, Long> map){
		sortedMap = new LinkedHashMap<AbstractCompany, Long>();
		map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEachOrdered(c -> sortedMap.put(c.getKey(), c.getValue()));
		return sortedMap;
	}
	
	public static Map<AbstractCompany, Long> getCountPerCompany(List<Violation> violationlist){
		sortedMap=violationlist.stream().
				collect(Collectors.groupingBy(Violation::getCompany, Collectors.counting()));
		return sortedMap;
	}

}
