package dao;/**
 * @author feiyang
 * @create 2022-08-02 9:03
 * @Description:
 * @FileName: IUseDao
 * @History:
 */

import model.GoodsSort;
import model.User;

import java.util.List;

/**
 * @author: feiyang
 * @create 2022-08-02 9:03
 * @Description:
 * @FileName: IUseDao
 * @History:
 * @自定义内容：
 */
public interface IUseDao {

    public List<User> queryI(User user);

    public int addI(User user);

    public int updateI(User user);

    public int upMoney(User user);
}
