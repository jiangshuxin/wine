package com.wuxian99.finance.common;

import java.util.Map;

/**
 * Created by sxjiang on 2017/3/1.
 */
public class CUDCommand implements Command {
	private String action;

	private String module;
	private Map<String, Map<String, Object>> data;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Map<String, Map<String, Object>> getData() {
		return data;
	}

	public void setData(Map<String, Map<String, Object>> data) {
		this.data = data;
	}

	public String getAction() {
		if (Constants.ACTION_MAP.get(Constants.DELETE).contains(action))
			return Constants.DELETE;
		return Constants.SAVE;
	}
	
	public String getPreAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public boolean isQuery() {
		return false;
	}
}
