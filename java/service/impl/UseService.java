package service.impl;

import dao.IOrdDao;
import dao.ITraDao;
import dao.IUseDao;
import dao.impl.UseDao;
import model.Trade;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IUseService;
import util.AopUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-08-02 19:48
 * @Description:
 * @FileName: UseService
 * @History:
 */
@Service
public class UseService implements IUseService {
    @Autowired
    private IUseDao useDao;
    @Autowired
    private ITraDao traDao;

    @Override
    public User queryI(User user) {
        String regex = "^[^0-9].*";
        String regex1 = "\\d{11}";
        String name = user.getUName();
        if (name.matches(regex)){
            return useDao.queryI(user).get(0);
        }else if (name.matches(regex1)){
            user.setUName(null);
            user.setUPhone(name);
            return useDao.queryI(user).get(0);
        }else {
            return null;
        }
    }

    @Override
    public int regist(User user) {
        User user1 = new User();
        user1.setUName(user.getUName());
        List<User> list = useDao.queryI(user1);
        if (list.size() != 0){
            return 1;
        }
        user1.setUName(null);
        user1.setUPass(user.getUPass());
        List<User> list1 = useDao.queryI(user1);
        if (list1.size() != 0){
            return 2;
        }
        user.setUMoney(new BigDecimal(0));
        int i = useDao.addI(user);
        return 0;
    }

    @Override
    public int addI(User user) {
        return 0;
    }

    @Override
    public int updateI(User user) {
        return useDao.updateI(user);
    }

    //充值
    @Override
    @Transactional//事务控制
    public Map<String, Object> updateM(Integer uMoney,User m) {
        Map<String, Object> map = new HashMap<>();
        //充值
        Integer um = uMoney * -1;
        User user = new User();
        user.setUMoney(new BigDecimal(um));
        user.setId(m.getId());
        int upMoney = useDao.upMoney(user);
        if (upMoney < 1){
            map.put("code",0);
            return map;
        }
        //生成订单号
        AopUtil aopUtil = new AopUtil();
        String orderNo = aopUtil.getOrderNo(m);
        //生成交易流水
        Trade trade = new Trade(m.getId(),1,orderNo,new BigDecimal(uMoney));
        int addI = traDao.addI(trade);
        if (addI < 1){
            map.put("code",0);
        }else {
            map.put("code",1);
        }
        return map;
    }
}
