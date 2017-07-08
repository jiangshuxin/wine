package com.wuxian99.finance.common;

/**
 *
 */
public interface Command {
	String getAction();
	
	String getModule();
	
	boolean isQuery();
}
