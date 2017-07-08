package com.wuxian99.finance.common;

/**
 * Created by sxjiang on 2017/3/3.
 */
public class SubQueryCommand {
    private String data;
    private String name;
    private Boolean searchable;
    private Boolean orderable;
    private Boolean nullable;
    private SearchCommand search;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public Boolean getOrderable() {
        return orderable;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
    }

    public SearchCommand getSearch() {
        return search;
    }

    public void setSearch(SearchCommand search) {
        this.search = search;
    }

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}
}
