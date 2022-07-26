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

    //后台界面数据查询
    @Override
    public Map<String,Object> queryG(Goods goods, Integer pageNo) {
        if (pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        int rowCount = 4;
        int totalCount = gooDao.queryCountG(goods);
        int totalPage = totalCount / rowCount;
        if (totalCount % rowCount != 0 || totalPage < 1){
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

    //加载前台商品列表界面
    @Override
    public Map<String, Object> queryRI(Integer sid, Integer pageNo,String gName) {
        if (pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        int rowCount = 15;
        Map<String, Object> map = new HashMap<>();
        if (gName != null && gName != ""){
            gName = "%"+gName+"%";
        }
        map.put("gName",gName);
        map.put("sid",sid);
        map.put("gStatus",1);
        int totalCount = gooDao.queryRC(map);
        int totalPage = totalCount / rowCount;
        if (totalCount % rowCount != 0 || totalPage < 1){
            totalPage ++;
        }
        if (pageNo > totalPage){
            pageNo = totalPage;
        }
        int limitFirst = (pageNo - 1) * rowCount;
        map.put("limitFirst",limitFirst);
        map.put("rowCount",rowCount);
        List<Goods> list = gooDao.queryRI(map);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list",list);
        resMap.put("pageNo",pageNo);
        resMap.put("totalPage",totalPage);
        return resMap;
    }

    //前台单件商品详情查询
    @Override
    public Goods queryRO(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("gStatus",1);
        Goods goods = gooDao.queryRI(map).get(0);
        return goods;
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
    public int addp(GoodsSort goodsSort) {
        int addp = gooDao.addp(goodsSort);
        return addp;
    }

    @Override
    public int updateG(Goods goods) {
        int updateG = gooDao.updateG(goods);
        return updateG;
    }

    @Override
    public int upsn(GoodsSort goodsSort) {
        int upsn = gooDao.upsn(goodsSort);
        return upsn;
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

    @Override
    public int delg(Integer id) {
        int delg = gooDao.delg(id);
        return delg;
    }
}
