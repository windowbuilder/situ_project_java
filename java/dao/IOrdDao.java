package dao;/**
 * @author feiyang
 * @create 2022-07-25 19:02
 * @Description:
 * @FileName: IOrdDao
 * @History:
 */

import dao.impl.OrdDao;
import model.Detail;
import model.Manager;
import model.Order;

import java.util.List;
import java.util.Map;

/**
 * @author: feiyang
 * @create 2022-07-25 19:02
 * @Description:
 * @FileName: IOrdDao
 * @History:
 * @自定义内容：
 */
public interface IOrdDao {
    public List<Detail> queryCD(Detail detail);

    public List<Order> queryO(Map<String,Object> map);

    public int queryCO(Order order);

    public int addD(Detail detail);

    public int addo(Order order);

    public int updateO(Order order);

    public int jobForOrder(Order order);
}
