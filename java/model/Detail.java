package model;

import java.math.BigDecimal;

/**
 * @author feiyang
 * @create 2022-07-25 16:45
 * @Description:
 * @FileName: Detail
 * @History:
 */
public class Detail {
    private Integer id;
    private String orderNo;
    private Integer gId;
    private String gName;
    private String imgUrl;
    private BigDecimal gPrice;
    private Integer buyCount;
    private BigDecimal totalMoney;

    public Detail() {
    }

    public Detail(Integer id, String orderNo, Integer gId, String gName, String imgUrl, BigDecimal gPrice, Integer buyCount, BigDecimal totalMoney) {
        this.id = id;
        this.orderNo = orderNo;
        this.gId = gId;
        this.gName = gName;
        this.imgUrl = imgUrl;
        this.gPrice = gPrice;
        this.buyCount = buyCount;
        this.totalMoney = totalMoney;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取
     * @return gId
     */
    public Integer getgId() {
        return gId;
    }

    /**
     * 设置
     * @param gId
     */
    public void setGId(Integer gId) {
        this.gId = gId;
    }

    /**
     * 获取
     * @return gName
     */
    public String getgName() {
        return gName;
    }

    /**
     * 设置
     * @param gName
     */
    public void setGName(String gName) {
        this.gName = gName;
    }

    /**
     * 获取
     * @return imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置
     * @param imgUrl
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取
     * @return gPrice
     */
    public BigDecimal getgPrice() {
        return gPrice;
    }

    /**
     * 设置
     * @param gPrice
     */
    public void setGPrice(BigDecimal gPrice) {
        this.gPrice = gPrice;
    }

    /**
     * 获取
     * @return buyCount
     */
    public Integer getBuyCount() {
        return buyCount;
    }

    /**
     * 设置
     * @param buyCount
     */
    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    /**
     * 获取
     * @return totalMoney
     */
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    /**
     * 设置
     * @param totalMoney
     */
    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String toString() {
        return "Detail{id = " + id + ", orderNo = " + orderNo + ", gId = " + gId + ", gName = " + gName + ", imgUrl = " + imgUrl + ", gPrice = " + gPrice + ", buyCount = " + buyCount + ", totalMoney = " + totalMoney + "}";
    }
}
