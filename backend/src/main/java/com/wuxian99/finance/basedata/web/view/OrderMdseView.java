package com.wuxian99.finance.basedata.web.view;

/**
 * 订单列表商品信息
 */
public class OrderMdseView {

  private String name;
  private String pic;
  private Long price;
  private Long count;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }
}
