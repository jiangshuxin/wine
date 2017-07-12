package com.wuxian99.finance.basedata.web.view;

import com.google.gson.Gson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 发现、动态
 */
public class DiscoverView implements Serializable {

  private Long discoverId;
  private String pic;
  private String title;
  private String tag;
  private String description;

  public Long getDiscoverId() {
    return discoverId;
  }

  public void setDiscoverId(Long discoverId) {
    this.discoverId = discoverId;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }
}
