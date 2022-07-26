package service;/**
 * @author feiyang
 * @create 2022-07-21 17:25
 * @Description:
 * @FileName: IManService
 * @History:
 */

import model.Manager;

import java.util.List;
import java.util.Map;

/**
 * @author: feiyang
 * @create 2022-07-21 17:25
 * @Description:
 * @FileName: IManService
 * @History:
 * @自定义内容：
 */
public interface IManService {
    public Manager querylogin(Manager manager);

    public Map<String,Object> query(Manager manager, Integer pageNo);

    public int add(Manager manager);

    public int update(Manager manager);
}
