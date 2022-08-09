package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author feiyang
 * @create 2022-08-06 10:04
 * @Description:
 * @FileName: Trade
 * @History:
 */
public class Trade {
    private Integer id;
    private Integer uId;
    private Integer tradeType;//1-充值、2-支付
    private String orderNo;
    private BigDecimal tradeMoney;
    private Timestamp createTime;

    public Trade() {
    }

    public Trade(Integer uId, Integer tradeType, String orderNo, BigDecimal tradeMoney) {
        this.uId = uId;
        this.tradeType = tradeType;
        this.orderNo = orderNo;
        this.tradeMoney = tradeMoney;
    }

    public Trade(Integer id, Integer uId, Integer tradeType, String orderNo, BigDecimal tradeMoney, Timestamp createTime) {
        this.id = id;
        this.uId = uId;
        this.tradeType = tradeType;
        this.orderNo = orderNo;
        this.tradeMoney = tradeMoney;
        this.createTime = createTime;
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
     * @return tradeType
     */
    public Integer getTradeType() {
        return tradeType;
    }

    /**
     * 设置
     * @param tradeType
     */
    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
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
     * @return tradeMoney
     */
    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    /**
     * 设置
     * @param tradeMoney
     */
    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    /**
     * 获取
     * @return createTime
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return "Trade{id = " + id + ", uId = " + uId + ", tradeType = " + tradeType + ", orderNo = " + orderNo + ", tradeMoney = " + tradeMoney + ", createTime = " + createTime + "}";
    }
}
