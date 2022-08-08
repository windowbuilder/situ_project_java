package dao.impl;

import dao.IUseDao;
import model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author feiyang
 * @create 2022-08-02 19:42
 * @Description:
 * @FileName: UseDao
 * @History:
 */
@Repository
public class UseDao implements IUseDao {
    @Autowired
    private SqlSession sqlSession;
    private final String URL ="dao.IUseDao";

    @Override
    public List<User> queryI(User user) {
        return sqlSession.selectList(URL + ".queryLoginUser",user);
    }

    @Override
    public int addI(User user) {
        return sqlSession.insert(URL + ".addUser",user);
    }

    @Override
    public int updateI(User user) {
        return sqlSession.update(URL + ".updateUser",user);
    }

    @Override
    public int upMoney(User user) {
        return sqlSession.update(URL + ".upMoney",user);
    }
}
