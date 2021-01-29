package com.ivc.talonsystem.util;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.ivc.talonsystem.entity.AbstractCompany;
import com.ivc.talonsystem.entity.Violguide;

public class MapUtility {
	 public static <K extends Comparable<K>, V > Map<Violguide, Map<AbstractCompany, Integer>> sortOnKeys(Map<Violguide, Map<AbstractCompany, Integer>> perviolguide) {
		 Map<Violguide, Map<AbstractCompany, Integer>> sortedMap = new TreeMap<Violguide, Map<AbstractCompany, Integer>>(new Comparator<Violguide>() {

			@Override
			public int compare(Violguide key1, Violguide key2) {
				// TODO Auto-generated method stub
				return key1.getId().compareTo(key2.getId());
			}
		});
	        sortedMap.putAll(perviolguide);
	        return sortedMap;
	    }
}

