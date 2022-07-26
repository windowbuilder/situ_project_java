package dao;

import model.Manager;

import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-21 17:08
 * @Description:
 * @FileName: IManDao
 * @History:
 */
public interface IManDao {
    public List<Manager> querylogin(Manager manager);

    public List<Manager> query(Map<String,Object> map);

    public int queryCount(Manager manager);

    public int add(Manager manager);

    public int update(Manager manager);
}
