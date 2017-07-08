package com.wuxian99.finance.basedata.domain.model;

import java.util.Map;

public class WaitingInfo {
	private int count;
	private Map<String, Object> modules;
	private Map<String, Integer> countMap;
	private Map<String, String> pathMap;

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public Map<String, Object> getModules() {
		return modules;
	}

	public void setModules(Map<String, Object> modules) {
		this.modules = modules;
	}

	public Map<String, Integer> getCountMap() {
		return countMap;
	}

	public void setCountMap(Map<String, Integer> countMap) {
		this.countMap = countMap;
	}

	public Map<String, String> getPathMap() {
		return pathMap;
	}

	public void setPathMap(Map<String, String> pathMap) {
		this.pathMap = pathMap;
	}

	public static void main(String[] args) {
		System.out.println(0&1);
		System.out.println(1&1);
		System.out.println(2&1);
		System.out.println(3&1);
		
		System.out.println(0&2);
		System.out.println(1&2);
		System.out.println(2&2);
		System.out.println(3&2);
	}
}
