package dao.impl;

import dao.IOrdDao;
import model.Detail;
import model.Order;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-25 19:33
 * @Description:
 * @FileName: OrdDao
 * @History:
 */
@Repository
public class OrdDao implements IOrdDao {
    @Autowired
    private SqlSession sqlSession;
    private final String URL ="dao.IOrdDao";

    @Override
    public List<Detail> queryCD(Detail detail) {
        return sqlSession.selectList(URL + ".queryCD",detail);
    }

    @Override
    public List<Order> queryO(Map<String, Object> map) {
        return sqlSession.selectList(URL + ".queryO",map);
    }

    @Override
    public int queryCO(Order order) {
        return sqlSession.selectList(URL + ".queryCO",order).size();
    }

    @Override
    public int addD(Detail detail) {
        return sqlSession.insert(URL + ".addD",detail);
    }

    @Override
    public int addo(Order order) {
        return sqlSession.insert(URL + ".addo",order);
    }

    @Override
    public int updateO(Order order) {
        return sqlSession.update(URL + ".updateO",order);
    }

    @Override
    public int jobForOrder(Order order) {
        return sqlSession.update(URL + ".jobForOrder",order);
    }
}
