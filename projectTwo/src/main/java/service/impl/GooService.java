package service.impl;

import dao.IGooDao;
import dao.IManDao;
import model.Goods;
import model.GoodsSort;
import model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IGooService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-22 20:59
 * @Description:
 * @FileName: GooService
 * @History:
 */
@Service
public class GooService implements IGooService {
    @Autowired
    private IGooDao gooDao;

    //界面数据查询
    @Override
    public Map<String,Object> queryG(Goods goods, Integer pageNo) {
        if (pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        int rowCount = 10;
        int totalCount = gooDao.queryCountG(goods);
        int totalPage = totalCount / rowCount;
        if (totalCount % rowCount != 0){
            totalPage ++;
        }
        if (pageNo > totalPage){
            pageNo = totalPage;
        }
        int limitFirst = (pageNo - 1) * rowCount;
        Map<String, Object> map = new HashMap<>();
        map.put("goods",goods);
        map.put("limitFirst",limitFirst);
        map.put("rowCount",rowCount);
        List<Goods> list = gooDao.queryG(map);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list",list);
        resMap.put("pageNo",pageNo);
        resMap.put("totalPage",totalPage);
        return resMap;
    }

    @Override
    public Goods queryGo(Map<String,Object> map) {
        Goods goods = gooDao.queryG(map).get(0);
        return goods;
    }

    @Override
    public List<GoodsSort> queryClassG(GoodsSort goodsSort) {
        List<GoodsSort> list = gooDao.queryClassG(goodsSort);
        return list;
    }

    @Override
    public int addG(Goods goods) {
        int addG = gooDao.addG(goods);
        return addG;
    }

    @Override
    public int updateG(Goods goods) {
        int updateG = gooDao.updateG(goods);
        return updateG;
    }

    //上架，下架
    @Override
    public int updown(String[] arr,Goods goods) {
        if (arr == null || arr.length == 0){
            return -1;
        }
        int count = 0;
        for (String id : arr) {
            goods.setId(Integer.valueOf(id));
            int updateG = gooDao.updateG(goods);
            count += updateG;
        }
        if (count == arr.length){
            return 1;
        }else if (count == 0){
            return 0;
        }else {
            return -1;
        }
    }
}
