package service;/**
 * @author feiyang
 * @create 2022-07-25 19:49
 * @Description:
 * @FileName: IOrdService
 * @History:
 */

import model.Detail;
import model.Manager;
import model.Order;

import java.util.List;
import java.util.Map;

/**
 * @author: feiyang
 * @create 2022-07-25 19:49
 * @Description:
 * @FileName: IOrdService
 * @History:
 * @自定义内容：
 */
public interface IOrdService {
    public List<Detail> queryCD(Detail detail);

    public List<Detail> queryACD(Detail detail);

    public List<Order> queryIO(Integer uId,Integer orderStatus);

    public Map<String,Object> queryO(Order order, Integer pageNo);

    public int addo(Order order);

    public int updateO(String[] arr,Order order);

    public int updateAO(Order order);
}
