package dao.impl;

import dao.ICarDao;
import model.Car;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-08-04 15:09
 * @Description:
 * @FileName: CarDao
 * @History:
 */
@Repository
public class CarDao implements ICarDao {
    @Autowired
    private SqlSession sqlSession;
    private final String URL ="dao.ICarDao";

    @Override
    public int queryCC(Car car) {
        return sqlSession.selectList(URL+".queryCC",car).size();
    }

    @Override
    public List<Car> queryIC(Map<String, Object> map) {
        return sqlSession.selectList(URL+".queryIC",map);
    }

    @Override
    public int addIC(Car car) {
        return sqlSession.insert(URL + ".addIC",car);
    }

    @Override
    public int updateIC(Car car) {
        return sqlSession.update(URL + ".updateIC",car);
    }
}
