package service;

import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author feiyang
 * @create 2022-08-05 14:00
 * @Description:
 * @FileName: IShoGooService
 * @History:
 */
public interface IShoGooService {
    public Map<String,Object> addCar(Integer uid,Integer gid,Integer buyCount);

    public Map<String,Object> buyDeleteCar(Integer id,Integer gid,Integer buyCount);

    public int deleteCar(Integer id);

    public Map<String,Object> createOrd(Integer uid,Integer gid,Integer buyCount);

    public Map<String, Object> ordIng(String orderNo,String uPass);
}
