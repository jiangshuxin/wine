package com.wuxian99.finance.common;

import java.util.List;

public class ResultList<T> {

	private List<T> result;
	private int totalCount = -1;

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
