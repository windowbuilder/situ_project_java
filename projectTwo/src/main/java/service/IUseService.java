package service;/**
 * @author feiyang
 * @create 2022-08-02 19:47
 * @Description:
 * @FileName: IUseService
 * @History:
 */

import model.User;

import java.util.List;
import java.util.Map;

/**
 * @author: feiyang
 * @create 2022-08-02 19:47
 * @Description:
 * @FileName: IUseService
 * @History:
 * @自定义内容：
 */
public interface IUseService {

    public User queryI(User user);

    public int addI(User user);

    public int updateI(User user);

    public Map<String, Object> updateM(Integer uMoney, User m);
}
