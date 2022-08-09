package service.impl;

import dao.IGooDao;
import dao.IOrdDao;
import dao.ITraDao;
import dao.IUseDao;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IOrdService;

import java.math.BigDecimal;
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
    @Autowired
    private IUseDao useDao;
    @Autowired
    private IGooDao gooDao;
    @Autowired
    private ITraDao traDao;

    @Override
    public List<Detail> queryCD(Detail detail) {
        if (detail.getOrderNo() == null || detail.getOrderNo().equals("")){
            return null;
        }
        List<Detail> list = ordDao.queryCD(detail);
        return list;
    }

    @Override
    public List<Detail> queryACD(Detail detail) {
        List<Detail> list = ordDao.queryCD(detail);
        return list;
    }

    @Override
    public List<Order> queryIO(Integer uId,Integer orderStatus) {
        Map<String, Object> map = new HashMap<>();
        Order order = new Order();
        order.setUId(uId);
        order.setOrderStatus(orderStatus);
        map.put("order",order);
        List<Order> list = ordDao.queryO(map);
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
        int addo = ordDao.addo(order);
        return addo;
    }

    //管理员退款
    @Override
    @Transactional
    public int updateO(String[] arr,Order order) {
        //1 修改订单状态
        if (arr == null || arr.length == 0){
            return -1;
        }
        for (String orderNo : arr) {
            order.setOrderNo(orderNo);
            int updateG = ordDao.updateO(order);
            if (updateG < 1){
                return 0;
            }
            //2 退款，更新账户余额
            Detail detail = new Detail();
            detail.setOrderNo(orderNo);
            order.setOrderStatus(null);
            Map<String, Object> map = new HashMap<>();
            map.put("order",order);
            Order order1 = ordDao.queryO(map).get(0);
            List<Detail> list = ordDao.queryCD(detail);
            User user = new User(order1.getuId());
            user.setUMoney(order1.getOrderMoney().multiply(new BigDecimal(-1)));
            int i1 = useDao.upMoney(user);
            if (i1 < 1){
                return 0;
            }
            //3 退还库存
            for (Detail detail1 : list) {
                map.put("id",detail1.getgId());
                map.put("buyCount",detail1.getBuyCount()*-1);
                int i = gooDao.subCount(map);
                if (i < 1){
                    return 0;
                }
                //4 生成退款交易流水，交易类型3
                Trade trade = new Trade(order1.getuId(),3,orderNo,order1.getOrderMoney());
                int addI = traDao.addI(trade);
                if (addI < 1){
                    return 0;
                }
            }
        }
        return 1;
    }

    @Override
    @Transactional
    public int updateAO(Order order) {
        Map<String, Object> map = new HashMap<>();
        if(order.getOrderStatus() == null){
            order.setOrderStatus(1);
            //查询订单信息
            map.put("order",order);
            List<Order> list = ordDao.queryO(map);
            ordDao.jobForOrder(order);
            //改变订单状态
            for (Order order1:list) {
                Detail detail = new Detail();
                detail.setOrderNo(order1.getOrderNo());
                //订单详情
                List<Detail> list1 = ordDao.queryCD(detail);
                //返还库存
                for (Detail detail1:list1) {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("id",detail1.getgId());
                    map1.put("buyCount",detail1.getBuyCount()*-1);
                    int i = gooDao.subCount(map1);
                    if (i < 1){
                        return 0;
                    }
                }
            }
        }else if (order.getOrderStatus() == 7){
            order.setOrderStatus(null);
            map.put("order",order);
            List<Order> list = ordDao.queryO(map);
            ordDao.jobForOrder(order);
            //改变订单状态
            for (Order order1:list) {
                Detail detail = new Detail();
                detail.setOrderNo(order1.getOrderNo());
                //订单详情
                List<Detail> list1 = ordDao.queryCD(detail);
                //返还库存
                for (Detail detail1:list1) {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("id",detail1.getgId());
                    map1.put("buyCount",detail1.getBuyCount()*-1);
                    int i = gooDao.subCount(map1);
                    if (i < 1){
                        return 0;
                    }
                }
            }
        }else {
            ordDao.updateO(order);
        }
        return 1;
    }
}
