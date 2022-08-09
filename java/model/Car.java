package model;

import java.math.BigDecimal;

/**
 * @author feiyang
 * @create 2022-08-04 10:20
 * @Description:
 * @FileName: Car
 * @History:
 */
public class Car {
    private Integer id;
    private Integer uId;
    private Integer gId;
    private Integer buyCount;
    private String gName;
    private String imgUrl;
    private BigDecimal gPrice;
    private BigDecimal totalMoney;
    private Integer cStatus;//0-未操作，1购买，2删除

    public Car() {
    }

    public Car(Integer uId, Integer gId) {
        this.uId = uId;
        this.gId = gId;
    }

    public Car(Integer id, Integer uId, Integer gId, Integer buyCount, String gName, String imgUrl, BigDecimal gPrice, BigDecimal totalMoney, Integer cStatus) {
        this.id = id;
        this.uId = uId;
        this.gId = gId;
        this.buyCount = buyCount;
        this.gName = gName;
        this.imgUrl = imgUrl;
        this.gPrice = gPrice;
        this.totalMoney = totalMoney;
        this.cStatus = cStatus;
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
     * @return uId
     */
    public Integer getUId() {
        return uId;
    }

    /**
     * 设置
     * @param uId
     */
    public void setUId(Integer uId) {
        this.uId = uId;
    }

    /**
     * 获取
     * @return gId
     */
    public Integer getGId() {
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
    public BigDecimal getGPrice() {
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

    /**
     * 获取
     * @return cStatus
     */
    public Integer getCStatus() {
        return cStatus;
    }

    /**
     * 设置
     * @param cStatus
     */
    public void setCStatus(Integer cStatus) {
        this.cStatus = cStatus;
    }

    public String toString() {
        return "Car{id = " + id + ", uId = " + uId + ", gId = " + gId + ", buyCount = " + buyCount + ", gName = " + gName + ", imgUrl = " + imgUrl + ", gPrice = " + gPrice + ", totalMoney = " + totalMoney + ", cStatus = " + cStatus + "}";
    }
}
