package service.impl;

import dao.ICarDao;
import dao.IOrdDao;
import model.Car;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ICarService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-08-04 15:18
 * @Description:
 * @FileName: CarService
 * @History:
 */
@Service
public class CarService implements ICarService {
    @Autowired
    private ICarDao carDao;

    @Override
    public Map<String, Object> queryIC(Integer uid,Integer pageNo) {
        if (pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        int rowCount = 4;
        Car car = new Car();
        car.setUId(uid);
        car.setCStatus(0);
        int totalCount = carDao.queryCC(car);
        int totalPage = totalCount / rowCount;
        if (totalCount % rowCount != 0 || totalPage < 1){
            totalPage ++;
        }
        if (pageNo > totalPage){
            pageNo = totalPage;
        }
        int limitFirst = (pageNo - 1) * rowCount;
        Map<String, Object> map = new HashMap<>();
        map.put("car",car);
        map.put("limitFirst",limitFirst);
        map.put("rowCount",rowCount);
        List<Car> list = carDao.queryIC(map);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("list",list);
        resMap.put("pageNo",pageNo);
        resMap.put("totalPage",totalPage);
        return resMap;
    }

    @Override
    public int addIC(Car car) {
        int addIC = carDao.addIC(car);
        return addIC;
    }

    @Override
    public int updateIC(Car car) {
        int updateIC = carDao.updateIC(car);
        return updateIC;
    }
}
