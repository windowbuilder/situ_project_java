package service.impl;

import dao.IManDao;
import model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IManService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-21 17:26
 * @Description:
 * @FileName: ManService
 * @History:
 */
@Service
public class ManService implements IManService {
    @Autowired
    private IManDao manDao;

    //登录验证
    @Override
    public Manager querylogin(Manager manager) {
        if (manager.getmName() == null || manager.getmName().isEmpty()) return null;
        if (manager.getmPass() == null || manager.getmPass().isEmpty()) return null;
        manager.setMStatus(1);
        List<Manager> list = manDao.querylogin(manager);
        if (list == null || list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }
    }

    //页面数据查询
    @Override
    public Map<String,Object> query(Manager manager, Integer pageNo) {
        if (pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        int rowCount = 10;
        int totalCount = manDao.queryCount(manager);
        int totalPage = totalCount / rowCount;
        if (totalCount % rowCount != 0){
            totalPage ++;
        }
        if (pageNo > totalPage){
            pageNo = totalPage;
        }
        int limitFirst = (pageNo - 1) * rowCount;
        Map<String, Object> map = new HashMap<>();
        map.put("manager",manager);
        map.put("limitFirst",limitFirst);
        map.put("rowCount",rowCount);
        List<Manager> list = manDao.query(map);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list",list);
        resMap.put("pageNo",pageNo);
        resMap.put("totalPage",totalPage);
        return resMap;
    }

    @Override
    public int add(Manager manager) {
        return manDao.add(manager);
    }

    @Override
    public int update(Manager manager) {
        return manDao.update(manager);
    }
}
