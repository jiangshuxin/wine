package com.wuxian99.finance.basedata.support.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RSUtils {
	public static List<String> getCoulumnNames(ResultSet rs) throws SQLException {
		List<String> columNames = new ArrayList<>();
		int count = rs.getMetaData().getColumnCount();
		for (int i=1; i<=count; i++) {
			columNames.add(rs.getMetaData().getColumnName(i).toLowerCase());
		}
		return columNames;
	}
}
