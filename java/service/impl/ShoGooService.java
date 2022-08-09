package service.impl;

import dao.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IShoGooService;
import util.AopUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-08-05 14:05
 * @Description:
 * @FileName: ShoGooService
 * @History:
 */
@Service
public class ShoGooService implements IShoGooService {
    @Autowired
    private IGooDao gooDao;
    @Autowired
    private IUseDao useDao;
    @Autowired
    private IOrdDao ordDao;
    @Autowired
    private ITraDao traDao;
    @Autowired
    private ICarDao carDao;

    //加入购物车
    @Override
    public Map<String, Object> addCar(Integer uid, Integer gid, Integer buyCount) {
        Map<String, Object> returnMap = new HashMap<>();
        //根据用户ID和商品ID查询购物车表是否存在记录
        Car car = new Car(uid,gid);
        Map<String, Object> map = new HashMap<>();
        map.put("car",car);
        List<Car> list = carDao.queryIC(map);
        int carSize = list.size();
        if (carSize > 1){
            //如果存在，则购买数量累加
            list.get(0).setBuyCount(buyCount);
            int i = carDao.updateIC(list.get(0));
            returnMap.put("code",i);
        }else {
            //如果不存在，则插入一条购物车记录
            car.setBuyCount(buyCount);
            car.setCStatus(0);
            int i = carDao.addIC(car);
            returnMap.put("code",i);
        }
        return returnMap;
    }

    //生成订单、删除购物车记录
    @Override
    @Transactional
    public Map<String, Object> buyDeleteCar(Integer id, Integer gid, Integer buyCount) {
        Map<String, Object> returnMap = new HashMap<>();
        //1.扣减库存
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",gid);
        map.put("buyCount",buyCount);
        int count = gooDao.subCount(map);
        if (count < 1){
            returnMap.put("code",0);
            return returnMap;
        }
        //2.生成订单
        //2-1查询购物车记录
        Car car = new Car();
        car.setId(id);
        map.put("car",car);
        Car car1 = carDao.queryIC(map).get(0);
        //2-2生成订单号
        User u = useDao.queryI(new User(car1.getUId())).get(0);
        AopUtil aopUtil = new AopUtil();
        String orderNo = aopUtil.getOrderNo(u);
        //2-3.插入订单记录
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUId(car1.getUId());
        Goods goods = gooDao.queryRI(map).get(0);
        BigDecimal orderMoney = goods.getgPrice().multiply(new BigDecimal(buyCount));
        order.setOrderMoney(orderMoney);
        order.setOrderStatus(1);
        ordDao.addo(order);
        //2-4.在订单详情中插入记录
        Detail detail = new Detail();
        detail.setOrderNo(orderNo);
        detail.setGId(gid);
        detail.setBuyCount(buyCount);
        ordDao.addD(detail);
        //3.删除购物车记录
        carDao.deleteI(car);
        returnMap.put("code",1);
        returnMap.put("orderNo",orderNo);
        return returnMap;
    }

    @Override
    public int deleteCar(Integer id) {
        Car car = new Car();
        car.setId(id);
        int i = carDao.deleteI(car);
        return i;
    }

    //扣减库存，生成订单
    @Override
    @Transactional
    public Map<String, Object> createOrd(Integer uid, Integer gid, Integer buyCount) {
        Map<String, Object> returnMap = new HashMap<>();
        //1.扣减库存
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",gid);
        map.put("buyCount",buyCount);
        int count = gooDao.subCount(map);
        if (count < 1){
            returnMap.put("code",0);
            returnMap.put("msg","库存不足，订单创建失败");
            return returnMap;
        }
        //2.生成订单
        //2-1生成订单号
        User u = useDao.queryI(new User(uid)).get(0);
        AopUtil aopUtil = new AopUtil();
        String orderNo = aopUtil.getOrderNo(u);
        //2-2.插入订单记录
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUId(uid);
        Goods goods = gooDao.queryRI(map).get(0);
        BigDecimal orderMoney = goods.getgPrice().multiply(new BigDecimal(buyCount));
        order.setOrderMoney(orderMoney);
        order.setOrderStatus(1);
        ordDao.addo(order);
        //2-3.在订单详情中插入记录
        Detail detail = new Detail();
        detail.setOrderNo(orderNo);
        detail.setGId(gid);
        detail.setBuyCount(buyCount);
        ordDao.addD(detail);
        returnMap.put("code",1);
        returnMap.put("msg","订单创建成功");
        returnMap.put("orderNo",orderNo);
        returnMap.put("goods",goods);
        returnMap.put("orderMoney",orderMoney);
        return returnMap;
    }

    //支付按钮
    @Override
    @Transactional//事务控制
    public Map<String, Object> ordIng(String orderNo,String uPass) {
        Map<String, Object> returnMap = new HashMap<>();
        //1.1查询订单具体信息
        Map<String, Object> map = new HashMap<>();
        Order order1 = new Order();
        order1.setOrderNo(orderNo);
        map.put("order",order1);
        Order order = ordDao.queryO(map).get(0);
        //1.2验证密码，根据用户ID和密码查询扣款
        User user = new User();
        user.setId(order.getuId());
        user.setUPass(uPass);
        int count = useDao.queryI(user).size();
        if (count < 1){
            returnMap.put("code",0);
            return returnMap;
        }
        //2.更新用户账户余额
        user.setUMoney(order.getOrderMoney());
        int upMoney = useDao.upMoney(user);
        if (upMoney < 1){
            returnMap.put("code",0);
            return returnMap;
        }
        //3.修改订单状态,订单状态:2-待发货（已付款)
        order.setOrderStatus(2);
        int updateO = ordDao.updateO(order);
        if (updateO < 1){
            returnMap.put("code",0);
            return returnMap;
        }
        //4.生成交易流水
        Trade trade = new Trade(order.getuId(),2,orderNo,order.getOrderMoney());
        int addI = traDao.addI(trade);
        if (addI < 1){
            returnMap.put("code",0);
        }else {
            returnMap.put("code",1);
        }
        return returnMap;
    }


}
