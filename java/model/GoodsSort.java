package model;

/**
 * @author feiyang
 * @create 2022-07-22 20:40
 * @Description:
 * @FileName: GoodsSort
 * @History:
 */
public class GoodsSort {
    private Integer id;
    private String sName;
    private Integer parentId;

    public GoodsSort() {
    }

    public GoodsSort(Integer id, String sName, Integer parentId) {
        this.id = id;
        this.sName = sName;
        this.parentId = parentId;
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

    /**
     * 获取
     * @return parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String toString() {
        return "GoodsSort{id = " + id + ", sName = " + sName + ", parentId = " + parentId + "}";
    }
}
