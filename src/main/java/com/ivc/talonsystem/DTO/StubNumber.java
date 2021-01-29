package com.ivc.talonsystem.DTO;

import java.util.ArrayList;
import java.util.List;

public final class StubNumber {
	public static final int firststubnum=1;
	public static final int secondstubnum=2;
	public static final int thirdstubnum=3;
	
	public static List<Integer> stubnums;
	private StubNumber() {}
	
	public static List<Integer> getStubnums(){
		stubnums=new ArrayList<>();
		stubnums.add(firststubnum);
		stubnums.add(secondstubnum);
		stubnums.add(thirdstubnum);
		return stubnums;
	}
	public static void main(String [] args) {
		StubNumber.getStubnums().forEach(s->{
			System.out.println(s);
		});
	}
}
