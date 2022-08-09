package model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author feiyang
 * @create 2022-08-02 8:58
 * @Description:
 * @FileName: User
 * @History:
 */
public class User {
    private Integer id;
    private String uName;
    private String uPhone;
    private String uPass;
    private BigDecimal uMoney;
    private Timestamp createTime;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String uName, String uPhone, String uPass, BigDecimal uMoney, Timestamp createTime) {
        this.id = id;
        this.uName = uName;
        this.uPhone = uPhone;
        this.uPass = uPass;
        this.uMoney = uMoney;
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
     * @return uName
     */
    public String getUName() {
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
     * @return uPhone
     */
    public String getUPhone() {
        return uPhone;
    }

    /**
     * 设置
     * @param uPhone
     */
    public void setUPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    /**
     * 获取
     * @return uPass
     */
    public String getUPass() {
        return uPass;
    }

    /**
     * 设置
     * @param uPass
     */
    public void setUPass(String uPass) {
        this.uPass = uPass;
    }

    /**
     * 获取
     * @return uMoney
     */
    public BigDecimal getUMoney() {
        return uMoney;
    }

    /**
     * 设置
     * @param uMoney
     */
    public void setUMoney(BigDecimal uMoney) {
        this.uMoney = uMoney;
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
        return "User{id = " + id + ", uName = " + uName + ", uPhone = " + uPhone + ", uPass = " + uPass + ", uMoney = " + uMoney + ", createTime = " + createTime + "}";
    }
}
