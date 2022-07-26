package model;

import java.math.BigDecimal;

/**
 * @author feiyang
 * @create 2022-07-22 19:44
 * @Description:商品实体类
 * @FileName: Goods
 * @History:
 */
public class Goods {
    private Integer id;
    private String gName;
    private String imgUrl;
    private BigDecimal gPrice;
    private Integer gCount;
    private Integer gStatus;//商品状态 1-上架 0-下架
    private Integer soId;
    private String soName;
    private Integer sId;
    private String sName;

    public Goods() {
    }

    public Goods(Integer id, String gName, String imgUrl, BigDecimal gPrice, Integer gCount, Integer gStatus, Integer soId, String soName, Integer sId, String sName) {
        this.id = id;
        this.gName = gName;
        this.imgUrl = imgUrl;
        this.gPrice = gPrice;
        this.gCount = gCount;
        this.gStatus = gStatus;
        this.soId = soId;
        this.soName = soName;
        this.sId = sId;
        this.sName = sName;
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
     * @return gCount
     */
    public Integer getgCount() {
        return gCount;
    }

    /**
     * 设置
     * @param gCount
     */
    public void setGCount(Integer gCount) {
        this.gCount = gCount;
    }

    /**
     * 获取
     * @return gStatus
     */
    public Integer getGStatus() {
        return gStatus;
    }

    /**
     * 设置
     * @param gStatus
     */
    public void setGStatus(Integer gStatus) {
        this.gStatus = gStatus;
    }

    /**
     * 获取
     * @return soId
     */
    public Integer getSoId() {
        return soId;
    }

    /**
     * 设置
     * @param soId
     */
    public void setSoId(Integer soId) {
        this.soId = soId;
    }

    /**
     * 获取
     * @return soName
     */
    public String getSoName() {
        return soName;
    }

    /**
     * 设置
     * @param soName
     */
    public void setSoName(String soName) {
        this.soName = soName;
    }

    /**
     * 获取
     * @return sId
     */
    public Integer getSId() {
        return sId;
    }

    /**
     * 设置
     * @param sId
     */
    public void setSId(Integer sId) {
        this.sId = sId;
    }

    /**
     * 获取
     * @return sName
     */
    public String getsName() {
        return sName;
    }

    /**
     * 设置
     * @param sName
     */
    public void setSName(String sName) {
        this.sName = sName;
    }

    public String toString() {
        return "Goods{id = " + id + ", gName = " + gName + ", imgUrl = " + imgUrl + ", gPrice = " + gPrice + ", gCount = " + gCount + ", gStatus = " + gStatus + ", soId = " + soId + ", soName = " + soName + ", sId = " + sId + ", sName = " + sName + "}";
    }
}
