package dao.impl;

import dao.ITraDao;
import model.Trade;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author feiyang
 * @create 2022-08-06 10:37
 * @Description:
 * @FileName: TraDao
 * @History:
 */
@Repository
public class TraDao implements ITraDao {
    @Autowired
    private SqlSession sqlSession;
    private final String URL ="dao.ITraDao";

    @Override
    public int addI(Trade trade) {
        return sqlSession.insert(URL + ".addI",trade);
    }
}
