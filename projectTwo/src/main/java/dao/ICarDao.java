package dao;/**
 * @author feiyang
 * @create 2022-08-04 14:57
 * @Description:
 * @FileName: ICarDao
 * @History:
 */

import model.Car;
import model.Detail;
import model.Order;

import java.util.List;
import java.util.Map;

/**
 * @author: feiyang
 * @create 2022-08-04 14:57
 * @Description:
 * @FileName: ICarDao
 * @History:
 * @自定义内容：
 */
public interface ICarDao {

    public int queryCC(Car car);

    public List<Car> queryIC(Map<String,Object> map);

    public int addIC(Car car);

    public int updateIC(Car car);

    public int deleteI(Car car);
}
