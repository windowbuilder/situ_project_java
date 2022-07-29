package service.impl;

import dao.IGooDao;
import dao.IOrdDao;
import model.Detail;
import model.Goods;
import model.Manager;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IOrdService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-25 20:03
 * @Description:
 * @FileName: OrdService
 * @History:
 */
@Service
public class OrdService implements IOrdService {
    @Autowired
    private IOrdDao ordDao;

    @Override
    public List<Detail> queryCD(Detail detail) {
        if (detail.getOrderNo() == null || detail.getOrderNo().equals("")){
            return null;
        }
        List<Detail> list = ordDao.queryCD(detail);
        return list;
    }

    @Override
    public Map<String, Object> queryO(Order order, Integer pageNo) {
        if (pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        int rowCount = 10;
        int totalCount = ordDao.queryCO(order);
        int totalPage = totalCount / rowCount;
        if (totalCount % rowCount != 0 || totalPage < 1){
            totalPage ++;
        }
        if (pageNo > totalPage){
            pageNo = totalPage;
        }
        int limitFirst = (pageNo - 1) * rowCount;
        Map<String, Object> map = new HashMap<>();
        map.put("order",order);
        map.put("limitFirst",limitFirst);
        map.put("rowCount",rowCount);
        List<Order> list = ordDao.queryO(map);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list",list);
        resMap.put("pageNo",pageNo);
        resMap.put("totalPage",totalPage);
        return resMap;
    }

    @Override
    public int addo(Order order) {
        return 0;
    }

    @Override
    public int updateO(String[] arr,Order order) {
        if (arr == null || arr.length == 0){
            return -1;
        }
        int count = 0;
        for (String orderNo : arr) {
            order.setOrderNo(orderNo);
            int updateG = ordDao.updateO(order);
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
