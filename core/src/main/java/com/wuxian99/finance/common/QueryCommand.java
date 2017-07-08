package com.wuxian99.finance.common;

import java.util.List;

/**
 * Created by sxjiang on 2017/3/3.
 */
public class QueryCommand implements Command {
	private String module;
    private Integer start;
    private Integer length;
    private Integer draw;
    private List<SubQueryCommand> columns;
    private List<OrderCommand> order;
    private SearchCommand search;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public List<SubQueryCommand> getColumns() {
        return columns;
    }

    public void setColumns(List<SubQueryCommand> columns) {
        this.columns = columns;
    }

    public List<OrderCommand> getOrder() {
        return order;
    }

    public void setOrder(List<OrderCommand> order) {
        this.order = order;
    }

    public SearchCommand getSearch() {
        return search;
    }

    public void setSearch(SearchCommand search) {
        this.search = search;
    }

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getAction() {
		return Constants.FIND;
	}

	@Override
	public boolean isQuery() {
		return true;
	}
}
