package model;

/**
 * @author feiyang
 * @create 2022-07-21 16:52
 * @Description:
 * @FileName: Manager
 * @History:
 */
public class Manager {
    private Integer id;
    private String mName;
    private String mPass;
    private Integer mLevel;//权限 1-超级管理员 0-普通管理员
    private Integer mStatus;//状态 1-启用 0-禁用

    public Manager() {
    }

    public Manager(Integer id, String mName, String mPass, Integer mLevel, Integer mStatus) {
        this.id = id;
        this.mName = mName;
        this.mPass = mPass;
        this.mLevel = mLevel;
        this.mStatus = mStatus;
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
     * @return mName
     */
    public String getmName() {
        return mName;
    }

    /**
     * 设置
     * @param mName
     */
    public void setMName(String mName) {
        this.mName = mName;
    }

    /**
     * 获取
     * @return mPass
     */
    public String getmPass() {
        return mPass;
    }

    /**
     * 设置
     * @param mPass
     */
    public void setMPass(String mPass) {
        this.mPass = mPass;
    }

    /**
     * 获取
     * @return mLevel
     */
    public Integer getmLevel() {
        return mLevel;
    }

    /**
     * 设置
     * @param mLevel
     */
    public void setMLevel(Integer mLevel) {
        this.mLevel = mLevel;
    }

    /**
     * 获取
     * @return mStatus
     */
    public Integer getmStatus() {
        return mStatus;
    }

    /**
     * 设置
     * @param mStatus
     */
    public void setMStatus(Integer mStatus) {
        this.mStatus = mStatus;
    }

    public String toString() {
        return "Manager{id = " + id + ", mName = " + mName + ", mPass = " + mPass + ", mLevel = " + mLevel + ", mStatus = " + mStatus + "}";
    }
}
