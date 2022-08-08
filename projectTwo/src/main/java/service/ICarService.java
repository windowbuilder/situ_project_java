package service;/**
 * @author feiyang
 * @create 2022-08-04 15:18
 * @Description:
 * @FileName: ICarService
 * @History:
 */

import model.Car;
import model.Order;

import java.util.List;
import java.util.Map;

/**
 * @author: feiyang
 * @create 2022-08-04 15:18
 * @Description:
 * @FileName: ICarService
 * @History:
 * @自定义内容：
 */
public interface ICarService {

    public Map<String, Object> queryIC(Integer sid,Integer pageNo);

    public int addIC(Car car);

    public int updateIC(Car car);
}
