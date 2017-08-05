package com.wuxian99.finance.basedata.web.view;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Banner列表展示Bean
 */
public class BannerListView implements Serializable {

  private String pic;
  private Long mdseId;

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public Long getMdseId() {
    return mdseId;
  }

  public void setMdseId(Long mdseId) {
    this.mdseId = mdseId;
  }
}
