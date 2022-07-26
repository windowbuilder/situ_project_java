package dao.impl;

import dao.IManDao;
import model.Manager;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-21 17:15
 * @Description:
 * @FileName: ManDao
 * @History:
 */
@Repository
public class ManDao implements IManDao {
    @Autowired
    private SqlSession sqlSession;
    private final String URL ="dao.IManDao";

    @Override
    public List<Manager> querylogin(Manager manager) {
        return sqlSession.selectList(URL+".querylogin",manager);
    }

    @Override
    public List<Manager> query(Map<String,Object> map) {
        return sqlSession.selectList(URL+".query",map);
    }

    @Override
    public int queryCount(Manager manager) {
        return sqlSession.selectList(URL+".queryCount",manager).size();
    }

    @Override
    public int add(Manager manager) {
        return sqlSession.insert(URL+".add",manager);
    }

    @Override
    public int update(Manager manager) {
        return sqlSession.update(URL+".update",manager);
    }
}
