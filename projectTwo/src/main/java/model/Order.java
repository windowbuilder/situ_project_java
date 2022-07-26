package model;

import java.math.BigDecimal;
import java.rmi.server.UID;
import java.sql.Timestamp;

/**
 * @author feiyang
 * @create 2022-07-25 16:37
 * @Description:
 * @FileName: Order
 * @History:
 */
public class Order {
    private String orderNo;
    private Integer uId;
    private String uName;
    private BigDecimal orderMoney;
    private Timestamp createTime;
    private Integer orderStatus;//订单状态 1-待付款 2-待发货 3-已发货 4-已签收 5-退款中 6-已退款 7-交易结束
    private Timestamp deleteTime;

    public Order() {
    }

    public Order(String orderNo, Integer uId, String uName, BigDecimal orderMoney, Timestamp createTime, Integer orderStatus, Timestamp deleteTime) {
        this.orderNo = orderNo;
        this.uId = uId;
        this.uName = uName;
        this.orderMoney = orderMoney;
        this.createTime = createTime;
        this.orderStatus = orderStatus;
        this.deleteTime = deleteTime;
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
     * @return uId
     */
    public Integer getuId() {
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
     * @return uName
     */
    public String getuName() {
        return uName;
    }

    /**
     * 设置
     * @param uName
     */
    public void setUName(String uName) {
        this.uName = uName;
    }

    /**
     * 获取
     * @return orderMoney
     */
    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    /**
     * 设置
     * @param orderMoney
     */
    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
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

    /**
     * 获取
     * @return orderStatus
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置
     * @param orderStatus
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取
     * @return deleteTime
     */
    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    /**
     * 设置
     * @param deleteTime
     */
    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String toString() {
        return "Order{orderNo = " + orderNo + ", uId = " + uId + ", uName = " + uName + ", orderMoney = " + orderMoney + ", createTime = " + createTime + ", orderStatus = " + orderStatus + ", deleteTime = " + deleteTime + "}";
    }
}
