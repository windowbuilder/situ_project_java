package dao;/**
 * @author feiyang
 * @create 2022-07-22 19:50
 * @Description:
 * @FileName: IGooDao
 * @History:
 */

import model.Goods;
import model.GoodsSort;

import java.util.List;
import java.util.Map;

/**
 * @author: feiyang
 * @create 2022-07-22 19:50
 * @Description:
 * @FileName: IGooDao
 * @History:
 * @自定义内容：
 */
public interface IGooDao {
    public List<Goods> queryG(Map<String,Object> map);

    public int queryCountG(Goods goods);

    public List<Goods> queryRI(Map<String,Object> map);

    public int queryRC(Map<String,Object> map);

    public List<GoodsSort> queryClassG(GoodsSort goodsSort);

    public int addG(Goods goods);

    public int addp(GoodsSort goodsSort);

    public int updateG(Goods goods);

    public int subCount(Map<String,Object> map);

    public int upsn(GoodsSort goodsSort);

    public int delg(Integer id);
}
