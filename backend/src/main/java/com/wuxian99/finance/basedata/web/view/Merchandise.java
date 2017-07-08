package com.wuxian99.finance.basedata.web.view;

import java.io.Serializable;

/**
 * Created by sxjiang on 2017/2/27.
 */
public class Merchandise implements Serializable{

	private static final long serialVersionUID = 6207546222157853770L;
	
	private String id;
    private String name;
    private String type;
    private String attr;
    private String desc;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
