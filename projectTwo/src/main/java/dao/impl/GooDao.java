package dao.impl;

import dao.IGooDao;
import dao.IManDao;
import model.Goods;
import model.GoodsSort;
import model.Manager;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author feiyang
 * @create 2022-07-22 20:51
 * @Description:
 * @FileName: GooDao
 * @History:
 */
@Repository
public class GooDao implements IGooDao {
    @Autowired
    private SqlSession sqlSession;
    private final String URL ="dao.IGooDao";

    @Override
    public List<Goods> queryG(Map<String, Object> map) {
        return sqlSession.selectList(URL + ".queryG",map);
    }

    @Override
    public int queryCountG(Goods goods) {
        return sqlSession.selectList(URL +".queryCountG",goods).size();
    }

    @Override
    public List<GoodsSort> queryClassG(GoodsSort goodsSort) {
        return sqlSession.selectList(URL + ".queryClassG",goodsSort);
    }

    @Override
    public int addG(Goods goods) {
        return sqlSession.insert(URL + ".addG",goods);
    }

    @Override
    public int addp(GoodsSort goodsSort) {
        return sqlSession.insert(URL + ".addp",goodsSort);
    }

    @Override
    public int updateG(Goods goods) {
        return sqlSession.update(URL + ".updateG",goods);
    }

    @Override
    public int upsn(GoodsSort goodsSort) {
        return sqlSession.update(URL + ".upsn",goodsSort);
    }

    @Override
    public int delg(Integer id) {
        return sqlSession.delete(URL + ".delg",id);
    }
}
